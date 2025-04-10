select *
from tab;

-- 이벤트 등록 테이블.
create table tbl_event (
  title varchar2(100) not null,
  start_date varchar2(20) not null,
  end_date   varchar2(20)
);

select *
from tbl_event;

insert into tbl_event 
values ('회의1', '2025-04-12', '2025-04-15');
insert into tbl_event 
values ('회의2', '2025-04-18', '2025-04-20');
insert into tbl_event 
values ('회의3', '2025-04-22', '2025-04-25');

drop table tbl_reply purge;
create table tbl_reply (
  reply_no number,
  reply    varchar2(300) not null,
  replyer  varchar2(30) not null,
  reply_date date default sysdate,
  board_no number );

alter table tbl_reply add constraint reply_pk primary key(reply_no);

insert into tbl_reply (reply_no, reply, replyer, board_no)
select reply_seq.nextval, reply, replyer, board_no
from tbl_reply
where board_no = 152;

-- 페이지당 5건씩 댓글출력.
select b.*
from  (select /*+ INDEX_DESC(r reply_pk) */ rownum rn, r.*
       from tbl_reply r
       where board_no = :bno) b
where b.rn > (:page - 1) * 5
and   b.rn <= :page * 5;

select *
from tbl_board
order by 1 desc;

insert into tbl_reply (reply_no, reply, replyer, board_no)
values(reply_seq.nextval, '152번글에 댓글', 'user01', 152);

insert into tbl_reply (reply_no, reply, replyer, board_no)
values(reply_seq.nextval, '152번글에 댓글 두번째', 'user02', 152);

insert into tbl_reply (reply_no, reply, replyer, board_no)
values(reply_seq.nextval, '152번글에 댓글 세번째', 'user03', 152);

insert into tbl_reply (reply_no, reply, replyer, board_no)
select reply_seq.nextval, reply, replyer, 203
from tbl_reply
where board_no = 152;

select *
from tbl_reply
where board_no = 152
order by 1 desc;

delete from tbl_reply
where reply_no > 200;

--실행페이지, 아이피, 로그시간
create sequence log_seq;
drop table tbl_log purge;
create table tbl_log (
 log_sec number primary key,
 exec_page varchar2(50),
 exec_ip varchar2(50),
 exec_time date default sysdate);

insert into tbl_log
values(log_seq.nextval, '/board.do', '192.168.0.40', sysdate);

select log_sec, exec_page, exec_ip, to_char(exec_time, 'rrrr-mm-dd hh24:mi:ss') xtime
from tbl_log
where exec_time > trunc(sysdate)
order by 1 desc;

select exec_page, count(1)
from tbl_log
where exec_time > trunc(sysdate)
group by exec_page;

update tbl_member
set responsibility = 'User';
insert into tbl_member
values('admin', '9999', '관리자',  'Admin');

update tbl_member
set images='lch.jpg'
where member_id='user01';

select *
from tbl_member;

alter table tbl_member add responsibility varchar2(30);
alter table tbl_member add images varchar2(50);

--게시판(글번호, 제목, 내용, 작성자, 작성일시)
create table tbl_board (
 board_no number primary key --글번호(PK)
 ,title varchar2(300) not null --글제목(not null)
 ,content varchar2(1000) not null --내용(not null)
 ,writer varchar2(20) not null --작성자(not null)
 ,write_date date default sysdate --작성일시
);
create sequence board_seq;

insert into tbl_board(board_no, title, content, writer)
values(board_seq.nextval, '글등록연습', '첫번째 글등록입니다', 'user01');
insert into tbl_board(board_no, title, content, writer)
values(board_seq.nextval, '오늘은금요일', '즐거운날입니다', 'user02');
insert into tbl_board(board_no, title, content, writer)
values(board_seq.nextval, '서블릿공부', '하는 날이네요..', 'user03');

insert into tbl_board
select board_seq.nextval, title, content, writer, write_date
from tbl_board
--order by board_no desc
;
-- paging
select b.*
from   (select rownum rn, a.*
        from (select *
              from tbl_board
              where writer like '%'||'오늘은'||'%'
              order by board_no) a) b
where b.rn > (:page - 1) * 5
and   b.rn <= (:page * 5);

select count(*) from tbl_board;

delete from tbl_board
where board_no = 8;

select *--empno, ename, job, mgr, hiredate, sal
from emp
order by empno desc;

insert into emp (empno, ename, job, mgr, hiredate, sal)
values (9998, '홍길동', 'CLERK', 7788, '2020-01-01', 1000);

update emp
set    sal = 2000
      ,deptno = 10
where empno = 9998;

delete from emp
where empno = 9999;

commit;

-- table 생성.
-- 도서코드, 도서명, 저자, 출판사, 도서가격
create table tbl_book (
 book_code varchar2(5) primary key, --도서코드.
 book_title varchar2(50) not null, --도서명.
 author varchar2(30) not null, --저자.
 company varchar2(30) not null, --출판사.
 price number default 1000
);
create sequence book_seq;
select book_seq.nextval from dual;

insert into tbl_book (book_code, book_title, author, company, price)
values(book_seq.nextval, '이것이자바다', '신용권', '한빛출판사', 20000);
insert into tbl_book (book_code, book_title, author, company, price)
values(book_seq.nextval, '혼공자', '신용권', '한빛출판사', 22000);
insert into tbl_book (book_code, book_title, author, company, price)
values(book_seq.nextval, '웹기초', '김기초', '기초출판사', 23000);

select *
from tbl_book
where company = nvl('', company)
order by book_code;

-- 데이터베이스 프로그래밍.
update tbl_book
set    book_title = nvl(?, book_title)
      ,price      = ?
      ,author     = nvl(?, author)
      ,company    = nvl(?, company)
where book_code = ?;

create table tbl_member (
 user_id varchar2(10) primary key, --사용자 ID
 password varchar2(30) not null, --비밀번호
 user_name varchar2(50) not null
);
insert into tbl_member 
values ('user01', '1111', '홍길동');
insert into tbl_member 
values ('user02', '2222', '김길동');
insert into tbl_member 
values ('user03', '3333', '박길동');

select *
from tbl_member;

