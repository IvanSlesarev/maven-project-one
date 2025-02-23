-- Таблица для хранения данных о пользователе
CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- 'customer', 'carrier', 'admin'
    contact_info TEXT NOT NULL,
    legal_name VARCHAR(255) -- Только для перевозчиков (может быть NULL)
);

--Таблица для хранения информации о транспортных средствах перевозчиков
CREATE TABLE Vehicles (
    vehicle_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(user_id) NOT NULL, -- Перевозчик
    vehicle_number VARCHAR(50) NOT NULL,
    trailer_number VARCHAR(50), -- Может быть NULL, если прицепа нет
    driver_name VARCHAR(255),
    driver_contact_info TEXT
);

--Таблица, которая хранит информацию о маршрутах заказчика
CREATE TABLE Routes (
    route_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(user_id) NOT NULL, -- Заказчик
    start_point VARCHAR(255) NOT NULL,
    end_point VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL, -- 'open', 'in_progress', 'completed'
);

--Таблица с заявками и инфо
CREATE TABLE Cargo_Requests (
    request_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(user_id) NOT NULL,
    route_id INT REFERENCES Routes(route_id) NOT NULL,
    cargo_description TEXT NOT NULL,
    weight DECIMAL(10, 2) NOT NULL,
    volume DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL -- 'pending', 'accepted', 'rejected', 'completed'
);

