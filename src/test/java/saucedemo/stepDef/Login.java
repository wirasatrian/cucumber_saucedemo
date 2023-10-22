package saucedemo.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


public class Login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        // setup chome driver using web driver manager
        WebDriverManager.chromedriver().setup();
        // create object to setup option for chrome driver
        ChromeOptions opt = new ChromeOptions();
        // chrome driver not using a headless mode
        opt.setHeadless(false);

        // apply chrome driver setup to driver
        driver = new ChromeDriver(opt);
        // set max timeout to 60 second for web driver on waiting element
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        // maximize windows
        driver.manage().window().maximize();
        // show Login Page
        driver.get(baseUrl);
    }

    @When("I enter the username {string}")
    public void iEnterTheUsername(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("I enter a password {string}")
    public void iEnterAPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should see the products page")
    public void iShouldSeeTheProductsPage() {
        String TitlePage = driver.findElement(By.className("title")).getText();
        Assert.assertEquals(TitlePage, "Products");
        driver.close();
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        String ErrorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
//        String ErrorMessage = driver.findElement(By.cssSelector("h3")).getText();
        Assert.assertEquals(ErrorMessage, "Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }
}
