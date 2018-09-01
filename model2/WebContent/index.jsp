<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

1. 디비 연결하는 구조 (mysql.jar)설정 -> maven project
2. 빈2개 로그인용, 게시물용
3. jsp (로그인 처리) -> jsp(게시물용)
   login -> loginProc -> bbs 

create table tbl_board
(
     bno int not null auto_increment,
     title varchar(200) not null,
     content text null,
     writer varchar(50) not null,
     regdate timestamp not null default now(),
     viewcnt int default 0,
     replycnt int default 0,
     primary key (bno)
)
CREATE TABLE `tbl_user` (
	`idx` INT(11) NOT NULL AUTO_INCREMENT,
	`uid` VARCHAR(50) NOT NULL COMMENT '아이디',
	`upw` VARCHAR(50) NOT NULL COMMENT '비밀번호',
	`regdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일',
	PRIMARY KEY (`idx`)
)
-- 전체 게시물 가져오기
select * from tbl_board order by bno desc;

select * from tbl_user;

-- 로그인 
select * from tbl_user where uid='guest' and upw='1234';
    