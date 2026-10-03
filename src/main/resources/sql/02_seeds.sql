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
INSERT INTO users (username, email, age)
VALUES ('Laura', 'lauran.farrell@hotmail.com', 45),
       ('Tom', 'tomi.blick@hotmail.com', 15),
       ('Donald', 'donald.krajcik@hotmail.com', 23),
       ('Rose', 'rosalind.pacocha@yahoo.com', 33),
       ('Nelly', 'nella.rath@yahoo.com', 14),
       ('Vic', 'vicky.hackett@yahoo.com', 24),
       ('Kim', 'kimbery.wehner@hotmail.com', 38),
       ('Grad', 'grady.ernser@hotmail.com', 34),
       ('Tasha', 'tasha.witting@gmail.com', 26),
       ('Bet', 'bettyann.gleichner@gmail.com', 16),
       ('Jess', 'jess.rice@gmail.com', 57),
       ('Bruce', 'bruce.schowalter@yahoo.com', 47),
       ('Dam', 'damion.stokes@yahoo.com', 23),
       ('Clem', 'clementina.lueilwitz@yahoo.com', 9);
INSERT INTO addresses (user_id, city)
VALUES ((SELECT id FROM users WHERE email = 'signoraann@gmail.com'), 'Oslo'),
       ((SELECT id FROM users WHERE email = 'hatters@mail.com'), 'Brest'),
       ((SELECT id FROM users WHERE email = 'lauran.farrell@hotmail.com'), 'Oslo'),
       ((SELECT id FROM users WHERE email = 'tomi.blick@hotmail.com'), 'Palermo'),
       ((SELECT id FROM users WHERE email = 'donald.krajcik@hotmail.com'), 'Romo'),
       ((SELECT id FROM users WHERE email = 'rosalind.pacocha@yahoo.com'), 'Gdansk'),
       ((SELECT id FROM users WHERE email = 'nella.rath@yahoo.com'), 'Riga'),
       ((SELECT id FROM users WHERE email = 'vicky.hackett@yahoo.com'), 'Brest'),
       ((SELECT id FROM users WHERE email = 'kimbery.wehner@hotmail.com'), 'Warsaw'),
       ((SELECT id FROM users WHERE email = 'grady.ernser@hotmail.com'), 'New York'),
       ((SELECT id FROM users WHERE email = 'tasha.witting@gmail.com'), 'Brest'),
       ((SELECT id FROM users WHERE email = 'bettyann.gleichner@gmail.com'), 'Minsk'),
       ((SELECT id FROM users WHERE email = 'jess.rice@gmail.com'), 'Krasnodar'),
       ((SELECT id FROM users WHERE email = 'bruce.schowalter@yahoo.com'), 'Warsaw'),
       ((SELECT id FROM users WHERE email = 'damion.stokes@yahoo.com'), 'Brest'),
       ((SELECT id FROM users WHERE email = 'clementina.lueilwitz@yahoo.com'), 'Vena'),
       ((SELECT id FROM users WHERE email = 'some@mail.com'), 'Riga');

INSERT INTO bankaccount(account_name, balance)
VALUES ('A', 100.0),
       ('B', 50.5);

/*INSERT INTO addresses (user_id, city)
VALUES ((SELECT id FROM users WHERE email = 'signoraann@gmail.com'), 'Oslo');*/

