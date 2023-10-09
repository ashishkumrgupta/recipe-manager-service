## Sample Curl Requests for each endpoint

### Create a Recipe

```bash
curl -v -X POST -H "Content-Type: application/json" -d '{"isVegetarian":true,"suitableFor":3,"ingredients":["tomato","onion"],"instructions":"Thisistestinstuctions"}' 'http://localhost:8080/recipe-manager/recipes'
```

### Get a Recipe based on 'Indicator if the dish is vegetarian.'

```bash
curl -v -X GET -H "Content-Type: application/json" -d '{"category": "IS_VEGETARIAN","isVegetarian": true}' 'http://localhost:8080/recipe-manager/recipes'
```

### Get a Recipe based on 'Indicator displaying the number of people the dish is suitable for.'

```bash
curl -v -X GET -H "Content-Type: application/json" -d '{"category": "SUITABLE_FOR","suitableFor": 3}' 'http://localhost:8080/recipe-manager/recipes'
```

### Get a Recipe based on 'Display ingredients as a list.'

```bash
curl -v -X GET -H "Content-Type: application/json" -d '{"category": "INGREDIENTS","ingredients":["onion"]}' 'http://localhost:8080/recipe-manager/recipes'
```

### Get a Recipe based on 'Date and time of creation (formatted as dd‐MM‐yyyy HH:mm).'

```bash
curl -v -X GET -H "Content-Type: application/json" -d '{"category": "CREATION_DATETIME","creationDate": "01-05-2022 22:46"}' 'http://localhost:8080/recipe-manager/recipes'
```

### Get a Recipe based on 'Cooking instructions.'

```bash
curl -v -X GET -H "Content-Type: application/json" -d '{"category": "COOKING_INSTRUCTIONS","instructions": "This is test instructions"}' 'http://localhost:8080/recipe-manager/recipes'
```

### Update a Recipe

```bash
curl -v -X PUT -H "Content-Type: application/json" -d '{"id":1, "isVegetarian":true,"suitableFor":1,"ingredients":["tomato","onion"],"instructions":"Updatedinstuctions"}' 'http://localhost:8080/recipe-manager/recipes'
```

### Delete a Recipe

```bash
curl -v -X DELETE 'http://localhost:8080/recipe-manager/recipes?id=1'
```