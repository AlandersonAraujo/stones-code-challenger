CREATE TABLE IF NOT EXISTS credit_card (
    id INT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(255) NOT NULL,
    cv VARCHAR(255) NOT NULL,
    holder_name VARCHAR(255) NOT NULL,
    exp_date VARCHAR(255) NOT NULL
);