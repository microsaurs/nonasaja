create table lionpoint (
    point_num number not null,
    mem_num number not null, 
    lionpoint number(8) not null, //충전이면 양수(+) 결제면 음수(-)
    cash number(8) not null, //member_detail에서 가져올 것
    remain number(8) not null,
    order_num number,
    reg_date date not null,
    constraint point_pk primary key (point_num),
	constraint point_fk1 foreign key (mem_num) references member(mem_num)
);

create sequence lionpoint_seq;

create table club_board (
    club_num number not null,
    club_title varchar2(50) not null,
    club_leader number not null,
    club_content clob not null,
    club_date date not null,
    club_img blob,
    club_img_name varchar2(100),
    club_code number(1) not null, //1:운동,2:오락,3:맛집,4:노래,5:여행,6:스터디,7:기타
    club_limit number(3) not null,
    club_pre number(3) default 1 not null,
    club_hit number(6) default 0 not null,
    constraint club_board_pk primary key (club_num),
	constraint club_board_fk1 foreign key (club_leader) references member(mem_num)
);

create sequence club_board_seq;

create table club_chat(
    club_chat_num number not null,
    club_chat_leader number not null,
    club_chat_participant number(3) not null, //참여인원
    club_chat_title varchar(30) not null, //채팅방 이름
    constraint club_chat_pk primary key (club_chat_num),
	constraint club_chat_fk1 foreign key (club_chat_leader) references member(mem_num)
);

create sequence club_chat_seq;