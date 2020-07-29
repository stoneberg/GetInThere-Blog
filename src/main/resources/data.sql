

INSERT INTO blog_users(create_date, email, oauth, "password", "role", username) 
VALUES ('2020-07-28 12:44:59.529', 'stoneberg73@gmail.com', 'kakao', '$2a$10$RaT5SdU2J2T0Z3WbFta1Xu1reYzDNZj8Z9sfApBRGjcygbtY8mkcK', 'USER', 'stoneberg73@gmail.com_1406853202');
INSERT INTO blog_users(create_date, email, oauth, "password", "role", username) 
VALUES ('2020-07-28 13:45:58.373', 'zetlee@gmail.com', null, '$2a$10$zWTpW5pBEDBaRbuHVfrrkuaEyqZA.KEU8Ux3vT7d/RhcE2Mk5ekRO', 'USER', 'zetlee');
INSERT INTO blog_users(create_date, email, oauth, "password", "role", username) 
VALUES ('2020-07-28 13:48:42.443', 'redfoxer@gmail.com', null, '$2a$10$DlAvdOBkkBbKFoZj6Gu4f.Ud075tvHM7QYh1Yvtf9J/UUF8vJHsNK', 'USER', 'redfoxer');

-- content @Lob
INSERT INTO blog_board("content", "count", create_date, title, user_id) 
VALUES ('19192', 0, '2020-07-28 14:23:12.719', 'This is new era', 1);


INSERT INTO blog_board_replay(CONTENT, board_id, USER_id, create_date)
VALUES ('first reply', 1, 2, now());

INSERT INTO blog_board_replay(CONTENT, board_id, USER_id, create_date)
VALUES ('second reply', 1, 2, now());

INSERT INTO blog_board_replay(CONTENT, board_id, USER_id, create_date)
VALUES ('third reply', 1, 3, now());


-- blog_academy
INSERT INTO blog_academy(name) VALUES ('IT-School-01');
INSERT INTO blog_academy(name) VALUES ('IT-School-02');
INSERT INTO blog_academy(name) VALUES ('IT-School-03');
INSERT INTO blog_academy(name) VALUES ('IT-School-04');
INSERT INTO blog_academy(name) VALUES ('IT-School-05');
INSERT INTO blog_academy(name) VALUES ('IT-School-06');
INSERT INTO blog_academy(name) VALUES ('IT-School-07');
INSERT INTO blog_academy(name) VALUES ('IT-School-08');
INSERT INTO blog_academy(name) VALUES ('IT-School-09');
INSERT INTO blog_academy(name) VALUES ('IT-School-10');

-- blog_teacher
INSERT INTO blog_teacher(name) VALUES ('Teacher-01');
INSERT INTO blog_teacher(name) VALUES ('Teacher-02');
INSERT INTO blog_teacher(name) VALUES ('Teacher-03');
INSERT INTO blog_teacher(name) VALUES ('Teacher-04');
INSERT INTO blog_teacher(name) VALUES ('Teacher-05');
INSERT INTO blog_teacher(name) VALUES ('Teacher-06');
INSERT INTO blog_teacher(name) VALUES ('Teacher-07');
INSERT INTO blog_teacher(name) VALUES ('Teacher-08');
INSERT INTO blog_teacher(name) VALUES ('Teacher-09');
INSERT INTO blog_teacher(name) VALUES ('Teacher-10');

-- blog_subject
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-01', 1, 1);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-11', 1, 1);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-02', 2, 2);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-12', 2, 2);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-03', 3, 3);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-13', 3, 3);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-04', 4, 4);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-14', 4, 4);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-05', 5, 5);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-15', 5, 5);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-06', 6, 6);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-16', 6, 6);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-07', 7, 7);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-17', 7, 7);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-08', 8, 8);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-18', 8, 8);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-09', 9, 9);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-19', 9, 9);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-10', 10, 10);
INSERT INTO blog_subject(name, academy_id, teacher_id) VALUES ('JavaWebDev-20', 10, 10);







