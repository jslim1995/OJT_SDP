package org.spring.printMenu.control;

import java.util.List;

import org.spring.printMenu.dto.Paging;
import org.spring.printMenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MenuController {
	@Autowired
	private MenuService service;
	
	@RequestMapping(value= {"/", "/check"})
	public String goCheck(Model model) {
		List<String> restList = service.getCondition().getRestNames();
		List<String> divList = service.getCondition().getDivNames();
		List<String> divIdList = service.getCondition().getDivIds();
		model.addAttribute("restList", restList);
		model.addAttribute("divList", divList);
		model.addAttribute("divIdList", divIdList);
		return "check";
	}
	
	@RequestMapping(value="/printPreview", method=RequestMethod.POST)
	public String goPrint(String restName, String divName, String startDate, String endDate, Model model) throws Exception {
		restName = new String(restName.getBytes("8859_1"), "euc-kr");
		Paging pg = service.getSelectedData(restName, divName, startDate, endDate);
		model.addAttribute("pg", pg);
		return "printPreview2";
	}
	
	@RequestMapping("getSunday")
	@ResponseBody
	public String getSunday(String date) {
		return service.getSunday(date);
	}
}
