# Progetto Palestra Discipline Orientali e Pilates 🧘‍♀️🤸‍♂️

## Introduzione 🚀

Questo progetto promuove le attività di una palestra specializzata in discipline orientali (Qigong, Tai Chi) e Pilates. Offre informazioni su discipline, insegnanti e calendario, con area utenti per l'acquisto di abbonamenti.

## Team di Sviluppo 🧑‍💻👩‍💻

*   Marjan Zagharitafreshi ( [email protected] )
*   Giorgia Favale ( [email protected] )
*   Maurizia Porcaro ( [email protected] )
*   Adriana Pollio ( [email protected] )
*   Bianca Maria De Focatiis ( [email protected] )
*   Ludovica Poi ( [email protected] )

## Tecnologie Utilizzate 💻

| Tecnologia    | Badge                                   |
|---------------|-----------------------------------------|
| HTML          | [![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)](https://www.w3.org/html/) |
| CSS           | [![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)](https://www.w3.org/Style/CSS/)|
| JavaScript    | [![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)](https://www.javascript.com/)|
| Java          | [![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/it/)|
| Spring Boot   | [![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)|
| MySQL/phpMyAdmin | [![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/) |
| PHP          | [![PHP](https://img.shields.io/badge/php-%23777BB4.svg?style=for-the-badge&logo=php&logoColor=white)](https://www.php.net/) |

## Struttura del Progetto 📂
palestra-webapp/
├── README.md # Questo file 📖
├── database/ # Contiene il dump del database 💾
│ └── palestra.sql
├── frontend/ # Contiene il codice HTML, CSS e JavaScript 🌐
│ ├── index.html # Pagina introduttiva
│ ├── discipline/ # Cartella per le pagine delle discipline
│ │ ├── qigong.html
│ │ ├── tai-chi.html
│ │ ├── pilates.html
│ │ └── ...
│ ├── css/
│ │ └── style.css
│ ├── js/
│ │ └── script.js
│ └── login.html
│ └── registrazione.html
├── backend/ # Contiene il codice Java e i file di configurazione Spring ☕
│ ├── src/
│ │ └── main/
│ │ ├── java/
│ │ │ └── com/palestra/
│ │ │ ├── controller/
│ │ │ ├── model/
│ │ │ ├── service/
│ │ │ └── ...
│ │ └── resources/
│ │ └── application.properties
│ ├── pom.xml # File di configurazione Maven
├── php/ # Contiene eventuali script PHP
│ └── connessione.php # esempio file di connessione al db
└── ...


## Installazione e Configurazione 🛠️

1.  **Clonare il repository:**
    ```bash
    git clone [URL del repository GitHub]
    cd palestra-discipline-orientali
    ```

2.  **Database (MySQL/phpMyAdmin):**
    *   Importare `database/palestra.sql` in phpMyAdmin.

3.  **Backend (Java/Spring):**
    *   Modificare `backend/src/main/resources/application.properties`:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/palestra?useSSL=false&serverTimezone=UTC
        spring.datasource.username=[tuo username]
        spring.datasource.password=[tua password]
        ```
    *   Eseguire `mvn spring-boot:run` nella directory `backend/`.

4.  **Frontend:**
    *   Aprire i file HTML in `frontend/` con il browser.
    *   Configurare `frontend/js/script.js` per puntare al backend (es. `http://localhost:8080`).

## Presentazione (Demo) 🎬

1.  **Nuovo Account:** Mostrare il processo di registrazione.
2.  **Login:** Accedere con un account esistente.
3.  **Acquisto Abbonamento:** Selezionare disciplina e tipo, simulare il pagamento.
4.  **Storico Acquisti:** Visualizzare gli abbonamenti sottoscritti.

## Note Aggiuntive ⚠️

*   Progetto demo, richiede ulteriori sviluppi per produzione.
*   Integrazione PayPal simulata.
