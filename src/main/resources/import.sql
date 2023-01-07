INSERT INTO `clientes` VALUES (1,'Rodríguez Bravo',NULL,'Calle Concepción Nª 107','damAdmin@hotmail.com','perfil.jpg','Alejandro','admin','$2a$10$aNC9DK/P5dsuniCWKYiTj.djhuFyVbSyU2nDHYDDq6qdooaulLPzi','686177070'),(2,'test',NULL,'test','test@gmail.com','mario.jpg','test','test','$2a$10$x.0cvFfBVjNwobArvVYUEe1xXFBjDB37MXb3.4MWArGrhfbg9gmcy','696969698'), (3,'prueba',NULL,'prueba','test@gmail.com','mario.jpg','pueba','prueba','$2a$10$x.0cvFfBVjNwobArvVYUEe1xXFBjDB37MXb3.4MWArGrhfbg9gmcy','696969698');

INSERT INTO `productos` VALUES (1,100,'2022-05-12','Sofá color beig ','sofa.jpg','Sofá',560,2),(2,100,'2022-05-13',' Salón moderno','default.jpg','Mueble salón oferta',1500,2);

INSERT INTO `facturas` VALUES (1,'factura prueba 1','2022-05-12',NULL,'0000000001',560,2),(2,'factura prueba 2','2022-05-13',NULL,'0000000002',560,2);

INSERT INTO `facturas_items` VALUES (1,1,'Sofá',560,560,1,2),(2,1,'Sofá',560,560,2,2);

INSERT INTO `rol` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');

INSERT INTO `usuario_rol` VALUES (1,1), (2,2);