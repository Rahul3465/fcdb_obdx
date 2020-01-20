package com.jmr.testsuite.obdx.page;

import org.springframework.stereotype.Component;

import io.github.millij.poi.ss.model.annotations.Sheet;
import io.github.millij.poi.ss.model.annotations.SheetColumn;

@Component
@Sheet(value = "Transfer Money")
public class TransferMoneyInternational_Page
{
	private String testCaseResult;
	
	@SheetColumn(value = "Test Case Id")
	private String testCaseId;
	
	@SheetColumn(value = "RunMode")
	private String runMode;
	
	@SheetColumn(value = "ToggleMenu")
	private String toggleMenu;
	
	@SheetColumn(value = "TransferType")
	private String transferType;

	@SheetColumn(value = "Payee")
	private String payee;
	
	@SheetColumn(value = "InternationalPayee")
	private String internationalPayee;
	
	@SheetColumn(value = "TransferFrom")
	private String transferFrom;
	
	@SheetColumn(value = "Currency")
	private String currency;
	
	@SheetColumn(value = "Amount")
	private String amount;
	
	@SheetColumn(value = "TransferWhen")
	private String transferWhen;
	
	@SheetColumn(value = "TransferDate")
	private String transferDate;
	
	@SheetColumn(value = "CorrespondenceCharges")
	private String correspondenceCharges;
	
	@SheetColumn(value = "TransferViaIntermediaryBank")
	private String transferViaIntermediaryBank;
	
	@SheetColumn(value = "Payvia")
	private String payvia;
	
	@SheetColumn(value = "SwiftCode_NCC")
	private String swiftCode_NCC;
	
	@SheetColumn(value = "BankName")
	private String bankName;
	
	@SheetColumn(value = "BankAddress")
	private String bankAddress;
	
	@SheetColumn(value = "Country")
	private String country;
	
	@SheetColumn(value = "City")
	private String city;
	
	@SheetColumn(value = "PaymentDetails")
	private String paymentDetails;
	
	@SheetColumn(value = "Note")
	private String note;
	
	@SheetColumn(value = "Transfer_Cancel")
	private String transfer_Cancel;
	
	@SheetColumn(value = "Confirm_Back_Cancel")
	private String confirm_Back_Cancel;

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

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getInternationalPayee() {
		return internationalPayee;
	}

	public void setInternationalPayee(String internationalPayee) {
		this.internationalPayee = internationalPayee;
	}

	public String getTransferFrom() {
		return transferFrom;
	}

	public void setTransferFrom(String transferFrom) {
		this.transferFrom = transferFrom;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransferWhen() {
		return transferWhen;
	}

	public void setTransferWhen(String transferWhen) {
		this.transferWhen = transferWhen;
	}

	public String getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}

	public String getCorrespondenceCharges() {
		return correspondenceCharges;
	}

	public void setCorrespondenceCharges(String correspondenceCharges) {
		this.correspondenceCharges = correspondenceCharges;
	}

	public String getTransferViaIntermediaryBank() {
		return transferViaIntermediaryBank;
	}

	public void setTransferViaIntermediaryBank(String transferViaIntermediaryBank) {
		this.transferViaIntermediaryBank = transferViaIntermediaryBank;
	}

	public String getPayvia() {
		return payvia;
	}

	public void setPayvia(String payvia) {
		this.payvia = payvia;
	}

	public String getSwiftCode_NCC() {
		return swiftCode_NCC;
	}

	public void setSwiftCode_NCC(String swiftCode_NCC) {
		this.swiftCode_NCC = swiftCode_NCC;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTransfer_Cancel() {
		return transfer_Cancel;
	}

	public void setTransfer_Cancel(String transfer_Cancel) {
		this.transfer_Cancel = transfer_Cancel;
	}

	public String getConfirm_Back_Cancel() {
		return confirm_Back_Cancel;
	}

	public void setConfirm_Back_Cancel(String confirm_Back_Cancel) {
		this.confirm_Back_Cancel = confirm_Back_Cancel;
	}

	@Override
	public String toString() {
		return "TransferMoneyInternational_Page [testCaseResult=" + testCaseResult + ", testCaseId=" + testCaseId
				+ ", runMode=" + runMode + ", toggleMenu=" + toggleMenu + ", transferType=" + transferType + ", payee="
				+ payee + ", internationalPayee=" + internationalPayee + ", transferFrom=" + transferFrom
				+ ", currency=" + currency + ", amount=" + amount + ", transferWhen=" + transferWhen + ", transferDate="
				+ transferDate + ", correspondenceCharges=" + correspondenceCharges + ", transferViaIntermediaryBank="
				+ transferViaIntermediaryBank + ", payvia=" + payvia + ", swiftCode_NCC=" + swiftCode_NCC
				+ ", bankName=" + bankName + ", bankAddress=" + bankAddress + ", country=" + country + ", city=" + city
				+ ", paymentDetails=" + paymentDetails + ", note=" + note + ", transfer_Cancel=" + transfer_Cancel
				+ ", confirm_Back_Cancel=" + confirm_Back_Cancel + "]";
	}

}
