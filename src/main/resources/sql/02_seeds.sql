INSERT INTO users (username, email, age)
VALUES ('Ann', 'signoraann@gmail.com', 19);
/*INSERT INTO users (username, age)
VALUES ('Ben', 23); --this row is broken by purpose
INSERT INTO users (username, email, age)
VALUES ('Nastya', '', 9); --this row is broken by purpose*/
INSERT INTO users (username, email, age)
VALUES ('Olga', 'hatters@mail.com', 69);
/*INSERT INTO users (username, email, age)
VALUES ('Polina', 'hatters@mail.com', 29); --that duplicates are blocked by purpose
INSERT INTO users (username, email, age)
VALUES ('Pavel', 'pashka@hmail.com', 30);
INSERT INTO users (username, email, age, status)
VALUES ('Roman', 'chamomile@mail.com', 20, '123456789123456789123'); --this row is broken by purpose
INSERT INTO users (username, email, age)
VALUES ('', 'empty@hmail.com', 21); --this row is broken by purpose
INSERT INTO users (email, age)
VALUES ('null@hmail.com', 34); --this row is broken by purpose*/
INSERT INTO posts(user_id, post_title, content)
VALUES ((SELECT id FROM users WHERE email = 'signoraann@gmail.com'), 'My first post!', 'Hello World!');
INSERT INTO posts(user_id, post_title, content)
VALUES ((SELECT id FROM users WHERE email = 'signoraann@gmail.com'), 'My hobbies', 'I love dancing and swimming :)');
INSERT INTO posts(user_id, post_title)
VALUES ((SELECT id FROM users WHERE email = 'hatters@mail.com'),
        'Why I think databases are actually pretty interesting');
INSERT INTO users (id, username, email)
VALUES (3000000000, 'someuser', 'some@mail.com');
INSERT INTO posts (user_id, post_title)
VALUES (3000000000, 'Post title');
/*DELETE
FROM users
WHERE id IN (SELECT id FROM users WHERE email = 'signoraann@gmail.com');*/
INSERT INTO residents (name, city, age)
VALUES ('Jess', 'Minsk', 16);
INSERT INTO residents (name, city, age)
VALUES ('Karina', 'Krasnodar', 55);
INSERT INTO residents (name, city, age)
VALUES ('Ann', 'Brest', 19);
INSERT INTO residents (name, city, age)
VALUES ('Will', 'Minsk', 22);
INSERT INTO residents (name, city, age)
VALUES ('Love', 'New York', 33);
INSERT INTO residents (name, city, age)
VALUES ('Ken', 'Warsaw', 14);
INSERT INTO residents (name, city, age)
VALUES ('Felix', 'Riga', 26);
INSERT INTO residents (name, city, age)
VALUES ('Vera', 'Gdansk', 36);
INSERT INTO residents (name, city, age)
VALUES ('Damiano', 'Romo', 28);
INSERT INTO residents (name, city, age)
VALUES ('Ethan', 'Palermo', 27);
INSERT INTO residents (name, city, age)
VALUES ('Vic', 'Oslo', 40);
INSERT INTO residents (name, city, age)
VALUES ('Dean', 'Oslo', 34);
INSERT INTO residents (name, city, age)
VALUES ('Stas', 'Riga', 24);
INSERT INTO residents (name, city, age)
VALUES ('Inga', 'Krasnodar', 5);
INSERT INTO residents (name, city, age)
VALUES ('Nastya', 'Brest', 17);
INSERT INTO residents (name, city, age)
VALUES ('Sofa', 'Warsaw', 19);