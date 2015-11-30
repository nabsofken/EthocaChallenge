import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**General methods that are for Web products
 * @author nibrahim
 *
 */
public class WebUtility {

	public static WebDriver webdriver = null;
    private static final int MAXTIME = 30;

    public static void webSetUp(final String url){
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/chromedriver");
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        webdriver = new ChromeDriver(options);
        webdriver.get(url);
    }

	/**
	 * This method will click an element on the page.
	 * @param byLocator - The locator strategy used to find the desired element
	 * @return - Return the element if found, null otherwise
	 */
	public static boolean clickWebElement(By byLocator) {
		WebElement el = getElement(byLocator);
        try {
            el.click();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
		return true;
	}

    /**
     * Get an element based on the given locator info
     * @param byLocator - The locator strategy
     * @return - The WebElement found, null otherwise
     */
    public static WebElement getElement (By byLocator){
        final WebDriverWait wait = new WebDriverWait(webdriver, MAXTIME);
        WebElement el = null;
        try {
            el = wait.until(ExpectedConditions
                    .elementToBeClickable(byLocator));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return el;
    }

    /**
     * Select an option based on the visible text from a dropdown menu
     * @param byLocator - The locator strategy used to find the Select element
     * @param text - The name of the option as it appears in the list
     * @return - Return true if selection is set
     */
    public static boolean setSelectItem(final By byLocator, final String text){
        String selectedOption;
        try{
            Select select = new Select(webdriver.findElement(byLocator));
            select.selectByVisibleText(text);
            selectedOption = select.getFirstSelectedOption().getText();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return text.equals(selectedOption);

    }

    /**
     * Get the text content of an element
     * @param byLocator - the locator strategy
     * @return - Return the text of the element if found, empty string otherwise
     */
    public static String getText (final By byLocator){
        final WebElement element = getElement(byLocator);
        return element != null ? element.getText() : "";
    }

    public static boolean setText (final By byLocator, final String text){
        final WebElement element = getElement(byLocator);
        if (element == null) return false;
        try{
            element.sendKeys(text);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    public static boolean hoverOverElement(By bylocator){
        Actions action = new Actions(webdriver);
        WebElement el = getElement(bylocator);
        if (el == null)return false;
        action.moveToElement(el).build().perform();
        return true;
    }

    public static boolean isElementPresent(final By byLocator) {
            final WebDriverWait wait = new WebDriverWait(webdriver, MAXTIME);
            WebElement el = null;
            try {
                el = wait.until(ExpectedConditions
                        .presenceOfElementLocated(byLocator));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return el != null;
    }

}
