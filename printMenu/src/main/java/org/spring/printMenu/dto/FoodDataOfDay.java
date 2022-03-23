package org.spring.printMenu.dto;

import java.util.ArrayList;
import java.util.List;

public class FoodDataOfDay {
	private String day;
	private String date;
	private List<iMenuInfo> data;
	private int[] dataSizes;
	private int perPage = 20;
	
	public FoodDataOfDay(String day, String date) {
		this.day = day;
		this.date = date.substring(5);
		data = new ArrayList<iMenuInfo>();
		dataSizes = new int[4];
	}
	
	public List<iMenuInfo> getList(int pageNum) {
		int from = (pageNum -1)*perPage;
		int to = from + perPage;
		
		if(to > data.size()) {
			to = data.size();
		}
		// ¸®¹ö½º
//		Collections.reverse(foodDodList);
		return data.subList(from, to);
	}
	
	public int getTotalPageCount() {
		int size = data.size();
		int count = size / perPage;
		if(size % perPage != 0) {
			count++;			
		}
		return count;
	}
	public int getCntDiv(List<iMenuInfo> data, String divName) {
		int cnt = 0;
		for(iMenuInfo temp : data) {
			if(temp.getDivName().equals(divName)) {
				cnt++;
			}
		}
		return cnt;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<iMenuInfo> getData() {
		return data;
	}

	public void setData(List<iMenuInfo> data) {
		this.data = data;
	}

	public int[] getDataSizes() {
		return dataSizes;
	}

	public void setDataSizes(int[] dataSizes) {
		this.dataSizes = dataSizes;
	}

	@Override
	public String toString() {
		return "FoodDataOfDay [day=" + day + ", data=" + data + "]";
	}
	
}
