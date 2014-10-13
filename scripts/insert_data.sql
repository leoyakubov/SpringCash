USE `springcash`;


INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER'),(3,'DEMO');

INSERT INTO `users` VALUES
(1,'google','google@gmail.com','$2a$10$QVURUREsvaP.yGNKK9Gmcuw4tdfR2v4fuUh.OdOL.9xHrNFvWchPa','Google','Inc',TRUE,1),
(2,'microsoft','microsoft@gmail.com','$2a$10$PZzH10esghhK9hrZo4h0zOFeZkbN1HM5NGFBM5nERSG6UZfanE/Yi','Microsoft','Inc',TRUE,2),
(3,'apple','apple@gmail.com','$2a$10$AZaO7lJjfJ5zlqFIyJ5VkupExuEl/s66Lw/aeD4vjD4hEv5xs2w1.','Apple','Inc',FALSE,1),
(4,'oracle','oracle@gmail.com','$2a$10$tuqkWdZ/sKAAWWRo6eoUnuuBJHk7G5vB4zYmswkhuaxmF60NsbrvO','Oracle','Inc',TRUE,3);