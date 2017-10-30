DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;

DROP TABLE if exists Note;

CREATE TABLE Note( 
    noteid INT AUTO_INCREMENT NOT NULL,
    dateCreated DATETIME NOT NULL,
    contents NVARCHAR(10000) NOT NULL,
    PRIMARY KEY (noteid)
);

INSERT INTO Note values(1, SYSDATE(), 'Hello World!!!');
INSERT INTO Note values(2, SYSDATE(), 'asdasd');
INSERT INTO Note values(3, SYSDATE(), 'Hsdfsdfsdf');
INSERT INTO Note values(4, SYSDATE(), 'ertertert');
