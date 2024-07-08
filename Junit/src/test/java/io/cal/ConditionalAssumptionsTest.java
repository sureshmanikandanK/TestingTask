package io.cal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class ConditionalAssumptionsTest {
	
	@Test
	@EnabledOnOs(OS.LINUX)
	void testOperatingSys() {
		
		System.out.println("I am on windows 10");
	}
	void testValue() {
		
		ConditionalAssumptions c1 = new ConditionalAssumptions();
		
		boolean val = false;
		assumeTrue(val);
		int expected = 10;
		int actual = c1.value();
		assertEquals(expected, actual);
		
		
	}
	@Test
	@DisplayName("Multiply Method")
	void testMultiply() {
		ConditionalAssumptions c1 = new ConditionalAssumptions();
		
		assertAll(() -> assertEquals(4,c1.multiply(2,2)),
				() -> assertEquals(0,c1.multiply(2,0)),
				() -> assertEquals(-2,c1.multiply(2,-1))
				);		
		
	}
	
}
