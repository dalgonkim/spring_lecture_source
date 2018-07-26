package mvjsp.chap13.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mvjsp.chap13.exception.ProductNotFoundException;
import mvjsp.chap13.exception.ServiceException;
import mvjsp.chap13.model.Product;
import mvjsp.chap13.model.ProductCommand;
import mvjsp.chap13.model.ProductListView;
import mvjsp.chap13.service.MemberService;
import mvjsp.chap13.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productSvc;
	@Autowired
	MemberService memberSvc;

	public void setProductService(ProductService productSvc) {
		this.productSvc = productSvc;
	}

	@RequestMapping(value = "productList")
	public String productList(@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model) {

		String url = "productList";

		ProductListView viewData = null;

		try {
			viewData = productSvc.getProductList(pageNumber);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		model.addAttribute("viewData", viewData);
		model.addAttribute("pageNumber", pageNumber);

		return url;
	}

	@RequestMapping(value = "/writeProduct", method = RequestMethod.GET)
	public String writeProductForm() {
		String url = "writeProductForm";
		return url;
	}

	@RequestMapping(value = "/writeProduct", method = RequestMethod.POST)
	public String writeProduct(ProductCommand productCommand, HttpServletRequest request) {
		String url = "redirect:productList";

		String upload = request.getSession().getServletContext().getRealPath("resources/product-images");

		MultipartFile image = productCommand.getImage();
		Product product = new Product(productCommand);
		String fileName = image.getOriginalFilename() + "$$" + System.currentTimeMillis();
		if (!image.isEmpty()) {
			File file = new File(upload, fileName);
			try {
				image.transferTo(file);
				product.setImage(fileName);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (product.getImage() == null || product.getImage().isEmpty())
			product.setImage("no-image.jpg");
		try {
			productSvc.write(product);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return url;
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
	public String updateProductForm(@RequestParam(value = "proNum") int proNum, Model model) {
		String url = "updateProductForm";
		try {
			Product product = productSvc.getProduct(proNum);
			model.addAttribute(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return url;
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public String updateProduct(ProductCommand productCommand,
								@RequestParam("nonmakeImg") String nonmakeImg,
								HttpServletRequest request) {
		String url = "redirect:productList";

		String upload = request.getSession().getServletContext().getRealPath("resources/product-images");

		MultipartFile image = productCommand.getImage();
		Product product = new Product(productCommand);
		String fileName = image.getOriginalFilename() + "$$" + System.currentTimeMillis();

		if (!image.isEmpty()) {
			File file = new File(upload, fileName);
			try {
				image.transferTo(file);
				product.setImage(fileName);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			product.setImage(nonmakeImg);
		}

		try {
			productSvc.updateProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	@RequestMapping(value = "deleteProduct", method = RequestMethod.GET)
	public String deleteProductConfirm(@RequestParam(value = "proNum") int proNum, Model model) {
		String url = "confirmProductDeletion";

		model.addAttribute("proNum", proNum);

		return url;
	}

	@RequestMapping(value = "deleteProduct", method = RequestMethod.POST)
	public String deleteProduct(@RequestParam(value = "proNum") int proNum,
			@RequestParam(value = "password") String password, Model model) {

		String url = "deleteProduct";

		boolean invalidPassowrd = false;

		if (!password.equals(memberSvc.getMember("admin").getPwd())) {
			invalidPassowrd = true;
		} else {
			try {
				productSvc.deleteProduct(proNum);
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (ProductNotFoundException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("invalidPassowrd", invalidPassowrd);
		return url;
	}

}
