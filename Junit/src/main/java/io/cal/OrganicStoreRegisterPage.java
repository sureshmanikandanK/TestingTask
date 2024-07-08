package io.cal;

public class OrganicStoreRegisterPage {
	
public String UserName(String Name) {
		
		return("Name");
	}
	
	public String EmailId(String EmailID) {
		
		return("EmailID");
	}
public Long ContactNo(Long ConNumber) {
		
		return(ConNumber);
	}
	
	public boolean CheckPassword(String pass) {
		return pass.length() <= 6;
	}
public String YourAddress(String address) {
		
		return("address");
	}
	
	public boolean submit(String name,String email,String password,String address) {
		
		return true;
	}
	

} 
