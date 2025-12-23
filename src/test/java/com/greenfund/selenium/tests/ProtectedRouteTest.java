package com.greenfund.selenium.tests;

import com.greenfund.selenium.BaseTest;
import com.greenfund.selenium.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests de protection des routes (accès sans authentification) sur le frontend Angular.
 */
@DisplayName("Tests de protection des routes")
public class ProtectedRouteTest extends BaseTest {

    @Test
    @DisplayName("TC-004: Accès au dashboard sans authentification -> redirection vers /login")
    void testRedirectToLoginWhenAccessingRoot() {
        // When - Accès direct à la racine de l'application admin
        driver.get("http://localhost:4200/");

        // Then - Redirection vers la page de login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/login"));

        LoginPage loginPage = new LoginPage(driver);
        assertThat(loginPage.isAt())
                .as("La page de connexion devrait être affichée")
                .isTrue();
    }

    @Test
    @DisplayName("TC-005: Accès à /projects sans authentification -> redirection vers /login")
    void testRedirectToLoginWhenAccessingProjects() {
        // When - Accès direct à la page des projets
        driver.get("http://localhost:4200/projects");

        // Then - Redirection vers /login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/login"));

        assertThat(driver.getCurrentUrl())
                .as("L'utilisateur devrait être redirigé vers /login")
                .contains("/login");
    }

    @Test
    @DisplayName("TC-006: Accès à /users sans authentification -> redirection vers /login")
    void testRedirectToLoginWhenAccessingUsers() {
        // When - Accès direct à la page des utilisateurs
        driver.get("http://localhost:4200/users");

        // Then - Redirection vers /login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/login"));

        assertThat(driver.getCurrentUrl())
                .as("L'utilisateur devrait être redirigé vers /login")
                .contains("/login");
    }
}

