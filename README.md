# Spring Discord Management System
Powerful Discord Server management system (website) based off on Spring (Boot, Security, Data, MVC) and H2 Database

## Services
| Type                        | Badges                                                               |
| --------------------------- | -------------------------------------------------------------------- |
| **Code quality:**           | [![maintability-icon]][maintability]                                 |
| **Continuous Integration:** | [![travis-icon]][travis] [![appveyor-icon]][appveyor]                |

## Features
- [x] Login through Discord (OAuth2)
- [x] Home page
- [x] Profile page
- [ ] todo

## Endpoints

| Method                                             | Optional query parameters      | Success status codes   | Error status codes |
| -------------------------------------------------- | --------------------------     | ---------------------  | ------------------ |                   
| -------------------                                |  ------------------------      | ---------------------  | ------------------ |

## Configuration
Create file named `application.yml` in your resource directory, with the following content:
```yml
spring:
  # Java Persistence Api
  jpa:
    open-in-view: true
    generate-ddl: true
    hibernate:
      ddl-auto: update
    show-sql: true

  # Datasource
  datasource:
    url: "jdbc:h2:~/discordms"
    username: "root"
    password: "root"
    driver-class-name: org.h2.Driver

  # H2 Embedded
  h2:
    console:
      enabled: true
      path: "/console"

  # Security OAuth2
  security:
    oauth2:
      client:
        registration:
          discord:
            client-id: "client-id"
            client-secret: "client-secret"
            clientAuthenticationMethod: post
            clientAuthenticationScheme: form
            authorizationGrantType: authorization_code
            scope:
              - identify
              - email
              - connections
              - guilds
            redirectUriTemplate: "{baseUrl}/login/oauth2/code/discord"
            clientName: discordms
        provider:
          discord:
            authorizationUri: https://discordapp.com/api/oauth2/authorize
            tokenUri: https://discordapp.com/api/oauth2/token
            userInfoUri: https://discordapp.com/api/users/@me
            usernameAttribute: username

  # Jackson
  jackson:
    serialization:
      indent_output: true
```

## Contributing
- Choose one of tasks to do from Issues,
- Fork it,
- Commit and push your changes,
- Create pull request,
- Wait for the review.

The first rule, the only rule, is that you need use [Google Code Style][google-code-style]

## License
See the [License][license] file.

[maintability-icon]: https://api.codeclimate.com/v1/badges/61b7460c1d6a639b2f4c/maintainability
[maintability]: https://codeclimate.com/github/bmstefanski/spring-discord-management-system/maintainability
[appveyor-icon]: https://ci.appveyor.com/api/projects/status/ntharbqfx7i861u6?svg=true
[appveyor]: https://ci.appveyor.com/project/bmstefanski/spring-discord-management-system
[travis-icon]: https://travis-ci.org/bmstefanski/spring-discord-management-system.svg?branch=master
[travis]: https://travis-ci.org/bmstefanski/spring-discord-management-system
[license]: https://github.com/bmstefanski/spring-discord-management-system/blob/master/LICENSE
[google-code-style]: https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml
