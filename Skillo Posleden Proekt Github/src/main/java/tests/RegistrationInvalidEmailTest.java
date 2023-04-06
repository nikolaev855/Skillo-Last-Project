package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.*;


public class RegistrationInvalidEmailTest extends BaseTest {
    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][]{
                {"shano20", "shano202@", "shanoItaliano", "shanoItaliano"},
        };

    }

    @Test(dataProvider = "userData")
    public void emailWithoutDomain(String username, String invalidEmail, String password, String confirmPassword) {

        System.out.println("1.Navigate to Login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.verifyUrl();

        System.out.println("2.Click on Register");
        loginPage.openRegisterPage();


        System.out.println("3.Validate that url has changed");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.verifyUrl();

        System.out.println("4.Validate that the Sign up header is shown");
        String signUpHeaderText = registrationPage.getSignUpHeaderText();
        Assert.assertEquals(signUpHeaderText, "Sign up", "Incorrect Sign Up header text!");


        System.out.println("5.Enter valid username");
        registrationPage.enterUsername(username);


        System.out.println("6.Enter invalid email (without domain)");
        registrationPage.enterInvalidEmail(invalidEmail);

        System.out.println("7.Check presented string \"Email invalid!\" on email field");
        Assert.assertEquals(registrationPage.getInvalidEmailFeedbackText(),"Email invalid!","The string is not presented!");

        System.out.println("8.Enter valid password");
        registrationPage.enterPassword(password);

        System.out.println("9.Confirm password");
        registrationPage.enterConfirmPassword(confirmPassword);

        System.out.println("10.Click on Sign in button");
        registrationPage.clickSignInButton();

        System.out.println("11.Validate that appear popup message -  Successful register!");
        Assert.assertEquals(registrationPage.getRegistrationFailedToastMessage(), "Successful register!", "Wrong toast message appear!");


    }


}