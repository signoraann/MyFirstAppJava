SELECT users.username, posts.post_title
FROM users
         INNER JOIN posts ON users.id = posts.user_id;