package org.spring.printMenu.dto;

import java.util.ArrayList;
import java.util.List;

public class Condition {
	private List<String> restNames;
	private List<String> divNames;
	private List<String> divIds;
	
	public Condition() {
		restNames = new ArrayList<String>();
		divNames = new ArrayList<String>();
		divIds = new ArrayList<String>();
	}

	public List<String> getRestNames() {
		return restNames;
	}

	public void setRestNames(List<String> restNames) {
		this.restNames = restNames;
	}

	public List<String> getDivNames() {
		return divNames;
	}

	public void setDivNames(List<String> divNames) {
		this.divNames = divNames;
	}
	
	public List<String> getDivIds() {
		return divIds;
	}

	public void setDivIds(List<String> divIds) {
		this.divIds = divIds;
	}

	@Override
	public String toString() {
		return "Condition [restNames=" + restNames + ", divNames=" + divNames + ", divIds=" + divIds + "]";
	}


	
}
