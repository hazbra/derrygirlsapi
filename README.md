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
    "name": "Season One"
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
    "season": {
        "id": 1,
        "name": "Season One"
    }
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)