package com.greenfund.selenium.tests;

import com.greenfund.selenium.BaseTest;
import com.greenfund.selenium.pages.DashboardPage;
import com.greenfund.selenium.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests de la fonctionnalité de connexion.
 */
@DisplayName("Tests de connexion")
public class LoginTest extends BaseTest {

    // Identifiants de test (créés par DataInitializer)
    private static final String VALID_EMAIL = "admin@greenfund.com";
    private static final String VALID_PASSWORD = "admin123";
    private static final String INVALID_EMAIL = "invalid@test.com";
    private static final String INVALID_PASSWORD = "wrongpassword";

    @Test
    @DisplayName("TC-001: Connexion réussie avec des identifiants valides")
    void testLoginSuccess() {
        // Given
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        // When
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        // Then
        DashboardPage dashboardPage = new DashboardPage(driver);
        assertThat(dashboardPage.isAt())
                .as("L'utilisateur devrait être redirigé vers le tableau de bord")
                .isTrue();
        
        assertThat(driver.getCurrentUrl())
                .as("L'URL devrait être celle du dashboard")
                .doesNotContain("/login");
    }

    @Test
    @DisplayName("TC-002: Échec de connexion avec des identifiants invalides")
    void testLoginFailure() {
        // Given
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        // When
        loginPage.login(INVALID_EMAIL, INVALID_PASSWORD);

        // Then
        assertThat(loginPage.hasErrorMessage())
                .as("Un message d'erreur devrait être affiché")
                .isTrue();
        
        assertThat(loginPage.getErrorMessage())
                .as("Le message d'erreur ne devrait pas être vide")
                .isNotBlank();
        
        assertThat(driver.getCurrentUrl())
                .as("L'utilisateur devrait rester sur la page de connexion")
                .contains("/login");
    }

    @Test
    @DisplayName("TC-003: Échec de connexion avec email vide")
    void testLoginWithEmptyEmail() {
        // Given
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        // When
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSubmit();

        // Then
        // Le formulaire HTML5 devrait empêcher la soumission
        // ou un message d'erreur devrait apparaître
        assertThat(loginPage.isAt())
                .as("L'utilisateur devrait rester sur la page de connexion")
                .isTrue();
    }
}

