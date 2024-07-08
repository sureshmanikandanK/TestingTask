package io.cal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrganicStoreRegisterPageTest {

	//@Test
	void testName() {
		
		OrganicStoreRegisterPage o = new OrganicStoreRegisterPage();
		
		String expectedName = "Suresh";
		String actualResult = o.UserName(expectedName);
		
		assertSame(o.UserName(expectedName), actualResult,"Please Provide the UserName");
		assertEquals(o.UserName(expectedName), actualResult);
		 
	}
	//@Test
	void testEmailId() {
		OrganicStoreRegisterPage o = new OrganicStoreRegisterPage();
		
		
		String expectedEmailID = "suresh123@gmail.com";
		String actualResult = o.EmailId(expectedEmailID);
		
		assertSame(o.EmailId(expectedEmailID), actualResult,"Please Provide the Email");
		assertEquals(o.EmailId(expectedEmailID), actualResult);
	}
	
	
	//@Test
	void testContactNo() {
		OrganicStoreRegisterPage o = new OrganicStoreRegisterPage();
		
		
		Long expectedContactNo = 7878787878l;
		Long actualResult = o.ContactNo(expectedContactNo);
		
		assertSame(o.ContactNo(expectedContactNo), actualResult,"Please Provide the ContactNumber");
		assertEquals(o.ContactNo(expectedContactNo), actualResult);
		
	}
	
	//@Test
	void testPassword() {
		OrganicStoreRegisterPage o = new OrganicStoreRegisterPage();
		
		
		String expectedpass = "suresh";
		boolean actualResult = o.CheckPassword(expectedpass);
		
		assertSame(o.CheckPassword(expectedpass), actualResult,"Password must be greater than 6 character or atleast 6 character");
		assertEquals(o.CheckPassword(expectedpass), actualResult);
		
	}
	// @Test
	    public void testEmptyPassword() {
		 OrganicStoreRegisterPage o = new OrganicStoreRegisterPage();
			
	        assertFalse(o.CheckPassword("")); 
	    }
	 
	 //@Test
	    public void testshortPassword() {
		 OrganicStoreRegisterPage o = new OrganicStoreRegisterPage();
		 String expectedpass = "suresh";
		 boolean actualResult = o.CheckPassword(expectedpass);
			
	        assertFalse(o.CheckPassword("smk")); 
	    }
	//@Test
		void testAddress() {
			OrganicStoreRegisterPage o = new OrganicStoreRegisterPage();
			
			
			String expectedAddress = "Chennai";
			String actualResult = o.YourAddress(expectedAddress);
			
			assertSame(o.YourAddress(expectedAddress), actualResult,"Please Provide the Email");
			assertEquals(o.YourAddress(expectedAddress), actualResult);
		}
		//@Test
				void testAddressint() {
					OrganicStoreRegisterPage o = new OrganicStoreRegisterPage();
					
					
					String expectedAddress = "Chennai";
					String actualResult = o.YourAddress(expectedAddress);
					
					assertFalse(o.CheckPassword("124456")); 
				}
		@Test		
	 void testclickSubmit() {
				    	
		 OrganicStoreRegisterPage o = new OrganicStoreRegisterPage();
		 
		String expectedname = "sureshmanikandan";
		String expectedEmailID = "suresh123@gmail.com";
		String expectedpass = "suresh";
		String expectedAddress = "Chennai";
		
		
		boolean actualResult = o.submit(expectedname, expectedEmailID, expectedpass,expectedAddress);
				    	
		assertFalse(o.submit("", "", "", ""),"Please Provide the Required Fields");
				    	
				    }		
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
