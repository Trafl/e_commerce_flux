CREATE TABLE clients(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(300) NOT NULL,
    email VARCHAR(150) NOT NULL,
    birth_day DATE,
    street VARCHAR(300) NOT NULL,
    city VARCHAR(300) NOT NULL,
    number INT NOT NULL,
    postal_number INT
);