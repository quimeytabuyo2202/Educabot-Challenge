package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage  extends BasePage{

    By usernameField = By.xpath("//*[@*='Username']");
    By passwordField = By.xpath("//*[@*='Password']");
    By loginButton = By.xpath("//*[@id ='login-button']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void ingresarUsuario (String username) {
        this.find(usernameField).sendKeys(username);
    }
    public void ingresarPassword(String password) {
        this.find(passwordField).sendKeys(password);
    }
    public void clickLoginButton() {
        this.find(loginButton).click();
    }
}