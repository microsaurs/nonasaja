--회원
create table member(
	mem_num number not null,
	id varchar2(255) not null,
	auth number(1) default 2 not null,
	nickname varchar2(30) not null,
	constraint member_pk primary key (mem_num),
	constraint id_unique unique (id)
);
--회원 상세
create table member_detail(
	mem_num number not null,
	name varchar2(15) not null,
	passwd varchar2(15),
	phone varchar2(15) not null,
	email varchar2(50) not null,
	zipcode varchar2(5) not null,
	addr1 varchar2(100) not null,
	addr2 varchar2(100) not null,
	reg_date date default sysdate not null,
	modify_date date,
	photo blob,
	photo_name varchar2(100),
	interest varchar2(100),
	cash number(8) default 0 not null,
	root number(1) default 0 not null, --가입수단- 0:자체회원, 1:네이버, 2:카카오
	constraint member_detail_pk primary key (mem_num),
	constraint member_detail_fk foreign key (mem_num) references member (mem_num)
);
create sequence member_seq;
--신고
create table report(
	report_num number not null,
	reporter_mem number not null,--신고를 한 회원
	reported_mem number not null,--신고를 당한 회원
	report_code number(1) not null,--1:중고거래/2:동호회/3:커뮤니티
	report_content varchar2(900) not null,--신고 내용
	reg_date date default sysdate not null,--신고일
	used_num number,  
	club_num number,
	commu_num number,
	constraint report_pk primary key (report_num),
	constraint report_fk1 foreign key (reporter_mem) references member (mem_num)
);
create sequence report_seq;