package entity.invoice;

import entity.order.Order;

public class RushInvoice extends Invoice {
	public RushInvoice() {
		super();
	}
	
	public RushInvoice(Order order) {
		super(order);
	}
}
