# warehouse-inventory
This Project for the warehouse inventories.

[![Contributors][contributors-shield]][contributors-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- ABOUT THE PROJECT -->
# Java Project
This is a web project using Java 1.8, Spring Boot, Spring JPA, Spring Data Rest, Spring Security, Hibernate, Postgres, Lombok


## Steps to Use the Project

1. Install Postgres db into your localhost with user _**postgres**_ and password _**postgres**_.
2. Run the postgres schema which attached to the into the resources section in the common module.
3. Run this command ```mvn clean install``` then run the main class in the warehouse-service.
4. Open the browser and open this url `http://localhost:8080/welcome`, it will show you the welcome screen.
5. Open the test section and test all the authorized endpoints that are mentioned there according to the roles in the 'SecurityConfiguration' class using Postman.
6. The first role name is ```user``` and the password is ```user```, the second role name is ```sales_person``` and the password is ```sales_person```.
7. Wait for 1 minutes and run the authorized endpoints again, the session will be invalidated and will redirect to the login page again to login with the ```user``` or the ```sales_person```.


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/AbdelazizSaid250
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/abdelaziz-said-4a9b12127# warehouse-inventory
