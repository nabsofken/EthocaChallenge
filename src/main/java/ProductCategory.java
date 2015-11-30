import org.openqa.selenium.By;

/**This contains all methods related to the home page
 * Created by nibrahim on 2015-11-29.
 */
public class ProductCategory extends WebUtility{

    public static boolean addToCart(final String product){
        //First tap the nav bar category
        if (!isElementPresent(By.linkText(product))){
            System.out.println("AddToCart: "+product+" not available");
            return false;
        }
        String productcol = "//a[text()='"+product+"']/../..";
        //Get the price to verify after adding to cart
        String price = getText(By.xpath(productcol+"//span[contains(@class,'currentprice')]"));

        if (!clickWebElement(By.xpath(productcol+"//input[@value='Add To Cart']"))){
            System.out.println("Could not click Add to cart");
            return false;
        }

        if (!isElementPresent(By.xpath("//span[text()='You just added \""+product+"\" to your cart.']"))){
            System.out.println("AddToCart: product not added to cart");
            return false;
        }
        clickWebElement(By.className("go_to_checkout"));
        //get price on checkout page
        return price.equals(Checkout.getProductPrice(product)) && Checkout.getProductQuantity(product).equals("1");
    }


}
