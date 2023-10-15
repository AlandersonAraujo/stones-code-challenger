CREATE TABLE IF NOT EXISTS purchase (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    purchase_value INT NOT NULL,
    purchase_date DATE NOT NULL,
    credit_card_id INT,
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (credit_card_id) REFERENCES credit_card(id)
);