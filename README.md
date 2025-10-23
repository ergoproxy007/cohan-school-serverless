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

### Modelo de Datos Actual:

###### Actualización Modelo de Datos Propuesto:
[PENDIENTE]

###### Validaciones Adicionales:

## Pruebas
[PENDIENTE]

### Monitoreo, KPIs, Evidencias
[PENDIENTE]

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
