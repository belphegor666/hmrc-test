package gov.hmrc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Shop {
	
	final String APPLE  = "apple";
	final String ORANGE = "orange";
	
	// Initialise price catalogue
	final HashMap<String, OrderItem> prices = new HashMap<String, OrderItem>() {{
	    put(APPLE, new OrderItem(APPLE, 0.60d));
	    put(ORANGE, new OrderItem(ORANGE, 0.25d));
	}}; 
	
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public double checkout(List<String> shoppingList) throws UnknownItemException {
		addItems(shoppingList);
		double total = 0.00d;
		
		for ( OrderItem item : orderItems ) {
			total += item.getPrice();
		}
		return total;
	}
	
	private void addItems(List<String> items) throws UnknownItemException {
		for(String item:items) {
			addItem(item.toLowerCase());
		}
	}
	
	public void addItem(String itemName) throws UnknownItemException {
		OrderItem item = null;
		switch (itemName) {
			case APPLE:
				item = new OrderItem(prices.get(APPLE));
				orderItems.add(item);
				break;
			case ORANGE:
				item = new OrderItem(prices.get(ORANGE));
				orderItems.add(item);
				break;
			default:
				throw new UnknownItemException(itemName);
				
		}
		
	}
	
	private class OrderItem {
		private final String UNDEFINED_ITEM_NAME = "Undefined";
		private String itemName = UNDEFINED_ITEM_NAME;
		private double itemPrice = 0.00d;
		
		private OrderItem(){};
		
		public OrderItem(String name, double price) {
			this.itemName = name;
			this.itemPrice = price;
		}
		
		public OrderItem(OrderItem item) {
			this(item.getName(),item.getPrice());
		}
		
		public String getName() { return itemName; }
		public double getPrice() { return itemPrice; }

	}

}