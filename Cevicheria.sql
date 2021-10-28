---CREACION DE LA BD--
USE master
GO
IF EXISTS
(
	SELECT name
	FROM sysdatabases
	WHERE name='Cevicheria'
)
DROP DATABASE Cevicheria
GO
CREATE DATABASE Cevicheria
ON PRIMARY
(
	NAME='Cevicheria_dat',
	FILENAME='D:\db\Cevicheria_dat.mdf',
	SIZE=30MB,
	MAXSIZE=50MB,
	FILEGROWTH=10MB
)
LOG ON
(
	NAME='Cevicheria_log',
	FILENAME='D:\db\Cevicheria_log.ldf',
	SIZE=30MB,
	MAXSIZE=UNLIMITED,
	FILEGROWTH=10%
)
GO
USE Cevicheria
GO
--CREACION DE TABLAS--
CREATE TABLE cliente
(
	dni_cli CHAR(8) NOT NULL
	PRIMARY KEY,
	nom_cli VARCHAR(35) NOT NULL,
	ape_cli VARCHAR(40) NOT NULL,
	sex_cli VARCHAR(30),
	tel_cli CHAR(9),
	dir_cli VARCHAR(50),
	email_cli VARCHAR(50)
)
GO
CREATE TABLE producto
(
	cod_prod VARCHAR(100) NOT NULL
	PRIMARY KEY,
	nom_prod VARCHAR(50),
	pre_prod DECIMAL(4,2),
	des_prod VARCHAR(280)
)
GO
CREATE TABLE empleado
(
	cod_emp VARCHAR(100) NOT NULL
	PRIMARY KEY,
	nom_emp VARCHAR(35) NOT NULL,
	ape_emp VARCHAR(40) NOT NULL,
	sex_emp VARCHAR(30),
	dni_emp CHAR(8) NOT NULL,
	tel_emp CHAR(9),
	email_emp VARCHAR(30),
	cargo_emp VARCHAR(30)
)
GO
CREATE TABLE venta
(
	num_ven VARCHAR(500) NOT NULL
	PRIMARY KEY,
	fec_doc_pag DATE,
	dni_cli CHAR(8),
	cod_emp VARCHAR(100),
	num_ped VARCHAR(500),
	sub_total DECIMAL(5,2),
	igv DECIMAL(5,2),
	total DECIMAL(5,2)
)
GO
CREATE TABLE pedido
(
	num_ped VARCHAR(500) NOT NULL
	PRIMARY KEY,
	fec_ped DATE,
	dni_cli CHAR(8),
	cod_emp VARCHAR(100)
)
GO
CREATE TABLE detalle_pedido
(
	num_ped VARCHAR(500),
	cod_prod VARCHAR(100),
	cant_prod INT
)
GO
--LLAVES FORANEAS
ALTER TABLE pedido
ADD CONSTRAINT pedido_cliente_dni_cli
FOREIGN KEY(dni_cli)
REFERENCES cliente(dni_cli)
GO
ALTER TABLE pedido
ADD CONSTRAINT pedido_empleado_cod_emp
FOREIGN KEY(cod_emp)
REFERENCES empleado(cod_emp)
GO
ALTER TABLE venta
ADD CONSTRAINT venta_cliente_dni_cli
FOREIGN KEY(dni_cli)
REFERENCES cliente(dni_cli)
GO
ALTER TABLE venta
ADD CONSTRAINT venta_empleado_cod_emp
FOREIGN KEY(cod_emp)
REFERENCES empleado(cod_emp)
GO
ALTER TABLE venta
ADD CONSTRAINT venta_pedido_num_ped
FOREIGN KEY(num_ped)
REFERENCES pedido(num_ped)
GO
ALTER TABLE detalle_pedido
ADD CONSTRAINT detalle_pedido_pedido_num_ped
FOREIGN KEY(num_ped)
REFERENCES pedido(num_ped)
GO
ALTER TABLE detalle_pedido
ADD CONSTRAINT detalle_pedido_producto_cod_prod
FOREIGN KEY(cod_prod)
REFERENCES producto(cod_prod)
GO

--EMPLEADO--
--INSERTAR
INSERT INTO empleado VALUES('EMP1','Juan','Perez','Masculino','76299118','3461895','juan_perez@gmail.com','Mozo')
--MOSTRAR
SELECT cod_emp,nom_emp,ape_emp,sex_emp,dni_emp,tel_emp,email_emp,cargo_emp FROM empleado WHERE cod_emp='EMP1';
--MODIFICAR
UPDATE empleado SET cod_emp='EMP1', nom_emp='Juan', ape_emp='Perez', sex_emp='Maculino', dni_emp='76299118', tel_emp='3461895', email_emp='juanp05@gmail.com',cargo_emp='Mozo' WHERE cod_emp='EMP1';
--ELIMINAR
DELETE FROM empleado WHERE cod_emp='EMP01'
SELECT*FROM empleado

--CLIENTE--
--INSERTAR
INSERT INTO cliente VALUES ('73755441','Leila','Cordova','Femenino','943514137','Jr.Manuel Pineda 401','leilacordova6@gmail.com');
INSERT INTO cliente VALUES ('09614063','Diana','Ortiz','Femenino','923333142','Jr.Manuel Pineda 403','dortizluna@gmail.com');
--MOSTRAR
SELECT dni_cli,nom_cli,ape_cli,sex_cli,tel_cli,dir_cli, email_cli FROM cliente WHERE dni_cli='73755441'
--MODIFICAR
UPDATE cliente SET dni_cli='73755441',nom_cli='Leila',ape_cli='Cordova',sex_cli='Femenino',tel_cli='943514137',dir_cli='Jr.Manuel Pineda 403',email_cli='leilacordova6@gmail.com' WHERE dni_cli='73755441';
--ELIMINAR
DELETE FROM cliente WHERE dni_cli='73755441';
SELECT*FROM cliente

--PRODUCTO--
INSERT INTO producto VALUES('PROD1','Ceviche','20','Personal');
INSERT INTO producto Values('PROD2','Arroz con marizcos',30.5,'Familiar');

--MOSTRA
SELECT cod_prod,nom_prod,pre_prod,des_prod FROM producto WHERE cod_prod='PROD1';
--MODIFICAR
UPDATE producto SET cod_prod='PROD2', nom_prod='Arroz con Marisco',pre_prod=30.5, des_prod='Familiar' WHERE cod_prod='PRO2';
--ELIMINAR
DELETE FROM producto WHERE cod_prod='PROD01'
SELECT*FROM producto

--PEDIDO--
--INSERTAR
INSERT INTO pedido VALUES('PED1',GETDATE(),'73755441','EMP1')
INSERT INTO pedido VALUES('PED2',GETDATE(),'09614063','EMP1')

DELETE FROM pedido WHERE num_ped='PED2'
SELECT*FROM pedido

--DETALLE PEDIDO--
--INSERTAR
INSERT INTO detalle_pedido VALUES('PED1','PROD1',2)
INSERT INTO detalle_pedido VALUES('PED1','PROD2',1);
INSERT INTO detalle_pedido VALUES('PED2','PROD1',1)

--ELIMINAR PEDIDO Y DETALLE DE PEDIDO
CREATE PROCEDURE us_eliminarpedido
(
	@num_ped VARCHAR(500)
)
AS
BEGIN
	DELETE FROM detalle_pedido
	WHERE num_ped=@num_ped

	DELETE FROM pedido
	WHERE num_ped=@num_ped
END
GO
EXECUTE us_eliminarpedido 'PED3'

SELECT*FROM detalle_pedido
SELECT*FROM producto

--VENTA--
INSERT INTO venta VALUES('VEN1','2020-07-02','73755441','EMP1','PED1',70.5,12.69,83.19);
INSERT INTO venta VALUES('VEN2',GETDATE(),'73755441','EMP1','PED1',70.5,12.69,83.19);
DELETE FROM venta where num_ven='null'
SELECT*FROM venta

--BUSCAR VENTA
SELECT v.num_ven,v.dni_cli,c.nom_cli,c.ape_cli,v.cod_emp,e.ape_emp,e.nom_emp,v.num_ped,v.sub_total,v.igv,v.total
FROM venta v
INNER JOIN cliente c
ON v.dni_cli=c.dni_cli
INNER JOIN empleado e
ON v.cod_emp=e.cod_emp
WHERE num_ven='VEN1'


--BUSCAR PEDIDO
SELECT p.num_ped,p.dni_cli,c.nom_cli,c.ape_cli,p.cod_emp,e.ape_emp,e.nom_emp 
FROM pedido p 
INNER JOIN cliente c 
ON p.dni_cli=c.dni_cli 
INNER JOIN empleado e 
ON p.cod_emp=e.cod_emp 
WHERE p.num_ped='PED1';

--BUSCAR CLIENTE EN FRAME PEDIDO / VENTA REALIZADA
SELECT dni_cli,nom_cli,ape_cli FROM cliente WHERE dni_cli='73755441';
--BUSCAR EMPLEADO EN FRAME PEDIDO / VENTA REALIZADA
SELECT cod_emp,ape_emp,nom_emp FROM empleado WHERE cod_emp='EMP01';
--BUSCAR PRODUCTO EN FRAME PEDIDO
SELECT cod_prod,nom_prod FROM producto WHERE cod_prod='PROD01'
--MOSTRAR TABLA PRODUCTO EN FRAME PEDIDO
SELECT d.cod_prod,p.nom_prod,d.cant_prod 
FROM detalle_pedido d 
INNER JOIN producto p 
ON d.cod_prod=p.cod_prod 
WHERE d.num_ped='PED1';
--BUSCAR DATOS DE NUM.PED EN FRAME VENTAS REALIZADAS
SELECT dp.num_ped,
	   SUM(pr.pre_prod*dp.cant_prod) AS 'SUB.TOTAL', 
	   SUM(pr.pre_prod*dp.cant_prod*0.18) AS 'IGV', 
	   SUM(pr.pre_prod*dp.cant_prod)+SUM(pr.pre_prod*dp.cant_prod*0.18) AS 'TOTAL'
FROM detalle_pedido dp
INNER JOIN producto pr
ON dp.cod_prod=pr.cod_prod
WHERE dp.num_ped='PED1'
GROUP BY dp.num_ped--,pr.pre_prod,dp.cant_prod

--PEDIDOS REALIZADOS(frame lista pedido)
select dp.num_ped,CONCAT(c.nom_cli ,' ', c.ape_cli),p.cod_emp,p.fec_ped,sum(pr.pre_prod*dp.cant_prod) 
from detalle_pedido dp
inner join pedido p
on p.num_ped=dp.num_ped
INNER JOIN cliente c 
ON c.dni_cli=p.dni_cli
INNER JOIN producto pr 
ON dp.cod_prod=pr.cod_prod
GROUP BY dp.num_ped,c.nom_cli,c.ape_cli,p.cod_emp,p.fec_ped

-------FRAME CIERRE DE CAJA-------------
--PEDIDOS
select dp.num_ped,CONCAT(c.nom_cli ,' ', c.ape_cli),p.cod_emp,p.fec_ped,sum(pr.pre_prod*dp.cant_prod) 
from detalle_pedido dp
inner join pedido p
on p.num_ped=dp.num_ped
INNER JOIN cliente c 
ON c.dni_cli=p.dni_cli
INNER JOIN producto pr 
ON dp.cod_prod=pr.cod_prod
WHERE p.fec_ped='2020-07-03'
GROUP BY dp.num_ped,c.nom_cli,c.ape_cli,p.cod_emp,p.fec_ped
--VENTAS
SELECT cod_emp,SUM(total),count(num_ven)
FROM venta
WHERE fec_doc_pag='2020-07-05'
GROUP BY cod_emp


select*from venta

--CONTAR(PARA CODIGO AUTOMATICO)
select count(cod_emp) AS num from empleado
SELECT COUNT(cod_prod) AS num FROM producto
SELECT COUNT(num_ped) AS num FROM pedido
SELECT COUNT(num_ven) AS num FROM venta


--Reporte
SELECT v.num_ven AS 'NUM.VENTA',
	   v.dni_cli AS 'DNI CLIENTE',
	   c.ape_cli+' '+c.nom_cli AS 'CLIENTE',
	   v.cod_emp AS 'COD.EMPLEADO',
	   v.num_ped AS 'NUM.PED',
	   dp.cod_prod AS 'COD.PRODUCTO',
	   p.nom_prod AS 'NOMBRE PRODUCTO',
	   dp.cant_prod AS 'CANT.PROD',
	   p.pre_prod AS 'PRECIO UNIDAD',
	   SUM(dp.cant_prod*p.pre_prod) AS 'TOTAL',
	
FROM venta v
INNER JOIN detalle_pedido dp
ON v.num_ped=dp.num_ped
INNER JOIN producto p
ON p.cod_prod=dp.cod_prod
INNER JOIN cliente c
ON c.dni_cli=v.dni_cli
WHERE v.num_ven='VEN1'
GROUP BY num_ven,v.dni_cli,v.cod_emp,dp.cant_prod,p.pre_prod,v.num_ped,dp.cod_prod,p.nom_prod,c.nom_cli,c.ape_cli
