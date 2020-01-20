package com.jmr.testsuite.obdx.page;

import org.springframework.stereotype.Component;

import io.github.millij.poi.ss.model.annotations.Sheet;
import io.github.millij.poi.ss.model.annotations.SheetColumn;

@Component
@Sheet(value = "View Statement")
public class ViewStatement_Page {

	private String testCaseResult;

	@SheetColumn(value =  "TestCase Id")
	private String testCaseId;

	@SheetColumn(value = "RunMode")
	private String runMode;
	
	@SheetColumn(value = "ToggleMenu")
	private String toggleMenu;

	@SheetColumn(value = "TransferType")
	private String transfertype;

	@SheetColumn(value = "Payee")
	private String payee;

	@SheetColumn(value = "Transferfrom")
	private String transferfrom;

	@SheetColumn(value = "AccountNumber")
	private String accountNumber;

	@SheetColumn(value = "Period")
	private String period;

	@SheetColumn(value = "TransactionType")
	private String transactionType;

	@SheetColumn(value = "StartDate")
	private String startDate;

	@SheetColumn(value = "EndDate")
	private String endDate;

	@SheetColumn(value = "FileFormat")
	private String fileFormat;

	public String getTestCaseResult() {
		return testCaseResult;
	}

	public void setTestCaseResult(String testCaseResult) {
		this.testCaseResult = testCaseResult;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getRunMode() {
		return runMode;
	}

	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}

	public String getToggleMenu() {
		return toggleMenu;
	}

	public void setToggleMenu(String toggleMenu) {
		this.toggleMenu = toggleMenu;
	}

	public String getTransfertype() {
		return transfertype;
	}

	public void setTransfertype(String transfertype) {
		this.transfertype = transfertype;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getTransferfrom() {
		return transferfrom;
	}

	public void setTransferfrom(String transferfrom) {
		this.transferfrom = transferfrom;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
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

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	@Override
	public String toString() {
		return "ViewStatement_Page [testCaseResult=" + testCaseResult + ", testCaseId=" + testCaseId + ", runMode="
				+ runMode + ", toggleMenu=" + toggleMenu + ", transfertype=" + transfertype + ", payee=" + payee
				+ ", transferfrom=" + transferfrom + ", accountNumber=" + accountNumber + ", period=" + period
				+ ", transactionType=" + transactionType + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", fileFormat=" + fileFormat + "]";
	}

	
}
