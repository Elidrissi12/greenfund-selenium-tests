package com.greenfund.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model pour le tableau de bord admin (frontend Angular).
 */
public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Navigation latérale Angular (data-testid ajoutés dans admin-dashboard.component.html)
    private static final By NAV_DASHBOARD = By.cssSelector("[data-testid='nav-dashboard']");
    private static final By NAV_PROJECTS = By.cssSelector("[data-testid='nav-projects']");
    private static final By NAV_USERS = By.cssSelector("[data-testid='nav-users']");
    private static final By NAV_STATS = By.cssSelector("[data-testid='nav-stats']");
    private static final By NAV_TRANSACTIONS = By.cssSelector("[data-testid='nav-transactions']");

    // Contenu du dashboard
    private static final By DASHBOARD_TITLE = By.xpath("//h1[contains(text(), 'Tableau de bord') or contains(text(), 'Dashboard')]");
    private static final By KPI_CARDS = By.cssSelector(".kpi-card, .stat-card");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Vérifie si on est sur une page du dashboard admin.
     * On considère que le dashboard est "chargé" si la navigation admin est présente.
     */
    public boolean isAt() {
        try {
            // Présence de la navigation latérale = on est dans l'espace admin (et pas sur /login)
            wait.until(ExpectedConditions.presenceOfElementLocated(NAV_DASHBOARD));
            return !driver.getCurrentUrl().contains("/login");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clique sur le lien "Projets à valider" dans la navigation.
     */
    public void clickProjectsNav() {
        wait.until(ExpectedConditions.presenceOfElementLocated(NAV_PROJECTS));
        WebElement projectsLink = wait.until(ExpectedConditions.elementToBeClickable(NAV_PROJECTS));
        projectsLink.click();
        wait.until(ExpectedConditions.urlContains("/projects"));
    }

    /**
     * Clique sur le lien "Utilisateurs" dans la navigation.
     */
    public void clickUsersNav() {
        wait.until(ExpectedConditions.presenceOfElementLocated(NAV_USERS));
        WebElement usersLink = wait.until(ExpectedConditions.elementToBeClickable(NAV_USERS));
        usersLink.click();
        wait.until(ExpectedConditions.urlContains("/users"));
    }

    /**
     * Vérifie si les cartes KPI sont affichées
     */
    public boolean areKPICardsDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(KPI_CARDS));
            return driver.findElements(KPI_CARDS).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Récupère le titre de la page
     */
    public String getPageTitle() {
        try {
            WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(DASHBOARD_TITLE));
            return title.getText();
        } catch (Exception e) {
            return "";
        }
    }
}

