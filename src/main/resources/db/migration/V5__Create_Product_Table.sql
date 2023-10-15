CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    zipcode VARCHAR(255) NOT NULL,
    seller_id INT,
    thumbnailHd VARCHAR(255),
    date DATE,
    FOREIGN KEY (seller_id) REFERENCES seller(id)
);

CREATE TABLE IF NOT EXISTS product_seller (
    product_id INT,
    seller_id INT,
    PRIMARY KEY (product_id, seller_id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (seller_id) REFERENCES seller(id)
);