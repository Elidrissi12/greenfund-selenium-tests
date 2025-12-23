# Guide Flutter Web pour Tests Selenium

Ce document explique comment adapter les tests Selenium pour Flutter Web.

## 🎯 Différences Flutter Web vs Angular

### Sélecteurs

**Angular** utilise `data-testid` :
```html
<input data-testid="login-email" />
```

**Flutter Web** convertit les `Key` en attributs dans le DOM :
```html
<input data-flutter-key="login-email" />
```

### Sélecteurs dans les Tests

Dans les Page Objects, utilisez :

```java
// Flutter Web
By.cssSelector("input[data-flutter-key='login-email']")

// Avec fallback pour compatibilité
By.cssSelector("input[data-flutter-key='login-email'], input[aria-label*='Email']")
```

## 🔧 Configuration

### Port par défaut

Flutter Web utilise généralement le port **5000** par défaut, mais peut varier.

Pour vérifier le port :
```powershell
flutter run -d chrome
# Regardez la console pour voir le port utilisé
```

### Modifier le port dans les tests

Dans `BaseTest.java` :
```java
protected static final String BASE_URL = "http://localhost:5000"; // Flutter Web
```

## 📝 Ajout de Keys aux Widgets Flutter

Pour que les tests Selenium fonctionnent, ajoutez des `Key` aux widgets importants :

### Exemple : LoginScreen

```dart
TextField(
  key: const Key('login-email'),
  controller: emailController,
  // ...
)
```

### Exemple : GreenTextField (Widget réutilisable)

```dart
class GreenTextField extends StatelessWidget {
  final Key? testKey; // Ajouter ce paramètre
  
  const GreenTextField({
    super.key,
    this.testKey, // Ajouter ici
    // ...
  });
  
  @override
  Widget build(BuildContext context) {
    return TextFormField(
      key: testKey, // Utiliser ici
      // ...
    );
  }
}
```

## 🚀 Démarrer Flutter Web

```powershell
cd C:\Users\ABDO EL IDRISSI\Desktop\GreenFund
flutter run -d chrome
```

## ⚠️ Notes Importantes

1. **Temps de chargement** : Flutter Web peut prendre plus de temps à charger qu'Angular. Augmentez les timeouts si nécessaire.

2. **Sélecteurs dynamiques** : Flutter Web génère du HTML dynamique. Utilisez des sélecteurs robustes avec fallbacks.

3. **Navigation** : Flutter utilise souvent `BottomNavigationBar` ou `NavigationBar` au lieu de liens HTML classiques.

4. **Tests de création** : Pour tester la création de projet, vous aurez besoin d'un utilisateur avec le rôle `OWNER`.

## 🔍 Débogage

### Voir le HTML généré par Flutter

1. Ouvrez Chrome DevTools (F12)
2. Allez dans l'onglet "Elements"
3. Recherchez les éléments avec `data-flutter-key`

### Vérifier les sélecteurs

Dans Chrome DevTools Console :
```javascript
// Tester un sélecteur
document.querySelector("input[data-flutter-key='login-email']")
```

## 📚 Ressources

- [Flutter Web Documentation](https://docs.flutter.dev/platform-integration/web)
- [Selenium Flutter Web Testing](https://docs.flutter.dev/testing/integration-tests/web)

