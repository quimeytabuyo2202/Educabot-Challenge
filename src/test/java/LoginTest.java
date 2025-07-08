import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.utils.BaseTest;

public class LoginTest extends BaseTest {
    public LoginPage loginPage;

    @Test
    public void loginValido_01() {
        loginPage = new LoginPage(this.driver);
        loginPage.ingresarUsuario(config.getProperty("username"));
        loginPage.ingresarPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("inventory"));
    }
    @Test
    @Parameters({"username","password"})
    public void loginFallido_02(String username, String password) {
        loginPage = new LoginPage(this.driver);
        loginPage.ingresarUsuario(username);
        loginPage.ingresarPassword(password);
        loginPage.clickLoginButton();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("inventory"));
    }
}
