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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public void successfulLoginTest(String email, String password) {
        try {
            LoginData login = new LoginData(driver);
            login.inputEmail(email).inputPassword(password).clickLoginButton();
//            String actual = driver.getCurrentUrl();
//            String expected = "https://ita-social-projects.github.io/GreenCityClient/#/profile/42";
//            Assert.assertEquals(actual, expected);
            Assertions.assertTrue(login.userNameIsDisplayed());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

//    @Description("test verify id user can NOT login with invalid Email")
//    @ParameterizedTest
//    @ArgumentsSource(IncorrectEmailArgumentsProvider.class)
//    public void incorrectEmailLoginTest(String email, String password) {
//        try {
//            LoginData login = new LoginData(driver);
//
//            String expectedErrorEmailMessage = "Перевірте, чи правильно вказано вашу адресу електронної пошти";
//            String actualErrorEmailMessage = login.getTextErorEmail();
//            login.inputEmail(email).inputPassword(password).clickLoginButton();
//            SoftAssert softAssert = new SoftAssert();
//            softAssert.assertEquals(actualErrorEmailMessage, expectedErrorEmailMessage,
//                    "verify if user can't login with incorrect Email, check error message");
//
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(IncorrectPasswordArgumentsProvider.class)
//    public void incorrectPasswordLoginTest(String email, String password) {
//        try {
//            LoginData login = new LoginData(driver);
//            login.inputEmail(email).inputPassword(password).clickLoginButton();
//            String expectedErrorPasswordMessage = "Пароль повинен містити принаймі 8 символів";
//            String actualErrorPasswordMessage = login.getTextErorPassword();
//            SoftAssert softAssert = new SoftAssert();
//            softAssert.assertEquals(actualErrorPasswordMessage, expectedErrorPasswordMessage,
//                    "verify if user can't login with incorrect Password, check error message");
//
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }


    @AfterEach
    public void AfterMethod() {
        driver.quit();
    }

}