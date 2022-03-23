package org.spring.printMenu.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TheMenu {
	private String restName;
	private String startDate;
	private String endDate;
	private String monDate;
	private String sunDate;
	private int totalDate;
	private List<Div> divs;
	
	public TheMenu() {
		
	}
	
	public TheMenu(List<SelectedData> sdList, String restName, String startDate, String endDate) {
		this.restName = restName;
		this.startDate = startDate;
		this.endDate = endDate;
		divs = new ArrayList<Div>();
		init(sdList);
	}
	
	private void init(List<SelectedData> sdList) {
		divs.add(new Div("조식"));
		divs.add(new Div("중식"));
		divs.add(new Div("석식"));
		divs.add(new Div("간식"));
		Calendar cd = Calendar.getInstance();
		try {
			int year = Integer.parseInt(startDate.substring(0, 4));
			int month = Integer.parseInt(startDate.substring(5, 7))-1;
			int day = Integer.parseInt(startDate.substring(8));
			cd.set(year, month, day);
			if(cd.get(Calendar.DAY_OF_WEEK) > 1) {
				cd.set(year, month, day+2-cd.get(Calendar.DAY_OF_WEEK));
			} else {
				cd.set(year, month, day-5-cd.get(Calendar.DAY_OF_WEEK));
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			monDate = sdf.format(cd.getTime());
//			monDate = cd.get(Calendar.YEAR) + "-" + cd.get(Calendar.MONTH) + "-" + cd.getFirstDayOfWeek();
			cd.set(year, month, cd.get(Calendar.DAY_OF_MONTH)+6);
			sunDate = sdf.format(cd.getTime());
		} catch (Exception e) {
			System.out.println("check");
		}
		
		String date = monDate;
		for(int idxDay = 0; idxDay<7; idxDay++) {
			addDayOfDiv(date);
			for(SelectedData temp : sdList) {
				if(date.equals(temp.getDate())) {
					try {
						int idxDiv = Integer.parseInt(temp.getDiv().substring(1))-1;
						divs.get(idxDiv).getDays().get(idxDay).getFoods().add(new Food(temp.getFoodName(), temp.getDivName(), temp.getIngredientList()));
					} catch (NumberFormatException e) {}
				}
			}
			date = nextDate(date);
		}
	}
	private void addDayOfDiv(String date) {
		for(Div temp : divs) {
			temp.getDays().add(new DayOfDiv(date));
		}
	}
	private String nextDate(String currentDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nextDate ="";
		try {
			nextDate = sdf.format(new Date(sdf.parse(currentDate).getTime()+(24*60*60*1000)));
		} catch (Exception e) {}
		return nextDate;
	}
	
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<Div> getDivs() {
		return divs;
	}
	public void setDivs(List<Div> divs) {
		this.divs = divs;
	}
	
}
