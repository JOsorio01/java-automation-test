docker-compose -f grid.yml up --scale chrome=2 -d

BROWSER=chrome docker-compose up

docker-compose -f grid.yml up --scale firefox=2 -d

BROWSER=firefox docker-compose up

docker-compose -f grid.yml down
docker-compose down
