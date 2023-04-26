package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        // Open browser
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        // Close all windows
        closeBrowser();
    }

    /**
     * This method select menu on top menu
     * @param menu
     */
    public void selectMenu(String menu) { //Parameterised method with string type variable
        //Clicking on the menu and passing parameter
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='" + menu + "']"));
    }

    @Test
    public void verifyPageNavigation() {
        String menuName = "Computers";
        String expectedMessage = "Computers"; //Expected message

        selectMenu(menuName); //Selecting  Computers tab on top menu
        String actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Computers']")); //Getting actual message
        Assert.assertEquals(expectedMessage, actualMessage); // verifying the computers page

    }

}
