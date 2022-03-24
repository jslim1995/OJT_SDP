package org.spring.printMenu.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Paging {
	private TheMenu theMenu;
	private String restName;
	private String nowDate;
	private String startDate;
	private String endDate;
	private int totalDate;
	private List<FoodDataOfDay> foodDodList;	// 사실상 여기서 페이징을... 합니다....
	
	
	public Paging(TheMenu theMenu) {
		this.theMenu = theMenu;
		restName = theMenu.getRestName();
		startDate = theMenu.getStartDate();
		endDate = theMenu.getEndDate();
		totalDate = theMenu.getTotalDate();
		foodDodList = new ArrayList<FoodDataOfDay>();
		init();
	}
	
	private void init() {
		Calendar cd = Calendar.getInstance();
		nowDate = cd.get(Calendar.YEAR) + "." + (cd.get(Calendar.MONTH)+1) + "." + cd.get(Calendar.DAY_OF_MONTH);
		List<Div> divs = theMenu.getDivs();
		for(DayOfDiv temp : divs.get(0).getDays()) {
			foodDodList.add(new FoodDataOfDay(temp.getDay(), temp.getDate()));
		}
		int idxDiv = 0;
		for(Div divTemp : divs) {
			addFoodData(divs, divTemp, idxDiv);
			correctFoodDataSize(divTemp, idxDiv);
			idxDiv++;
		}
	}
	
	private void addFoodData(List<Div> divs, Div divTemp, int idxDiv) {
		int idxFoodDodList = -1;
		// 요일 갯수만큼 반복
		for(DayOfDiv temp : divs.get(0).getDays()) {
			//시작날짜의 인덱스번호
			idxFoodDodList++;
			
			List<Food> foodList = divTemp.getDays().get(idxFoodDodList).getFoods();
			// 음식들 추가
			for(Food foodTemp : foodList) {
				foodDodList.get(idxFoodDodList).getData().add(foodTemp);
				foodDodList.get(idxFoodDodList).getDataSizes()[idxDiv] += 1;
				// 재료들 추가
				for(Ingredient ingTemp : foodTemp.getIngredientList()) {
					foodDodList.get(idxFoodDodList).getData().add(ingTemp);
					foodDodList.get(idxFoodDodList).getDataSizes()[idxDiv] += 1;
				}
			}
		}
	}
	
	private void correctFoodDataSize(Div divTemp, int idxDiv) {
		int[] nums = new int[foodDodList.size()];
		int idx=0;
		for(FoodDataOfDay temp : foodDodList) {
			nums[idx++] = temp.getDataSizes()[idxDiv];
		}
		Arrays.sort(nums);
		int max = 0;
		if(nums.length-1 >= 0) {
			max = nums[nums.length-1];
		}
		for(FoodDataOfDay temp : foodDodList) {
//			System.out.println("초기사이즈 : " + temp.getDataSizes()[idxDiv]);
			while(temp.getDataSizes()[idxDiv] < max) {
				temp.getData().add(new Ingredient(divTemp.getDivName()));
				temp.getDataSizes()[idxDiv] += 1;
			}
//			System.out.println("보정된 사이즈: " + temp.getDataSizes()[idxDiv]);
		}
	}

	public TheMenu getTheMenu() {
		return theMenu;
	}

	public void setTheMenu(TheMenu theMenu) {
		this.theMenu = theMenu;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
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

	public int getTotalDate() {
		return totalDate;
	}

	public void setTotalDate(int totalDate) {
		this.totalDate = totalDate;
	}

	public List<FoodDataOfDay> getFoodDodList() {
		return foodDodList;
	}

	public void setFoodDodList(List<FoodDataOfDay> foodDodList) {
		this.foodDodList = foodDodList;
	}
	
}
