package com.jmr.testsuite.obdx;

import java.util.Date;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportFactory {
	

		static String path = null;
		public static ExtentReports getInstance() {
			ExtentReports reports;
			path = getReportName();// "reports//RegressionTestResults.html";
			setPath(path);
			reports = new ExtentReports(path,false);
			return reports;

		}

		public static String getPath() {
			return path;
		}

		public static void setPath(String path) {
			ExtentReportFactory.path = path;
		}

		public static String getCurrentDateAndTime() {
			Date date = new Date();
			String date1 = date.toString().replaceAll(" ", "_").replaceAll(":", "_");
			// display time and date using toString()
			System.out.println(date1);

			return date1;
		}

		public static String getReportName() {
			String name = "reports//report_" + getCurrentDateAndTime() + ".html";
			return name;
		}

		public static String getLogName(String logName) {
			String name = "reports//" + logName + "_" + getCurrentDateAndTime() + ".txt";
			return name;
		}

		/**
		 * 
		 * @param from
		 * @param password
		 * @param to
		 * @param subject
		 * @param body
		 * @throws EmailException 
		 */
		
		/*public static void sendReportByGMail(String... to) throws EmailException 
		{
			String ssName = null;
			EmailAttachment attachment = new EmailAttachment();
			  attachment.setPath(ExtentReportFactory.getPath());
			  attachment.setDisposition(EmailAttachment.ATTACHMENT);
			  attachment.setDescription("TEST EXECUTION REPORT");
			  attachment.setName("AutomationExecutionReport" + ExtentReportFactory.getCurrentDateAndTime() + ".html");
			  // Create the email message
			  MultiPartEmail email = new MultiPartEmail();
			  email.setHostName("smtp.gmail.com");
			  //email.addTo(to, "Sujeetkumar Umbarkar");
			  if(to.length==0){
				  email.addTo("sujeetkumar.b@jmrinfotech.com");
			  }else
			  {
				  email.addTo(to);
			  }
			  email.setAuthenticator(new DefaultAuthenticator("sujeetkumar.b@jmrinfotech.com", "Flexcube@1"));
			  email.setSSLOnConnect(true);
			  email.setFrom("sujeetkumar.b@jmrinfotech.com", "Flexcube Automation Team");
			  email.setSubject("Automation Test Execution report for Flexcube");
			  email.setMsg("Refer the attachment fot the detailed Execution Report");
			  

			  // add the attachment
			  email.attach(attachment);
			  if(Log.ssPath.size()>0){
				  Iterator<String>  it = Log.ssPath.iterator();
				  while(it.hasNext()){
					  	ssName = it.next();
					  	System.out.println(ssName);
					  	email.attach(new File(ssName));
				  }
			  }
			  // send the email
			  email.send();
		}*/

}
