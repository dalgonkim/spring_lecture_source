package com.spring.dto;

import java.io.Serializable;

public class PlayerVO implements Serializable{
	
	private String id;
	private String lastName;
	private String firstname;
	private String position;
	private int birthYear;
	private int debutYear;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public int getDebutYear() {
		return debutYear;
	}
	public void setDebutYear(int debutYear) {
		this.debutYear = debutYear;
	}
	@Override
	public String toString() {
		return "PlayerVO [id=" + id + ", lastName=" + lastName + ", firstname=" + firstname + ", position=" + position
				+ ", birthYear=" + birthYear + ", debutYear=" + debutYear + "]";
	}
	
	
}








