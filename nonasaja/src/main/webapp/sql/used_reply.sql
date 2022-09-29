
  CREATE TABLE "C##TEAM03"."USED_REPLY" 
   (	"REPLY_NUM" NUMBER NOT NULL ENABLE, 
	"USED_NUM" NUMBER NOT NULL ENABLE, 
	"MEM_NUM" NUMBER NOT NULL ENABLE, 
	"REPLY_CONTENT" VARCHAR2(900 BYTE) NOT NULL ENABLE, 
	"REPLY_DATE" DATE DEFAULT sysdate NOT NULL ENABLE, 
	 CONSTRAINT "USED_REPLY_PK" PRIMARY KEY ("REPLY_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "USED_REPLY_FK1" FOREIGN KEY ("USED_NUM")
	  REFERENCES "C##TEAM03"."USED_BOARD" ("USED_NUM") ENABLE, 
	 CONSTRAINT "USED_REPLY_FK2" FOREIGN KEY ("MEM_NUM")
	  REFERENCES "C##TEAM03"."MEMBER" ("MEM_NUM") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
