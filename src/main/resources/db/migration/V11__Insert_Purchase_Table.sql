INSERT INTO purchase (client_id, purchase_value, purchase_date, credit_card_id)
VALUES (
    (SELECT id FROM client WHERE name = 'Luke Skywalker'),
    1234,
    '2016-08-19',
    (SELECT id FROM credit_card WHERE number LIKE '%1234')
);

INSERT INTO purchase (client_id, purchase_value, purchase_date, credit_card_id)
VALUES (
    (SELECT id FROM client WHERE name = 'Luke Skywalker'),
    1234,
    '2016-08-19',
    (SELECT id FROM credit_card WHERE number LIKE '%1234')
);

INSERT INTO purchase (client_id, purchase_value, purchase_date, credit_card_id)
VALUES (
    (SELECT id FROM client WHERE name = 'Luke Skywalker'),
    1234,
    '2016-08-19',
    (SELECT id FROM credit_card WHERE number LIKE '%1234')
);
