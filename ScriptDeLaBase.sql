--------------------------------------------------------
--  File created - Sunday-March-31-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table USUARIO
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."USUARIO" 
   (	"ID" VARCHAR2(20 BYTE), 
	"CLAVE" VARCHAR2(20 BYTE), 
	"CARGO" VARCHAR2(40 BYTE), 
	"ID_FUNCIONARIO" VARCHAR2(20 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table FUNCIONARIO
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."FUNCIONARIO" 
   (	"ID" VARCHAR2(10 BYTE), 
	"NOMBRE" VARCHAR2(10 BYTE), 
	"DEPENDENCIA" NUMBER(10,0)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table DEPENDENCIA
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."DEPENDENCIA" 
   (	"CODIGO" NUMBER(10,0), 
	"NOMBRE" VARCHAR2(20 BYTE), 
	"ADMINISTRADOR" VARCHAR2(10 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table SOLICITUD
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."SOLICITUD" 
   (	"ID" NUMBER(10,0), 
	"COMPROBANTE" VARCHAR2(10 BYTE), 
	"FECHA" DATE, 
	"TIPO" VARCHAR2(35 BYTE), 
	"CANTIDAD" NUMBER(10,0), 
	"TOTAL" NUMBER(25,0), 
	"ESTADO" VARCHAR2(20 BYTE), 
	"DEPENDENCIA" NUMBER(10,0), 
	"REGISTRADOR" VARCHAR2(20 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table BIEN
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."BIEN" 
   (	"NUMERO" NUMBER(10,0), 
	"DESCRIPCION" VARCHAR2(20 BYTE), 
	"MODELO" VARCHAR2(20 BYTE), 
	"PRECIO" NUMBER(25,0), 
	"CANTIDAD" NUMBER(10,0), 
	"NUM_SOLICITUD" NUMBER(10,0), 
	"MARCA" VARCHAR2(20 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SYSTEM.USUARIO
SET DEFINE OFF;
Insert into SYSTEM.USUARIO (ID,CLAVE,CARGO,ID_FUNCIONARIO) values ('1','1','Administrador','Pablo');
Insert into SYSTEM.USUARIO (ID,CLAVE,CARGO,ID_FUNCIONARIO) values ('2','2','Administrador','Joel');
REM INSERTING into SYSTEM.FUNCIONARIO
SET DEFINE OFF;
Insert into SYSTEM.FUNCIONARIO (ID,NOMBRE,DEPENDENCIA) values ('Pablo','Pablo',2);
Insert into SYSTEM.FUNCIONARIO (ID,NOMBRE,DEPENDENCIA) values ('2','Joel',1);
REM INSERTING into SYSTEM.DEPENDENCIA
SET DEFINE OFF;
Insert into SYSTEM.DEPENDENCIA (CODIGO,NOMBRE,ADMINISTRADOR) values (1,'Informatica','Joel');
Insert into SYSTEM.DEPENDENCIA (CODIGO,NOMBRE,ADMINISTRADOR) values (2,'Biologia','Pablo');
REM INSERTING into SYSTEM.SOLICITUD
SET DEFINE OFF;
Insert into SYSTEM.SOLICITUD (ID,COMPROBANTE,FECHA,TIPO,CANTIDAD,TOTAL,ESTADO,DEPENDENCIA,REGISTRADOR) values (45,' prueba',to_date('03-APR-14','DD-MON-RR'),'Compra',0,0,'Recibida',2,null);
REM INSERTING into SYSTEM.BIEN
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index PKFUNCIONARIOS
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."PKFUNCIONARIOS" ON "SYSTEM"."USUARIO" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index FUNCIONARIO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."FUNCIONARIO_PK" ON "SYSTEM"."FUNCIONARIO" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index DEPENDENCIAS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."DEPENDENCIAS_PK" ON "SYSTEM"."DEPENDENCIA" ("CODIGO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SOLCITUD_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SOLCITUD_PK" ON "SYSTEM"."SOLICITUD" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index BIEN_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."BIEN_PK" ON "SYSTEM"."BIEN" ("NUMERO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Trigger SOLICITUD_ON_INSERT
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."SOLICITUD_ON_INSERT" 
  BEFORE INSERT ON solicitud
  FOR EACH ROW
BEGIN
  SELECT solicitud_sequence.nextval
  INTO :new.id
  FROM dual;
END;
/
ALTER TRIGGER "SYSTEM"."SOLICITUD_ON_INSERT" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BIEN_ON_INSERT
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."BIEN_ON_INSERT" 
  BEFORE INSERT ON bien
  FOR EACH ROW
BEGIN
  SELECT bien_sequence.nextval
  INTO :new.numero
  FROM dual;
END;
/
ALTER TRIGGER "SYSTEM"."BIEN_ON_INSERT" ENABLE;
--------------------------------------------------------
--  Constraints for Table USUARIO
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."USUARIO" ADD CONSTRAINT "PKFUNCIONARIOS" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table FUNCIONARIO
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."FUNCIONARIO" ADD CONSTRAINT "FUNCIONARIO_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SYSTEM"."FUNCIONARIO" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."FUNCIONARIO" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DEPENDENCIA
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."DEPENDENCIA" ADD CONSTRAINT "DEPENDENCIAS_PK" PRIMARY KEY ("CODIGO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SYSTEM"."DEPENDENCIA" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."DEPENDENCIA" MODIFY ("CODIGO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SOLICITUD
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."SOLICITUD" ADD CONSTRAINT "SOLCITUD_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SYSTEM"."SOLICITUD" MODIFY ("COMPROBANTE" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."SOLICITUD" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BIEN
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."BIEN" ADD CONSTRAINT "BIEN_PK" PRIMARY KEY ("NUMERO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SYSTEM"."BIEN" MODIFY ("NUMERO" NOT NULL ENABLE);
  
create or replace PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
 CREATE SEQUENCE  "SYSTEM"."BIEN_SEQUENCE"  MINVALUE 0 MAXVALUE 99999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
    CREATE SEQUENCE  "SYSTEM"."SOLICITUD_SEQUENCE"  MINVALUE 0 MAXVALUE 9999999999999 INCREMENT BY 1 START WITH 63 CACHE 20 NOORDER  NOCYCLE ;
	create or replace TRIGGER "SYSTEM".bien_on_insert
  BEFORE INSERT ON bien
  FOR EACH ROW
BEGIN
  SELECT bien_sequence.nextval
  INTO :new.numero
  FROM dual;
END;
create or replace TRIGGER "SYSTEM".solicitud_on_insert
  BEFORE INSERT ON solicitud
  FOR EACH ROW
BEGIN
  SELECT solicitud_sequence.nextval
  INTO :new.id
  FROM dual;
END;
--------------------------------------------------------
--  File created - Sunday-March-31-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ELIMINAR_BIEN
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."ELIMINAR_BIEN" (numeroin in bien.numero%type) AS 
BEGIN
  delete from bien where numero=numeroin;
END ELIMINAR_BIEN;

/
--------------------------------------------------------
--  DDL for Procedure ELIMINAR_DEPENDENCIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."ELIMINAR_DEPENDENCIA" (codigo_bus dependencia.codigo%type) AS 
BEGIN
  delete from dependencia where codigo_bus=codigo;

END;

/
--------------------------------------------------------
--  DDL for Procedure ELIMINAR_FUNCIONARIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."ELIMINAR_FUNCIONARIO" (idbus in funcionario.id%type) AS 
BEGIN
  delete from funcionario where idbus = id;
END ;

/
--------------------------------------------------------
--  DDL for Procedure ELIMINAR_SOLICITUD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."ELIMINAR_SOLICITUD" (idbus in solicitud.id%type) AS 
BEGIN
  delete from solicitud where idbus = id;
END ;

/
--------------------------------------------------------
--  DDL for Procedure ELIMINAR_USUARIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."ELIMINAR_USUARIO" (idbus in usuario.id%type) AS 
BEGIN
  delete from usuario where idbus = id;
END ;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_BIEN
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."INSERTAR_BIEN" (numeroin in bien.numero%type,descripcionin in bien.descripcion%type,modeloin in bien.modelo%type,
precioin in bien.precio%type, cantidadin in bien.cantidad%type, num_solicitudin in bien.num_solicitud%Type,marcain in bien.marca%type)as
BEGIN
  insert into bien values(numeroin,descripcionin,modeloin,precioin,cantidadin,num_solicitudin,marcain);
END ;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_DEPENDENCIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."INSERTAR_DEPENDENCIA" (codigo in dependencia.codigo%type,nombre in dependencia.nombre%type,administrador in dependencia.administrador%type)
AS 
BEGIN
insert into dependencia values(codigo,nombre,administrador);
END INSERTAR_DEPENDENCIA;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_FUNCIONARIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."INSERTAR_FUNCIONARIO" (id in funcionario.id%type,nombre in funcionario.nombre%type,dependencia in funcionario.dependencia%type) AS 
BEGIN
  insert into funcionario values(id,nombre,dependencia);
END;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_SOLICITUD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."INSERTAR_SOLICITUD" (idin in solicitud.id%type,comprobantein in solicitud.comprobante%type,
fechain in solicitud.fecha%Type, tipoin in solicitud.tipo%type,cantidadin in solicitud.cantidad%Type, totalin in solicitud.total%type,
estadoin in solicitud.estado%type, dependenciain in solicitud.dependencia%type, registradorin in solicitud.registrador%Type)
AS 
BEGIN
insert into solicitud values(idin,comprobantein,fechain,tipoin,cantidadin,totalin,estadoin,dependenciain,registradorin);
  
END ;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_USUARIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."INSERTAR_USUARIO" (id in usuario.id%type, clave in usuario.clave%type, 
cargo in usuario.cargo%Type, id_funcionario in usuario.id_funcionario%type) AS 
BEGIN
insert into usuario values(id,clave,cargo,id_funcionario);
END ;

/
--------------------------------------------------------
--  DDL for Procedure MODIFICAR_DEPENDENCIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."MODIFICAR_DEPENDENCIA" (codigoin in dependencia.codigo%type,nombrein in dependencia.nombre%type, administradorin in dependencia.administrador%type)
as
BEGIN
update dependencia set  nombre = nombrein, administrador = administradorin where codigo = codigoin;

END ;

/
--------------------------------------------------------
--  DDL for Procedure MODIFICAR_FUNCIONARIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."MODIFICAR_FUNCIONARIO" (idin in funcionario.id%type, nombrein in funcionario.nombre%type, 
dependenciain in funcionario.dependencia%type)  AS 
BEGIN
  update funcionario set nombre = nombrein, dependencia = dependenciain where id = idin;
  END;

/
--------------------------------------------------------
--  DDL for Procedure MODIFICAR_SOLICITUD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."MODIFICAR_SOLICITUD" (idin in solicitud.id%type,comprobantein in solicitud.comprobante%type,
fechain in solicitud.fecha%Type, tipoin in solicitud.tipo%type,cantidadin in solicitud.cantidad%Type, totalin in solicitud.total%type,
estadoin in solicitud.estado%type, dependenciain in solicitud.dependencia%type, registradorin in solicitud.registrador%Type) AS 
BEGIN
  update solicitud set comprobante=comprobantein,fecha=fechain,tipo=tipoin,cantidad=cantidadin,total=totalin,estado=estadoin,
  dependencia=dependenciain,registrador=registrador where id= idin;
END ;

/
--------------------------------------------------------
--  DDL for Procedure MODIFICAR_USUARIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."MODIFICAR_USUARIO" (idin in usuario.id%type, clavein in usuario.clave%Type, cargoin in usuario.cargo%type,
id_funcionarioin usuario.id_funcionario%type ) AS 
BEGIN
   update usuario set clave = clavein where id= idin;
END MODIFICAR_USUARIO;

/
--------------------------------------------------------
--  File created - Sunday-March-31-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Function LISTAR_BIENES
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."LISTAR_BIENES" 
RETURN Types.ref_cursor 
AS 
        bien_cursor types.ref_cursor; 
begin open bien_cursor for
        select numero,descripcion,modelo,precio,cantidad,num_solicitud,marca from bien;
  RETURN bien_cursor;
END ;

/
--------------------------------------------------------
--  DDL for Function BUSCAR_BIEN
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."BUSCAR_BIEN" (numerobus bien.numero%type)
RETURN Types.ref_cursor 
AS 
        bien_cursor types.ref_cursor; 
begin open bien_cursor for
        select numero,descripcion,modelo,precio,cantidad,num_solicitud,marca from bien where numero=numerobus;
  RETURN bien_cursor;
END ;

/
--------------------------------------------------------
--  DDL for Function BUSCAR_DEPENDENCIA
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."BUSCAR_DEPENDENCIA" (codigobus dependencia.codigo%Type)
RETURN Types.ref_cursor 
AS 
        dependencia_cursor types.ref_cursor; 
begin open dependencia_cursor for
        select codigo,nombre,administrador  from dependencia where codigo=codigobus;
  RETURN dependencia_cursor;
END ;

/
--------------------------------------------------------
--  DDL for Function BUSCAR_FUNCIONARIO
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."BUSCAR_FUNCIONARIO" (idbus funcionario.id%type)
RETURN Types.ref_cursor 
AS 
        funcionario_cursor types.ref_cursor; 
begin open funcionario_cursor for
        select id,nombre,dependencia from funcionario where id=idbus;
  RETURN funcionario_cursor;
END ;

/
--------------------------------------------------------
--  DDL for Function BUSCAR_SOLICITUD
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."BUSCAR_SOLICITUD" (idbus solicitud.id%Type)
RETURN Types.ref_cursor 
AS 
        solicitud_cursor types.ref_cursor; 
begin open solicitud_cursor for
        select id,comprobante,fecha,tipo,cantidad,total,estado,dependencia,registrador  from solicitud where idbus=id;
  RETURN solicitud_cursor;
END ;

/
--------------------------------------------------------
--  DDL for Function BUSCAR_USUARIO
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."BUSCAR_USUARIO" (idbus usuario.id%type)
RETURN Types.ref_cursor 
AS 
        usuario_cursor types.ref_cursor; 
begin open usuario_cursor for
        select id,clave,cargo,id_funcionario from usuario where idbus= id;
  RETURN usuario_cursor;
END ;

/
--------------------------------------------------------
--  DDL for Function LISTAR_BIENES_SOLICITUD
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."LISTAR_BIENES_SOLICITUD" (num_solicitudbus bien.num_solicitud%type)
RETURN Types.ref_cursor 
AS 
        bien_cursor types.ref_cursor; 
begin open bien_cursor for
        select numero,descripcion,modelo,precio,cantidad,num_solicitud,marca from bien where num_solicitud=num_solicitudbus;
  RETURN bien_cursor;
END ;

/
--------------------------------------------------------
--  DDL for Function LISTAR_DEPENDENCIAS
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."LISTAR_DEPENDENCIAS" 
RETURN Types.ref_cursor 
AS 
        dependencia_cursor types.ref_cursor; 
BEGIN 
  OPEN dependencia_cursor FOR 
       SELECT codigo,nombre,administrador FROM dependencia ; 
RETURN dependencia_cursor; 
END;

/
--------------------------------------------------------
--  DDL for Function LISTAR_FUNCIONARIO
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."LISTAR_FUNCIONARIO" 
RETURN Types.ref_cursor 
AS 
        dependencia_cursor types.ref_cursor; 
begin open dependencia_cursor for
        select codigo,nombre,administrador from dependencia;
  RETURN dependencia_cursor;
END ;

/
--------------------------------------------------------
--  DDL for Function LISTAR_SOLICI_COM
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."LISTAR_SOLICI_COM" (compbus solicitud.comprobante%type)
RETURN Types.ref_cursor 
AS
        solicitud_cursor types.ref_cursor; 
begin open solicitud_cursor for
        select id,comprobante,fecha,tipo,cantidad,total,estado,dependencia,registrador  from solicitud where comprobante like '%'||compbus||'%';
  RETURN solicitud_cursor;
END ;

/
--------------------------------------------------------
--  DDL for Function LISTAR_SOLICITUD_DEPENDENCIA
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."LISTAR_SOLICITUD_DEPENDENCIA" (depdenciabus solicitud.dependencia%Type)
RETURN Types.ref_cursor 
AS 
        solicitud_cursor types.ref_cursor; 
begin open solicitud_cursor for
        select id,comprobante,fecha,tipo,cantidad,total,estado,dependencia,registrador  from solicitud where dependencia=depdenciabus;
  RETURN solicitud_cursor;
END ;

/
--------------------------------------------------------
--  DDL for Function LISTAR_USUARIOS
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SYSTEM"."LISTAR_USUARIOS" 
RETURN Types.ref_cursor 
AS 
        usuario_cursor types.ref_cursor; 
begin open usuario_cursor for
        select id,clave,cargo,id_funcionario from usuario;
  RETURN usuario_cursor;
END ;

/