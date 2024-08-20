CREATE TYPE product_category AS ENUM ('kitchen', 'cleaning', 'climate', 'self_care', 'multimedia', 'office');
CREATE TYPE gender AS ENUM ('male', 'female');

CREATE TABLE IF NOT EXISTS addresses (
id UUID PRIMARY KEY NOT NULL,
country VARCHAR(75) NOT NULL,
city VARCHAR(75) NOT NULL,
street VARCHAR(75) NOT NULL
);

CREATE TABLE IF NOT EXISTS clients (
id UUID PRIMARY KEY NOT NULL,
client_name VARCHAR(50) NOT NULL,
client_surname VARCHAR(50) NOT NULL,
birth_date DATE NOT NULL,
gender gender NOT NULL,
registration_date DATE NOT NULL,
address_id UUID NOT NULL,
FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE TABLE IF NOT EXISTS products (
id UUID PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
category product_category NOT NULL,
price DOUBLE PRECISION NOT NULL CHECK (available_stock >= 0),
available_stock INT NOT NULL CHECK (available_stock >= 0),
last_update_date DATE NOT NULL,
supplier_id UUID NOT NULL,
image_id UUID,
FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
FOREIGN KEY (image_id) REFERENCES images(id)
);

CREATE TABLE IF NOT EXISTS suppliers (
id UUID PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
address_id UUID NOT NULL,
phone_number VARCHAR(20) NOT NULL,
FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE TABLE IF NOT EXISTS images (
id UUID PRIMARY KEY NOT NULL,
image BYTEA NOT NULL
);

