INSERT INTO product (title, price, zipcode, seller_id, thumbnailHd, date) VALUES (
    'Blusa do Imperio', 7990, '78993-000', (SELECT id FROM seller WHERE name = 'João da Silva'), 'https://cdn.awsli.com.br/600x450/21/21351/produto/3853007/f66e8c63ab.jpg', '2015-11-26'
);

INSERT INTO product (title, price, zipcode, seller_id, thumbnailHd, date) VALUES (
    'Blusa Han Shot First', 7990, '13500-110', (SELECT id FROM seller WHERE name = 'Joana'), 'https://cdn.awsli.com.br/1000x1000/21/21351/produto/7234148/55692a941d.jpg', '2015-11-26'
);

INSERT INTO product (title, price, zipcode, seller_id, thumbnailHd, date) VALUES (
    'Sabre de luz', 150000, '13537-000', (SELECT id FROM seller WHERE name = 'Mario Mota'), 'http://www.obrigadopelospeixes.com/wp-content/uploads/2015/12/kalippe_lightsaber_by_jnetrocks-d4dyzpo1-1024x600.jpg', '2015-11-20'
);