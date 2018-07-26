package mvjsp.chap13.model;

import java.util.List;

public class MemberListView {
	private int memberTotalCount;
	private int currentPageNumber;
	private List<Member> memberList;
	private int pageTotalCount;
	private int memberCountPerPage;
	private int firstRow;
	private int endRow;

	public MemberListView(List<Member> memberList, int memberTotalCount,
			int currentPageNumber, int memberCountPerPage, int startRow,
			int endRow) {
		this.memberList = memberList;
		this.memberTotalCount = memberTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.memberCountPerPage = memberCountPerPage;
		this.firstRow = startRow;
		this.endRow = endRow;

		calculatePageTotalCount();
	}

	private void calculatePageTotalCount() {
		if (memberTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = memberTotalCount / memberCountPerPage;
			if (memberTotalCount % memberCountPerPage > 0) {
				pageTotalCount++;
			}
		}
	}

	public int getMemberTotalCount() {
		return memberTotalCount;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public List<Member> getMemberList() {
		return memberList;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public int getMemberCountPerPage() {
		return memberCountPerPage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getEndRow() {
		return endRow;
	}
	
	public boolean isEmpty() {
		return memberTotalCount == 0;
	}
}
