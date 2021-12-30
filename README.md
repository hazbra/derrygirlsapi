# Derry Girls API

This project is a java api that contains information on episodes of the TV show Derry Girls

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
            "seasonId": 1
        },
        {
            "id": 2,
            "name": "Episode Two",
            "description": "Everyone is really excited when Sister Michael announces the school trip to Paris; Back at the Quinn house, Uncle Colm is visiting and the whole family are losing the will to live.",
            "seasonId": 1
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
http://localhost:8080/derrygirls/episode/6
{
    "id": 6,
    "name": "Episode Six",
    "description": "Erin is over the moon when she becomes editor of the school magazine; Orla's obsessed by step aerobics; Da Gerry is in trouble with Ma Mary; There's romance for Aunt Sarah.",
    "seasonId": 1
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)