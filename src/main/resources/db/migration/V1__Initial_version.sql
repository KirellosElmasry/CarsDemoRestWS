/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Kirellos
 * Created: Oct 25, 2018
 */

CREATE TABLE CARS (carId bigint not null auto_increment, brand varchar(255), modelDetails varchar(255), model varchar(255), productionYear varchar(255), primary key (carId));

INSERT INTO CARS (carId, brand,model,productionYear,modelDetails) VALUES (1, 'TOYOTA','COROLA','2010','SEDAN');
INSERT INTO CARS (carId, brand,model,productionYear,modelDetails) VALUES (2, 'BMW','x3','2014','CROSOVER');
INSERT INTO CARS (carId, brand,model,productionYear,modelDetails) VALUES (3, 'MAZDA','2','2016','SEDAN');
INSERT INTO CARS (carId, brand,model,productionYear,modelDetails) VALUES (4, 'OPEL','CORSA','2015','SEDAN');