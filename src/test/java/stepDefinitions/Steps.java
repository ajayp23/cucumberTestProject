package stepDefinitions;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import utility.Utility;

public class Steps {
    WebDriver driver;

    @Given("^user is on Home Page$")
    public void user_is_on_Home_Page(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.shop.demoqa.com");
//        System.out.println("Navigated to http://www.shop.demoqa.com");
    }

    @When("^he search for 'dress'$")
    public void he_search_for()  {
        driver.navigate().to("http://shop.demoqa.com/?s=" + "dress" + "&post_type=product");
    }

    @When("^choose to buy the first item$")
    public void choose_to_buy_the_first_item() {
        List<WebElement> items = driver.findElements(By.cssSelector(".noo-product-inner"));
        items.get(0).click();

        WebElement addToCart = driver.findElement(By.cssSelector("button.single_add_to_cart_button"));
        addToCart.click();
    }

    @When("^moves to checkout from mini cart$")
    public void moves_to_checkout_from_mini_cart(){
        WebElement cart = driver.findElement(By.cssSelector(".cart-button"));
        cart.click();

        WebElement continueToCheckout = driver.findElement(By.cssSelector(".checkout-button.alt"));
        continueToCheckout.click();
    }

    @When("^enter personal details on checkout page$")
    public void enter_personal_details_on_checkout_page() throws InterruptedException {
        Thread.sleep(5000);
        WebElement firstName = driver.findElement(By.cssSelector("#billing_first_name"));
        firstName.sendKeys("Lakshay");

        WebElement lastName = driver.findElement(By.cssSelector("#billing_last_name"));
        lastName.sendKeys("Sharma");

        WebElement emailAddress = driver.findElement(By.cssSelector("#billing_email"));
        emailAddress.sendKeys("test@gmail.com");

        WebElement phone = driver.findElement(By.cssSelector("#billing_phone"));
        phone.sendKeys("07438862327");

        WebElement countryDropDown = driver.findElement(By.cssSelector("#billing_country_field .select2-arrow"));
        countryDropDown.click();
        Thread.sleep(2000);

        List<WebElement> countryList = driver.findElements(By.cssSelector("#select2-drop ul li"));
        for(WebElement country : countryList){
            if(country.getText().equals("India")) {
                country.click();
                Thread.sleep(3000);
                break;
            }
        }

        WebElement city = driver.findElement(By.cssSelector("#billing_city"));
        city.sendKeys("Delhi");

        WebElement address = driver.findElement(By.cssSelector("#billing_address_1"));
        address.sendKeys("Shalimar Bagh");

        WebElement postcode = driver.findElement(By.cssSelector("#billing_postcode"));
        postcode.sendKeys("110088");

        WebElement countyDropDown = driver.findElement(By.cssSelector("#billing_state_field .select2-arrow"));
        countyDropDown.click();
        Thread.sleep(2000);

        List<WebElement> countyList = driver.findElements(By.cssSelector("#select2-drop ul li"));
        for(WebElement county : countyList){
            if(county.getText().equals("Delhi")) {
                county.click();
                Thread.sleep(3000);
                break;
            }
        }
    }

    @When("^select same delivery address$")
    public void select_same_delivery_address() throws InterruptedException{
        WebElement shipToDifferetAddress = driver.findElement(By.cssSelector("#ship-to-different-address-checkbox"));
        shipToDifferetAddress.click();
        Thread.sleep(3000);
    }

    @When("^select payment method as 'check' payment$")
    public void select_payment_method_as_payment(){
        List<WebElement> paymentMethod = driver.findElements(By.cssSelector("ul.wc_payment_methods li"));
        paymentMethod.get(0).click();
    }

    @When("^place the order$")
    public void place_the_order() {
        WebElement acceptTC = driver.findElement(By.cssSelector("#terms.input-checkbox"));
        acceptTC.click();

        WebElement placeOrder = driver.findElement(By.cssSelector("#place_order"));
        placeOrder.submit();
    }

    @Given("^I send below data in data table$")
    public void i_send_below_data_in_data_table(DataTable dataTable) throws Throwable {
        List<List<String>> ls = dataTable.raw();
        Utility.dataList = ls;
    }

    @And("^I populate the data in console$")
    public void i_populate_the_data_in_console() throws Throwable {
        for (List<String> l: Utility.dataList) {
            System.out.print("| ");
            for (String str: l) {
                System.out.print(str + " | ");
            }
            System.out.println();
        }
    }

    @Given("^I make connection to the database$")
    public Statement createMyCon( ) throws Exception {
        return Utility.stmt = Utility.createCon();
    }

    @When("^I execute query to fetch details$")
    public ResultSet executeStmt() throws Exception {
        return Utility.rs = Utility.result(Utility.stmt);
    }

    @Then("^I print the details$")
    public void printData() throws Exception {
        while(Utility.rs.next()) {
            System.out.println(Utility.rs.getInt(1)+"  "+Utility.rs.getString(2)+"  "+Utility.rs.getString(3));
        }
    }


}