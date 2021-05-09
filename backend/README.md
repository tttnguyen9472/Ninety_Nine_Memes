# Ninety-Nine Memes

Technologies: Java, Spring Boot.

We built this meme generator social media platform during an internal hackathon organized by Green Fox Academy.

Contributors: Dániel Babinszky, Péter Tóth, Thanh Tung Nguyen

## Getting Started

### Prerequisites

 * JDK 11
 * Intellij IDEA
 * MySQL 8 
 
### Run the app locally

#### Clone this repository

```
> git clone https://github.com/MSCardo/Ninety_Nine_Memes.git
```

If you want to see the current status of the development, switch to branch `development`

```
> git checkout development
```

#### Initialise a database
Create the database and initialize with some seed data (basic roles, technologies, example apprentices...)

```
> mysql -u <username> -p

mysql> create database memes;
mysql> \q
```

#### Set up Lombok

Our project uses [Lombok](https://projectlombok.org/), to enable it in IntelliJ you have to add its plugin 
 * follow these [instructions](https://projectlombok.org/setup/intellij)
 * restart IntelliJ IDEA
 * make sure that annotation processing is enabled in `Settings>Build>Compiler>Annotation processors`
 
#### Environment variables

**Database connection**

| Key | Value |
| --- | ----- |
|DATABASE_URL | jdbc:mysql://localhost/memes?serverTimezone=UTC |
|DATABASE_USERNAME | *your local mysql username* |
|DATABASE_PASSWORD | *your local mysql password* |

**Others**

| Key | Value |
| --- | ----- | 
|SECRET_KEY| this will be used for token generation, you can use any random string |
