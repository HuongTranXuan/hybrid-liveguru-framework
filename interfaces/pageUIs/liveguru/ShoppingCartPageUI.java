package pageUIs.liveguru;

public class ShoppingCartPageUI {
	public static final String SUCCESS_MSG = "//li[@class='success-msg']//span";
	public static final String COUPON_CODE = "//input[@id='coupon_code']";
	public static final String APPLY_LINK = "//span[text()='Apply']";
	public static final String DISCOUNT_MONEY ="//td[contains(text(),'Subtotal')]//parent::tr/following-sibling::tr//span[@class='price']";
	public static final String DISCOUNT_CODE="//td[contains(text(),'Discount')]";
	public static final String GRANT_TOTAL_MONEY="//strong[contains(text(),'Grand Total')]/parent::td/following-sibling::td//span";
}
