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
	private List<FoodDataOfDay> foodDodList;	// ��ǻ� ���⼭ ����¡��... �մϴ�....
	
	
	public Paging(TheMenu theMenu) {
		this.theMenu = theMenu;
		restName = theMenu.getRestName();
		startDate = theMenu.getStartDate();
		endDate = theMenu.getEndDate();
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
			int idxFoodDodList = -1;
			// ���� ������ŭ �ݺ�
			for(DayOfDiv temp : divs.get(0).getDays()) {
				//���۳�¥�� �ε�����ȣ
				idxFoodDodList++;
				
				List<Food> foodList = divTemp.getDays().get(idxFoodDodList).getFoods();
				// ���ĵ� �߰�
				for(Food foodTemp : foodList) {
					foodDodList.get(idxFoodDodList).getData().add(foodTemp);
					foodDodList.get(idxFoodDodList).getDataSizes()[idxDiv] += 1;
					// ���� �߰�
					for(Ingredient ingTemp : foodTemp.getIngredientList()) {
						foodDodList.get(idxFoodDodList).getData().add(ingTemp);
						foodDodList.get(idxFoodDodList).getDataSizes()[idxDiv] += 1;
					}
				}
			}
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
//				System.out.println("�ʱ������ : " + temp.getDataSizes()[idxDiv]);
				while(temp.getDataSizes()[idxDiv] < max) {
					temp.getData().add(new Ingredient(divTemp.getDivName()));
					temp.getDataSizes()[idxDiv] += 1;
				}
//				System.out.println("������ ������: " + temp.getDataSizes()[idxDiv]);
			}
			idxDiv++;
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

	public List<FoodDataOfDay> getFoodDodList() {
		return foodDodList;
	}

	public void setFoodDodList(List<FoodDataOfDay> foodDodList) {
		this.foodDodList = foodDodList;
	}
	
}