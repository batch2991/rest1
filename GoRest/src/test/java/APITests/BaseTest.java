package APITests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;

public class BaseTest 
{

	public static ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;
	public Faker faker=new Faker();

	@BeforeSuite
	public void initialize()
	{
		SimpleDateFormat d=new SimpleDateFormat("dd-MM-yyyy");
		String d1=d.format(new Date());
		String reportname="./Report/GoRest"+d1+".html";

		spark=new ExtentSparkReporter(new File(reportname));
		report=new ExtentReports();
		report.attachReporter(spark);
		spark.config().setDocumentTitle("GoRestAPITesting");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("GoRest");

		report.setSystemInfo("Environment","TestEnv");
		report.setSystemInfo("TesterName","Tester1");

	}
	@AfterSuite
	public void saveReport()
	{
		report.flush();
	}

}
