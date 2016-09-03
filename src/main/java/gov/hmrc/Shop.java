package gov.hmrc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Shop {
	
	final String APPLE  = "apple";
	final String ORANGE = "orange";
	
	// Initialise price catalogue
	final HashMap<String, OrderItem> prices = new HashMap<String, OrderItem>() {{
	    put(APPLE, new OrderItem(APPLE, 0.60d, 2, 0.00d));
	    put(ORANGE, new OrderItem(ORANGE, 0.25d, 3, 0.00d));
	}}; 
	
	private int appleCount = 0;
	private int orangeCount = 0;
	
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
				appleCount++;
				item = new OrderItem(prices.get(APPLE));
				if ((appleCount % item.getOfferFrequency()) == 0) {
					item.setPrice(item.offerPrice);
				}
				orderItems.add(item);
				break;
			case ORANGE:
				orangeCount++;
				item = new OrderItem(prices.get(ORANGE));
				if ((orangeCount % item.getOfferFrequency()) == 0) {
					item.setPrice(item.offerPrice);
				}
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
		private int offerFrequency = -1;
		private double offerPrice = 0.00d;
		
		private OrderItem(){};
		
		public OrderItem(String name, double price, int offerFrequency, double offerPrice) {
			this.itemName = name;
			this.itemPrice = price;
			this.offerFrequency = offerFrequency;
			this.offerPrice = offerPrice;
		}
		
		public OrderItem(OrderItem item) {
			this(item.getName(),item.getPrice(),item.getOfferFrequency(),item.getOfferPrice());
		}
		
		public String getName() { return itemName; }
		public double getPrice() { return itemPrice; }
		public int getOfferFrequency() { return offerFrequency; }
		public double getOfferPrice() { return offerPrice; }
		
		public void setPrice(double price) { this.itemPrice = price; }
	}

}