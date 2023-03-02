package com.estore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.estore.actiondriver.Action;
import com.estore.base.BaseClass;

/**
 * @author kanwaljeetsingh
 * PageFactory is used to initialize elements on pages
 */
public class HomePage extends BaseClass {
//	@FindBy(xpath = "//span[text()='My wishlists']")
//	WebElement myWishlists;

	@FindBy(xpath = "//span[@class='hm-icon-label']")
	WebElement allItemsLabel;
	
	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}

	// validate All Items label is displayed
	public boolean validateAllItemsLabel() throws Exception {
		return Action.isDisplayed(allItemsLabel);
	}

	// get current page URL
	public String getCurrentUrl() {
		String homePageUrl = getDriver().getCurrentUrl();
		return homePageUrl;
	}
	
	// get page title
	public String getStoreTitle() {

		String storeTitle = getDriver().getTitle();
		return storeTitle;
	}
}
