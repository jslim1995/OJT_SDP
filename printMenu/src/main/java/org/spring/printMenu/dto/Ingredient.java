package org.spring.printMenu.dto;

public class Ingredient implements iMenuInfo {
	private String name;
	private String divName;
	
	public Ingredient(String divName) {
		this.name = "";
		this.divName = divName;
	}
	
	public Ingredient(String name, String divName) {
		this.name = name;
		this.divName = divName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	@Override
	public String toString() {
		return name;
	}
}
