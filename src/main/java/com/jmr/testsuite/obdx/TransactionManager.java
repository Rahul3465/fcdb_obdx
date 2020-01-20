package com.jmr.testsuite.obdx;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

//@Component
public class TransactionManager {

	private final String updateTestProgCaseExecStatAsU = "update auto_test_program_case set execution_status='W' where test_case_id=?";
	private final String updateTestProgCaseExecResAsSuccss = "update auto_test_program_case set execution_status='P', execution_result='S' where test_case_id=?";
	private final String updateTestProgCaseExecResAsFailed = "update auto_test_program_case set execution_status='P', execution_result='F' where test_case_id=?";
	private final String purgeSuccessTestCaseProgramToHistory = "insert into auto_test_program_case_hist  select * from auto_test_program_case where test_case_id =?";
	private final String deleteSuccessTestCaseProgram = "delete from auto_test_program_case where test_case_id=?";

	@Autowired
	private DriverManagerDataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void updateTestProgCaseExecStatAsWIP(String testCaseId) throws SQLException {
		jdbcTemplate.update(updateTestProgCaseExecStatAsU, testCaseId);
	}

	public void updateTestProgCaseExecStatAsProceedAndResultAsFailed(String testCaseId) throws SQLException {
		jdbcTemplate.update(updateTestProgCaseExecResAsFailed, testCaseId);
	}

	public void updateTestProgCaseExecStatAsProceedAndResultAsSuccess(String testCaseId) throws SQLException {
		jdbcTemplate.update(updateTestProgCaseExecResAsSuccss, testCaseId);
		jdbcTemplate.update(purgeSuccessTestCaseProgramToHistory, testCaseId);
		jdbcTemplate.update(deleteSuccessTestCaseProgram, testCaseId);
	}

}
