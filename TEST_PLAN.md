# Plan de Tests Selenium - GreenFund Admin

## Vue d'ensemble

Ce document décrit les scénarios de tests fonctionnels UI pour l'application GreenFund Admin (Angular).

**Application testée** : GreenFund Admin (Angular)  
**URL de base** : http://localhost:4200  
**Backend** : http://localhost:8080  
**Framework** : Selenium WebDriver + JUnit 5  
**Architecture** : Page Object Model (POM)

---

## Scénarios de Tests (Priorité Haute)

### SC-001 : Connexion réussie

**Objectif** : Vérifier qu'un utilisateur admin peut se connecter avec des identifiants valides.

**Préconditions** :
- Le backend est démarré et accessible sur http://localhost:8080
- Le frontend est démarré et accessible sur http://localhost:4200
- Un utilisateur admin existe avec les identifiants :
  - Email : `admin@greenfund.com`
  - Mot de passe : `admin123`

**Données de test** :
- Email : `admin@greenfund.com`
- Mot de passe : `admin123`

**Étapes** :
1. Ouvrir la page de connexion (http://localhost:4200/login)
2. Remplir le champ email avec `admin@greenfund.com`
3. Remplir le champ mot de passe avec `admin123`
4. Cliquer sur le bouton "Se connecter"
5. Attendre la redirection

**Résultat attendu** :
- L'utilisateur est redirigé vers le tableau de bord (/)
- L'URL ne contient plus "/login"
- Le tableau de bord est affiché avec les statistiques

**Test ID** : TC-001

---

### SC-002 : Accès protégé - Redirection vers login

**Objectif** : Vérifier qu'un utilisateur non authentifié est redirigé vers la page de connexion lors de l'accès à une route protégée.

**Préconditions** :
- Le backend est démarré
- Le frontend est démarré
- Aucune session active (navigateur frais ou cookies supprimés)

**Données de test** : Aucune

**Étapes** :
1. Ouvrir directement l'URL du dashboard (http://localhost:4200/)
2. Observer la redirection

**Résultat attendu** :
- L'utilisateur est automatiquement redirigé vers `/login`
- La page de connexion est affichée
- L'URL contient "/login"

**Variantes** :
- Accès à `/projects` → Redirection vers `/login`
- Accès à `/users` → Redirection vers `/login`
- Accès à `/transactions` → Redirection vers `/login`

**Test IDs** : TC-004, TC-005, TC-006

---

### SC-003 : Validation de projet (Action métier critique)

**Objectif** : Vérifier qu'un admin peut accéder à la page des projets en attente et visualiser la liste.

**Préconditions** :
- Le backend est démarré avec des données de test
- Le frontend est démarré
- L'utilisateur admin est connecté

**Données de test** :
- Email : `admin@greenfund.com`
- Mot de passe : `admin123`

**Étapes** :
1. Se connecter avec les identifiants admin
2. Cliquer sur "Projets à valider" dans la navigation
3. Attendre le chargement de la page

**Résultat attendu** :
- La page `/projects` est affichée
- Le titre "Projets en attente" est visible
- Soit des projets sont affichés, soit l'état vide "Aucun projet en attente"

**Test IDs** : TC-007, TC-008, TC-009

---

## Tableau de Cas de Test

| ID | Objectif | Préconditions | Étapes | Résultat Attendu | Priorité |
|----|----------|---------------|--------|------------------|----------|
| TC-001 | Connexion réussie | Backend/Frontend démarrés, user admin existe | 1. Ouvrir /login<br>2. Remplir email/password<br>3. Cliquer submit | Redirection vers dashboard | 🔴 Haute |
| TC-002 | Échec connexion (identifiants invalides) | Backend/Frontend démarrés | 1. Ouvrir /login<br>2. Remplir email/password invalides<br>3. Cliquer submit | Message d'erreur affiché, reste sur /login | 🔴 Haute |
| TC-003 | Échec connexion (champs vides) | Backend/Frontend démarrés | 1. Ouvrir /login<br>2. Laisser champs vides<br>3. Cliquer submit | Validation HTML5 ou message erreur | 🟡 Moyenne |
| TC-004 | Redirection / → /login | Backend/Frontend démarrés, pas de session | 1. Accéder directement à / | Redirection automatique vers /login | 🔴 Haute |
| TC-005 | Redirection /projects → /login | Backend/Frontend démarrés, pas de session | 1. Accéder directement à /projects | Redirection automatique vers /login | 🔴 Haute |
| TC-006 | Redirection /users → /login | Backend/Frontend démarrés, pas de session | 1. Accéder directement à /users | Redirection automatique vers /login | 🔴 Haute |
| TC-007 | Accès page projets en attente | User admin connecté | 1. Se connecter<br>2. Cliquer "Projets à valider" | Page /projects affichée | 🔴 Haute |
| TC-008 | Affichage liste projets | User admin connecté, projets en DB | 1. Se connecter<br>2. Aller sur /projects | Liste projets ou état vide affiché | 🔴 Haute |
| TC-009 | Navigation entre pages | User admin connecté | 1. Se connecter<br>2. Naviguer entre pages | Navigation fonctionnelle | 🟡 Moyenne |

---

## Données de Test

### Utilisateur Admin (Créé par DataInitializer)

L'utilisateur admin est automatiquement créé au démarrage du backend via `DataInitializer.java` :

- **Email** : `admin@greenfund.com`
- **Mot de passe** : `admin123`
- **Rôle** : `ADMIN`
- **Statut** : `ACTIVE`

**Note** : Si l'utilisateur n'existe pas, il sera créé automatiquement au premier démarrage du backend.

---

## Stratégie de Sélecteurs

### Sélecteurs Prioritaires

1. **data-testid** (priorité 1) : Sélecteurs stables ajoutés spécifiquement pour les tests
   - Exemple : `[data-testid='login-email']`

2. **ID** (priorité 2) : Sélecteurs par ID HTML natif
   - Exemple : `#email`, `#password`

3. **CSS Selectors** (priorité 3) : Sélecteurs par classe ou structure
   - Exemple : `.submit-btn`, `.error-message`

### Sélecteurs Ajoutés au Frontend

Les `data-testid` suivants ont été ajoutés au frontend Angular :

- `login-email` : Champ email de connexion
- `login-password` : Champ mot de passe
- `login-submit` : Bouton de soumission
- `login-error` : Message d'erreur
- `nav-dashboard` : Lien navigation "Tableau de bord"
- `nav-projects` : Lien navigation "Projets à valider"
- `nav-users` : Lien navigation "Utilisateurs"
- `nav-transactions` : Lien navigation "Transactions"
- `project-card-{id}` : Carte de projet (dynamique)
- `approve-project-{id}` : Bouton approuver projet
- `reject-project-{id}` : Bouton rejeter projet

---

## Critères de Réussite

### Tests de Connexion
- ✅ Connexion réussie redirige vers le dashboard
- ✅ Connexion échouée affiche un message d'erreur
- ✅ L'utilisateur reste sur /login en cas d'erreur

### Tests de Protection
- ✅ Toutes les routes protégées redirigent vers /login
- ✅ La redirection est automatique et immédiate

### Tests de Navigation
- ✅ Les liens de navigation fonctionnent correctement
- ✅ Les pages se chargent sans erreur
- ✅ Le contenu attendu est affiché

---

## Notes Techniques

### Timeouts
- **Implicit Wait** : 5 secondes
- **Explicit Wait** : 10 secondes (WebDriverWait)
- **Page Load Timeout** : Géré par Selenium

### Screenshots
- Les screenshots sont automatiquement pris en cas d'échec de test
- Sauvegardés dans le dossier `screenshots/`
- Format : `{TestName}_{Timestamp}.png`

### Robustesse
- Utilisation de `WebDriverWait` au lieu de `Thread.sleep()`
- Gestion des exceptions avec fallback sur sélecteurs alternatifs
- Vérifications multiples pour confirmer l'état de la page

---

## Évolutions Futures

### Tests à Ajouter (Priorité Moyenne)
- [ ] Test de validation d'un projet (approuver/rejeter)
- [ ] Test de déconnexion
- [ ] Test de navigation complète (toutes les pages)
- [ ] Test de gestion des utilisateurs

### Tests à Ajouter (Priorité Basse)
- [ ] Tests de responsive design
- [ ] Tests de performance (temps de chargement)
- [ ] Tests cross-browser (Firefox, Edge)

---

**Date de création** : 2024-12-22  
**Version** : 1.0.0  
**Auteur** : GreenFund QA Team

