package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidateAddressTest {
	private PlaceOrderController placeOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	 @CsvSource({
		 "Hai Duong,true",
		 "abc, true",
		 "phuc@123,true"
	 })
	public void test(String address, boolean expected) {
		boolean result = placeOrderController.validateAddress(address);
		assertEquals(result, expected);
	}
}
