package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {
    public String URL = "http://training.skillo-bg.com:4200/users/register";

    @FindBy(xpath = "//app-register//h4[text()='Sign up']")
    WebElement signUpHeader;

    @FindBy(name = "username")
    WebElement usernameField;

    @FindBy(xpath = "//input[@placeholder=\"email\"]")
    WebElement emailField;

    @FindBy(id = "defaultRegisterFormPassword")
    WebElement passwordField;
    @FindBy(id = "defaultRegisterPhonePassword")
    WebElement confirmPasswordField;

    @FindBy(id = "sign-in-button")
    WebElement signInButton;

    @FindBy(xpath = "//input/following-sibling::*")
    WebElement invalidEmailFeedback;

    @FindBy(className = "toast-message")
    WebElement toastMessageFailedReg;

    String toastMessageTextFailedReg;


    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyUrl() {
        verifyUrl(URL);
    }

    public String getSignUpHeaderText() {
        return getElementText(signUpHeader);
    }

    public void enterUsername(String username) {
        enterText(usernameField, username);
    }

    public void enterInvalidEmail(String invalidEmail) {
        enterText(emailField, invalidEmail);
    }

    public String getInvalidEmailFeedbackText() {
        return getElementText(invalidEmailFeedback);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        enterText(confirmPasswordField, confirmPassword);
    }

    public void clickSignInButton() {
        clickElement(signInButton);
    }

    public String getRegistrationFailedToastMessage() {
        smallWait.until(ExpectedConditions.visibilityOf(toastMessageFailedReg));
        return toastMessageTextFailedReg = toastMessageFailedReg.getText().trim();

    }


}