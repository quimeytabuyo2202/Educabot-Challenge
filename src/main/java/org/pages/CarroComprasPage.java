package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarroComprasPage extends BasePage{

    By anadirItemsButton = By.xpath("//*[contains(@id, 'add-to-cart')]");
    By productosEnCarritoButton = By.xpath("//*[@id= 'shopping_cart_container']");
    By checkoutButton = By.xpath("//*[@id= 'checkout']");
    By firstNameInput = By.xpath("//*[@id='first-name']");
    By lastNameInput = By.xpath("//*[@id='last-name']");
    By postalCodeInput = By.xpath("//*[@id= 'postal-code']");
    By continueButton = By.xpath("//*[@id= 'continue']");
    By finishButton = By.xpath("//*[@id= 'finish']");
    By mensajeCompraRealizada = By.xpath("//*[contains(text(), 'Thank you for your order')]/following-sibling::div");


    public CarroComprasPage(WebDriver driver) {
        super(driver);
    }

    public void agregarItemsAlCarroDeCompras(int cantidadItems) {
        for (int i = 0 ; i < cantidadItems ; i++) {
            this.findElements(anadirItemsButton).get(cantidadItems).click();
        }
    }
    public void accederACarrito() {
        this.find(productosEnCarritoButton).click();
    }
    public void clickEnCheckoutButton() {
        this.find(checkoutButton).click();
    }
    public void agregarInformacionDeUsuario(String firstName, String lastName, String postalCode) {
        this.find(firstNameInput).sendKeys(firstName);
        this.find(lastNameInput).sendKeys(lastName);
        this.find(postalCodeInput).sendKeys(postalCode);
    }
    public void clickContinueButton() {
        this.find(continueButton).click();

    }
    public void clickFinishButton() {
        this.find(finishButton).click();

    }
    public String obtenerMensajeCompraRealizada() {
        return this.find(mensajeCompraRealizada).getText();
    }

}