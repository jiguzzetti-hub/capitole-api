# price-api

#### Cómo consumir esta API

* **Get item price by date**

Se debe enviar una petición GET con los parámetros 'brand_id', 'product_id' y 'datetime'. Este último en formato "yyyy-MM-dd HH:mm:ss".
```text
curl --location --request GET 'http://localhost:8082/inditex/prices/getItemPriceByDate/{brand_id}/{product_id}/{datetime}'
```

Se agrega al proyecto la carpeta 'test_cases' donde está la collection de Postman para poder consumir los 5 casos solicitados en la prueba.
Además hay otro archivo con los resultados de los test armados para correr dichos casos, matcheando status_code = 200 y body del response.

