# ATM JSF - Proyecto listo para Tomcat

## Requisitos
- Java 17
- Apache Tomcat 10.x
- Maven

## Importar en IntelliJ
1. File -> New -> Project from Existing Sources -> selecciona el pom.xml
2. Ejecutar `mvn package` para generar `atm-jsf.war` en target/
3. Desplegar en Tomcat (OPCIONAL: configurar Tomcat en IntelliJ y Run)

## Notas
- El proyecto incluye Mojarra (jakarta.faces) para que funcione en Tomcat.
- Los datos de clientes están en `src/main/resources/clientes.csv`.
- Usuarios de prueba: 12345(pin1111), 67890(pin2222), 54321(pin3333), 98765(pin4444), 11223(pin5555)
