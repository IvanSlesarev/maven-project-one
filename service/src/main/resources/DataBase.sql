-- Таблица для хранения данных о пользователе
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- 'customer', 'carrier', 'admin'
    contact_info TEXT NOT NULL,
    legal_name VARCHAR(255) -- Только для перевозчиков (может быть NULL)
);

--Таблица для хранения информации о транспортных средствах перевозчиков
CREATE TABLE Vehicle (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id) NOT NULL, -- Перевозчик
    vehicle_number VARCHAR(50) NOT NULL,
    trailer_number VARCHAR(50), -- Может быть NULL, если прицепа нет
    driver_name VARCHAR(255),
    driver_contact_info TEXT
);

--Таблица, которая хранит информацию о маршрутах заказчика
CREATE TABLE Driver_Request (
    id SERIAL PRIMARY KEY,
    cargo_request_id INT REFERENCES Cargo_Request(id),
    user_id INT REFERENCES Users(id) NOT NULL, -- driver
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL, -- 'open', 'in_progress', 'completed'
);

--Таблица с заявками и инфо
CREATE TABLE Cargo_Request (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id) NOT NULL,
    cargo_description TEXT NOT NULL,
    start_point VARCHAR(255) NOT NULL,
    end_point VARCHAR(255) NOT NULL,
    weight DECIMAL(10, 2) NOT NULL,
    volume DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL -- 'pending', 'accepted', 'rejected', 'completed'
);

