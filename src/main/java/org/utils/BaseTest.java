package org.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public Properties config;

    @BeforeMethod
    public void setUp() {
        try {
            config = loadConfig();
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");

            this.driver = new ChromeDriver(options);
            this.driver.get(config.getProperty("baseUrl"));
            this.driver.manage().window().maximize();

            // Crear directorios si no existen
            createOutputDirectories();

        } catch (Exception e) {
            System.out.println("Error al inicializar WebDriver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            // Tomar captura de pantalla si el test falló
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshot(result);
            }
            driver.quit();
        }
    }

    private void createOutputDirectories() {
        try {
            // Directorio principal test-output
            Path testOutputPath = Paths.get("test-output");
            if (!Files.exists(testOutputPath)) {
                Files.createDirectory(testOutputPath);
            }

            // Directorio para screenshots
            Path screenshotsPath = Paths.get("test-output/screenshots");
            if (!Files.exists(screenshotsPath)) {
                Files.createDirectory(screenshotsPath);
            }

            // Directorio para reportes adicionales
            Path reportsPath = Paths.get("test-output/reports");
            if (!Files.exists(reportsPath)) {
                Files.createDirectory(reportsPath);
            }
        } catch (IOException e) {
            System.err.println("Error al crear directorios: " + e.getMessage());
        }
    }

    private void takeScreenshot(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = testName + "_" + timeStamp + ".png";

            // Tomar screenshot
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Ruta de destino relativa
            String screenshotRelativePath = "screenshots/" + screenshotName;
            Path destinationPath = Paths.get("test-output", screenshotRelativePath);

            // Crear directorio si no existe
            Files.createDirectories(destinationPath.getParent());

            // Mover el archivo
            Files.move(screenshotFile.toPath(), destinationPath);

            // Obtener ruta relativa para el reporte
            String reportScreenshotPath = "../" + screenshotRelativePath;

            // Agregar al reporte de TestNG
            System.setProperty("org.uncommons.reportng.escape-output", "false");
            Reporter.log("<a href='" + reportScreenshotPath + "' target='_blank'>");
            Reporter.log("<img src='" + reportScreenshotPath + "' height='100' width='200'/>");
            Reporter.log("</a><br/>");

        } catch (Exception e) {
            System.err.println("Error al tomar screenshot: " + e.getMessage());
        }
    }

    public Properties loadConfig() {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}