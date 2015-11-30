import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

/**This contains all methods related to the home page
 * Created by nibrahim on 2015-11-29.
 */
public class Checkout extends WebUtility{

    /**
     * Get the price of the product based on the product name
     * @param product - The name of the product
     * @return - Return the price of the product as a String value
     */
    public static String getProductPrice(final String product){
        return getText(By.xpath("//a[text()='"+product+"']/../..//span[@class='pricedisplay']"));
    }

    /**
     * Get the quantity of the product based on the given name
     * @param product - The name of the product
     * @return - Return the quantity or empty string if product not found
     */
    public static String getProductQuantity(final String product){
        WebElement quantity = getElement(By.xpath("//a[text()='"+product+"']/../..//input[@name='quantity']"));
        return quantity != null ? quantity.getAttribute("value") : "";
    }

    public static boolean proceed(){
        return clickWebElement(By.xpath("//span[text()='Continue']"));
    }

    public static boolean calculateShippingCost(final String country, final String province){
            return setSelectItem(By.id("current_country"), country) &&
            setSelectItem(By.xpath("//select[@title='shippingregion']"), province);
    }

    /**
     * The billing or shipping details to enter
     * @param addressType - Specify the type of address i.e 'billing' or 'shipping'
     * @return - Return true if all fields have been filled, false otherwise
     */
    public static boolean enterDetails(final String addressType) {

        HashMap<String, String> details = new HashMap<>();
        details.put(addressType + "firstname", "Nabeel");
        details.put(addressType + "lastname", "Ibrahim");
        details.put(addressType + "city", "Toronto");
        details.put(addressType + "state", "Ontario");
        details.put(addressType + "postcode", "N2L 3E3");
        if (addressType.equals("billing")){
            details.put("billingemail", "hello@gmail.com");
            details.put("billingphone", "1231231234");

        }
        if (!setText(By.xpath("//textarea[@title='" + addressType + "address']"), "1 Hello Street"))
            return false;
        for (Map.Entry<String, String> entry : details.entrySet()) {
            if (!setText(By.xpath("//input[@title='" + entry.getKey() + "']"), entry.getValue()))
                return false;
        }
        return setSelectItem(By.xpath("//select[@title='"+addressType + "country']"), "Canada");
    }

    public static boolean purchase(){
        return clickWebElement(By.xpath("//input[@value='Purchase']")) &&
                isElementPresent(By.xpath("//p[text()='Thank you, your purchase is pending. You will be sent an email once the order clears.']"));
    }


}
