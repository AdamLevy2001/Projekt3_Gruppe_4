USE bilabonnement;

-- Users
INSERT INTO users (username, password, role)
VALUES ('demo', '$2a$12$df3jMypen3.b1BcDnPaEM.pAZwAacNf.v2ILv3HH/DukYm.LhiSMC', 'admin'),
       ('dataregistrering', '$2a$12$wH8SRhCGhluK3emKOnkpGOCwF5H2kakxC4whauvBRnSDrV5YB1M7.', 'dataregistrering'),
       ('skadeudbedring', '$2a$12$4hUUaGSIstOwJef4kZ4WJOYG9naR7C.1ooa9iXG/qnGWH7nb89Lsu', 'skadeudbedring'),
       ('forretningsudvikler', '$2a$12$muw/4qSr9BEhAminNmGmouB6zPqhofKakS1e/MejorQQaVqPvJFyK', 'forretningsudvikler');

-- Delivery Locations
INSERT INTO deliveryLocations (address, name)
VALUES ('Vibeholmsvej 31, 2650 Brøndby', 'Bilabonnement'),
       ('Virumgårdsvej 4-10, 2830 Virum', 'DS Virum'),
       ('Slet Parkvej 2, 8310 Tranbjerg', 'DS Aarhus'),
       ('Nørrevænget 9, 8600 Silkeborg', 'DS Silkeborg'),
       ('Håndværkervej 22, 9000 Aalborg', 'DS Aalborg');

-- Cars
INSERT INTO cars (chassis_no, brand, model, purchase_price, status)
VALUES
-- Available
('WBA1A51070J123456', 'BMW', '3-serie', 320000, 'available'),
('WBA1A51070J234567', 'BMW', '5-serie', 480000, 'available'),
('WDD2050091R123456', 'Mercedes', 'C-klasse', 350000, 'available'),
('WDC1660231A123456', 'Mercedes', 'GLE', 680000, 'available'),
('SCA664S50CUX12345', 'Bentley', 'Bentayga', 2100000, 'available'),
('WP0ZZZ99ZTS123456', 'Porsche', 'Cayenne', 890000, 'available'),
('WAUZZZ4G9EN123456', 'Audi', 'Q7', 710000, 'available'),
('WBA5A51070J123456', 'BMW', 'X5', 760000, 'available'),
('YV1XZ852491123456', 'Volvo', 'XC90', 620000, 'available'),
('VF7NCBHZBES123456', 'Peugeot', '308', 195000, 'available'),
('VF1RG000X63123456', 'Renault', 'Austral', 320000, 'available'),
('TMBZZZ3VZ12123456', 'Skoda', 'Octavia', 220000, 'available'),
('VF7AZBHZBES111111', 'DS', 'DS3', 380000, 'available'),
('VF7AZBHZBES222222', 'DS', 'DS4', 420000, 'available'),
('VF7AZBHZBES333333', 'DS', 'DS7', 520000, 'available'),
-- Leased
('WDD2050091R234567', 'Mercedes', 'E-klasse', 520000, 'leased'),
('WDD2050091R345678', 'Mercedes', 'A-klasse', 280000, 'leased'),
('VF1RFD00X63123456', 'Renault', 'Megane', 210000, 'leased'),
('WP0ZZZ99ZTS234567', 'Porsche', 'Macan', 650000, 'leased'),
('WAUZZZ4G9EN234567', 'Audi', 'Q5', 530000, 'leased'),
('WBA5A51070J234567', 'BMW', 'X3', 580000, 'leased'),
('TMBZZZ5JZ14123456', 'Skoda', 'Superb', 350000, 'leased'),
('WDC1660231A234567', 'Mercedes', 'GLS', 820000, 'leased'),
('VF7AZBHZBES444444', 'DS', 'DS4', 420000, 'leased'),
('VF7AZBHZBES555555', 'DS', 'DS9', 680000, 'leased'),
-- Returned
('WAUZZZ8K9BA123456', 'Audi', 'A4', 380000, 'returned'),
('WAUZZZ8K9BA234567', 'Audi', 'A6', 490000, 'returned'),
('YV1XZ852491234567', 'Volvo', 'XC60', 480000, 'returned'),
('VF7AZBHZBES666666', 'DS', 'DS4', 410000, 'returned'),
('WBA1A51070J999999', 'BMW', '7-serie', 980000, 'returned');

-- Customers
INSERT INTO customers (first_name, last_name, email, phone)
VALUES ('Anders', 'Jensen', 'anders.jensen@gmail.com', '12345678'),
       ('Mette', 'Nielsen', 'mette.nielsen@gmail.com', '23456789'),
       ('Lars', 'Hansen', 'lars.hansen@gmail.com', '34567890'),
       ('Sofie', 'Pedersen', 'sofie.pedersen@gmail.com', '45678901'),
       ('Thomas', 'Christensen', 'thomas.christensen@gmail.com', '56789012'),
       ('Camilla', 'Mortensen', 'camilla.mortensen@gmail.com', '67890123'),
       ('Mikkel', 'Andersen', 'mikkel.andersen@gmail.com', '78901234'),
       ('Louise', 'Rasmussen', 'louise.rasmussen@gmail.com', '89012345'),
       ('Jonas', 'Eriksen', 'jonas.eriksen@gmail.com', '90123456'),
       ('Emma', 'Larsen', 'emma.larsen@gmail.com', '11223344'),
       ('Peter', 'Madsen', 'peter.madsen@gmail.com', '22334455'),
       ('Anna', 'Olsen', 'anna.olsen@gmail.com', '33445566'),
       ('Christian', 'Møller', 'christian.moller@gmail.com', '44556677'),
       ('Maria', 'Thomsen', 'maria.thomsen@gmail.com', '55667788'),
       ('Kasper', 'Kristiansen', 'kasper.kristiansen@gmail.com', '66778899');

-- Leases
INSERT INTO leases (carVehicle_no, customer_id, deliveryLocation_id, down_payment, monthly_payment, km_per_month,
                    start_date, end_date, status)
VALUES
-- Aktive lejeaftaler
(16, 1, 1, 15000, 5200, 1500, '2025-01-01', '2025-07-01', 'active'),
(17, 2, 1, 12000, 4800, 2000, '2025-02-01', '2025-08-01', 'active'),
(18, 3, 1, 10000, 3900, 1750, '2025-03-01', '2025-09-01', 'active'),
(19, 4, 1, 25000, 8500, 2000, '2025-02-01', '2025-08-01', 'active'),
(20, 5, 1, 18000, 6800, 1750, '2025-03-01', '2025-09-01', 'active'),
(21, 6, 1, 20000, 7100, 2500, '2025-01-01', '2025-07-01', 'active'),
(22, 7, 1, 28000, 9500, 2000, '2025-04-01', '2025-10-01', 'active'),
(23, 8, 1, 22000, 8200, 2500, '2025-03-01', '2025-09-01', 'active'),
(24, 9, 2, 18000, 6200, 1500, '2025-02-01', '2025-08-01', 'active'),
(25, 10, 3, 25000, 8800, 2000, '2025-03-01', '2025-09-01', 'active'),
-- Afsluttede lejeaftaler
(26, 11, 1, 18000, 6100, 2500, '2024-09-01', '2025-02-01', 'completed'),
(27, 12, 1, 20000, 7200, 3000, '2024-10-01', '2025-03-01', 'completed'),
(28, 13, 1, 16000, 5900, 1500, '2024-12-01', '2025-05-01', 'completed'),
(29, 14, 4, 14000, 5100, 1750, '2024-10-01', '2025-03-01', 'completed'),
(30, 15, 5, 35000, 12000, 3000, '2024-11-01', '2025-04-01', 'completed');

-- Damage Reports
INSERT INTO damageReports (lease_id, created_at)
VALUES (11, '2025-02-10'),
       (12, '2025-03-15'),
       (13, '2025-05-05'),
       (14, '2025-03-20'),
       (15, '2025-04-10');

-- Damages
INSERT INTO damages (damageReport_id, description, price)
VALUES (1, 'Ridse på venstre dør', 1500),
       (1, 'Revnet forrude', 3000),
       (1, 'Plet på forsæde', 500),
       (2, 'Bulet kofanger', 2500),
       (2, 'Ridset alufælg', 400),
       (2, 'Plet på bagagerum', 800),
       (3, 'Ridse på motorhjelm', 1200),
       (3, 'Knust baglygte', 2200),
       (3, 'Bulet bagdør', 3500),
       (4, 'Ridset fælg', 600),
       (4, 'Ridse på kofanger', 900),
       (5, 'Knust sidevindue', 4500),
       (5, 'Bulet skærm', 2800),
       (5, 'Ridse på tag', 1800);