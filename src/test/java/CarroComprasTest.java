import org.pages.CarroComprasPage;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.utils.BaseTest;

public class CarroComprasTest extends BaseTest {
    public LoginPage loginPage;
    public CarroComprasPage compraPage;

    @Test
    @Parameters({"cantidadItems","firstName","lastName","postalCode"})
    public void comprarUnItem_03(int cantidadItems, String firstName, String lastName, String postalCode){
        loginPage = new LoginPage(this.driver);
        loginPage.ingresarUsuario(config.getProperty("username"));
        loginPage.ingresarPassword(config.getProperty("password"));
        loginPage.clickLoginButton();

        compraPage = new CarroComprasPage(this.driver);
        compraPage.agregarItemsAlCarroDeCompras(cantidadItems);
        compraPage.accederACarrito();
        compraPage.clickEnCheckoutButton();

        compraPage.agregarInformacionDeUsuario(firstName, lastName, postalCode);
        compraPage.clickContinueButton();
        compraPage.clickFinishButton();

        Assert.assertTrue(compraPage.obtenerMensajeCompraRealizada().contains("Your order has been dispatched"));
    }
    @AfterMethod
    public void tearDown () {
        this.driver.close();
    }
}
