# online-game-of-life

setx /m JAVA_HOME "C:\Users\Dinkertons\.jdks\corretto-17.0.7"

cd back-end
./mvnw spring-boot:run

docker-compose up -d

base account: test / test
Can be changed in 
src/main/resources/application.properties


Idea: after the end of the match : show "review the game using this ID" and the ID is the one of the game.
Create a route to get a history of every moves of a game, it will return all the tiles of the match
like : 

1: [ ..., ..., ...]
2: [ ..., ..., ...]
