import org.openqa.selenium.By;

/**This contains all methods related to the home page
 * Created by nibrahim on 2015-11-29.
 */
public class HomePage extends WebUtility{

    public static boolean selectNavBarItem(final String category, final String item){
        //First tap the nav bar category
        if (!hoverOverElement(By.linkText(category))){
            System.out.println("Could not hover over the nav bar category");
            return false;
        }
        if (!clickWebElement(By.linkText(item))){
            System.out.println("Could not click item called: "+item);
            return false;
        }
        String title = webdriver.getTitle();
        return title.substring(0, title.indexOf("|")-1).equals(item);
    }


}
