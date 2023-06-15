# Contenu du Projet Hopital :
1. [Description du projet] (#description)
    1. [Objectifs](#objectifs)
    2. [Fonctionnalités existantes](#fonction)
    3. [Packages](#packages)
    4. [Informations d'installation](#install)
2. [Fonctionnalités à venir](#soon)
    1. [Gestion des personnels](#people)
    2. [Fonctionnalités des vues](#views)
    3. [Autres fonctionntaliés](#other)

# Description du projet :  <a name="description"></a>

## Objectifs : <a name="Objectifs"></a>

L'objectif de ce projet est de fournir une application simple et intuitive, permettant la gestion simplifiée d'un hopital. Elle doit être en mesure de traiter les cas d'usage suivants :
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
Heberge les classes des différents contrôleurs pour les vues disponibles : Ecran de connexion, Recherche de patient, affichage et modification de patient, création et modification de patient

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

### hopital_modeles
Regroupe les modèles utilisés par les contrôleurs et les vues. Il y en a 4 en tout : ModelAffichagePatient pour gérer l'affichage d'un patient sélectionné, ModelConnexion pour gérer la connexion à l'application, ModelCreationPatient pour gérer la création d'un nouveau patient, et ModelGestionPatient pour la recherche et suppression d'un patient

### idGenerator
Ce package a été constitué afin de regrouper les classes permettant de générer des identifiants uniques pour les classes métiers.
Cependant, étant la diversité des moyens pour gérer la création de clé primaire pour enregistrement en BDD, ce package sera a supprimé lors d'un refactoring

### RessourcesBDD
Contient une copie des tables représentant les classes métiers au niveau physique, une copie du cahier des charges et une TO DO liste.

### Test
Contient les classes de test JUnit du projet. Pour le moment, il n'y a que des classes de test pour les DAO. 

### Vue
Regroupe les vues utiliés pour l'affichage des différents écrans, ainsi que des composants utilisés dans lesdites vues comme PatientModelTable. Pour le moment, 3 vues sont utilisées et fonctionnelles : 
1. VuePatient pour l'affichage ou la création de patient
2. EcranConnexion pour gérer la connexion à l'application
3. VueRechercheAgentPatient pour la recherche et suppression de patient

Dans le reste des vues, 1 peut être affichée via l'application en se connectant en tant que médecin (VueRechercheConsultation) et une autre peut être affichée via sa classe (VueConsultation).


## Informations d'installation <a name="install"></a>
Pour installer et utiliser cette application, il suffit d'implémenter la base de données jointe, initulée projethopital_rvorlet.sql, au projet via un logiciel de gestion de base de données type MySQL, et de réaliser l'import. 
Une fois l'import fait et la base de données mise en ligne/activée, il suffit de double-cliquer sur le jar fourni ou de lancer l'application via un IDE via la classe LancementApplication.java.
Pour se connecter, il faut ensuite utiliser les logins mis à disposition dans le fichier loginTest.txt

Compatible avec Windows 11. 


# Fonctionnalités à venir: <a name="soon"></a>

L'application propose pour le moment des fonctionnalités basiques mais est amenée à s'enrichir de plus de fonctionnalités dans un futur proche 

## Gestion des personnels : <a name="people"></a>
- Gestion des ajouts des salariés avec gestion des droits, dont celui d'administrateur
- Gestion de la notion de service où exercent le personnel

## Fonctionnalités des vues : <a name="views"></a>
- Edititon d'ordonnance, de dossier patient
- Production de statistiques sur les pathologies diagnostiquées, le nombre d'actes réalisées, le nombre d’appareils octroyés par patient, les périodes de l’année qui enregistre les plus de consultations, etc
- Rôle de Super admin pour l'administration des personnels dans l'application
- Implémentation des services pour la gestion du budget et les évolutions entre services 

## Autres fonctionnalités : <a name="other"></a>
- Encodage en base64 des mots de passe dans la base de données 
- Gestion de la touche 'entrée' pour lancer la recherche
- Utilisation d'API pour récupérer la ville à partir du code postal pour la France    
