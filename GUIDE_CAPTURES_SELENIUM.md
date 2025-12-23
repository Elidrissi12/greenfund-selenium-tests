# 📸 Guide : Captures d'Écran pour Tests Selenium

Ce guide liste toutes les captures d'écran nécessaires pour documenter les tests Selenium dans votre rapport.

---

## 📸 CAPTURE 1 : Arborescence du Projet Selenium

### Fichier à capturer
**Explorateur de fichiers** (VS Code, IntelliJ, ou Explorateur Windows)

### Chemin
```
C:\Users\ABDO EL IDRISSI\Desktop\greenfund-selenium-tests\
```

### Instructions détaillées
1. Ouvrir l'explorateur de fichiers
2. Naviguer vers `greenfund-selenium-tests/`
3. Déplier l'arborescence pour montrer :
   - `pom.xml`
   - `README.md`
   - `TEST_PLAN.md`
   - `GUIDE_CAPTURES_SELENIUM.md`
   - `src/test/java/com/greenfund/selenium/`
     - `BaseTest.java`
     - `pages/` (LoginPage, DashboardPage, PendingProjectsPage)
     - `tests/` (LoginTest, ProtectedRouteTest, ValidateProjectTest)

### Ce qui doit être visible
- ✅ La structure complète du projet
- ✅ Les fichiers Java avec extensions `.java`
- ✅ Les fichiers de documentation `.md`
- ✅ Le fichier `pom.xml`

---

## 📸 CAPTURE 2 : Exécution des Tests (Console Maven)

### Fichier à capturer
**Terminal / Console PowerShell**

### Commande à exécuter
```powershell
cd C:\Users\ABDO EL IDRISSI\Desktop\greenfund-selenium-tests
mvn clean test
```

### Instructions détaillées
1. Ouvrir PowerShell dans le dossier `greenfund-selenium-tests`
2. S'assurer que le backend et le frontend sont démarrés
3. Exécuter `mvn clean test`
4. Attendre la fin de l'exécution
5. Capturer la console avec :
   - La commande exécutée (`mvn clean test`)
   - Les logs d'exécution des tests
   - Le résumé final :
     ```
     [INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
     [INFO] 
     [INFO] ------------------------------------------------------------------------
     [INFO] BUILD SUCCESS
     [INFO] ------------------------------------------------------------------------
     ```
   - Le temps d'exécution

### Ce qui doit être visible
- ✅ La commande `mvn clean test`
- ✅ Les logs d'exécution (compilation, tests)
- ✅ Le message "BUILD SUCCESS"
- ✅ Le résumé (Tests run, Failures, Errors, Skipped)
- ✅ Le temps total

### Astuce
Si les tests prennent du temps, vous pouvez capturer juste le début et la fin.

---

## 📸 CAPTURE 3 : Exécution d'un Test (Navigateur - Page de Connexion)

### Fichier à capturer
**Navigateur Chrome** (pendant l'exécution d'un test)

### Instructions détaillées
1. **Lancer un test spécifique** :
   ```powershell
   mvn test -Dtest=LoginTest#testLoginSuccess
   ```

2. **Pendant l'exécution**, le navigateur Chrome s'ouvre automatiquement

3. **Capturer la fenêtre du navigateur** avec :
   - La page de connexion affichée
   - Les champs email et password visibles
   - Le bouton "Se connecter" visible
   - L'URL dans la barre d'adresse : `http://localhost:4200/login`

### Ce qui doit être visible
- ✅ La page de connexion complète
- ✅ Les champs de formulaire
- ✅ Le bouton de soumission
- ✅ L'URL dans la barre d'adresse

### Astuce
Utilisez un outil de capture d'écran (Snipping Tool, ShareX) pour capturer uniquement la fenêtre du navigateur.

---

## 📸 CAPTURE 4 : Exécution d'un Test (Navigateur - Dashboard)

### Fichier à capturer
**Navigateur Chrome** (après connexion réussie)

### Instructions détaillées
1. **Lancer le test de connexion** :
   ```powershell
   mvn test -Dtest=LoginTest#testLoginSuccess
   ```

2. **Pendant l'exécution**, après la connexion, le navigateur affiche le dashboard

3. **Capturer la fenêtre du navigateur** avec :
   - Le tableau de bord affiché
   - Les cartes KPI (Utilisateurs, Projets actifs, etc.)
   - La navigation latérale visible
   - L'URL dans la barre d'adresse : `http://localhost:4200/` (sans /login)

### Ce qui doit être visible
- ✅ Le tableau de bord complet
- ✅ Les statistiques affichées
- ✅ La navigation latérale
- ✅ L'URL sans "/login"

---

## 📸 CAPTURE 5 : Exécution d'un Test (Navigateur - Page Projets)

### Fichier à capturer
**Navigateur Chrome** (sur la page des projets)

### Instructions détaillées
1. **Lancer le test de navigation** :
   ```powershell
   mvn test -Dtest=ValidateProjectTest#testAccessPendingProjectsPage
   ```

2. **Pendant l'exécution**, après la navigation, le navigateur affiche la page des projets

3. **Capturer la fenêtre du navigateur** avec :
   - Le titre "Projets en attente"
   - Soit la liste des projets, soit l'état vide
   - L'URL dans la barre d'adresse : `http://localhost:4200/projects`

### Ce qui doit être visible
- ✅ La page des projets en attente
- ✅ Le contenu (projets ou état vide)
- ✅ L'URL avec "/projects"

---

## 📸 CAPTURE 6 : Screenshot Automatique en Cas d'Échec

### Fichier à capturer
**Screenshot généré automatiquement**

### Chemin
```
greenfund-selenium-tests/screenshots/
```

### Instructions détaillées
1. **Forcer un échec de test** (optionnel, pour démonstration) :
   - Modifier temporairement un test pour qu'il échoue
   - Exécuter : `mvn test`

2. **Ouvrir le dossier screenshots** :
   ```
   greenfund-selenium-tests/screenshots/
   ```

3. **Capturer** :
   - Le contenu du dossier avec les fichiers screenshots
   - Ou ouvrir un screenshot et le capturer

### Format des fichiers
- Nom : `{TestName}_{Timestamp}.png`
- Exemple : `testLoginSuccess_1703256000000.png`

### Ce qui doit être visible
- ✅ Le dossier screenshots avec les fichiers
- ✅ Les noms de fichiers avec timestamps
- ✅ Optionnel : Un screenshot ouvert montrant l'état de la page au moment de l'échec

---

## 📸 CAPTURE 7 : Structure du Code (Page Object Model)

### Fichier à capturer
**IDE** (VS Code, IntelliJ) avec le code ouvert

### Instructions détaillées
1. Ouvrir `LoginPage.java` dans votre IDE
2. Capturer l'écran avec :
   - Le code de la classe `LoginPage`
   - Les méthodes (open, login, isAt, etc.)
   - Les sélecteurs utilisant `data-testid`
   - La structure Page Object Model visible

### Ce qui doit être visible
- ✅ Le code de la classe Page Object
- ✅ Les méthodes de la page
- ✅ Les sélecteurs (data-testid)
- ✅ La structure claire et organisée

---

## 📸 CAPTURE 8 : Structure du Code (Test)

### Fichier à capturer
**IDE** avec un test ouvert

### Instructions détaillées
1. Ouvrir `LoginTest.java` dans votre IDE
2. Capturer l'écran avec :
   - Le code du test
   - Les annotations `@Test` et `@DisplayName`
   - L'utilisation de la Page Object (`LoginPage`, `DashboardPage`)
   - Les assertions AssertJ

### Ce qui doit être visible
- ✅ Le code du test
- ✅ Les annotations JUnit 5
   - ✅ L'utilisation des Page Objects
   - ✅ Les assertions claires

---

## 📝 Checklist des Captures

Avant de finaliser votre rapport, vérifiez que vous avez :

- [ ] ✅ Capture 1 : Arborescence du projet selenium
- [ ] ✅ Capture 2 : Exécution mvn test avec BUILD SUCCESS
- [ ] ✅ Capture 3 : Navigateur sur page de connexion (pendant test)
- [ ] ✅ Capture 4 : Navigateur sur dashboard (après connexion)
- [ ] ✅ Capture 5 : Navigateur sur page projets (navigation)
- [ ] ✅ Capture 6 : Screenshot automatique en cas d'échec (optionnel)
- [ ] ✅ Capture 7 : Structure du code (Page Object)
- [ ] ✅ Capture 8 : Structure du code (Test)

---

## 💡 Conseils pour de Bonnes Captures

1. **Résolution** : Utilisez une résolution suffisante (1920x1080 minimum)
2. **Légendes** : Ajoutez des légendes/annotations si nécessaire
3. **Cohérence** : Utilisez le même navigateur/IDE pour toutes les captures
4. **Qualité** : Vérifiez que le texte est lisible
5. **Format** : Sauvegardez en PNG ou JPG haute qualité
6. **Nommage** : Nommez les fichiers de manière claire (ex: `capture-1-structure-projet.png`)

---

## 🚀 Commandes Rapides pour Captures

```powershell
# 1. Exécuter tous les tests
cd C:\Users\ABDO EL IDRISSI\Desktop\greenfund-selenium-tests
mvn clean test

# 2. Exécuter un test spécifique (pour capture navigateur)
mvn test -Dtest=LoginTest#testLoginSuccess

# 3. Voir les screenshots générés
start screenshots
```

---

## 📌 Notes Importantes

- **Backend et Frontend doivent être démarrés** avant d'exécuter les tests
- Les **screenshots automatiques** sont générés uniquement en cas d'échec
- Pour capturer le navigateur pendant l'exécution, **lancez les tests un par un** avec `-Dtest`
- Les tests peuvent être **lents** (ouverture/fermeture du navigateur), c'est normal

---

✅ **Une fois toutes les captures effectuées, vous pouvez les intégrer dans votre rapport !**

