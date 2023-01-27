package com.sdet.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;

public class Homepage {

	WebDriver wd = null;
	WebElement lnkLastSuggestion = null;
	public Homepage(WebDriver driver) {
		this.wd = driver;
		PageFactory.initElements(wd, this);
	}


	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement txtSearchBox;

	@FindBy(xpath = "//div[@class='s-suggestion-container']")
	List<WebElement> lnkSuggestions;
	
	
	@FindBy(xpath = "//div[@class='a-section aok-relative s-image-square-aspect']/img")
	List<WebElement> lnkAllPens;
	
	@FindBy(xpath = "//span[@id='a-autoid-0-announce']")
	WebElement lnkSortBy;
	
	
	@FindBy(xpath = "//a[@id='s-result-sort-select_1']")
	WebElement lnkPriceSelection;

	@FindBy(xpath = "//span[@class='a-size-base-plus a-color-base a-text-normal']")
	List<WebElement> lnkListPens;
	
	@FindBy(xpath = "//span[@id='a-autoid-0-announce']")
	WebElement Qty;
	@FindBy(xpath = "//a[@id='quantity_1']")
	WebElement selectQty;
	
	@FindBy(xpath = "//input[@id='add-to-cart-button']")
	WebElement addToCart;
	
	@FindBy(xpath = "//input[contains(@value,'Proceed to checkout')]")
	WebElement lnkCart;
	
	@FindBy(xpath = "//input[@value='Delete']")
	WebElement lnkDelete;
	
	@FindBy(xpath = "//span[contains(text(),'was removed from Shopping Cart')]")
	WebElement lblConfirmMessage;
	
	
	public void searchAndAddGelPen(String item) throws InterruptedException {
		// Search Text
		txtSearchBox.sendKeys(item);
		
		Assert.assertTrue(verifySuggestion(item));
		
		Thread.sleep(3000);
		
		lnkLastSuggestion.click();
		
		// Verify all items text
		Assert.assertTrue(searchGelPenText());
		
		// Sort low to high
		lnkSortBy.click();
		lnkPriceSelection.click();
		
		//Select pen
		lnkListPens.get(0).click();
		
		//Select Quantity
		Qty.click();
		selectQty.click();
		
		//Add to cart
		addToCart.click();
		
	}
	
	public boolean verifySuggestion(String item) throws InterruptedException {
		boolean flag = true;
		Thread.sleep(2000);
		for (WebElement item1 : lnkSuggestions) {
			if (!item1.getText().toLowerCase().contains(item.toLowerCase())) {
				return false;
			}
			lnkLastSuggestion = item1;
		}
		return flag;
		
	}
	public boolean searchGelPenText() {
		boolean flag = false;
		for (WebElement item1 : lnkAllPens) {
			if (item1.getAttribute("alt").toLowerCase().contains("pen")) {
				flag = true;
			}
		}
		return flag;
	}
	
	public void verifyCart() throws InterruptedException {
		
		lnkCart.click();
		lnkDelete.click();
		Assert.assertTrue(lblConfirmMessage.isDisplayed());
	}
}
