# Spring Boot Application Starter

Welcome to our Spring Boot Application Starter! This setup is designed to streamline your development process, featuring hot-reload capabilities, database migrations with Flyway, MySQL in Docker, and dynamic HTML rendering with Thymeleaf. Get started quickly with just `docker-compose up`.

## Features

- **Spring Boot & Maven**: Simplify your Java development with Spring Boot and manage dependencies with Maven.
- **Hot-Reload with Docker**: Develop seamlessly with hot-reload enabled through Docker, allowing instant feedback.
- **Flyway Database Migrations**: Manage your database schema with Flyway for reliable migrations.
- **MySQL in Docker**: Use a pre-configured MySQL database within Docker for a consistent development environment.
- **Thymeleaf Templates**: Create dynamic HTML views with Thymeleaf, a modern server-side Java template engine.
- **User & Event Registration System**: Built-in functionality for registering users and events.
- **OMDB API Consumption**: Example of consuming external APIs, such as OMDB for movie data.

## Getting Started

### Prerequisites

- Docker
- Docker-Compose

### Installation

1. Clone the repository:

    ```bash
    git clone git@github.com:satodu/springboot-docker.git
    cd springboot-docker
    ```

2. Copy the env file based in file ```.env.example```:

    ```bash
    cp .env.example .env
    ```

2. Start the environment with docker-compose:

    ```bash
    docker-compose up
    ```

Your application and MySQL database will now be accessible at `http://localhost:6868`.

## Features in Detail

### Thymeleaf Integration

Thymeleaf is integrated for rendering dynamic HTML content. It allows for clear separation of server-side logic and HTML design, enhancing maintainability and developer productivity.

#### User & Event Registration Pages

Access `/users/new` and `/events/new` to interact with the user and event registration forms, dynamically rendered using Thymeleaf templates.

#### OMDB API Example

Fetch movie data with an example controller method using Thymeleaf to display the results:

```java
@GetMapping("/movies/{title}")
public String getMovieData(@PathVariable String title, Model model) {
    // Example of consuming OMDB API
    String omdbUrl = "http://www.omdbapi.com/?t=" + title + "&apikey=yourapikey";
    Movie movie = restTemplate.getForObject(omdbUrl, Movie.class);
    model.addAttribute("movie", movie);
    return "movie-view";
}
```

Replace yourapikey with your OMDB API key. Thymeleaf will render movie-view.html to display the fetched movie data.

### Database Migrations with Flyway
Flyway simplifies database migrations. Add new migrations in src/main/resources/db/migration to evolve your schema.

### Hot-Reload Setup
Spring Boot DevTools and Docker facilitate hot-reload. Enjoy immediate updates to your application without restarts.

### Acknowledgements
Thank you to all contributors for their hard work and dedication to this project.

### License
This project is under the MIT License - see the LICENSE.md file for details.

