package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidatePhoneNumberTest {
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
	public void test(String phone, boolean expected) {
		boolean result = placeOrderController.validatePhoneNumber(phone);
		assertEquals(result, expected);
	}
}
