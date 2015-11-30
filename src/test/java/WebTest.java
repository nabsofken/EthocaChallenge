import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by nibrahim on 2015-11-29.
 */
public class WebTest extends WebUtility{

    private static final String URL = "http://store.demoqa.com/";
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        webSetUp(URL);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        webdriver.quit();
    }

    @Test
    public void buyItem(){
        Assert.assertTrue(HomePage.selectNavBarItem("Product Category", "Accessories"));
        Assert.assertTrue(ProductCategory.addToCart("Magic Mouse"));
        Assert.assertTrue(Checkout.proceed());
        //Assert.assertTrue(Checkout.calculateShippingCost("Canada", "Ontario"));
        Assert.assertTrue(Checkout.enterDetails("billing"));
        Assert.assertTrue(Checkout.enterDetails("shipping"));
        Assert.assertTrue(Checkout.purchase());
    }
}
