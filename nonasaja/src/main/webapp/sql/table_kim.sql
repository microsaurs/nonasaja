--공동구매
create table product(
 product_num number not null,
 name varchar2(300) not null, --상품명
 kind number(1) not null, --상품종류
 sub_category varchar2(100) not null, --상품 상세종류
 title varchar2(100) not null, --제목
 price1 number(6) not null, --상품 원가
 price2 number(6) not null, --상품 판매가
 quantity number(6) not null, --상품 총 수량
 req_quantity number(5) not null, --구매 요구수량
 photo1 blob not null,	--상품 사진
 photo2 blob, 			--상품 사진
 photo3 blob, 			 --상품 사진
 company varchar2(30) not null,	--제조업체
 origin varchar2(30) not null,	--상품 원산지
 status number(1) not null,	--판매상태
 deadline date not null,	--구매기한
 reg_date date not null,	--등록일
 modify_date date,			--수정일 
 photo1_name varchar2(100) not null, --상품 사진
 photo2_name varchar2(100),	--상품 사진
 photo3_name varchar2(100),	--상품 사진
 quantity_detail varchar2(100) not null, -- 수량 상세
 exp_date varchar2(100) not null, --유통기한
 storage varchar2(100) not null, --보관방법
 cus_phone varchar2(20) not null -- 고객지원 번호
 constraint product_pk primary key (product_num)
);
create table product_review(
 review_num number not null,
 product_num number not null, 	--후기 점수 매긴 상품 번호
 mem_num number not null,		--후기 점수 매긴 회원 번호
 score number(1) not null,		--후기 점수		
 content varchar2(900),			--내용
 reg_date date default sysdate not null,
 constraint product_review_pk primary key (review_num),
 constraint product_review_fk1 foreign key (product_num) references product (product_num),
 constraint product_review_fk2 foreign key (mem_num) references member (mem_num)
);

create sequence product_seq;
create sequence product_review_seq;







