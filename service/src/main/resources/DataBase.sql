-- Таблица для хранения данных о пользователе
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    customer_id SERIAL PRIMARY KEY,
    carrier_id SERIAL PRIMARY KEY,
    contact_info TEXT NOT NULL,
    legal_name VARCHAR(255) NOT NULL,
);

--Таблица для хранения информации о транспортных средствах перевозчиков
CREATE TABLE Vehicles (
    vehicle_id SERIAL PRIMARY KEY,
    carrier_id INT REFERENCES Carriers(carrier_id) NOT NULL,
    vehicle_number VARCHAR(50) NOT NULL,
    trailer_number VARCHAR(50) NULL , -- если вдруг будет менее габаритный транспорт без прицепа
    driver_name VARCHAR(255),
    driver_contact_info TEXT
);

--Таблица, которая хранит информацию о маршрутах заказчика
CREATE TABLE Routes (
    route_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES Customers(customer_id) NOT NULL,
    start_point VARCHAR(255) NOT NULL,
    end_point VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--Таблица связывает маршурты с перевозчиками и их транспортными средствами
CREATE TABLE Route_Assignments (
    assignment_id SERIAL PRIMARY KEY,
    route_id INT REFERENCES Routes(route_id) NOT NULL,
    carrier_id INT REFERENCES Carriers(carrier_id) NOT NULL,
    vehicle_id INT REFERENCES Vehicles(vehicle_id) NOT NULL,
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Cargo_Requests (
    request_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES Customers(customer_id) NOT NULL,
    route_id INT REFERENCES Routes(route_id) NOT NULL,
    cargo_description TEXT NOT NULL, -- Описание груза
    weight DECIMAL(10, 2) NOT NULL, -- Вес груза
    volume DECIMAL(10, 2) NOT NULL, -- Объем груза
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL
);

