SELECT users.username, posts.post_title
FROM users
         INNER JOIN posts ON users.id = posts.user_id;

SELECT COUNT(*)
FROM users;

SELECT AVG(age)
FROM users;

SELECT SUM(age)
FROM users;

SELECT residents.city, COUNT(residents.name)
FROM residents
GROUP BY residents.city;
