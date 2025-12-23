package com.greenfund.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model pour la page de connexion (frontend Angular admin).
 */
public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Sélecteurs Angular basés sur les data-testid réels dans login.component.html
    // Voir: data-testid="login-email", "login-password", "login-error", "login-submit"
    private static final By EMAIL_INPUT = By.cssSelector("[data-testid='login-email']");
    private static final By PASSWORD_INPUT = By.cssSelector("[data-testid='login-password']");
    private static final By SUBMIT_BUTTON = By.cssSelector("[data-testid='login-submit']");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-testid='login-error']");

    // Fallback sur les IDs natifs
    private static final By EMAIL_INPUT_FALLBACK = By.id("email");
    private static final By PASSWORD_INPUT_FALLBACK = By.id("password");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Ouvre la page de connexion Angular.
     */
    public void open() {
        driver.get("http://localhost:4200/login");
        waitForPageLoad();
    }

    /**
     * Vérifie si on est sur la page de connexion.
     */
    public boolean isAt() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_INPUT));
            return driver.getCurrentUrl().contains("/login")
                    && driver.findElement(EMAIL_INPUT).isDisplayed();
        } catch (Exception e) {
            try {
                return driver.getCurrentUrl().contains("/login")
                        && driver.findElement(EMAIL_INPUT_FALLBACK).isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    /**
     * Remplit le champ email.
     */
    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_INPUT));
        emailField.clear();
        emailField.sendKeys(email);
    }

    /**
     * Remplit le champ mot de passe.
     */
    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(PASSWORD_INPUT));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Clique sur le bouton de connexion.
     */
    public void clickSubmit() {
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON));
        submitBtn.click();
    }

    /**
     * Effectue une connexion complète.
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSubmit();
    }

    /**
     * Vérifie si un message d'erreur est affiché.
     */
    public boolean hasErrorMessage() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(ERROR_MESSAGE));
            return driver.findElement(ERROR_MESSAGE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Récupère le message d'erreur.
     */
    public String getErrorMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(ERROR_MESSAGE));
            return errorElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Attend que la page soit chargée.
     */
    private void waitForPageLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_INPUT));
    }
}
