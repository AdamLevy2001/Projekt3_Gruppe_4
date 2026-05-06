DROP DATABASE IF EXISTS Bilabonnement;

CREATE DATABASE Bilabonnement;

USE Bilabonnement;

CREATE TABLE users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE cars (
    vehicle_no     INT AUTO_INCREMENT PRIMARY KEY,
    chassis_no     VARCHAR(50) NOT NULL UNIQUE,
    brand          VARCHAR(50) NOT NULL,
    model          VARCHAR(50) NOT NULL,
    purchase_price DOUBLE      NOT NULL,
    status         VARCHAR(50) NOT NULL
);

CREATE TABLE customers (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    phone      VARCHAR(20)  NOT NULL
);

CREATE TABLE deliveryLocations (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    name    VARCHAR(100) NOT NULL
);

CREATE TABLE leases (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    carVehicle_no       INT         NOT NULL,
    customer_id         INT         NOT NULL,
    deliveryLocation_id INT         NOT NULL,
    down_payment        DOUBLE      NOT NULL,
    monthly_payment     DOUBLE      NOT NULL,
    km_per_month        INT         NOT NULL,
    start_date          DATE        NOT NULL,
    end_date            DATE        NOT NULL,
    status              VARCHAR(50) NOT NULL,
    FOREIGN KEY (carVehicle_no) REFERENCES cars (vehicle_no),
    FOREIGN KEY (customer_id) REFERENCES customers (id),
    FOREIGN KEY (deliveryLocation_id) REFERENCES deliveryLocations (id)
);

CREATE TABLE damageReports (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    lease_id INT  NOT NULL,
    created_at     DATE NOT NULL,
    FOREIGN KEY (lease_id) REFERENCES leases (id)
);

CREATE TABLE damages (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    damageReport_id INT          NOT NULL,
    description     VARCHAR(255) NOT NULL,
    price           DOUBLE       NOT NULL,
    FOREIGN KEY (damageReport_id) REFERENCES damageReports (id)
);
