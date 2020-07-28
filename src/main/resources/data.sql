

INSERT INTO blog_users("id", create_date, email, oauth, "password", "role", username) 
VALUES (1, '2020-07-28 12:44:59.529', 'stoneberg73@gmail.com', 'kakao', '$2a$10$RaT5SdU2J2T0Z3WbFta1Xu1reYzDNZj8Z9sfApBRGjcygbtY8mkcK', 'USER', 'stoneberg73@gmail.com_1406853202');
INSERT INTO blog_users("id", create_date, email, oauth, "password", "role", username) 
VALUES (2, '2020-07-28 13:45:58.373', 'zetlee@gmail.com', null, '$2a$10$zWTpW5pBEDBaRbuHVfrrkuaEyqZA.KEU8Ux3vT7d/RhcE2Mk5ekRO', 'USER', 'zetlee');
INSERT INTO blog_users("id", create_date, email, oauth, "password", "role", username) 
VALUES (3, '2020-07-28 13:48:42.443', 'redfoxer@gmail.com', null, '$2a$10$DlAvdOBkkBbKFoZj6Gu4f.Ud075tvHM7QYh1Yvtf9J/UUF8vJHsNK', 'USER', 'redfoxer');

-- content @Lob
INSERT INTO blog_board("id", "content", "count", create_date, title, user_id) 
VALUES (2, '19192', 0, '2020-07-28 14:23:12.719', 'This is new era', 1);


INSERT INTO blog_board_replay(CONTENT, board_id, USER_id, create_date)
VALUES ('first reply', 1, 2, now());

INSERT INTO blog_board_replay(CONTENT, board_id, USER_id, create_date)
VALUES ('second reply', 1, 2, now());

INSERT INTO blog_board_replay(CONTENT, board_id, USER_id, create_date)
VALUES ('third reply', 1, 3, now());


INSERT INTO blog_academy("name") VALUES ('GangNamSchool01');
INSERT INTO blog_academy("name") VALUES ('GangNamSchool02');
INSERT INTO blog_academy("name") VALUES ('GangNamSchool03');
INSERT INTO blog_academy("name") VALUES ('GangNamSchool04');
INSERT INTO blog_academy("name") VALUES ('GangNamSchool05');
INSERT INTO blog_academy("name") VALUES ('GangNamSchool06');
INSERT INTO blog_academy("name") VALUES ('GangNamSchool07');
INSERT INTO blog_academy("name") VALUES ('GangNamSchool08');
INSERT INTO blog_academy("name") VALUES ('GangNamSchool09');
INSERT INTO blog_academy("name") VALUES ('GangNamSchool10');


INSERT INTO blog_teacher("name") VALUES ('Teacher01');
INSERT INTO blog_teacher("name") VALUES ('Teacher02');
INSERT INTO blog_teacher("name") VALUES ('Teacher03');
INSERT INTO blog_teacher("name") VALUES ('Teacher04');
INSERT INTO blog_teacher("name") VALUES ('Teacher05');
INSERT INTO blog_teacher("name") VALUES ('Teacher06');
INSERT INTO blog_teacher("name") VALUES ('Teacher07');
INSERT INTO blog_teacher("name") VALUES ('Teacher08');
INSERT INTO blog_teacher("name") VALUES ('Teacher09');
INSERT INTO blog_teacher("name") VALUES ('Teacher10');


INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev1', 1, 1);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev2', 2, 2);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev3', 3, 3);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev4', 4, 4);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev5', 5, 5);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev6', 6, 6);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev7', 7, 7);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev8', 8, 8);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev9', 9, 9);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev010', 10, 10);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev011', 1, 1);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev012', 2, 2);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev012', 3, 3);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev014', 4, 4);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev015', 5, 5);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev016', 6, 6);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev017', 7, 7);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev018', 8, 8);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev019', 9, 9);
INSERT INTO blog_subject("name", academy_id, teacher_id) VALUES ('JavaWebDev020', 10, 10);







