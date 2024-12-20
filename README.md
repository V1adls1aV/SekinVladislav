# Sekin Vladislav

## Homework 07

### First query

Сделаем left-outer join:

```postgresql
SELECT COUNT(profile.profile_id)
FROM profile LEFT JOIN post ON profile.profile_id = post.profile_id
WHERE post.post_id is NULL
```

### Result

```
5
```

### Second query

```postgresql
SELECT post.post_id
FROM post JOIN comment ON post.post_id = comment.post_id
WHERE post.title ~ '^\d' AND LENGTH(post.content) > 20
GROUP BY post.post_id
HAVING COUNT(comment.comment_id) = 2
ORDER BY post.post_id;
```

### Result
```
 post_id 
---------
      22
      24
      26
      28
      32
      34
      36
      38
      42
      44
```

### Third query

```postgresql
SELECT post.post_id
FROM post LEFT JOIN comment ON post.post_id = comment.post_id
GROUP BY post.post_id
HAVING COUNT(comment.comment_id) <= 1
ORDER BY post.post_id
LIMIT 10;
```

### Result

```
 post_id 
---------
       1
       3
       5
       7
       9
      11
      13
      15
      17
      19
```
