package gov.hmrc;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import gov.hmrc.Shop;
import gov.hmrc.UnknownItemException;

public class TestShop {
	
	// one apple costs 60p
	@Test
	public void testOneApple() throws Exception {
		List<String> items = Arrays.asList("apple");
		Shop shop = new Shop();
		assertEquals(0.60d, shop.checkout(items), 0.01d);
	}
	
	// two apples costs 60p with bogof
	@Test
	public void testTwoApples() throws Exception {
		List<String> items = Arrays.asList("apple","apple");
		Shop shop = new Shop();
		assertEquals(0.60d, shop.checkout(items), 0.01d);
	}
	
	// three apples costs £1.20 with bogof
	@Test
	public void testThreeApples() throws Exception {
		List<String> items = Arrays.asList("apple","apple","apple");
		Shop shop = new Shop();
		assertEquals(1.20d, shop.checkout(items), 0.01d);
	}	
	
	// ten apples costs £3.00 with bogof
	@Test
	public void testTenApples() throws Exception {
		List<String> items = Arrays.asList("apple","apple","apple","apple","apple","apple","apple","apple","apple","apple");
		Shop shop = new Shop();
		assertEquals(3.00d, shop.checkout(items), 0.01d);
	}
	
	// one orange costs 25p
	@Test
	public void testOneOrange() throws Exception {
		List<String> items = Arrays.asList("orange");
		Shop shop = new Shop();
		assertEquals(0.25d, shop.checkout(items), 0.01d);
	}
	
	// two oranges costs 50p
	@Test
	public void testTwoOranges() throws Exception {
		List<String> items = Arrays.asList("orange","orange");
		Shop shop = new Shop();
		assertEquals(0.50d, shop.checkout(items), 0.01d);
	}
	
	// three oranges costs 50p with 3-for-2
	@Test
	public void testThreeOranges() throws Exception {
		List<String> items = Arrays.asList("orange","orange","orange");
		Shop shop = new Shop();
		assertEquals(0.50d, shop.checkout(items), 0.01d);
	}	
	
	// ten oranges costs £1.75 with 3-for-2
	@Test
	public void testTenOranges() throws Exception {
		List<String> items = Arrays.asList("orange","orange","orange","orange","orange","orange","orange","orange","orange","orange");
		Shop shop = new Shop();
		assertEquals(1.75d, shop.checkout(items), 0.01d);
	}
	
	// an unknown item will throw an exception
	@Test(expected=UnknownItemException.class)
	public void testUnknownItem() throws Exception {
		List<String> items = Arrays.asList("apple","orange","kiwi");
		Shop shop = new Shop();
		shop.checkout(items);
		fail("testUnkownItem");
	}

	// item names are case insensitive
	// an oRAnGe and an ApPLe costs 85p
	@Test
	public void testCaseInsensitive() throws Exception {
		List<String> items = Arrays.asList("oRAnGe","ApPLe");
		Shop shop = new Shop();
		assertEquals(0.85d, shop.checkout(items), 0.01d);
	}
	
	// one apple and one orange costs 85p
	@Test
	public void testOneAppleOneOrange() throws Exception {
		List<String> items = Arrays.asList("orange","apple");
		Shop shop = new Shop();
		assertEquals(0.85d, shop.checkout(items), 0.01d);
	}

	// one apple and two oranges costs £1.10
	@Test
	public void testOneAppleTwoOranges() throws Exception {
		List<String> items = Arrays.asList("orange","apple","orange");
		Shop shop = new Shop();
		assertEquals(1.10d, shop.checkout(items), 0.01d);
	}
	
	// three apples and one orange costs £1.45 with bogof for apples
	@Test
	public void testThreeApplesOneOrange() throws Exception {
		List<String> items = Arrays.asList("apple","apple","orange","apple");
		Shop shop = new Shop();
		assertEquals(1.45d, shop.checkout(items), 0.01d);
	}
	
	// three apples and five oranges costs £2.20 with bogof for apples and 3-for-2 on oranges
	@Test
	public void testThreeApplesFiveOranges() throws Exception {
		List<String> items = Arrays.asList("apple","apple","orange","orange","apple","orange","orange","orange");
		Shop shop = new Shop();
		assertEquals(2.20d, shop.checkout(items), 0.01d);
	}
}
