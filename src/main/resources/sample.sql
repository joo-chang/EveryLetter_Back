INSERT INTO USER(`email`, `password`, `nickname`, `role`, createdDate, modifiedDate)values ('jooc00@gmail.com', '{bcrypt}$2a$10$0ELRTFHBSGS0ECkdMmyAauMQCp7Tzp29qOtzOFxCrPt9yVQXwy7AO', '창창', 'ROLE_ADMIN', now(3), now(3));
INSERT INTO USER(`email`, `password`, `nickname`, `role`, createdDate, modifiedDate) values ('jooc01@gmail.com', '{bcrypt}$2a$10$0ELRTFHBSGS0ECkdMmyAauMQCp7Tzp29qOtzOFxCrPt9yVQXwy7AO', '창1', 'ROLE_LETTER', now(3), now(3));
INSERT INTO USER(`email`, `password`, `nickname`, `role`, createdDate, modifiedDate) values ('jooc02@gmail.com', '{bcrypt}$2a$10$0ELRTFHBSGS0ECkdMmyAauMQCp7Tzp29qOtzOFxCrPt9yVQXwy7AO', '창2', 'ROLE_USER', now(3), now(3));
INSERT INTO USER(`email`, `password`, `nickname`, `role`, createdDate, modifiedDate) values ('jooc0311@gmail.com', '{bcrypt}$2a$10$0ELRTFHBSGS0ECkdMmyAauMQCp7Tzp29qOtzOFxCrPt9yVQXwy7AO', '창gmail', 'ROLE_USER', now(3), now(3));
INSERT INTO CATEGORY(`name`, content) value ('IT', '개발자들의 다양한 이야기들을 담는 공간입니다.');
INSERT INTO CATEGORY(`name`, content) value ('수다', '모든 사람들의 고민, 사는 얘기 등 다양한 이야기를 나눠보세요.');
