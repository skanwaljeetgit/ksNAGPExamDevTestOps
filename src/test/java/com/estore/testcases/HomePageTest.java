package com.estore.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.estore.base.BaseClass;
import com.estore.dataprovider.DataProviders;
import com.estore.pageobjects.HomePage;
import com.estore.utility.Log;

/**
 * @author kanwaljeetsingh This test case validates the orderHistoryLabel on
 *         HomePage
 */

public class HomePageTest extends BaseClass {
//	LoginPage loginPage;// = new IndexPage();;
//	IndexPage indexPage;
	HomePage homePage;

//	@Test(groups = "Smoke", dataProvider = "credentials", dataProviderClass = DataProviders.class)
//	public void orderHistoryDetailLabelTest(String userName, String password) throws Exception {
//		Log.startTestCase("orderHistoryDetailLabelTest");
//		indexPage = new IndexPage();
//		Log.info("Index Page load");
//		loginPage = indexPage.clickOnSignIn();
//		Log.info("Clicked on Sign in button");
//		homePage = loginPage.userLogin(userName, password);
//		Log.info("Entered Email address: " + userName + "and password: " + password + " for login");
//		boolean orderHistory = homePage.validateOrderHistory();
//		Assert.assertTrue(orderHistory, "orderHistoryLabel_Not_Found");
//		Log.info("orderHistory label validated successfully");
//		Log.endTestCase("orderHistoryDetailLabelTest");
//
//	}
//	
	@Test(groups = "Smoke")
	public void allItemsLabelTest() throws Exception {
		Log.startTestCase("allItemsLabelTest");
		homePage = new HomePage();
		boolean allItemsLabelVisible = homePage.validateAllItemsLabel();
		Assert.assertTrue(allItemsLabelVisible, "allItemsLabel_Not_Found");
		Log.info("All Items Label validated successfully");
		Log.endTestCase("allItemsLabelTest");

	}

	@Test(groups = "Smoke", dataProvider = "getUrl", dataProviderClass = DataProviders.class)
	public void urlTest(String url) throws Exception {
		Log.startTestCase("urlTest");
		homePage = new HomePage();
		String actualUrl = homePage.getCurrentUrl();
		String expectedUrl = url;
		Assert.assertEquals(actualUrl, expectedUrl, "Incorrect_URL_Found");
		Log.info("URL validated successfully");
		Log.endTestCase("urlTest");

	}
	
	@Test(groups = "Smoke", dataProvider = "getStoreTitle", dataProviderClass = DataProviders.class)
	public void storeTitleTest(String storeTitle) throws Exception {
		Log.startTestCase("storeTitleTest");
		homePage = new HomePage();
		String actualStoreTitle = homePage.getStoreTitle();
		String expectedStoreTitle = storeTitle;
		Assert.assertEquals(actualStoreTitle, expectedStoreTitle, "Store_Title_Not_Found");
		Log.info("Store Title validated successfully");
		Log.endTestCase("storeTitleTest");

	}
	
}
