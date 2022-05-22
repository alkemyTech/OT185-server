-- roles
insert into `role`(role_id, name, description, created_at) values (1, 'ROLE_ADMIN', 'Role for administrators' , CURRENT_DATE());

insert into `role`(role_id, name, description, created_at) values (2, 'ROLE_USER', 'Role for regular users' , CURRENT_DATE());
-- users
insert into `user` (user_id, first_name, last_name, email, password, photo, role_id, is_active, created_at) values (1, 'Admin', 'Admin', 'admin@somosmas.org', '$2a$10$Bz9kmnLhJb.xWc78LNymcugT0//TZRzNedtXgnfMWyYPVwuBcNlIG', 'https://static-url.com/avatar.jpg', 1, true, CURRENT_DATE());

insert into `user` (user_id, first_name, last_name, email, password, photo, role_id, is_active, created_at) values (2, 'Foo', 'Bar', 'fbar@somosmas.org', '$2a$10$npmFpCbBFdtXjGGhgc1ste1quwGaF4MvkgYk9N2rBDFkOrV5Lj6M6', 'https://static-url.com/avatar.jpg', 2, true, CURRENT_DATE());

insert into `user` (user_id, first_name, last_name, email, password, photo, role_id, is_active, created_at) values (99, 'Test', 'User', 'test@test.com', '$2a$10$Bz9kmnLhJb.xWc78LNymcugT0//TZRzNedtXgnfMWyYPVwuBcNlIG', 'https://static-url.com/avatar.jpg', 2, true, CURRENT_DATE());

insert into `user` (user_id, first_name, last_name, email, password, photo, role_id, is_active, created_at) values (3, 'user', 'casual', 'user@somosmas.org', '$2a$10$npmFpCbBFdtXjGGhgc1ste1quwGaF4MvkgYk9N2rBDFkOrV5Lj6M6', 'https://static-url.com/avatar.jpg', 2, true, CURRENT_DATE());

insert into `category` (category_id, name, is_active, created_at) values (1, 'Test category', true, CURRENT_DATE());

insert into `news` (news_id, name, content, image, category_id, is_active, created_at) values (1, 'Test news', 'Some content', 'Some image', 1, true, CURRENT_DATE());
