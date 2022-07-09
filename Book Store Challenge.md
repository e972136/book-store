# Book Store Challenge

## Environment:

-   Java version: 1.8 o superior

-   Maven version: 3.\*

-   Spring Boot version: 2.4.0 o superior

-   Repositorios en memoria

## Notas:

-   La aplicacion debe de aceptar las peticiones por el puerto **`8000`**, por defecto el puerto es  `8080` y debe de cambiarse.
    

## Requerimientos

Crear los endpoints para un sistema de tienda de libros. La definición y detalles del requerimiento estan detallados abajo.

### Book store CMS

Cada `Book` es un objecto JSON y debe de tener las siguientes propiedades:

-   `bookId`: identificador unico del libro

-   `title`: titulo del libro

-   `description`: informacion acerca del libro

-   `stock`: cuantos items de este libro estan disponibles

-   `salePrice`: cuanto costara el libro

-   `available`: indicador que muestra si el libro puede ser vendido

Es requerido implementar `/books` REST endpoint para las siguientes operaciones:

`POST` requests to `/books`:

-   Debe almacenar el libro dentro del repositorio y mostrar la informacion guardada con status code 201
    
-   Title, stock y salePrice son requeridos

-   `available` debe ser `true` por defecto

**Curl Request**

``` bash
curl --request POST \
    --url https://localhost/books \
    --header 'Accept: application/json' \
    --data '{
        "title": "El silencio de la ciudad blanca",
        "description": "Libro de thriller",
        "stock": 10,
        "salePrice": 450.50,
        "available": true
    }'
```

**Response Body**

``` json
{
  "bookId": 1,
  "title": "El silencio de la ciudad blanca",
  "description": "Libro de thriller",
  "stock": 10,
  "salePrice": 450.50,
  "available": true
}
```

`PUT` request to `/books/{bookId}`

-   Debe actualizar el libro dado el id y payload y debe de responder con status code 200
    
-   Title, stock, salePrice son requeridos

-   Availability by default should be `true`

**Curl Request**

``` bash
curl --request PUT \
  --url https://localhost/books/1 \
  --header 'Accept: application/json' \
  --data '{
    "title": "El silencio de los inocentes",
    "description": "otro libro de thriller",
    "stock": 10,
    "salePrice": 450,
    "available": true
  }'
```

**Response Body**

``` json
{
  "bookId": 1,
  "title": "El silencio de los inocentes",
  "description": "otro libro de thriller",
  "stock": 10,
  "salePrice": 450,
  "available": true
}
```

`PATCH` request to `/movies/{movieId}`

-   Debe de actualizar el libro dado el id y payload y retornar status code 200

**Curl Request**

``` bash
curl --request PATCH \
  --url https://localhost/books/1 \
  --header 'Accept: application/json' \
  --data '{
    "title": "El silencio de los inocentes",
    "description": "otro libro de thriller",
    "stock": 10,
    "salePrice": 450,
    "available": true
  }'
```

**Response Body**

``` json
{
  "bookId": 1,
  "title": "El silencio de los inocentes",
  "description": "otro libro de thriller",
  "stock": 10,
  "salePrice": 450,
  "available": true
}
```

`DELETE` request to `/books/{bookId}`

-   Debe de borrar el libro dado el id

-   Se debe de realizar un borrado en cascada

**Curl Request**

``` bash
curl --request DELETE \
  --url https://localhost/books/1 \
  --header 'Accept: application/json'
```

**Http Body**

``` html
HTTP/1.1 200 OK
```

`GET` requests to `/books`:

-   Debe de devolver una coleccion de libros. Por defecto, solo los libros disponibles deben de estar en el response, a menos que el parametro `unavailable` indique lo contrario. `unavailable=true` retorna todas los libros.
    
-   Debe responder una book’s page con paginación con parametros `size` y `page`, y sorting con `sort` parameters (sort parameter
    syntax `sort=<field[,asc|,desc]>`). Por defecto, cada pagina debe contener al menos 12 elementos y comenzar en la primera pagina con title ascendente. `page=1`, `size=20`, `sort=description,asc`
    
-   Debe de filtrar los libros por el parametro `title`. Donde el parametro es case-insensitive y puede ser solo una parte del titulo del libro.
    
-   Debe retornar un objecto conteniendo la propiedad `content`, que contenga el listado de libros encontrados.
    
-   Debe de retornar un objecto con las siguientes propiedades:

    -   `size`: el tamaño de la página

    -   `numberOfElements`: el número de elementos que se encuentran en la pagina actual
    
-   `totalElements`: el número de elementos encontrados dado el criterio
        
    -   `totalPages`: el número de paginas disponibles

    -   `number`: el número de página actual

**Curl Request**

``` bash
curl --request GET \
  --url https://localhost/books \
  --header 'Accept: application/json'
```

**Response Body**

```json
{
    "content": [
        {
            "id": 1,
            "title": "El silencio de la ciudad Blanca",
            "description": "libro recomendado",
            "stock": 10,
            "salePrice": 450.50,
            "available": true
        }
    ],
    "size": 12,
    "numberOfElements": 1,
    "totalElements": 1,
    "totalPages": 1,
    "number": 1
}
```



### Ventas

cada `sale` es un objecto JSON con las siguientes propiedades:

-   `id`: identificador unico

-   `bookId`: identificador unico del libro vendido

-   `customerEmail`: email del cliente que compra el libro

-   `price`: precio del libro que ha sido vendido

Se necesita que se implemente el `/sales` REST endpoint para las siguientes operaciones:

`POST` requests to `/sales`:

-   Debe de responder  201 cuando el libro haya sido vendido.

-   Solo los libros disponibles pueden ser vendidos.

-   El Stock debe de actualizarse.

**Curl Request**

``` bash
curl --request POST \
  --url https://localhost/sales \
  --header 'Accept: application/json' \
  --data '{
    "bookId": 1,
    "customerEmail": "customer@domain.com"
  }'
```

**Response Body**

``` json
{
  "bookId": 1,
  "customerEmail": "customer@domain.com",
  "price": 450.50
}
```

### Likes

Cada `like` es un objecto JSON con las siguientes propiedades:

-   `bookId`: identificador unico del libro gustado

-   `likes`: total likes dados por los clientes

-   `customers`: arreglo de los **distinct** clientes que han dado like al libro

Se necesita implementar el `/likes` REST endpoint para las siguientes operaciones:

`POST` requests to `/likes`:

-   Debe de responder 201 cuando un libro haya recibido un like por un cliente.

-   Solo los libros disponibles pueden recibir like.

**Curl Request**

``` bash
curl --request POST \
  --url https://localhost/likes \
  --header 'Accept: application/json' \
  --data '{
    "bookId": 1,
    "customerEmail": "customer@domain.com"
  }'
```

**Response Body**

``` json
{
  "bookId": 1,
  "likes": 1,
  "customers": ["customer@domain.com"]
}
```

### Transactions

Cada `transaction` es un objecto JSON con las siguientes propiedades:

-   `bookId`: identificador unico del libro vendido

-   `likes`: total likes dados por los clientes

-   `customers`: arreglo de los **distinct** clientes que han dado like al libro

Se necesita implementas el `/transactions` REST endpoint para las siguientes operaciones:

`GET` requests to `/transactions/books/{bookId}`:

-   Debe de retornar la información de las transacciones del libro dado el id.
    
-   Debe retornar 404 cuando no se encuentre información.

-   Debe de retornar la informacion dado el rango de fechas, si se ha realizado un venta dentro del rango de estas.
    `from=2000-01-01` `to=2021-12-31`

**Curl Request**

``` bash
curl --request GET \
  --url https://localhost/transactions/books/1?from=2000-01-01&to=2021-12-31 \
  --header 'Accept: application/json'
```

**Response Body**

``` json
{
  "bookId": 1,
  "sales": [
    "2019-09-19",
    "2012-02-24"
  ],
  "totalRevenue": 901,
  "customers": [
    "oiglesias@gmail.com",
    "rmilla@gmail.com"
  ]
}
```

