# GreenFund Selenium Tests

Projet de tests fonctionnels UI avec Selenium WebDriver pour l'application GreenFund Admin.

## 📋 Prérequis

- **Java 17** ou supérieur
- **Maven 3.6+**
- **Chrome Browser** (dernière version)
- **Backend Spring Boot** démarré sur `http://localhost:8080`
- **Frontend Angular** démarré sur `http://localhost:4200`

## 🚀 Démarrage Rapide

### 1. Démarrer le Backend

```powershell
cd C:\Users\ABDO EL IDRISSI\Desktop\greenfund-backend
mvn spring-boot:run
```

Vérifiez que le backend est accessible : http://localhost:8080

### 2. Démarrer le Frontend

```powershell
cd C:\Users\ABDO EL IDRISSI\Desktop\greenFund-angular\greenFund-angular
npm start
```

Par défaut, Angular démarre sur **http://localhost:4200**

### 3. Exécuter les Tests

```powershell
cd C:\Users\ABDO EL IDRISSI\Desktop\greenfund-selenium-tests
mvn clean test
```

Pour exécuter en mode silencieux :
```powershell
mvn -q test
```

Pour exécuter un test spécifique :
```powershell
mvn test -Dtest=LoginTest
```

## 📁 Structure du Projet

```
greenfund-selenium-tests/
├── pom.xml                          # Configuration Maven
├── README.md                        # Ce fichier
├── TEST_PLAN.md                     # Plan de tests détaillé
├── GUIDE_CAPTURES_SELENIUM.md       # Guide pour captures d'écran
├── screenshots/                     # Screenshots en cas d'échec (généré automatiquement)
└── src/
    └── test/
        └── java/
            └── com/
                └── greenfund/
                    └── selenium/
                        ├── BaseTest.java              # Classe de base pour tous les tests
                        ├── pages/                     # Page Object Model
                        │   ├── LoginPage.java
                        │   ├── DashboardPage.java
                        │   └── PendingProjectsPage.java
                        └── tests/                     # Tests fonctionnels
                            ├── LoginTest.java
                            ├── ProtectedRouteTest.java
                            └── ValidateProjectTest.java
```

## 🔑 Identifiants de Test

L'utilisateur admin est créé automatiquement par le backend via `DataInitializer` :

- **Email** : `admin@greenfund.com`
- **Mot de passe** : `admin123`
- **Rôle** : `ADMIN`

**Note** : Si l'utilisateur n'existe pas, il sera créé au premier démarrage du backend.

## 🧪 Tests Disponibles

### LoginTest
- ✅ TC-001 : Connexion réussie avec identifiants valides
- ✅ TC-002 : Échec de connexion avec identifiants invalides
- ✅ TC-003 : Échec de connexion avec champs vides

### ProtectedRouteTest
- ✅ TC-004 : Redirection vers login lors de l'accès au dashboard
- ✅ TC-005 : Redirection vers login lors de l'accès à /projects
- ✅ TC-006 : Redirection vers login lors de l'accès à /users

### ValidateProjectTest
- ✅ TC-007 : Accès à la page des projets en attente
- ✅ TC-008 : Affichage de la liste des projets
- ✅ TC-009 : Navigation entre les pages du dashboard

## 📸 Screenshots en Cas d'Échec

Les screenshots sont automatiquement générés en cas d'échec de test et sauvegardés dans :
```
greenfund-selenium-tests/screenshots/
```

Format : `{TestName}_{Timestamp}.png`

## ⚙️ Configuration

### URLs

Les URLs sont configurées dans `BaseTest.java` :

```java
protected static final String BASE_URL = "http://localhost:4200";
```

### Timeouts

- **Implicit Wait** : 5 secondes
- **Explicit Wait** : 10 secondes

### Navigateur

Par défaut, les tests utilisent **Chrome**. Pour utiliser un autre navigateur, modifiez `BaseTest.java`.

## 🛠️ Commandes Utiles

### Compiler le projet
```powershell
mvn clean compile
```

### Exécuter tous les tests
```powershell
mvn test
```

### Exécuter un test spécifique
```powershell
mvn test -Dtest=LoginTest#testLoginSuccess
```

### Exécuter en mode verbose
```powershell
mvn test -X
```

### Nettoyer et exécuter
```powershell
mvn clean test
```

## 🐛 Dépannage

### Problème : ChromeDriver non trouvé
**Solution** : WebDriverManager télécharge automatiquement ChromeDriver. Vérifiez votre connexion Internet.

### Problème : Tests échouent avec "Connection refused"
**Solution** : Vérifiez que le backend et le frontend sont démarrés :
- Backend : http://localhost:8080
- Frontend : http://localhost:4200

### Problème : Tests échouent avec "Element not found"
**Solution** : 
1. Vérifiez que le frontend a bien les `data-testid` ajoutés
2. Vérifiez que la page est complètement chargée
3. Augmentez les timeouts si nécessaire

### Problème : Chrome ne démarre pas
**Solution** : 
1. Vérifiez que Chrome est installé
2. Vérifiez que Chrome est à jour
3. Essayez de mettre à jour WebDriverManager : `mvn clean test -U`

## 📊 Résultats des Tests

Les résultats sont affichés dans la console Maven. Exemple :

```
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## 📝 Documentation Complémentaire

- **TEST_PLAN.md** : Plan de tests détaillé avec tous les scénarios
- **GUIDE_CAPTURES_SELENIUM.md** : Guide pour capturer les screenshots du rapport

## 🔄 Intégration CI/CD

Pour intégrer dans un pipeline CI/CD, ajoutez :

```yaml
# Exemple GitHub Actions
- name: Run Selenium Tests
  run: |
    cd greenfund-selenium-tests
    mvn clean test
```

**Note** : En CI/CD, utilisez le mode headless :
```java
options.addArguments("--headless");
```

## 📞 Support

Pour toute question ou problème, consultez :
- Le plan de tests : `TEST_PLAN.md`
- Les logs Maven : `mvn test -X`
- Les screenshots en cas d'échec : `screenshots/`

---

**Version** : 1.0.0  
**Dernière mise à jour** : 2024-12-22

