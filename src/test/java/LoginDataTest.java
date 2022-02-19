import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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



    @ParameterizedTest
    @ArgumentsSource(EmailPasswordArgumentsProvider.class)
    public void logintest(String input) {
        LoginData login = new LoginData(driver);
        login.inputEmail(input).inputPassword("Test-User123").clickLoginButton();
        String actual = driver.getCurrentUrl();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/profile/42";
        Assert.assertEquals(actual, expected);
    }

//    @After
//    public void AfterMethod() {
//        driver.quit();
//    }

}