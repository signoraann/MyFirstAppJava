SELECT users.username, posts.post_title
FROM users
         INNER JOIN posts ON users.id = posts.user_id;

SELECT COUNT(*)
FROM users;

SELECT AVG(age)
FROM users;

SELECT SUM(age)
FROM users;

SELECT city, COUNT(city)
FROM addresses
         JOIN users ON addresses.user_id = users.id
GROUP BY city;

SELECT city, COUNT(city)
FROM addresses
         JOIN users ON addresses.user_id = users.id
GROUP BY city
HAVING AVG(age) > 21;

SELECT users.username, COUNT(posts.id)
FROM users
         LEFT JOIN posts ON users.id = posts.user_id
GROUP BY users.id;
