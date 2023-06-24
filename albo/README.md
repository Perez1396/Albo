# API Marvel

Con el objetivo de persistir en una base de datos local, únicamente los personajes Iron Man (ironman) y Captain America (capamerica) se crea esta aplicación y los fines son:

- Listado de cómics en los que aparece cada personaje.
- Listado de cada uno de los colaboradores creativos, y su rol, que participa en cada uno de los cómics del punto anterior.

## Requisitos previos

Asegúrate de tener instalado lo siguiente:

- Java (versión 8)
- Gradle (versión 6.8.3)

## Configuración

1. Clona el repositorio:

   ```shell
   git clone https://github.com/Perez1396/Albo.git
2. Dentro del repositorio hay dos archivos .sh que son assemble.sh y avengers.sh. El primero es para construir (build) la aplicación y la segunda es para correrla.

## Uso
Uso
Una vez que la aplicación esté en funcionamiento, podrás acceder a la información sincronizada a través de los siguientes puntos finales:

Endpoint de cómics por personaje:

- GET /characters/{characterId}/comics
- 
Devuelve el listado de colaboradores creativos y su rol en el cómic con el ID especificado.

Endpoint de colaboradores creativos por cómic:

- GET /character

Devuelve el listado de cómics en los que aparece el personaje con el ID especificado.

Reemplaza {characterId} en el punto anterior con el ID correspondiente a Iron Man (ironman) y Captain America (capamerica) respectivamente.

## Base de datos
Se hizo uso de la base de datos en caché H2 para tener la facilidad de generar un esquema y persistir datos solo con correr la aplicación y ejecutar peticiones en postman

## Collection
Se añade una collection de postman que se puede descargar, importar y usar para verificar los endpoints.
Esta collection se encuentra dentro de la carpeta resources (albo/src/main/resources)

## Scripts shell
En el root del proyecto se encuentran dos archivos:
- assemble.sh, es el encargado de construir la aplicación.
- avengers.sh, es el encargado de correr la aplicación en el puerto 8080

## Respuestas

- Respuesta al buscar los creadores para Iron Man

``` json {
    "lastSync": "2023-06-24T14:56:12.815+0000",
    "editors": [
        "Tom Brevoort",
        "Elizabeth Pyle",
        "Darren Shan",
        "Annalise Bissa",
        "Jeff Youngquist"
    ],
    "writers": [
        "Luke Mcdonnell",
        "Amy Chu",
        "Ryan North",
        "Kurt Busiek",
        "Christopher Cantwell",
        "Jeremy Adams",
        "Jason Aaron",
        "Brian Michael Bendis",
        "Murewa Ayodele",
        "Jed Mackay",
        "Jim Zub",
        "Gerry Duggan"
    ],
    "colorists": [
        "Emily Warren",
        "Laura Martin",
        "Michael Spicer",
        "Bryan Valenza",
        "Chris Sotomayor",
        "Jordie Bellaire",
        "Michael Kelleher"
    ]
}
``` 

- Respuesta los personajes asociados a los comics

``` json {
    "lastSync": "2023-06-24T15:23:34.002+0000",
    "characters": [
        {
            "name": "3-D Man",
            "comics": [
                "Avengers: The Initiative (2007) #14",
                "Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)",
                "Avengers: The Initiative (2007) #15",
                "Avengers: The Initiative (2007) #16",
                "Avengers: The Initiative (2007) #17",
                "Avengers: The Initiative (2007) #18",
                "Avengers: The Initiative (2007) #18 (ZOMBIE VARIANT)",
                "Avengers: The Initiative (2007) #19",
                "Deadpool (1997) #44",
                "Marvel Premiere (1972) #35",
                "Marvel Premiere (1972) #36",
                "Marvel Premiere (1972) #37"
            ]
        },
        {
            "name": "A-Bomb (HAS)",
            "comics": [
                "FREE COMIC BOOK DAY 2013 1 (2013) #1",
                "Hulk (2008) #53",
                "Hulk (2008) #54",
                "Hulk (2008) #55"
            ]
        },
        {
            "name": "A.I.M.",
            "comics": [
                "Ant-Man & the Wasp (2010) #3",
                "Avengers (1998) #67",
                "Avengers (1963) #87",
                "Avengers and Power Pack Assemble! (2006) #2",
                "Avengers and Power Pack (2017) #3",
                "Avengers and Power Pack (2017) #4",
                "Avengers and Power Pack (2017) #5",
                "Avengers and Power Pack (2017) #6",
                "Avengers by Brian Michael Bendis: The Complete Collection Vol. 2 (Trade Paperback)",
                "Avengers Unlimited Infinity Comic (2022) #17",
                "Avengers Vol. 2: Red Zone (Trade Paperback)",
                "Avengers Vol. II: Red Zone (Trade Paperback)",
                "Captain America (1998) #28",
                "Captain America (1968) #132",
                "Captain America (1968) #133",
                "Captain America by Mark Waid, Ron Garney & Andy Kubert (Hardcover)",
                "Defenders (1972) #57",
                "Incredible Hulks (2010) #606 (VARIANT)",
                "Indestructible Hulk (2012) #3",
                "Iron Man (2012) #1"
            ]
        },
        {
            "name": "Aaron Stack",
            "comics": [
                "Dark Avengers (2012) #177",
                "Dark Avengers (2012) #179",
                "Dark Avengers (2012) #180",
                "Dark Avengers (2012) #181",
                "Dark Avengers (2012) #182",
                "Dark Avengers (2012) #183",
                "Hulk (2008) #43",
                "Universe X (2000) #6",
                "Universe X (2000) #7",
                "Universe X (2000) #8",
                "Universe X (2000) #9",
                "Universe X (2000) #10",
                "Universe X (2000) #11",
                "Universe X (2000) #12"
            ]
        }
    ]
}
``` 
## Swagger
Se implementa swagger como herramienta de documentación de endpoints, parametros y modelos.
