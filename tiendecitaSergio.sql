CREATE DATABASE tiendecitaSergio
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_spanish_ci;

USE tiendecitaSergio;

CREATE TABLE Articulo (
    idArticulo INT AUTO_INCREMENT PRIMARY KEY,
    descripcionArticulo VARCHAR(256),
    precioArticulo DECIMAL(10, 2),
    stockArticulo INT
);

CREATE TABLE Ticket (
    idTicket INT AUTO_INCREMENT PRIMARY KEY,
    fechaTicket DATE,
    totalPrecioTicket DECIMAL(10, 2)
);

CREATE TABLE RelacionTicketArticulo (
    idRelacionTicketArticulo INT AUTO_INCREMENT PRIMARY KEY,
    idTicketFK INT,
    idArticuloFK INT,
    FOREIGN KEY (idTicketFK) REFERENCES Ticket(idTicket),
    FOREIGN KEY (idArticuloFK) REFERENCES Articulo(idArticulo)
);

select * from articulo;

select * from RelacionTicketArticulo;
 