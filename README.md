# Projet IHM - Banquise
Ce projet est à réaliser avant le 30 avril 2021, dans le cadre de l'Unité d'Enseignement "Programmation des Interfaces Homme-Machine". Il porte sur la reprise d'un projet proposé dans le cadre de l'Unité d'enseignement "Programmation Orientée Objet" dont les informations sont détaillées dans le fichier `README_POO.md`. Plus particulièrement, ce projet cible le développement d'une interface graphique à partir d'un jeu d'aventure déjà développé.

Composition de notre groupe : Yann Berthelot, Vincent Commin & Louis Leenart.

# Sommaire
- [Installation <a id="installation">](#installation)
- [Utilisation <a id="utilisation">](#utilisation)
- [Notre approche de conception en partant de la conception existante <a id="conception">](#conception)
- [Autres points <a id="autres">](#autres)

# Installation
- Version du JDK de java : `15.0.1`
- Version du JDK de javafx : `15.0.1`
- Le lancement de l'application se fait via la classe `src/Main.java`.
# Utilisation
Pour lancer une partie de jeu, il faut suivre les étapes suivantes :
1. Le jeu se lance via le fichier `src/Main.java`
2. Entrer un nom pour votre personnage dans la zone d'entrée en haut à gauche
3. Ouvrez le menu et cliquez sur `Launch Game` pour démarrer la partie
4. Maintenant que la partie est lancée, vous pouvez faire plusieurs actions :
    - Lister les passages disponibles en cliquant sur `List Crossings`. Vous pouvez ouvrir un passage fermé en cliquant dessus dans la liste des passage sur la partie droite de l'écran.
    - Lister les personnages présents sur la case en cliquant sur `List Personnages`. Vous pouvez parler avec un personnage en cliquant sur son nom dans la liste des personnages affichés sur la partie droite de l'écran.
    - Lister les objets sur la case en cliquant sur `List Items`. Vous pouvez ramasser un objet pour le mettre dans votre inventaire en cliquant sur son nom dans la liste des objets affichés sur la partie droite de l'écran.
    - Lister les objets dans l'inventaire de votre personnage en cliquant sur `Inventory`. Vous pouvez utiliser un objet en cliquant sur son nom dans la liste des éléments, puis en sélectionnant le personnage sur lequel utiliser l'objet.
    - Déplacer votre personnage à une case adjacente en cliquant sur le bouton flèche correspondant (si le passage vers la case est fermé, il faut l'ouvrir).
5. Le but du jeu est de trouver le personnage `Chief Scientist` qui est présent sur une des case du jeu. Il faut ensuite lui parler pour gagner la partie.
# Notre approche de conception en partant de la conception existante

## Notre séparation modèle/vue/contrôleur

&nbsp;Pour séparer correctement nos fichiers, nous nous sommes servis de la séparation modèle/vue/contrôleur vu en cours. Nous avons donc géré nos fichiers de la sorte :

<p align="center"><img src="images/CRImages/MVC.PNG"></p>
<p align="center"><em>Schéma du cours expliquant le MVC (modèle/vue/contrôleur)</em></p>

- Nos fichiers de base venant du projet de POO étaient ce qui définissait notre jeu, que ce soit les items ou bien le comportement des personnages ainsi que la génération des tuiles. Nous avons donc mis ces fichiers dans le répertoire "modèle".
- Pour la vue, nous y avons mis notre fichier .fxml ainsi que sa classe appelant le fxmlLoader permettant de le charger dans l'application.
- Enfin, pour le contrôleur, nous avons mis la classe liant la vue au modèle.

## Notre interface

&nbsp;Notre jeu étant de base assez basique, nous n'avons pas eu besoin d'une multitude de scènes. Il n'y avait pas d'états particuliers pour les dialogues ou bien les combats, ce qui nous a simplifié la tâche et permis de tout mettre dans la même interface.

<p align="center"><img src="images/CRImages/POO%20-%20Interface%20Design.png"></p>
<p align="center"><em>Interface imaginée au départ via Lucidchart</em></p>

<p align="center"><img src="images/CRImages/POO_interface_jeu.PNG"></p>
<p align="center"><em>Interface rendue du jeu</em></p>

&nbsp;On peut constater que ces deux interfaces sont quasiment les mêmes à l'exception de l'inventaire et de la sortie des actions. 

&nbsp;A l'origine, l'inventaire devais uniquement être affiché dans la ListeView mais au fil du projet nous en avons décidé autrement. Maintenant, chaque action listant des objets/personnages/passages sont affichés dans la liste. Ceci nous permet en plus d'effectuer des actions sur les objets listés via la méthode OnMouseClicked.

&nbsp;Pareil pour la sortie des actions. On pensait y faire apparaître les points de vie des personnages mais on a préféré garder la description des actions ainsi que la sortie des dialogues.
