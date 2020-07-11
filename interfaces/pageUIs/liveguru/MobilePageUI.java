package pageUIs.liveguru;

public class MobilePageUI {
	public static final String PRODUCT_NAMES="//h2[@class='product-name']//a";
	public static final String PRODUCT_FRONT="//a[@title='";
	public static final String PRODUCT_COST_END ="']/parent::h2/following-sibling::div//span[@class='price']";
	public static final String PRODUCT_END="']";
	public static final String PRODUCT_BUTTON_ATC_END="']/parent::h2/following-sibling::div[@class='actions']//button";
}
