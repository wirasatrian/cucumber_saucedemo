package saucedemo.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class loginTDD {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("On the SauceDemo login page")
    public void onTheSauceDemoLoginPage() {
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

    @When("I enter (.*) as username$")
    public void iEnterUsernameAsUsername(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("I enter (.*) as password$")
    public void iEnterPasswordAsPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("I clicked the login button")
    public void iClickedTheLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I verify login (.*) as status$")
    public void iVerifyLoginStatusAsStatus(String status) {
        if (status.equals("success")) {
            String TitlePage = driver.findElement(By.className("title")).getText();
            Assert.assertEquals(TitlePage, "Products");
        } else {
//            String ErrorMessage = driver.findElement(By.cssSelector("h3")).getText();
            String ErrorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
            Assert.assertEquals(ErrorMessage, "Epic sadface: Username and passwo" +
                    "rd do not match any user in this service");
        }
        driver.close();
    }

}
