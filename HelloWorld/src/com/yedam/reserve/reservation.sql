create table tbl_customer ( customer_id varchar(10) primary key,
 		                    customer_pw varchar(10) not null,  
                            customer_name varchar(30) not null );
                            
create table tbl_room ( room_no varchar(30) primary key,
		                room_type varchar(30) not null); 
                        
create table tbl_reserve ( room_no varchar(30) primary key, 
		                   reserce_time date default sysdate, 
		                   reserve_name varchar(30) not null, 
		                   reserve_tel varchar(30) not null);
                           
                           
create table tbl_review ( review_no number primary key,  
                          title varchar2(100) not null,       
                          content varchar2(300) not null,    
                          writer varchar2(30) not null,      
                          write_date date default sysdate,   
                          view_cnt number default 0 );    
                          
                          
create table tbl_reply ( review_no number primary key,
                         reply_no number not null,    
                         reply varchar2(300) not null,      
                         replyer varchar2(30) not null,      
                         reply_date date default sysdate );   
                         
                         
drop table tbl_room;                         
                         
                         
 insert into tbl_customer (customer_id,
                  customer_pw,
                  customer_name)
 values ('user01',
         '1234',
         'ȫ�浿'
     );
     
insert into tbl_room (room_no,
                  room_type)
 values ('505ȣ',
         'O'         
          );
          
commit;      

select *
from tbl_customer;