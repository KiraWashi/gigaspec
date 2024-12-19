Membres du groupe : Léo PAUGAM, Anouk GOUHIER-DUPUIS, Nathanaël MONNIER

Nous avons développé toutes les parties jusqu'à la partie numéro 6 sur les lots d'instances, ce qui inclut :
- 2) : la version minimale
- 3) : Associations unidirectionnelles entre entités
- 4) : Héritage et Modèle
- 5) : Valeurs initiales des attributs

Nous avons deux fichiers XML :

Le fichier Param.xml contient tous les paramétrages d'imports et de packages pour la génération de code à partir du
modèle.
Le fichier Model.xml contient le modèle en lui-même avec les models, classes et attributs à générer.

Pour voir la génération à partir du fichier Model.xml, vous pouvez exécuter le test appelé testGeneration dans le fichier
de test JavaGeneratorTest. La génération sera ensuite présente dans le terminal.
La lecture des adresses mémoires dans le fichier Model.xml est bien prise en compte et permet de référencer d'autres types.