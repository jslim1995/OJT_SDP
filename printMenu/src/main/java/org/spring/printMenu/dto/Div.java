package org.spring.printMenu.dto;

import java.util.ArrayList;
import java.util.List;

public class Div {
	private String divName;
	private List<DayOfDiv> Days;
	
	public Div(String divName) {
		this.divName = divName;
		Days = new ArrayList<DayOfDiv>();
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public List<DayOfDiv> getDays() {
		return Days;
	}

	public void setDays(List<DayOfDiv> days) {
		Days = days;
	}

	@Override
	public String toString() {
		return "Div [divName=" + divName + ", Days=" + Days + "]";
	}
}
