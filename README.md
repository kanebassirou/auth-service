# AuthService - Microservice d'Authentification

## Description
Service d'authentification REST pour l'application de réservation de salles. Ce microservice gère l'authentification des utilisateurs et la gestion des comptes.

## Technologies utilisées
- **Spring Boot 3.5.3**
- **Java 22**
- **Spring Data JPA**
- **Base de données H2** (en mémoire)
- **Lombok** (pour réduire le code boilerplate)
- **Maven** (gestionnaire de dépendances)

## Architecture

```
auth-service/
├── src/main/java/com/example/auth_service/
│   ├── config/           # Configuration et initialisation des données
│   ├── controller/       # Controllers REST
│   ├── dto/             # Data Transfer Objects
│   ├── model/           # Entités JPA
│   ├── repository/      # Repositories JPA
│   ├── service/         # Services métier
│   └── AuthServiceApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## Configuration

### Port et Base de données
- **Port** : 8081
- **Base de données** : H2 en mémoire
- **URL JDBC** : `jdbc:h2:mem:authdb`
- **Console H2** : http://localhost:8081/h2-console

### Données de test
Le service charge automatiquement des utilisateurs de test :
- **admin** / admin123 (rôle: ADMIN)
- **user1** / password123 (rôle: USER)
- **user2** / password123 (rôle: USER)

## API Endpoints

### 1. Santé du service
```http
GET /api/auth/health
```
**Réponse** : `Auth Service is running`

### 2. Connexion utilisateur
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

**Réponse de succès** :
```json
{
  "success": true,
  "message": "Connexion réussie",
  "userId": 1,
  "username": "admin",
  "role": "ADMIN"
}
```

**Réponse d'erreur** :
```json
{
  "success": false,
  "message": "Mot de passe incorrect",
  "userId": null,
  "username": null,
  "role": null
}
```

### 3. Création d'utilisateur
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "newuser",
  "password": "password123",
  "email": "newuser@example.com",
  "role": "USER"
}
```

### 4. Liste des utilisateurs
```http
GET /api/auth/users
```

## Démarrage du service

### Prérequis
- Java 22
- Maven (ou utiliser le wrapper Maven inclus)

### Commandes
```bash
# Compilation
mvn clean compile

# Démarrage
mvn spring-boot:run

# Ou avec le wrapper Maven
./mvnw spring-boot:run    # Linux/Mac
mvnw.cmd spring-boot:run  # Windows
```

### Vérification du démarrage
```bash
# Vérifier que le port 8081 est ouvert
netstat -an | findstr ":8081"

# Tester l'API
curl http://localhost:8081/api/auth/health
```

## Base de données H2

### Accès à la console
1. Ouvrir http://localhost:8081/h2-console
2. Paramètres de connexion :
   - **JDBC URL** : `jdbc:h2:mem:authdb`
   - **User Name** : `sa`
   - **Password** : (vide)

### Requêtes utiles
```sql
-- Voir tous les utilisateurs
SELECT * FROM users;

-- Voir la structure de la table
SHOW COLUMNS FROM users;

-- Compter les utilisateurs
SELECT COUNT(*) FROM users;

-- Rechercher un utilisateur
SELECT * FROM users WHERE username = 'admin';
```

## Modèle de données

### Entité User
```java
@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String username;  // Unique
    private String password;
    private String email;
    private String role;      // "USER" ou "ADMIN"
}
```

## Tests

### Fichier de tests API
Le fichier `api-tests.http` contient des exemples de requêtes pour tester l'API.

### Tests manuels
```bash
# Test de santé
curl http://localhost:8081/api/auth/health

# Test de connexion
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123"}'
```

## Configuration CORS
Le service accepte les requêtes cross-origin avec `@CrossOrigin(origins = "*")`.

## Logs
Les requêtes SQL sont affichées dans la console grâce à `spring.jpa.show-sql=true`.

## Sécurité
⚠️ **Note importante** : Ce service utilise des mots de passe en clair pour la démonstration. En production, utilisez BCrypt ou un autre système de hachage sécurisé.

## Intégration avec d'autres services
Ce service peut être utilisé par d'autres microservices pour :
- Valider les sessions utilisateur
- Récupérer les informations d'utilisateur
- Gérer les permissions basées sur les rôles

## Dépannage

### Problèmes courants
1. **Port déjà utilisé** : Vérifiez qu'aucun autre service n'utilise le port 8081
2. **JAVA_HOME non configuré** : Assurez-vous que Java 22 est installé et JAVA_HOME configuré
3. **Console H2 inaccessible** : Vérifiez que le service est démarré et accessible sur le port 8081

### Logs utiles
```bash
# Voir les logs du service
tail -f logs/auth-service.log

# Vérifier les connexions réseau
netstat -an | findstr ":8081"
```

## Auteur
Service développé dans le cadre du projet d'application de réservation de salles.
