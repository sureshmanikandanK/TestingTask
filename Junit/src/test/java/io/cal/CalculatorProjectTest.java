package io.cal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorProjectTest {

	//@Test
	void addtest() {
	    
		CalculatorProject c = new CalculatorProject();
		
		int expected = 2;
		int actual = c.add(1,1);
		
		assertEquals(expected, actual,"Add Function can calculate two integer");
	}
	//@Test
	void subtest() {
		
		CalculatorProject c = new CalculatorProject();
		
		int expected = 0;
		int actual = c.sub(1,1);
		
		assertEquals(expected, actual,"sub Function can calculate two integer");
		
	}
	//@Test
	void multest() {
		CalculatorProject c = new CalculatorProject();
		
		int expected = 1;
		int actual = c.mul(1,1);
		
		assertEquals(expected, actual,"mul Function can calculate two integer");
		
	}
	//@Test
  	void divtest() {
		CalculatorProject c = new CalculatorProject();
		
		int expected = 2;
		int actual = c.div(2,1);
		
		assertThrows(NullPointerException.class,() ->c.div(1,0),"divide by Zero");
	
}
  	//@Test
	void AreaOfCircleTest() {
		CalculatorProject c = new CalculatorProject();
		
		//assertEquals(3.14, c.AreaOfCircle(10));
		assertEquals(314.1592653589793, c.AreaOfCircle(10));

}
	@Test
		void positiveNumberTest() {
			CalculatorProject c = new CalculatorProject();
			
			//assertEquals(3.14, c.AreaOfCircle(10));
			assertEquals(true, c.positiveNumber(1));
	}
	


}
