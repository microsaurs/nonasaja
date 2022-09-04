-- 장바구니
CREATE TABLE cart
(
    cart_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    product_num NUMBER NOT NULL,
    quantity_num NUMBER NOT NULL,
    reg_date DATE NOT NULL,
    CONSTRAINT cart_pk PRIMARY KEY(cart_num),
    CONSTRAINT cart_fk1 FOREIGN KEY (mem_num) REFERENCES member (mem_num),
    CONSTRAINT cart_fk2 FOREIGN KEY (product_num) REFERENCES product (product_num)
);

-- 지역 정보
CREATE TABLE region
(
    region_num NUMBER NOT NULL,
    si_num NUMBER NOT NULL,
    si_name VARCHAR2(20) NOT NULL,
    gu_num NUMBER NOT NULL,
    gu_name VARCHAR2(20) NOT NULL,
    CONSTRAINT region_pk PRIMARY KEY(region_num)
);

-- 커뮤니티
CREATE TABLE community_board
(
    commu_num NUMBER NOT NULL,
    commu_title VARCHAR2(150) NOT NULL,
    mem_num NUMBER NOT NULL,
    commu_content CLOB NOT NULL,
    commu_date DATE DEFAULT sysdate NOT NULL,
    commu_modify_date DATE,
    commu_code NUMBER(1) NOT NULL, -- 1 : 레시피공유 / 2 : 유머 / 3 : 자취정보
    commu_video VARCHAR2(150),
    commu_url VARCHAR2(150),
    commu_recommend NUMBER(6) DEFAULT 0 NOT NULL,
    commu_hit NUMBER(6) DEFAULT 0 NOT NULL,

    region_num NUMBER NOT NULL, --null?
    CONSTRAINT community_pk PRIMARY KEY(commu_num),
    CONSTRAINT community_fk1 FOREIGN KEY (mem_num) REFERENCES member (mem_num),
    CONSTRAINT community_fk2 FOREIGN KEY (region_num) REFERENCES region (region_num)
);

-- 커뮤니티 댓글
CREATE TABLE community_reply
(
    reply_num NUMBER NOT NULL,
    commu_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    reply_content VARCHAR2(900) NOT NULL,
    reply_date DATE NOT NULL,
    parent_num NUMBER,
    CONSTRAINT commureply_pk PRIMARY KEY(reply_num),
    CONSTRAINT commureply_fk1 FOREIGN KEY (commu_num) REFERENCES community_board (commu_num),
    CONSTRAINT commureply_fk2 FOREIGN KEY (mem_num) REFERENCES member (mem_num)
);

CREATE SEQUENCE cart_seq;
CREATE SEQUENCE region_seq;
CREATE SEQUENCE community_seq;
CREATE SEQUENCE commureply_seq;

