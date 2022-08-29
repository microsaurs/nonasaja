--중고거래 테아블
create table used_board(
 used_num number not null,
 title varchar2(150) not null, 
 content clob not null,
 kind number(1) not null,
 price number(6) default 0 not null,
 status number(1) not null,
 category varchar2(15) not null,
 trade varchar2(30),
 reg_date date not null,
 modify_date date,
 hit number(6) not null,
 constraint used_board_pk primary key (used_num),
 constraint used_board_fk1 foreign key (used_num) references member(mem_num),
 constraint used_board_fk2 foreign key (used_num) references region(region_num)
);

create sequence used_board_seq;

--중고거래 댓글 테이블 
create table used_reply(
 reply_num number not null,
 reply_content varchar2(900) not null,
 reply_date date not null,
 parent_num number not null,
 constraint used_reply_pk primary key (reply_num),
 constraint used_reply_fk1 foreign key (reply_num) references used_board(used_num),
 constraint used_reply_fk2 foreign key (reply_num) references member(mem_num)
);

create sequence used_reply_seq;
