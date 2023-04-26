package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    } //Opening URL in browser

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        //clicking on computers tab
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        //Clicking on desktop
        clickOnElement(By.xpath("//li[@class='active last']//a[normalize-space()='Desktops']"));
        //Selecting order by to 'Name: Z to A'
        selectFromDropDownMenu(By.xpath("//select[@id='products-orderby']"), "Name: Z to A");        //Expected text
        String expectedText = "Name: Z to A";
        // Getting actual text
        String actualText = getTextFromElement(By.xpath("//select[@id='products-orderby']/option[text()='Name: Z to A']"));
        //verifying that the products are in descending order
        Assert.assertEquals("not sorted by Name: Z to A", expectedText, actualText);
    }


    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws Exception {
        //clicking on computers tab in menu
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        //Clicking on desktop
        clickOnElement(By.xpath("//li[@class='active last']//a[normalize-space()='Desktops']"));
        //Selecting order by to 'Name: A to Z'
        selectFromDropDownMenu(By.xpath("//select[@id='products-orderby']"), "Name: A to Z");
        Thread.sleep(1000);
        //Clicking on "Add to cart" //can you use index path?
        clickOnElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//button[1]"));
        // Verify the Text "Build your own computer"
        String expectedMessage = "Build your own computer";
        String actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Build your own computer']"));
        Assert.assertEquals(expectedMessage, actualMessage);
        Thread.sleep(1000);
        selectFromDropDownMenu(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200"); // Selecting "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectFromDropDownMenu(By.id("product_attribute_2"), "8GB [+$60.00]");   //Selecting "8GB [+$60.00]" using Select class
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']")); //Selecting HDD radio "400 GB [+$100.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']")); //Select OS radio "Vista Premium [+$60.00]"
        selectCheckBox(By.xpath("//input[@id='product_attribute_5_10']"));//Selecting "Microsoft Office [+$50.00]" checkbox
        selectCheckBox(By.xpath("//input[@id='product_attribute_5_12']")); //Selecting "Total Commander[+$5.00]" checkbox.
        Thread.sleep(1000);
        //Verifying the price "$1,470.00"
        expectedMessage = "$1,475.00";
        actualMessage = getTextFromElement(By.xpath("//div[@class='product-price']")); //Checking actual and expected match
        Assert.assertEquals(expectedMessage, actualMessage);
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']")); // Click on "ADD TO CART" Button.
        Thread.sleep(1000);
        //Verifying the Message "The product has been added to your shopping cart" on Top green Bar is shown
        expectedMessage = "The product has been added to your shopping cart";
        Thread.sleep(1000);

        actualMessage = getTextFromElement(By.xpath("//div[@id='bar-notification']")); //Getting actual message
        Assert.assertEquals(actualMessage, expectedMessage); //Comparing actual and expected message
        clickOnElement(By.xpath("//span[@title='Close']")); //closing message using cross button
        Thread.sleep(1000);
        mouseHoverOnElement(By.xpath("//span[@class='cart-label']")); //Mouse hover over "Shopping Cart"
        mouseHoverAndClickOnElement(By.xpath("//span[text()='Shopping cart']")); //Hover and click on "Go to cart"
        expectedMessage = "Shopping cart"; //Expected message
        actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']")); //Getting actual message
        Assert.assertEquals(actualMessage, expectedMessage); //Comparing actual and expected

        // Change the Qty to "2" and Click on "Update shopping cart"
        sendTextToElement(By.xpath("//input[contains(@id,'itemquantity')]"), "2"); //Changing quantity to 2
        clickOnElement(By.xpath("//button[@id='updatecart']")); //Updating cart
        // Verify the Total"$2,950.00"
        String expectedTotal = "$2,950.00";
        String actualTotal = driver.findElement(By.xpath("//td[@class='subtotal']/span[text()='$2,950.00']")).getText();
        verifyElementMethod(expectedTotal,actualTotal);
        // click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        //Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));
        // Verify the Text “Welcome, Please Sign In!”
        expectedMessage = "Welcome, Please Sign In!";
        actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        Assert.assertEquals(expectedMessage, actualMessage);
        // Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));
        // Filling in all registration details
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "John");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Smith");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "johnsmith123@gmail.com");
        Thread.sleep(1000);
        selectFromDropDownMenu(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "Harley Street");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "lu48qa");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07857658963");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']")); //Click on continue
        Thread.sleep(1000);
        clickOnElement(By.xpath("//input[@id='shippingoption_1']")); // Clicking on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']")); //Click on continue
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']")); // Selecting Radio Button “Credit Card” for payment

        // Selecting “Master card” From Select credit card dropdown
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        Thread.sleep(1000);
        // Filling in all the required details
        selectFromDropDownMenu(By.xpath("//select[@id='CreditCardType']"), "Master card");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "John Smith");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "1111222233334444");
        selectFromDropDownMenu(By.xpath("//select[@id='ExpireMonth']"), "05");
        selectFromDropDownMenu(By.xpath("//select[@id='ExpireYear']"), "2026");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "028");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']")); //Clicking on continue
        Thread.sleep(1000);
        // Verifying “Payment Method” is “Credit Card”
        expectedMessage = "Credit Card";
        actualMessage = getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']"));
        Assert.assertEquals(expectedMessage, expectedMessage);

        expectedMessage = "Next Day Air"; // Verify the “Shipping Method” is “Next Day Air”
        actualMessage = getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']"));
        Assert.assertEquals(expectedMessage, expectedMessage);

        expectedMessage = "$2,950.00"; // Verify the Total is “$2,950.00”

        actualMessage = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        Assert.assertEquals(expectedMessage, expectedMessage);
        clickOnElement(By.xpath("//button[normalize-space()='Confirm']")); //Click on confirm
        expectedMessage = "Thank you"; //Verify text 'Thank you'
        actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Thank you']"));
        Assert.assertEquals(expectedMessage, expectedMessage);

        // Verify the message “Your order has been successfully processed!”
        expectedMessage = "Your order has been successfully processed!";
        actualMessage = getTextFromElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"));
        Assert.assertEquals(expectedMessage, expectedMessage);
        clickOnElement(By.xpath("//button[normalize-space()='Continue']")); //Click continue
        expectedMessage = "Welcome to our store"; // Verify the text “Welcome to our store”
        actualMessage = getTextFromElement(By.xpath("//h2[normalize-space()='Welcome to our store']"));
        Assert.assertEquals(expectedMessage, expectedMessage);
    }

    @After
    public void tearDown() {
        driver.close();
    }

}
