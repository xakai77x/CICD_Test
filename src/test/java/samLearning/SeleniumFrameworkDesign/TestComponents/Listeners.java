package samLearning.SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import samKong.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports report = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // thread safe
	@Override
	public void onTestStart(ITestResult result) {

		test = report.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, "PASSED");
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		String path = null;
		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver);

		} catch (IOException e) {

			e.printStackTrace();
		}

		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		String path = null;
		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver);

		} catch (IOException e) {

			e.printStackTrace();
		}

		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

		report.flush();

	}

}
