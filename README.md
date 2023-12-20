# CIBERIA - Gestión Bibliotecaria 📚


<p align="center" style="line-height: 1.6;">
  ⚙️ Eficiencia en el desarrollo | 📈 Mejora continua |  📚 Documentación completa |  UCLM ❤️ | 
  <a href="https://sonarcloud.io/summary/new_code?id=Ciberia1_biblioteca">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=Ciberia1_biblioteca&metric=alert_status" alt="Quality Gate Status" style="vertical-align: bottom;">
  </a>
</p>



¡Bienvenido al repositorio de CIBERIA - Gestión Bibliotecaria! Aquí encontrarás el código fuente de un programa diseñado para gestionar una biblioteca. Nuestro objetivo es proporcionarte una solución eficiente y fácil de usar para administrar los recursos bibliotecarios.

En este repositorio, encontrarás todas las funcionalidades necesarias para llevar a cabo tareas como agregar libros, buscar libros por título, realizar préstamos y devoluciones, entre otras. Hemos desarrollado este programa siguiendo las mejores prácticas de desarrollo, lo que garantiza un código limpio y fácilmente mantenible.

No olvides revisar nuestra memoria del proyecto, donde encontrarás detalles sobre la implementación y las decisiones tomadas durante el desarrollo.

¡Explora nuestro repositorio y disfruta gestionando tu biblioteca con CIBERIA! 😃✨ 

## Miembros
- Jesús Fernández López (👨‍💻‍👨‍💼 CEO y desarrollador)
- Andrea Ordoño Peña (👩‍💻 Desarrolladora)

## Ejecuciones con Maven
Para ejecutar el programa utilizando Maven, simplemente utiliza el siguiente comando:


mvn spring-boot:run

Este comando realiza varias tareas: compila el código, resuelve las dependencias, inicia un servidor y despliega la aplicación en él. Si se ejecuta correctamente, nuestra aplicación Spring Boot estará en funcionamiento y podremos acceder a ella a través de localhost.


mvn clean

Este comando es útil para eliminar todos los archivos generados por Maven durante sus compilaciones anteriores. Este comando asegura que todos los artefactos previos, como los directorios de destino y los archivos compilados, se eliminen, permitiendo que el próximo ciclo de compilación se realice desde cero. Esta función es especialmente útil para prevenir problemas derivados de compilaciones antiguas y asegurar que todas las dependencias se resuelvan correctamente.


mvn clean test jacoco:report

Este comando realiza varias tareas en una sola línea. mvn clean borra los archivos generados en compilaciones anteriores y con mvn test se ejecutan todos los casos de prueba en el proyecto. jacoco:report genera un informe de cobertura de código utilizando JaCoCo. JaCoCo es una herramienta que mide la cobertura de código de los programas Java, indicando qué partes del código se han ejecutado durante las pruebas y cuáles no.


mvn site:site

Este comando genera un sitio web de documentación para el proyecto. Maven tiene la capacidad de generar un sitio web que incluye información sobre este proyecto, documentación de dependencias, documentación de plugins, informes de pruebas unitarias, entre otros. Al ejecutar mvn site:site, se crea un sitio web en el directorio target/site. Este sitio web está en formato HTML y se puede ver en cualquier navegador web.


mvn verify sonar:sonar

Este comando ejecuta todas las fases de su ciclo de vida hasta la fase 'verify', incluyendo la compilación del proyecto, la ejecución de pruebas y la verificación de las condiciones necesarias para el despliegue del software. Al mismo tiempo, se lanza un análisis de SonarQube, una plataforma de código abierto para la inspección continua de la calidad del código que realiza revisiones automáticas para detectar errores, bugs, vulnerabilidades de seguridad y otros problemas en el código fuente. En resumen, este comando te permite compilar, testear, verificar y analizar la calidad de tu código en un solo paso.

## Extras:
También hemos incluido algunos extras interesantes en este repositorio. Puedes echar un vistazo a nuestro diagrama UML para comprender mejor la estructura del programa. Además, hemos proporcionado un archivo SQL que contiene las instrucciones necesarias para configurar la base de datos utilizada en el sistema.
- [UML](extras/UML.vpp)
- [SQL para nuestra base de datos](extras/BBDD.sql)