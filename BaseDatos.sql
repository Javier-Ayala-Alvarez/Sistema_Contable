/*
PostgreSQL Backup
Database: cicloContable/public
Backup Time: 2022-09-23 00:23:08
*/

DROP SEQUENCE IF EXISTS "public"."catalogo_id_seq";
DROP SEQUENCE IF EXISTS "public"."empresa_id_seq";
DROP SEQUENCE IF EXISTS "public"."partida_id_seq";
DROP SEQUENCE IF EXISTS "public"."registro_partida_id_seq";
DROP SEQUENCE IF EXISTS "public"."rol_id_seq";
DROP SEQUENCE IF EXISTS "public"."usuarios_id_seq";
DROP TABLE IF EXISTS "public"."catalogo";
DROP TABLE IF EXISTS "public"."empresa";
DROP TABLE IF EXISTS "public"."partida";
DROP TABLE IF EXISTS "public"."registro_partida";
DROP TABLE IF EXISTS "public"."rol";
DROP TABLE IF EXISTS "public"."usuarios";
DROP TABLE IF EXISTS "public"."usuarios_roles";
CREATE SEQUENCE "catalogo_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;
CREATE SEQUENCE "empresa_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;
CREATE SEQUENCE "partida_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "registro_partida_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "rol_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "usuarios_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE TABLE "catalogo" (
  "id" int4 NOT NULL DEFAULT nextval('catalogo_id_seq'::regclass),
  "codigo" varchar(8) COLLATE "pg_catalog"."default",
  "descripcion" varchar(2000) COLLATE "pg_catalog"."default",
  "nombre" varchar(255) COLLATE "pg_catalog"."default",
  "saldo_cuenta" varchar(255) COLLATE "pg_catalog"."default",
  "tipo_cuenta" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "catalogo" OWNER TO "postgres";
CREATE TABLE "empresa" (
  "id" int4 NOT NULL DEFAULT nextval('empresa_id_seq'::regclass),
  "descripcion" varchar(255) COLLATE "pg_catalog"."default",
  "nombre" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "empresa" OWNER TO "postgres";
CREATE TABLE "partida" (
  "id" int8 NOT NULL DEFAULT nextval('partida_id_seq'::regclass),
  "activo" bool,
  "descripcion" varchar(255) COLLATE "pg_catalog"."default",
  "fecha" timestamp(6)
)
;
ALTER TABLE "partida" OWNER TO "postgres";
CREATE TABLE "registro_partida" (
  "id" int8 NOT NULL DEFAULT nextval('registro_partida_id_seq'::regclass),
  "debe" numeric(19,2) NOT NULL,
  "haber" numeric(19,2) NOT NULL,
  "catalogo_id" int4,
  "partida_id" int8
)
;
ALTER TABLE "registro_partida" OWNER TO "postgres";
CREATE TABLE "rol" (
  "id" int8 NOT NULL DEFAULT nextval('rol_id_seq'::regclass),
  "nombre" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "rol" OWNER TO "postgres";
CREATE TABLE "usuarios" (
  "id" int8 NOT NULL DEFAULT nextval('usuarios_id_seq'::regclass),
  "apellido" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "nombre" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "usuarios" OWNER TO "postgres";
CREATE TABLE "usuarios_roles" (
  "usuario_id" int8 NOT NULL,
  "rol_id" int8 NOT NULL
)
;
ALTER TABLE "usuarios_roles" OWNER TO "postgres";
BEGIN;
LOCK TABLE "public"."catalogo" IN SHARE MODE;
DELETE FROM "public"."catalogo";
INSERT INTO "public"."catalogo" ("id","codigo","descripcion","nombre","saldo_cuenta","tipo_cuenta") VALUES (62, '120103', 'falta', 'INSTALACIONES', 'Deudor', 'Detalle'),(69, '120203R', 'falta', 'EQUIPO DE REPARTO', 'Acreedor', 'Detalle'),(84, '210201', 'Falta', 'LOCALES', 'Acreedor', 'Detalle'),(7, '3', 'Falta', 'PATRIMONIO', 'Acreedor', 'Detalle'),(86, '2103', 'SE ABONA: Con el valor de los documentos descontados en instituciones bancarias y financieras.
COMMIT;
BEGIN;
LOCK TABLE "public"."empresa" IN SHARE MODE;
DELETE FROM "public"."empresa";
INSERT INTO "public"."empresa" ("id","descripcion","nombre") VALUES (1, 'Empresa de ventas de productos basicos', 'Robles');
COMMIT;
BEGIN;
LOCK TABLE "public"."partida" IN SHARE MODE;
DELETE FROM "public"."partida";
COMMIT;
BEGIN;
LOCK TABLE "public"."registro_partida" IN SHARE MODE;
DELETE FROM "public"."registro_partida";
COMMIT;
BEGIN;
LOCK TABLE "public"."rol" IN SHARE MODE;
DELETE FROM "public"."rol";
INSERT INTO "public"."rol" ("id","nombre") VALUES (12, 'ROLE_USER'),(13, 'ROLE_USER');
COMMIT;
BEGIN;
LOCK TABLE "public"."usuarios" IN SHARE MODE;
DELETE FROM "public"."usuarios";
INSERT INTO "public"."usuarios" ("id","apellido","email","nombre","password") VALUES (13, 'Ayala', 'aa20005@ues.edu.sv', 'Javier', '$2a$10$19lmzDTHswDkNVB1O9N8JOyGOUHaejwgT2Eh.1M20cGu7NsPaPf12'),(14, 'Roble', 'franciscojavier20002017@gmail.com', 'Reina', '$2a$10$ghX9QaAXjj227pbDn91g3e/BdLq9CGZ6qN7GXk3GjURbX0KfJQ2Ia');
COMMIT;
BEGIN;
LOCK TABLE "public"."usuarios_roles" IN SHARE MODE;
DELETE FROM "public"."usuarios_roles";
INSERT INTO "public"."usuarios_roles" ("usuario_id","rol_id") VALUES (13, 12),(14, 13);
COMMIT;
ALTER TABLE "catalogo" ADD CONSTRAINT "catalogo_pkey" PRIMARY KEY ("id");
ALTER TABLE "empresa" ADD CONSTRAINT "empresa_pkey" PRIMARY KEY ("id");
ALTER TABLE "partida" ADD CONSTRAINT "partida_pkey" PRIMARY KEY ("id");
ALTER TABLE "registro_partida" ADD CONSTRAINT "registro_partida_pkey" PRIMARY KEY ("id");
ALTER TABLE "rol" ADD CONSTRAINT "rol_pkey" PRIMARY KEY ("id");
ALTER TABLE "usuarios" ADD CONSTRAINT "usuarios_pkey" PRIMARY KEY ("id");
ALTER TABLE "catalogo" ADD CONSTRAINT "uk_hl4ja1de23e44y12j8hv8q76a" UNIQUE ("codigo");
ALTER TABLE "registro_partida" ADD CONSTRAINT "fk7x2u2h1qjc7ywg244utm7vg59" FOREIGN KEY ("catalogo_id") REFERENCES "public"."catalogo" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "registro_partida" ADD CONSTRAINT "fk9sr8sw7tkwdog80ly2hhbv0mu" FOREIGN KEY ("partida_id") REFERENCES "public"."partida" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "usuarios" ADD CONSTRAINT "ukkfsp0s1tflm1cwlj8idhqsad0" UNIQUE ("email");
ALTER TABLE "usuarios_roles" ADD CONSTRAINT "fk6yxg1lhuv5nievqea7rvn6afm" FOREIGN KEY ("rol_id") REFERENCES "public"."rol" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "usuarios_roles" ADD CONSTRAINT "fkqcxu02bqipxpr7cjyj9dmhwec" FOREIGN KEY ("usuario_id") REFERENCES "public"."usuarios" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER SEQUENCE "catalogo_id_seq"
OWNED BY "catalogo"."id";
SELECT setval('"catalogo_id_seq"', 220, true);
ALTER SEQUENCE "catalogo_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "empresa_id_seq"
OWNED BY "empresa"."id";
SELECT setval('"empresa_id_seq"', 2, false);
ALTER SEQUENCE "empresa_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "partida_id_seq"
OWNED BY "partida"."id";
SELECT setval('"partida_id_seq"', 2, false);
ALTER SEQUENCE "partida_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "registro_partida_id_seq"
OWNED BY "registro_partida"."id";
SELECT setval('"registro_partida_id_seq"', 2, false);
ALTER SEQUENCE "registro_partida_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "rol_id_seq"
OWNED BY "rol"."id";
SELECT setval('"rol_id_seq"', 14, true);
ALTER SEQUENCE "rol_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "usuarios_id_seq"
OWNED BY "usuarios"."id";
SELECT setval('"usuarios_id_seq"', 15, true);
ALTER SEQUENCE "usuarios_id_seq" OWNER TO "postgres";