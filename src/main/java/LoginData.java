import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginData {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//a[@class = 'close-modal-window']/img")
    private WebElement close;

    @FindBy(how = How.XPATH, using = "//input[@id='email']")
    private WebElement emailField;
    @FindBy(how = How.XPATH, using = "//input[@id = 'password']")
    private WebElement passwordField;
    @FindBy(how = How.XPATH, using = "//button[@class = 'primary-global-button']")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//app-submit-button/button[@class = 'ubs-primary-global-button']")
    private WebElement signInGoogle;
    @FindBy(how = How.XPATH, using = "//span[@class = 'show-hide-btn']")
    private WebElement showHidePassword;
    @FindBy(how = How.XPATH, using = "//div[@class = 'forgot-wrapper']/a[@class = 'ubs-forgot-password']")
    private WebElement forgotPassword;
    @FindBy(how = How.XPATH, using = "//div[@id = 'email-err-msg']/app-error/div")
    private WebElement errorEmail;
    @FindBy(how = How.XPATH, using = "//div[@id = 'pass-err-msg']/app-error/div")
    private WebElement errorPassword;
    @FindBy(how = How.XPATH, using = "//div[@class = 'missing-account']/p/a[@class = 'ubs-sign-up-link']")
    private WebElement signUpLink;

    public LoginData(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //("click on 'close' button")
    public LoginData clickCloseIcon() {
        close.click();
        return this;
    }

    //input email | value = {emailInput}
    public LoginData inputEmail(String emailInput) {

        emailField.click();
        emailField.clear();
        emailField.sendKeys(emailInput, Keys.ENTER);
        return this;
    }

    //input password | value = {passwordInput}
    public LoginData inputPassword(String passwordInput) {
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(passwordInput, Keys.ENTER);
        return this;
    }

    // click on login button
    public LoginData clickLoginButton () {
        loginButton.click();
        return new LoginData(driver);
    }

    //click on 'show-hide password' button
    public LoginData clickShowHidePassword() {
        showHidePassword.click();
        return this;
    }

}