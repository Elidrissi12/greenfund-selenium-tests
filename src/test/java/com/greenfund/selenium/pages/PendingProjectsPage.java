package com.greenfund.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object Model pour la page des projets en attente.
 */
public class PendingProjectsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Sélecteurs
    private static final By PAGE_TITLE = By.xpath("//h1[contains(text(), 'Projets en attente')]");
    private static final By PROJECT_CARDS = By.cssSelector("[data-testid^='project-card-']");
    private static final By EMPTY_STATE = By.cssSelector(".empty-state");

    public PendingProjectsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Vérifie si on est sur la page des projets en attente
     */
    public boolean isAt() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(PAGE_TITLE));
            return driver.getCurrentUrl().contains("/projects");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Récupère le nombre de projets en attente
     */
    public int getProjectsCount() {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PROJECT_CARDS));
            return driver.findElements(PROJECT_CARDS).size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Vérifie si l'état vide est affiché (aucun projet)
     */
    public boolean isEmptyStateDisplayed() {
        try {
            return driver.findElement(EMPTY_STATE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clique sur le bouton "Approuver" pour un projet donné
     */
    public void approveProject(Long projectId) {
        By approveButton = By.cssSelector("[data-testid='approve-project-" + projectId + "']");
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(approveButton));
        btn.click();
        // Attendre que le projet disparaisse de la liste ou que la page se mette à jour
        wait.until(ExpectedConditions.invisibilityOfElementLocated(approveButton));
    }

    /**
     * Clique sur le bouton "Rejeter" pour un projet donné
     */
    public void rejectProject(Long projectId) {
        By rejectButton = By.cssSelector("[data-testid='reject-project-" + projectId + "']");
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(rejectButton));
        btn.click();
        // Attendre que le projet disparaisse de la liste ou que la page se mette à jour
        wait.until(ExpectedConditions.invisibilityOfElementLocated(rejectButton));
    }

    /**
     * Récupère tous les IDs des projets affichés
     */
    public List<Long> getProjectIds() {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PROJECT_CARDS));
            return driver.findElements(PROJECT_CARDS).stream()
                    .map(element -> {
                        String testId = element.getAttribute("data-testid");
                        if (testId != null && testId.startsWith("project-card-")) {
                            return Long.parseLong(testId.replace("project-card-", ""));
                        }
                        return null;
                    })
                    .filter(id -> id != null)
                    .toList();
        } catch (Exception e) {
            return List.of();
        }
    }
}

