CREATE TABLE IF NOT EXISTS transaction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    value INT NOT NULL,
    date DATE NOT NULL,
    credit_card_id INT,
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (credit_card_id) REFERENCES credit_card(id)
);