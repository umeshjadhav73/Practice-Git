package com.runner;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class Runner {
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		List<String> suites = new ArrayList<String>();
		suites.add(System.getProperty("user.dir")+"\\testng.xml");//path to xml..
	//	suites.add("c:/tests/testng2.xml");
		testng.setTestSuites(suites);
		testng.run();
	}

}
