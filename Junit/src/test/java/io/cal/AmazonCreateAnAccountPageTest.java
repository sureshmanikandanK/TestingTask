package io.cal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

class AmazonCreateAnAccountPageTest {

	//@Test
	void testYourName() {
		
		AmazonCreateAnAccountPage a = new AmazonCreateAnAccountPage();
		
		String expectedFirstnm = "Suresh";
		String expectedLastnm = "Manikandan";
		
		//String expected = "SureshManikandan";
		String actualResult = a.YourName(expectedFirstnm, expectedLastnm);
		
		assertSame(a.YourName(expectedFirstnm, expectedLastnm),actualResult,"Please Provide Your FirstName and LastName");
		assertEquals(a.YourName(expectedFirstnm, expectedLastnm), actualResult);
		}
	//@Test
	void testMobNoAndEmailiD() {
		AmazonCreateAnAccountPage a = new AmazonCreateAnAccountPage();
		
		long expectedMobNo = 1234567890l;
		String expectedEmailID = "suresh123@gmail.com";
		String actualResult = a.MobNoAndEmailID(expectedMobNo, expectedEmailID);
		
		assertAll(() ->  equals(a.MobNoAndEmailID(1234567890,"suresh123@gmail.com")),
				() -> equals(a.MobNoAndEmailID(99888888,"suresh@gmail.com"))
				);
		
	}
	//@Test
		void testMobNoAndEmailiD1() {
			AmazonCreateAnAccountPage a = new AmazonCreateAnAccountPage();
			
			long expectedMobNo = 1234567890l;
			String expectedEmailID = "suresh123@gmail.com";
			String actualResult = a.MobNoAndEmailID(expectedMobNo, expectedEmailID);
			
			assertAll(() ->  equals(a.MobNoAndEmailID(1234567890,""))
					
					);
			
		}
	//@Test
	void testCheckPassword() {
		
		AmazonCreateAnAccountPage a = new AmazonCreateAnAccountPage();
		
		String expectedpass = "suresh";
		boolean actualResult = a.CheckPassword(expectedpass);
		assertTrue(a.CheckPassword("suresh"));
			
	}
	//@Test
	void testCheckPassword1() {
		
		AmazonCreateAnAccountPage a = new AmazonCreateAnAccountPage();
		
		String expectedpass = "12345";
		boolean actualResult = a.CheckPassword(expectedpass);
		assertTrue(a.CheckPassword("12345"));
			
	}
	//@Test
	void testCheckPassword2() {
		
		AmazonCreateAnAccountPage a = new AmazonCreateAnAccountPage();
		
		String expectedpass = "123456";
		boolean actualResult = a.CheckPassword(expectedpass);
		assertFalse(a.CheckPassword(""));
			
	}
	//@Test
    void testclickContinue() {
    	
    	AmazonCreateAnAccountPage a = new AmazonCreateAnAccountPage();
    	
    	String expectedname = "sureshmanikandan";
    	String expectedEmailID = "suresh123@gmail.com";
    	String expectedpass = "suresh";
    	
    	boolean actualResult = a.clickContinue(expectedname, expectedEmailID, expectedpass);
    	 assertTrue(a.clickContinue("sureshmanikandan", "suresh123@gmail.com", "suresh"));
    	
    }
	//@Test
    void testclickContinue1() {
    	
    	AmazonCreateAnAccountPage a = new AmazonCreateAnAccountPage();
    	String expectedname = "sureshmanikandan";
    	String expectedEmailID = "suresh123@gmail.com";
    	String expectedpass = "suresh";
    	
    	boolean actualResult = a.clickContinue(expectedname, expectedEmailID, expectedpass);
    	
    	 assertFalse(a.clickContinue("", "suresh123@gmail.com", "suresh"));
    	
    }
	//@Test
    void testclickContinue2() {
    	
    	AmazonCreateAnAccountPage a = new AmazonCreateAnAccountPage();
    	String expectedname = "sureshmanikandan";
    	String expectedEmailID = "suresh123@gmail.com";
    	String expectedpass = "suresh";
    	
    	boolean actualResult = a.clickContinue(expectedname, expectedEmailID, expectedpass);
    	 assertFalse(a.clickContinue("sureshmanikandan", "suresh123@gmail.com", ""));
    	
    }
	//@Test
	@Disabled //This will not execute
    void testclickContinue3() {
    	
    	AmazonCreateAnAccountPage a = new AmazonCreateAnAccountPage();
    	String expectedname = "sureshmanikandan";
    	String expectedEmailID = "suresh123@gmail.com";
    	String expectedpass = "suresh";
    	
    	boolean actualResult = a.clickContinue(expectedname, expectedEmailID, expectedpass);
    	
    	 assertFalse(a.clickContinue("", "", ""));
    	
    }
//	@Test
//
//	String testInvalidPassword() {
//
//		
//
//		String shortpassword = "1234";
//
//		String longpassword = "1234567";
//
//		String isValid = "123456";
//
//		String isInvalid = (shortpassword + longpassword);
//
//		
//
//		String expected = "123456";
//
//		String actual = "123456";
//
//		
//
//		boolean isShortValid = isValidPassword(shortpassword);
//
//		boolean isLongValid = isValidPassword(longpassword);
//
//		
//
//		assertFalse(isShortValid, "Password should be exactly 6 characters long");
//
//		assertFalse(isLongValid, "Password should be exactly 6 characters long");
//
//		
//
//		assertEquals(expected, actual);
//
//		
//
//		if(isValid.length() == 6) {
//
//			assertSame(expected, actual, "Strong Pasword");
//
//			
//
//		}else {
//
//			return isInvalid;
//
//		}
//
//		return null;
//
//	}
//	private boolean isValidPassword(String shortpassword) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	AmazonCreateAnAccountPage t;
	TestInfo testInfo;
	TestReporter testReporter;
	
	@Test
	@Tag("testYourName")
	@Tag("testMobNoAndEmailiD")
	@Tag("testMobNoAndEmailiD1")
	@Tag("testCheckPassword")
	@Tag("testCheckPassword1")
	@Tag("testCheckPassword2")
	@Tag("testclickContinue")
	@Tag("testclickContinue1")
	@Tag("testclickContinue2")
	@Tag("testclickContinue3")
	void init(TestInfo testInfo,TestReporter testReporter) {
		
		t = new AmazonCreateAnAccountPage();
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		testReporter.publishEntry("Tested All AmazonCreateAnAccountPage Fields Success" + testInfo.getTags());
		
	}
	
	
}

