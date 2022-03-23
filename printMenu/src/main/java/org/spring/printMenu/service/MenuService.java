package org.spring.printMenu.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.spring.printMenu.dao.SpringJdbcMenuDao;
import org.spring.printMenu.dto.Condition;
import org.spring.printMenu.dto.Paging;
import org.spring.printMenu.dto.SelectedData;
import org.spring.printMenu.dto.TheMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
	@Autowired
	private SpringJdbcMenuDao dao;
	
	public Condition getCondition() {
		return dao.getCondition();
	}
	
	public Paging getSelectedData(String restName, String divName, String startDate, String endDate) {
		List<SelectedData> list = dao.getSelectedData(restName, divName, startDate, endDate);
		TheMenu theMenu = new TheMenu(list, restName, startDate, endDate);
		Paging pg = new Paging(theMenu);
		return pg;
	}
	
	public String getSunday(String date) {
		String sun = "";
		Calendar cd = Calendar.getInstance();
		try {
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7))-1;
			int day = Integer.parseInt(date.substring(8));
			cd.set(year, month, day);
			if(cd.get(Calendar.DAY_OF_WEEK) > 1) {
				cd.set(year, month, day+(8-cd.get(Calendar.DAY_OF_WEEK)));
			} else {
				cd.set(year, month, day);
			}
//			sun = cd.get(Calendar.YEAR) + "-" + (cd.get(Calendar.MONTH)+1) + "-" + cd.get(Calendar.DAY_OF_MONTH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sun = sdf.format(cd.getTime());
		} catch (Exception e) {
			System.out.println("check");
		}
		return sun;
	}
}
