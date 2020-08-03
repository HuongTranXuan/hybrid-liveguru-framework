package pageUIs.liveguru;

public class MobilePageUI {
	public static final String PRODUCT_NAMES="//h2[@class='product-name']//a";
	public static final String PRODUCT_FRONT="//div[@class='product-info']//a[@title='";
	public static final String PRODUCT_COST_END ="']/parent::h2/following-sibling::div//span[@class='price']";
	public static final String PRODUCT_END="']";
	public static final String PRODUCT_BUTTON_ATC_END="']/parent::h2/following-sibling::div[@class='actions']//button";
	public static final String PRODUCT_LINK_COMPARE_END="']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']";
	public static final String SUCCESS_MSG = "//li[@class='success-msg']//span";
	public static final String COMPARE_BUTTON = "//span[text()='Compare']";
	public static final String MOBILE_PAGE_ID ="10";
}
