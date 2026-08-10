CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL CHECK ( username <> '' ),
    email    VARCHAR(100) NOT NULL UNIQUE CHECK ( email <> '' ),
    age      INTEGER CHECK ( age > 0 ),
    status   VARCHAR(20) DEFAULT 'active'
);
INSERT INTO users (username, email, age)
VALUES ('Ann', 'signoraann@gmail.com', 19);
INSERT INTO users (username, age)
VALUES ('Ben', 23);
INSERT INTO users (username, email, age)
VALUES ('Nastya', '', 9);
INSERT INTO users (username, email, age)
VALUES ('Olga', 'hatters@mail.com', 69);
INSERT INTO users (username, email, age)
VALUES ('Polina', 'hatters@mail.com', 29);
INSERT INTO users (username, email, age)
VALUES ('Pavel', 'pashka@hmail.com', 30);
INSERT INTO users (username, email, age, status)
VALUES ('Roman', 'chamomile@mail.com', 20, '123456789123456789123');






