import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static org.junit.Assert.*;

public class LoginDataTest {
    private WebDriver driver;

    final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/";

    @Before
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE_URL);
        driver.findElement(By.partialLinkText("Увійти")).click();

    }

    @Test
    public void logintest() {

        LoginData login = new LoginData(driver);
        login.inputEmail("mirka.vyno@gmail.com").inputPassword("Test-User123").clickLoginButton();
        String actual = driver.getCurrentUrl();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/profile/42";
        Assert.assertEquals(actual, expected);

    }


//    @After
//    public void AfterMethod() {
//        driver.quit();
//    }

}