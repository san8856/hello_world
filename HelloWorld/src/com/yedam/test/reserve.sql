create table tbl_customer ( customer_id VARCHAR2(30) primary key,
 		                    customer_pw varchar2(30) not null,  
                            customer_name varchar2(30) not null );
                            
create table tbl_room ( room_no int primary key,
		                room_type varchar2(30) not null); 
                        
create table tbl_reserve ( room_no varchar2(30) primary key, 
		                   reserve_time date default sysdate, 
		                   reserve_name varchar2(30) not null, 
		                   reserve_tel varchar2(30) not null);
                           
                           
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

CREATE SEQUENCE review_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

SELECT review_no, title, customer_id FROM tbl_review;

--아이디에 이름이 들어감->join 해결
UPDATE tbl_review r
SET customer_id = (SELECT customer_id FROM tbl_customer c WHERE c.customer_name = r.customer_id)
WHERE EXISTS (SELECT 1 FROM tbl_customer c WHERE c.customer_name = r.customer_id);

