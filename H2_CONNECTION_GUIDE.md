# Instructions pour se connecter à la base H2

## 1. Console Web H2

1. Ouvrez votre navigateur et allez à : `http://localhost:8081/h2-console`
2. Utilisez ces paramètres de connexion :
   - **JDBC URL** : `jdbc:h2:mem:authdb`
   - **User Name** : `sa`
   - **Password** : (laissez vide)
   - **Driver Class** : `org.h2.Driver`

## 2. Requêtes SQL utiles

### Voir tous les utilisateurs :
```sql
SELECT * FROM users;
```

### Voir la structure de la table :
```sql
SHOW COLUMNS FROM users;
```

### Compter les utilisateurs :
```sql
SELECT COUNT(*) FROM users;
```

### Rechercher un utilisateur spécifique :
```sql
SELECT * FROM users WHERE username = 'admin';
```

### Voir tous les utilisateurs avec leur rôle :
```sql
SELECT id, username, email, role FROM users ORDER BY role;
```

## 3. Données de test disponibles

Les utilisateurs suivants sont automatiquement créés au démarrage :
- **admin** / admin123 (rôle: ADMIN)
- **user1** / password123 (rôle: USER)
- **user2** / password123 (rôle: USER)

## 4. Configuration actuelle

Base de données : H2 en mémoire
URL JDBC : jdbc:h2:mem:authdb
Port service : 8081
Console H2 : http://localhost:8081/h2-console
