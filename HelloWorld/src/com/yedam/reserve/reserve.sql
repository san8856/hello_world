create table tbl_customer ( customer_id varchar2(30) primary key,
 		                    customer_pw varchar2(30) not null,  
                            customer_name varchar2(30) not null );
                            
create table tbl_room ( room_no number primary key,
		                room_type varchar2(30) not null); 
                        
create table tbl_reserve ( room_no number primary key, 
		                   reserve_time date default sysdate, 
		                   reserve_name varchar(30) not null, 
		                   reserve_tel varchar(30) not null);
                           
                           
create table tbl_review ( review_no number primary key,  
                          title varchar2(100) not null,       
                          content varchar2(300) not null,    
                          customer_id varchar2(30) not null,      
                          write_date date default sysdate,   
                          view_cnt number default 0 );    
                          
                          
create table tbl_reply ( review_no number primary key,
                         reply_no number not null,    
                         reply varchar2(300) not null,      
                         replyer varchar2(30) not null,      
                         reply_date date default sysdate );   
                         
                         
drop table tbl_review;                         
                         
                         
 insert into tbl_customer (customer_id,
                  customer_pw,
                  customer_name)
 values ('user01',
         '1234',
         '홍길동'
     );
     
insert into tbl_room (room_no,
                  room_type)
 values (505,
         'O'         
          );
          
commit;      

select *
from tbl_customer;


UPDATE TBL_ROOM
SET ROOM_TYPE = 'O'
WHERE room_no = 505;

--name에 id가 입력되는 문제 해결
UPDATE tbl_review r
SET r.customer_id = (SELECT c.customer_id FROM tbl_customer c WHERE c.customer_name = r.customer_id)
WHERE EXISTS (SELECT 1 FROM tbl_customer c WHERE c.customer_name = r.customer_id);


--조회수
CREATE SEQUENCE review_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;