package org.spring.printMenu.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SelectedData {
	private String date;
	private String restName;
	private String div;
	private String divName;
	private String foodName;
	private List<String> ingredientList;
	
	public SelectedData() {
		super();
	}
	public SelectedData(String date, String restName, String div, String foodName, String ingredientName) {
		super();
		this.date = date;
		this.restName = restName;
		this.div = div;
		if(div.equals("M01")) {
			divName = "조식";
		} else if(div.equals("M02")) {
			divName = "중식";
		} else if(div.equals("M03")) {
			divName = "석식";
		} else if(div.equals("M04")) {
			divName = "간식";
		}
		this.foodName = foodName;
		ingredientList = strToList(ingredientName);
	}
	public SelectedData(String date) {
		super();
		this.date = date;
	}
	
	private List<String> strToList(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str, ",");
		while(st.hasMoreTokens()) {
			list.add(st.nextToken().trim());
		}
		return list;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
	}
	
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public List<String> getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(List<String> ingredientList) {
		this.ingredientList = ingredientList;
	}
	@Override
	public String toString() {
		return "SelectedData [date=" + date + ", restName=" + restName + ", div=" + div + ", foodName=" + foodName
				+ ", ingredientList=" + ingredientList + "]";
	}
	
	
}
