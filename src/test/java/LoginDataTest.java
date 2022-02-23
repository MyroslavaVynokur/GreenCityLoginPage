import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;


import java.time.Duration;

import static org.junit.Assert.*;

public class LoginDataTest {
    private WebDriver driver;

    final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/";

    @BeforeEach
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE_URL);
        driver.findElement(By.partialLinkText("Увійти")).click();

    }

    @Description("test verify id user can login with valid credentials")
    @ParameterizedTest
    @ArgumentsSource(ValidEmailPasswordArgumentsProvider.class)
    public void successfulLoginTest(String email, String password) throws InterruptedException {
        try {
            LoginData login = new LoginData(driver);
            login.inputEmail(email).inputPassword(password);

            WebDriverWait wait = new WebDriverWait(driver,30);
            //  wait.until(ExpectedConditions.elementToBeClickable("//button[@class = 'primary-global-button']"));
            //login.clickLoginButton();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='header_user-wrp']/li[1]/a")));
            Assertions.assertTrue(login.userNameIsDisplayed());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Description("test verify id user can NOT login with invalid Email")
    @ParameterizedTest
    @ArgumentsSource(IncorrectEmailArgumentsProvider.class)
    public void incorrectEmailLoginTest(String email, String password) {
        try {
            LoginData login = new LoginData(driver);

            login.inputEmail(email).inputPassword(password);

            WebDriverWait wait = new WebDriverWait(driver,30);
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class = 'primary-global-button']")));
            //login.clickLoginButton();

            String expectedErrorEmailMessage = "Перевірте, чи правильно вказано вашу адресу електронної пошти";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'email-err-msg']/app-error/div")));
            String actualErrorEmailMessage = login.getTextErorEmail();
            Assert.assertEquals(actualErrorEmailMessage, expectedErrorEmailMessage);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    @AfterEach
    public void AfterMethod() {
        driver.quit();
    }

}