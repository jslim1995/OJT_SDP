package org.spring.printMenu.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DayOfDiv {
	private int dayNum;
	private String day;
	private String date;
	private List<Food> foods;
	
	public DayOfDiv(String date) {
		this.date = date;
		dayNum = dateToDayNum(date);
		day = dayNumToDayStr(dayNum);
		foods = new ArrayList<Food>();
	}
	
	public int getDayNum() {
		return dayNum;
	}

	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	private int dateToDayNum(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cd = Calendar.getInstance();
		try {
			Date temp = sdf.parse(date);
			cd.setTime(temp);
		} catch (ParseException e) {}
		System.out.println(cd.get(Calendar.DAY_OF_WEEK));
		return cd.get(Calendar.DAY_OF_WEEK);
	}
	private String dayNumToDayStr(int dayNum) {
		String day = "";
		switch(dayNum){
	        case 1:
	            day = "일";
	            break ;
	        case 2:
	            day = "월";
	            break ;
	        case 3:
	            day = "화";
	            break ;
	        case 4:
	            day = "수";
	            break ;
	        case 5:
	            day = "목";
	            break ;
	        case 6:
	            day = "금";
	            break ;
	        case 7:
	            day = "토";
	            break ;  
	    }
		return day;
	}



	@Override
	public String toString() {
		return "DayOfDiv [day=" + day + ", foods=" + foods + "]";
	}
	
	
}
