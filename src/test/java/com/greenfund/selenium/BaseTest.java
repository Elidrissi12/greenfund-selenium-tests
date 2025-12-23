package com.greenfund.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * Classe de base pour tous les tests Selenium.
 * Configure le WebDriver, gère les timeouts et les screenshots en cas d'échec.
 */
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    // URL de base du frontend Angular (admin)
    // Adapter si vous changez le port dans angular.json (par défaut : 4200)
    protected static final String BASE_URL = "http://localhost:4200";
    protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    protected static final Duration IMPLICIT_WAIT = Duration.ofSeconds(5);

    @BeforeEach
    void setUp() {
        // Configuration de WebDriverManager pour télécharger automatiquement ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Configuration de Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        
        // Optionnel : mode headless (décommenter si nécessaire)
        // options.addArguments("--headless");

        // Initialisation du driver
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
        driver.manage().window().maximize();

        // Initialisation de WebDriverWait
        wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Prend un screenshot et le sauvegarde dans le dossier screenshots/
     * Utilisé en cas d'échec de test.
     */
    protected void takeScreenshot(String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
            
            // Créer le dossier screenshots s'il n'existe pas
            Path screenshotsDir = Paths.get("screenshots");
            if (!Files.exists(screenshotsDir)) {
                Files.createDirectories(screenshotsDir);
            }
            
            // Sauvegarder le screenshot
            Path screenshotPath = screenshotsDir.resolve(testName + "_" + System.currentTimeMillis() + ".png");
            Files.write(screenshotPath, screenshotBytes);
            
            System.out.println("Screenshot sauvegardé : " + screenshotPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du screenshot : " + e.getMessage());
        }
    }

    /**
     * Navigue vers une URL
     */
    protected void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Navigue vers la page de base
     */
    protected void navigateToBase() {
        navigateTo(BASE_URL);
    }
}

