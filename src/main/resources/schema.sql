CREATE TABLE produto (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          preco DOUBLE NOT NULL,
                          preco_final DOUBLE NOT NULL,
                          estoque INT NOT NULL
);