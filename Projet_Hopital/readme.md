# Contenu du Projet Hopital :
1. [Description du projet](#description)
    1. [Objectifs](#objectifs)
    2. [Fonctionnalités existantes](#fonction)
    3. [Packages](#packages)
    4. [Informations d'installation](#install)
2. [Fonctionnalités à venir](#soon)
    1. [Gestion des personnels](#people)
    2. [Fonctionnalités des vues](#views)
    3. [TBD](#TBD)

# Description du projet :  <a name="description"></a>

## Objectifs : <a name="Objectifs"></a>

L'objectif de ce projet est de fournir une application simple et intuitive, permettant la gestion simplifié d'un hopital. Elle doit être en mesure de traiter les cas d'usage suivants :
- Un patient se présente à l'accueil où il sera enregistré par un agent d'accueil. S'il n'a pas de dossier patient, alors l'agent doit créer le dossier du patient. L'agent doit également avoir la possibilité de le mettre à jour ou de le supprimer. 

- Une fois enregistré, le patient se rend à une consultation où il sera reçu par un médecin. Le médecin doit pouvoir consulter la liste des consultations dans le dossier patient et doit avoir la possibilité d'en ajouter une nouvelle, modifier ou supprimer une existante. 

- Le médecin doit pouvoir donner les détails cliniques de la consultation et la prescription (ordonnance) : soins, médicament ou appareil médical à octroyer au patient.

- Si du matériel médical est prescrit alors un technicien doit pouvoir accéder à la consultation du patient et octroyer le matériel. Seuls les appareils prescrits lors de la consultation doivent être accessibles. 

D'autres éléments doivent faire l'objet de développement afin que l'application soit cohérente :
- Gestion des ajouts des salariés avec gestion des droits, dont celui d'administrateur
- Edititon d'ordonnance, de dossier patient
- Production de statistiques sur les pathologies diagnostiquées, le nombre d'actes réalisées, le nombre d’appareils octroyés par patient, les périodes de l’année qui enregistre les plus de consultations, etc
- Gestion de la notion de service où exercent le personnel

Un cahier des charges est disponible dans le package Ressources BDD du projet pour plus de détails.

L'application est réalisée en Java seulement et utilise MySQL pour la persistance des données.

## Fonctionnalités existantes : <a name="fonction"></a>
Actuellement l'application peut :
1. Etre lancé 
2. Présenter un écran de connexion, avec saisie du login utilisateur et mot de passe

3. Afficher d'un écran de recherche de patients. Cela correspond au rôle d'agent d'accueil et la recherche s'effectue selon 3 critères :
a. Nom
b. Numéro de sécurité sociale
c. Numéro d'identifiant

4. Afficher un écran détaillant les informations personnelles et d'adresse d'un patient
a. Annuler une saisie de modification
b. Enregistrer les modifications faites pour un patient affiché
c. Bloquer des modifications ultérieures

5. Afficher un écran de création de patient et permettre sa modification une fois enregistré

6. Supprimer un patient sélectionné

7. Se déconnecter pour revenir à l'écran de connexion

Les écrans affichés peuvent être redimensionnés.


## Packages: <a name="packages"></a>
L'application comprend pour le moment 10 packages et implémente le pattern MVC :

### Connexion : 
Contient le singleton pour l'accès à la base de données et la classe Logins gérant les rôles pour l'accès aux différentes vues.

### Controleur :
Herbege les classes des différents contrôleurs pour les vues disponibles : Ecran de connexion, Recherche de patient, affichage et modification de patient, création et modification de patient

### DAO :
Gère les classes d'accès à la base de données pour l'enregistrement des objets métiers.
**En l'état**, certains DAO demandent à être retravaillés avant d'être utilisés dans un modèle : consultation, médecin, spécialité
Un refactoring pour une meilleure utilisation du pattern DAO est souhaitable également.

### Exceptions
Exceptions propres à l'application

### Hopital
Contient les classes métiers, représentant la réalité de l'hopital.
Les classes suivantes doivent être terminées : Technicien, AgentAccueil, Infirmier
La classe Administrateur (rôle de super admin) doit être créée



## Informations d'installation <a name="install"></a>
Ipsum Lorem


# Fonctionnalités à venir: <a name="soon"></a>

## Gestion des personnels : <a name="people"></a> v

Ipsum lorem

## Fonctionnalités des vues : <a name="views"></a>
Ipsum lorem

## TBD : <a name="TBD"></a>
