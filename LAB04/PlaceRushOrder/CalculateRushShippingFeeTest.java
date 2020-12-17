package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import entity.order.Order;

public class CalculateRushShippingFee {
	private PlaceRushOrderController placeRushOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}
	
	public void test() {
		int fee = placeRushOrderController.calculateRushShippingFee(new Order());
		assertEquals(fee, 100000);
	}
}
