package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Header;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class LoginTest extends BaseTest {

    @DataProvider(name = "users")
    public Object[][] getUsers() {
        return new Object[][]{
                {"shano11", "maimuna", "shano11"},
                {"shano11@abv.bg", "maimuna", "shano11"}

        };

    }

    @Test(dataProvider = "users")
    public void loginTest(String usernameOrEmail, String password, String username) {
        System.out.println("1. Open home page");
        HomePage homePage = new HomePage(driver);
        homePage.navigate();

        System.out.println("2. Open login page");
        Header header = new Header(driver);
        header.openLogin();

        System.out.println("3. Check the URL of Login Page is correct");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();

        System.out.println("4. Check if Sign in header is displayed");
        String headerText = loginPage.getSignInHeaderText();
        Assert.assertEquals(headerText, "Sign in", "Incorrect Sign In header text");

        System.out.println("5. Enter username");
        loginPage.enterUserName(usernameOrEmail);


        System.out.println("6. Enter password");
        loginPage.enterPassword(password);

        System.out.println("7. Click on Sign in button");
        loginPage.clickSignInButton();

        System.out.println("8. Verify it loads homepage URL");
        homePage.verifyUrl();

        System.out.println("9. Navigate to Profile page");
        header.openProfilePage();

        System.out.println("10. Check if it opens Profile page URL");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyUrl();


        System.out.println("11. Check if it opens correct user profile");
        String profileUserName = profilePage.getProfileUserName();
        Assert.assertEquals(profileUserName, username, "Incorrect username");

    }

    @AfterMethod
    public void closeDriver() {
        if (driver != null) {
            driver.close();
        }
    }
}