package com.greenfund.selenium.tests;

import com.greenfund.selenium.BaseTest;
import com.greenfund.selenium.pages.DashboardPage;
import com.greenfund.selenium.pages.LoginPage;
import com.greenfund.selenium.pages.PendingProjectsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests de validation de projets (scénario métier critique) sur le frontend Angular admin.
 */
@DisplayName("Tests de validation de projets")
public class ValidateProjectTest extends BaseTest {

    private static final String ADMIN_EMAIL = "admin@greenfund.com";
    private static final String ADMIN_PASSWORD = "admin123";

    @Test
    @DisplayName("TC-007: Accès à la page des projets en attente après connexion")
    void testAccessPendingProjectsPage() {
        // Given - Utilisateur connecté
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(ADMIN_EMAIL, ADMIN_PASSWORD);

        // When - Navigation vers la page des projets
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickProjectsNav();

        // Then - La page des projets devrait être affichée
        PendingProjectsPage pendingProjectsPage = new PendingProjectsPage(driver);
        assertThat(pendingProjectsPage.isAt())
                .as("La page des projets en attente devrait être affichée")
                .isTrue();

        assertThat(driver.getCurrentUrl())
                .as("L'URL devrait contenir /projects")
                .contains("/projects");
    }

    @Test
    @DisplayName("TC-008: Affichage de la liste des projets en attente")
    void testDisplayPendingProjects() {
        // Given - Utilisateur connecté et sur la page des projets
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(ADMIN_EMAIL, ADMIN_PASSWORD);

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickProjectsNav();

        // When - La page se charge
        PendingProjectsPage pendingProjectsPage = new PendingProjectsPage(driver);

        // Then - Soit des projets sont affichés, soit l'état vide
        boolean hasProjects = pendingProjectsPage.getProjectsCount() > 0;
        boolean isEmpty = pendingProjectsPage.isEmptyStateDisplayed();

        assertThat(hasProjects || isEmpty)
                .as("La page devrait afficher soit des projets, soit l'état vide")
                .isTrue();
    }

    @Test
    @DisplayName("TC-009: Navigation entre les pages du dashboard")
    void testNavigationBetweenPages() {
        // Given - Utilisateur connecté
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(ADMIN_EMAIL, ADMIN_PASSWORD);

        DashboardPage dashboardPage = new DashboardPage(driver);

        // When - Navigation vers différentes pages depuis la même session admin
        dashboardPage.clickProjectsNav();
        assertThat(driver.getCurrentUrl()).contains("/projects");

        // Navigation vers la page utilisateurs via la navigation latérale
        dashboardPage.clickUsersNav();

        // Then - La page des utilisateurs devrait être accessible
        assertThat(driver.getCurrentUrl())
                .as("L'URL devrait contenir /users")
                .contains("/users");
    }
}

