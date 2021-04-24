# Projet POO - Banquise
### Yann Berthelot, Vincent Commin et Louis Leenart

Ce projet a pour but de nous initier à la gestion de projet et à la mise en place d'un jeu comportant une structure de données complexe.

Note: Ce programme est encore en développement. Certaines fonctionnalités ne sont pas encore disponibles.

## Compilation
Pour compiler notre jeu, il faut utiliser la commande suivante :
```shell
javac -d ./out src/**/*.java
```

## Installation
### Windows
Pour lancer notre jeu sous Windows, il existe plusieurs méthodes pour lancer notre jeu. 
- Via ligne de commande :
```shell
cd out # Placez vous dans le dossier "out"
java modele.Game.Main 
```
- Via raccourci :
Ouvrez le fichier `RUN_WINDOWS.bat` qui se trouve dans la racine.
### Linux
Pour lancer notre jeu sous Linux, il existe plusieurs méthodes pour lancer notre jeu.
- Via ligne de commande :
```shell
cd out # Placez vous dans le dossier "out"
java modele.Game.Main 
```
- En exécutant le raccourci :
  Ouvrez le fichier `RUN_LINUX.sh` qui se trouve dans la racine via `bash RUN_LINUX.sh`


## Utilisation
L'utilisation de notre jeu se fait uniquement via la console de commande. Pour se faire, il existe plusieurs commandes pour réaliser des actions. La liste des commandes est résumée dans la commande help. L'utilisation est la suivante :

Commande GO permet de déplacer le joueur dans la direction souhaitée. Il faut qu'il existe une liaison (crossing) dans cette direction
```
go <dir_char> (ex: go N)
```
Pour obtenir la liste des liaisons, on peut utiliser la commande `look` ou `list crossing`. Le résultat est alors possiblement le suivant :
```shell
> look
This tile contains;
[2] crossings:
	[N] Pathway - close
	[S] Pathway - open
[1] characters:
	[0] Player (you)
[0] items:
	No item on this tile
> go S
```
La liste des commandes est la suivante :
```shell
debug
take <item_index>
help ?<order>
quit
save
load
info
talk <character_index>
open <crossing_direction>
list <target>
use <item_index> <target_character_index>
go <crossing_direction>
trade 
look
player
```
On note que certains ordres ne sont pas encore implémentés.

Pour completer le jeu, il faut parler (via la commande `talk`) au personnage appelé `Chief Scientist` qui est forcément présent quelque part sur la banquise.