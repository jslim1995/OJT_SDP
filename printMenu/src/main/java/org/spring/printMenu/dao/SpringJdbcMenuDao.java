package org.spring.printMenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.spring.printMenu.dto.Condition;
import org.spring.printMenu.dto.SelectedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SpringJdbcMenuDao {
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	@Value("#{sql['menu.restCondition']}")
	private String restCondition;
	
	@Value("#{sql['menu.divCondition']}")
	private String divCondition;

	@Value("#{sql['menu.checkData']}")
	private String checkData;
	
	@Value("#{sql['menu.checkDataDivAll']}")
	private String checkDataDivAll;

	public Condition getCondition() {
		Condition condition = new Condition();
		condition.setRestNames(jdbcTmp.query(restCondition, new ConditionMapper()));
		condition.setDivNames(jdbcTmp.query(divCondition, new ConditionMapper()));		
		condition.setDivIds(jdbcTmp.query(divCondition, new Condition2Mapper()));
		return condition;
	}
	
	private class ConditionMapper implements RowMapper<String> {
		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			String name = rs.getString("CODE_NM");
			return name;
		}
	}
	private class Condition2Mapper implements RowMapper<String> {
		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			String mealId = rs.getString("CODE_ID");
			return mealId;
		}
	}
	
	public List<SelectedData> getSelectedData(String restName, String divName, String startDate, String endDate) {
		List<SelectedData> sdList = null;
		if(divName.equals("M00")) {
			sdList = jdbcTmp.query(checkDataDivAll, new SelectedDataMapper(), startDate, endDate, restName);			
		} else {
			sdList = jdbcTmp.query(checkData, new SelectedDataMapper(), startDate, endDate, restName, divName);			
		}
		return sdList;
	}
	
	private class SelectedDataMapper implements RowMapper<SelectedData> {
		@Override
		public SelectedData mapRow(ResultSet rs, int rowNum) throws SQLException {
			String date = rs.getString("date");
			String restName = rs.getString("restName");
			String div = rs.getString("div");
			String foodName = rs.getString("foodName");
			String ingredientName = rs.getString("ingredientName");
			return new SelectedData(date, restName, div, foodName, ingredientName);
		}
	}
}
