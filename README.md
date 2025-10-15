# file-manager
> Par Frédéric LE COAT

## Objectifs
J'ai actuellement un **NAS** sur lequel je dépose tous mes fichiers. J'avais pour habitude de les ranger correctement de façon méthodique. Cependant mon système de rangement a régulièrement changer au cours des années en fonction de mes activités. Ainsi, une grande partie de mes fichiers se retrouvent dans des dossiers qui n'ont pas vraiment de sens. 

L'intéret de cette application est de gérer automatiquement le rangement de mes fichiers. Pour cela, je n'aurai qu'à les ranger dans un répertoire précis pour qu'il s'occupe de trier le fichier. Ensuite l'application récupéreras toutes les informations des fichiers dans le dossier source puis les transmettra à une IA (Chat GPT) qui devra analyser les méta-données et catégoriser le fichier. Enfin, cette dernière suggerera de mettre le fichier dans un certain dossier et je devrais valider ou non cette opération. Si je valide, le fichier est déplacé et le dossier est créé si ce n'étais pas déjà le cas.

## Outils utilisés 
Pour réaliser ce projet, je vais utiliser plusieurs outils :
- **Spring Boot** : pour le backend
- **Postgresql** : pour la base de données
- **Docker** : pour la conteneurisation

Dans un second temps :
- **Angular** : pour l'interface web qui permettra de valider ou non le tri

## Fonctionnalités :
### Configurer le programme 
- changer le dossier source
- changer le dossier destination
- changer le dossier tampon
- changer la clé api de l'IA

### Scan de fichier
- Récupérer toutes les informations sur tous les fichier à la source

### Analyse IA
- Comprendre les méta-données
- Renvoyer un JSON contenant le chemin d'accès suggéré et le taux de confiance qu'il a

### Validation de l'opération
- Lister toutes les opération en attente
- Valider ou annuler les opération en attente

### Déplacement du fichier
- Déplacer le fichier à l'emplacement désigné
- Créer le dossier si nécessaire
