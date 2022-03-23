package org.spring.printMenu.dto;

import java.util.ArrayList;
import java.util.List;

public class Food implements iMenuInfo {
	private String name;
	private String divName;
	private List<Ingredient> ingredientList;
	
	public Food(String foodName, String divName, List<String> ingredientList) {
		this.name = foodName;
		this.divName = divName;
		this.ingredientList = new ArrayList<Ingredient>();
		for(String temp : ingredientList) {
			this.ingredientList.add(new Ingredient(temp, divName));
		}
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

	public List<Ingredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
