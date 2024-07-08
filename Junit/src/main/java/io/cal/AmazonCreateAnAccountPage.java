package io.cal;

public class AmazonCreateAnAccountPage {
	
	public String YourName(String Firstnm, String Lastnm) {
		
		return("Firstnm" + "Lastnm");
	}
	
	public String MobNoAndEmailID(long expectedMobNo,String EmailID) {
		
		return(expectedMobNo + "EmailID");
	}
	
	public boolean CheckPassword(String pass) {
		return pass.length() == 6;
	}
	
	public boolean clickContinue(String name,String email,String password) {
		
		return true;
	}
	
	
	
	
}
