package pageUIs.liveguru;

public class TVPageUI {
	public static final String PRODUCTS = "//h2[@class='product-name']//a";
	public static final String PRODUCT_FRONT = "//div[@class='product-info']//a[@title='";
	public static final String PRODUCT_END = "']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-wishlist']";
	public static final String PRODUCT_NAME_END="']";
}
