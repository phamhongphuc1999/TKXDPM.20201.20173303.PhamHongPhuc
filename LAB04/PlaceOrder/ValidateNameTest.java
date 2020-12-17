package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidateNameTest {
	private PlaceOrderController placeOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	 @CsvSource({
		 "0123456789,true",
		 "1111111111,true",
		 "phamhongphuc,true"
	 })
	public void test(String name, boolean expected) {
		boolean result = placeOrderController.validateAddress(name);
		assertEquals(result, expected);
	}
}
