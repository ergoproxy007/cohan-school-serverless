# cohan-school-serverless
cohan-school-serverless

* Demo Cohan - Prueba Técnica Desarrollador de Software

## Descripción Cambio
Se requiere implementar una Aplicación WEB Frontend y Backend que implemente un CRUD para la entidad Person en el siguiente diagrama UML:
###### Modelo Propuesto:
<img width="889" height="477" alt="image" src="https://github.com/user-attachments/assets/e413a14a-94f0-4bde-8fd8-cbfc24946618" />

## Objetivos y Requisitos
* No es necesario implementar los métodos descritos en el diagrama.
* El CRUD se debe realizar sobre la entidad Person.
* El CRUD debe quedar expuesto como servicio web SOAP, REST o ambos.
* Preferiblemente implementar la solución en JAVA.
* Preferiblemente usar Hibernate o JPA como ORM.
* Se puede usar cualquier framework en el back end.
* Se puede usar cualquier motor de base de datos relacional para la persistencia.
* Se puede usar cualquier librería para la visualización en HTML.
* Se puede usar cualquier servidor de aplicaciones.

## Documentación Técnica
* Amazon RDS -> PostgreSQL RDBMS
* Amazon Lambda Funtions -> Java21 Serverless
* Amazon EC2 y API Gateway

<img width="802" height="622" alt="image" src="https://github.com/user-attachments/assets/0737ee04-1584-4ab2-abcb-28d077ef6dcc" />

###### Database Connection:
```
datasource.url=jdbc:postgresql://cohan-postgres.cnciw0u60cxr.us-east-2.rds.amazonaws.com:5432/cohan_school
datasource.username=postgres
datasource.password=postgres
```

### Deploy
* Maven Packaging Application > { excetute command } ->  `mvn clean package`

### Monitoreo, KPIs, Evidencias
* Puedes Visualizar la aplicación desde :
  https://ergoproxy007.github.io/cohan-school-vue/

<img width="972" height="472" alt="image" src="https://github.com/user-attachments/assets/fa346049-022c-4e2f-8f64-97a179e76aa5" />

## Pruebas
Se deja evidencia de pruebas en Front y Back.

| Front - GH-Pages | Back - Postman |
|-------------|---------|
| <img width="864" height="620" alt="Captura de pantalla 2025-10-23 162524" src="https://github.com/user-attachments/assets/f709b4c0-68e7-4c41-999b-da9b7f4b9414" /> | <img width="972" height="472" alt="image" src="https://github.com/user-attachments/assets/e0a0c338-7d7a-4286-910c-2fe2863e5cb8" /> |

* Puedes descargar la colección de POSTMAN para pruebas invocar los endpoint desplegados en AWS:
[COHAN.postman_collection.json.zip](https://github.com/user-attachments/files/23108871/COHAN.postman_collection.json.zip)

## Estrategia de Despliegue
* Despliegue de la nueva versión 1.12.2.
* En caso de ocurrir algún problema, Rollback a la versión 1.12.1.
* Despliegue Canary al 1% para comprobar que el proyecto levanta correctamente.
* Despliegue Canary al 30% durante 30 minutos para monitorear que todo vaya bien.
* Despliegue Blue Green para asentar la nueva versión.

###### Plantillas:
```
Docs Designs:
https://github.com/charliesbot/design-docs/tree/main/spanish#
formatting-syntax:
https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax
```
