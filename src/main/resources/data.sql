INSERT INTO USER (id, email_address, first_name, last_name , role, ssn, user_name) VALUES (101, 'harveyspecter@suits.com','Harvey','Specter','Managing Partner','SHDS686','harveyspecter');
INSERT INTO USER (id, email_address, first_name, last_name , role, ssn, user_name) VALUES (102, 'donnapaulson@suits.com','Donna','Paulson','Chief Operating Officer','SHDS6987','donnapaulson');
INSERT INTO USER (id, email_address, first_name, last_name , role, ssn, user_name) VALUES (103, 'louislitt@suits.com','Louis','Litt','Managing Partner','SHDS5365','louislitt');



INSERT INTO ORDERS (order_id,order_description,user_id) VALUES (2001,'order11',101);
INSERT INTO ORDERS (order_id,order_description,user_id) VALUES (2002,'order12',101);
INSERT INTO ORDERS (order_id,order_description,user_id) VALUES (2003,'order13',101);
INSERT INTO ORDERS (order_id,order_description,user_id) VALUES (2004,'order21',102);
INSERT INTO ORDERS (order_id,order_description,user_id) VALUES (2005,'order22',102);
INSERT INTO ORDERS (order_id,order_description,user_id) VALUES (2006,'order31',103);