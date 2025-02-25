package testCase;

import org.testng.annotations.Test;

import testBase.BaseClass;

public class Testt extends BaseClass{
	 	@Test
	    public void testttt() {
	        System.out.println("Operating System: " + System.getProperty("os.name"));
	    }
	

}
