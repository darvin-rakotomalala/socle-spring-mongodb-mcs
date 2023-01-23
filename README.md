## Projet socle Spring Data MongoDB
Dans ce repo, un projet socle de base Spring Boot Data Mongodb qui vise à respecter les bonnes pratiques de développement Java, Spring Boot et Api Rest (clean architecture, concepts SOLID et Clean Code). C'est un projet réutilisable.

### Technologies
---
- Java 11
- Spring Boot 2.7.7
- Spring Data Mongodb
- Lombok
- Maven 3+
- Mongodb 5 / Robo 3T
- MapStruct

### Architecture globale des couches
---
![archi_globale_socle](https://user-images.githubusercontent.com/75081354/152366192-6d607f66-f971-4c70-bfbd-90149ee8eb4c.jpg)
<br/>

### Explication des packages
---
`controller`
* Il contiendra la définition des API Rest et le corps de la requête
* Seuls les appels d'API doivent invoquer la couche contrôleur

`service`
* Il ne prendra que les données de la couche contrôleur et les transférera vers la couche référentiel
* Il contiendra également la logique métier et modélisera les données pour la couche référentiel
* Il prendra également les données de la couche référentiel et les renverra à la couche contrôleur
* Seule la couche contrôleur doit invoquer la couche service
	- Ce package peut être composé un ou plusieurs sous packages :
		- `business` : c'est un service métier. On utilise que le DO et Repository
		- `application` : c'est un service applicatif. On utilise que le DTO, l'interface des service métier et Mapper
		- `bussinesdelegate` : c'est un package pour l'appel API distant, par exemple appel Feign
		- `technical` : c'est un package pour 
`repository`
* Il interagira avec la base de données sous-jacente.
* La couche de service ne doit invoquer que la couche de référentiel.
- `model` : c'est un package qui contient les DO, DTO, constant (ENUM), commun
- `config` : contient tous les configuration utilisées par le projet
- `exception` : contient la gestion des exceptions globale 
- `mapper` : package qui convertis DO vers DTO et vis versa
- `utils` : package utilitaire 
- `validation` : package pour les validateurs

*Avoir ces packages sera notre première étape vers une séparation claire des limites de travail et un code plus propre. Chaque package a sa propre fonctionnalité définie et il n'y a aucun chevauchement entre les packages.*

### Méthode pour le corps de la demande
---
Les points de terminaison API REST peuvent effectuer différentes actions. Ces actions sont appelées verbesméthodes HTTP. Vous trouverez ci-dessous les méthodes HTTP les plus couramment utilisés.
* **POST** : utilisé pour enregistrer l'enregistrement dans la base de données
* **PUT** : utilisé pour mettre à jour les enregistrements existants dans la base de données
* **GET** : utilisé pour récupérer les données de la base de données
* **DELETE** : utilisé pour supprimer un enregistrement de la base de données

### Exécuter et tester le projet
---
- Exécuter l'application `mvn spring-boot:run`
- Importer dans Postman la collection `new-mongodb-mcs.postman_collection.json` pour tester les APIs
- Vous pouver utiliser aussi l'url du Swagger ou importer l'url Swagger dans Postman
  - Les descriptions OpenAPI seront disponibles au chemin /v3/api-docs par défaut : http://localhost:8081/v3/api-docs/ et/ou http://localhost:8081/swagger-ui/index.html