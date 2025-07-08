package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {
    protected static WebDriver driver;

    //CONSTRUCTOR DE LA CLASE
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    //OBTENER ELEMENTO
    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    //OBTENER LISTA DE ELEMENTOS
    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    public void sleep(int i) throws InterruptedException {
        Thread.sleep(1000 * 1);
    }
    public void log(String message) {
        System.out.println(message);
    }

}
