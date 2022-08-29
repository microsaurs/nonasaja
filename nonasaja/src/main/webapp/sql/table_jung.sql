--주문 테이블
create table norder(
  order_num number not null,
  mem_num number not null,
  product_name varchar2(300) not null,
  order_total number (8) not null,
  status number(1) not null, --1:배송대기,2:배송준비,3:배송중,4:배송완료,5:주문취소
  receive_name varchar2(15) not null,
  receive_post varchar2(5) not null,
  receive_address1 varchar2(100) not null,
  receive_address2 varchar2(100) not null,
  receive_phone varchar2(15) not null,
  notice varchar2(300),
  reg_date date default sysdate not null,
  modify_date date,
  constraint norder_pk primary key (order_num),
  constraint norder_fk foreign key (mem_num) references member (mem_num)
);

--주문 상세 테이블
create table norder_detail(
  detail_num number not null,
  product_num number not null,
  product_name varchar2 not null,
  product_price number(6) not null,
  product_total number(8) not null,
  order_quantity number(5) not null,
  order_num number not null,
  constraint norder_detail_pk primary key (detail_num),
  constraint norder_detail_fk foreign key (detail_num) references norder (order_num)
);

--세일정보 게시판 테이블
create table sale_board(
  board_num number not null,
  title varchar2(150) not null,
  content clob not null,
  reg_date date default sysdate not null,
  modify_date date,
  deadline date not null,
  hit number(6) not null,
  mem_num number not null,
  constraint sale_board_pk primary key (board_num),
  constraint sale_board_fk foreign key (mem_num) references member (mem_num)
);

--세일 정보 게시판 댓글 테이블
create table sale_reply(
  reply_num number not null,
  board_num number not null,
  mem_num number not null,
  reply_content varchar2(900) not null,
  reply_date date default sysdate not null,
  parent_num number,
  constraint sale_reply_pk primary key (reply_num),
  constraint sale_reply_fk1 foreign key (board_num) references sale_board (board_num),
  constraint sale_reply_fk2 foreign key (mem_num) references member (mem_num) 
);


--관심목록 테이블
create table FAV(
  fav_num number not null,
  product_num number,
  used_num number,
  club_num number,
  commu_num number,
  mem_num number not null,
  fav_code number not null, --1:공동구매,2:중고거래,3:동호회,4:커뮤니티
  constraint FAV_pk primary key (fav_num),
  constraint FAV_fk foreign key (mem_num) references member (mem_num)
);

create sequence norder_seq;
create sequence norder_detail_seq;
create sequence sale_board_seq;
create sequence sale_reply_seq;
create sequence FAV_seq;