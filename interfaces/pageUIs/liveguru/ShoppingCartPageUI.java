package pageUIs.liveguru;

public class ShoppingCartPageUI {
	public static final String SUCCESS_MSG = "//li[@class='success-msg']//span";
	public static final String COUPON_CODE = "//input[@id='coupon_code']";
	public static final String APPLY_LINK = "//span[text()='Apply']";
	public static final String DISCOUNT_MONEY = "//td[contains(text(),'Subtotal')]//parent::tr/following-sibling::tr//span[@class='price']";
	public static final String DISCOUNT_CODE = "//td[contains(text(),'Discount')]";
	public static final String GRANT_TOTAL_MONEY = "//strong[contains(text(),'Grand Total')]/parent::td/following-sibling::td//span";
	public static final String INPUT_QUALTY = "//input[@title='Qty']";
	public static final String BUTTON_UPDATE = "//button[@name='update_cart_action']//span[text()='Update']";
	public static final String ERROR_MSG = "//li[@class='error-msg']//span";
	public static final String ITEM_MSG_ERROR = "//p[@class='item-msg error']";
	public static final String EMPTY_CART = "//span[text()='Empty Cart']";
	public static final String MSR_ERROR_EMPTY_CART = "//div[@class='cart-empty']//p[contains(text(),'no item')]";
}
