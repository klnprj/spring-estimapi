INSERT INTO users (username, password, enabled) VALUES ('user', 'user', true);
INSERT INTO users (username, password, enabled) VALUES ('admin@mail.ru', '$2a$10$60mMZ0uJq7Ygpp0kaWcNoOwEXZRQVEdIVX5aYvyiC7cw5fyreOf3C', true);

INSERT INTO authorities(username, authority) VALUES ('user', 'ROLE');
INSERT INTO authorities(username, authority) VALUES ('admin@mail.ru', 'ROLE_ADMIN');