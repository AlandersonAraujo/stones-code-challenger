INSERT INTO transaction (client_id, value, date, credit_card_id)
VALUES (
    (SELECT id FROM client WHERE name = 'Luke Skywalker'),
    1234,
    '2016-08-19',
    (SELECT id FROM credit_card WHERE number LIKE '%1234' AND holder_name = 'Luke Skywalker')
);

INSERT INTO transaction (client_id, value, date, credit_card_id)
VALUES (
    (SELECT id FROM client WHERE name = 'Luke Skywalker'),
    1234,
    '2016-08-19',
    (SELECT id FROM credit_card WHERE number LIKE '%1234' AND holder_name = 'Luke Skywalker')
);

INSERT INTO transaction (client_id, value, date, credit_card_id)
VALUES (
    (SELECT id FROM client WHERE name = 'Luke Skywalker'),
    1234,
    '2016-08-19',
    (SELECT id FROM credit_card WHERE number LIKE '%1234' AND holder_name = 'Luke Skywalker')
);
