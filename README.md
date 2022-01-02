# Derry Girls API

This project is a (work in progress) java api that contains information on episodes of the TV show Derry Girls

## Build & Test

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

## Usage

### Show all seasons
```bash
http://localhost:8080/derrygirls/seasons
```

### Show one season by id
```bash
http://localhost:8080/derrygirls/season/1

{
    "id": 1,
    "name": "Season One",
    "episodes": [
        {
            "id": 1,
            "name": "Episode One",
            "description": "It's the early 90s, the first day of school and a terrible start for 16-year-old Erin as she wakes up to find her cousin Orla reading her diary; Granda Joe announces there's a bomb on the bridge.",
            "seasonId": 1,
            "characters": [
                {
                    "id": 1,
                    "name": "Erin Quinn"
                },
                {
                    "id": 2,
                    "name": "Orla McCool"
                }
        },
        {
            "id": 2,
            "name": "Episode Two",
            "description": "Everyone is really excited when Sister Michael announces the school trip to Paris; Back at the Quinn house, Uncle Colm is visiting and the whole family are losing the will to live.",
            "seasonId": 1,
            "characters": [
                {
                    "id": 1,
                    "name": "Erin Quinn"
                },
                {
                    "id": 2,
                    "name": "Orla McCool"
                },
          ]     
        }
    ]
}
```
### Show all episodes
```bash
http://localhost:8080/derrygirls/episodes
```
### Show one episode by id
```bash
http://localhost:8080/derrygirls/episode/3
{
    "id": 3,
    "name": "Episode Three",
    "description": "The girls are tense about a big exam and naturally jump at a dubious opportunity to get out of it, especially as it involves spending time with the beautiful Father Peter.",
    "seasonId": 1,
    "quotes": [
        {
            "id": 8,
            "description": "Calm down? We're still on William of Orange, Michelle! We haven't so much looked at the famine!",
            "characterId": 3,
            "episodeId": 3
        },
        {
            "id": 11,
            "description": "I just saw it too! The holy smirk, thanks be to God!",
            "characterId": 2,
            "episodeId": 3
        },
    ],
    "characters": [
        {
            "id": 2,
            "name": "Orla McCool",
            "quotes": [
                {
                    "id": 11,
                    "description": "I just saw it too! The holy smirk, thanks be to God!",
                    "characterId": 2,
                    "episodeId": 3
                }
            ]
        },
        {
            "id": 3,
            "name": "Clare Devlin",
            "quotes": [
                {
                    "id": 8,
                    "description": "Calm down? We're still on William of Orange, Michelle! We haven't so much looked at the famine!",
                    "characterId": 3,
                    "episodeId": 3
                }
            ]
        },
    ]
}
        
```
### Show all characters
```bash
http://localhost:8080/derrygirls/characters
```
### Show one character by id
```bash
http://localhost:8080/derrygirls/character/1
{
    "id": 1,
    "name": "Erin Quinn",
    "quotes": [
    {
        "id": 1,
        "description": "She's gone too far this time, Mammy. I mean, what next? Will I catch her trying on my knickers?",
        "characterId": 1,
        "episodeId": 1
    },
    {
        "id": 5,
        "description": "Macaulay Culkin isn't a Protestant, Ma.",
        "characterId": 1,
        "episodeId": 1
    }
    ]
}
```

### Show all quotes
```bash
http://localhost:8080/derrygirls/quotes
```
### Show one quote by id
```bash
http://localhost:8080/derrygirls/quote/1
{
    "id": 1,
    "description": "She's gone too far this time, Mammy. I mean, what next? Will I catch her trying on my knickers?",
    "characterId": 1,
    "episodeId": 1
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)