--중고거래 테아블
create table used_board(
 used_num number not null,
 mem_num number not null,
 region_num number not null,
 title varchar2(150) not null, 
 content clob not null,
 kind number(1) not null, --거래 종류(1중고,2무나,3교환)
 price number(6) default 0 not null,
 status number(1) not null,--거래상태(1판매중/2판매완료)
 category varchar2(15) not null,--상품 카테고리(주방,생활,식품용품)
 trade varchar2(30),
 reg_date date default SYSDATE not null,
 modify_date date,
 hit number(6) default 0 not null,
 constraint used_board_pk primary key (used_num),
 constraint used_board_fk1 foreign key (mem_num) references member(mem_num),
 constraint used_board_fk2 foreign key (region_num) references region(region_num)
);

create sequence used_board_seq;

--중고거래 댓글 테이블 
create table used_reply(
 reply_num number not null,
 used_num number not null,
 mem_num number not null,
 reply_content varchar2(900) not null,
 reply_date date not null,
 constraint used_reply_pk primary key (reply_num),
 constraint used_reply_fk1 foreign key (used_num) references used_board(used_num),
 constraint used_reply_fk2 foreign key (mem_num) references member(mem_num)
);

create sequence used_reply_seq;

--동호회 가입 테이블
create table join(
 join_num number not null,
 club_num number not null,
 mem_num number not null,
 join_reg_date date not null,
 constraint join_pk primary key (join_num),
 constraint join_fk1 foreign key (club_num) references club_board(club_num),
 constraint join_fk2 foreign key (mem_num) references member(mem_num)
);

create sequence club_seq;