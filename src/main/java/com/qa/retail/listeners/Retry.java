package com.qa.retail.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	/*			Retry
	 	1: Uses the IRetryAnalyzer interface
	 	2: Return failed test cases
	 	3: You can decide how many times to retry a failed test
	 	4: Retry is a testng framework feature
	 	5: Two ways to perform retry
	 		a: using the retry logic
	 		b: right-click prj > refresh >
	 	
	 	Should we use the retry logic in our framework?
	 		>> While application is in early stages of development
	 		>> network latency
	 		>> slow application -- RETAIL
	 	SHOULD NOT USING:
	 		>> lOCATOR ISSUES: Xpath -- CSS ..
	 		>> Script issue: problems in automation script
	 		>> Synchronization: WAIT time
	 		>> Application issue: Bugs -- unexpected behavior
	 		>> Network issue:
	 	DO: always diagnose the problem -- resolve it
	 
	 */
	
	private int count = 0;
	private static int maxTry = 2;
	
	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) { // Check if test not succeed
			if (count < maxTry) { // Check if maxtry count is reached
				count++; // Increase the maxTry count by 1
				iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
				return true; // Tells TestNG to re-run the test
			} else {
				iTestResult.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
		}
		return false;
	}

}
