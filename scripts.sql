-- Create the database
CREATE DATABASE orderdb;



-- Create a schema
CREATE SCHEMA sch;

-- Set the search path to use the new schema
SET search_path TO sch;

-- Create a table for storing customer information
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone_number VARCHAR(20),
    registration_date DATE DEFAULT CURRENT_DATE
);

-- Create a table for storing product information
CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    stock_quantity INTEGER DEFAULT 0
);

-- Create a table for storing order information
CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customers(customer_id),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount NUMERIC(10, 2) NOT NULL
);

-- Create a table for storing order details
CREATE TABLE order_details (
    order_detail_id SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES orders(order_id),
    product_id INTEGER REFERENCES products(product_id),
    quantity INTEGER NOT NULL,
    unit_price NUMERIC(10, 2) NOT NULL
);
INSERT INTO customers (first_name, last_name, email, phone_number, registration_date) VALUES
('Kevin', 'Lee', 'kevin.lee@email.com', '555-666-7777', '2022-01-15'),
('Laura', 'Walker', 'laura.walker@email.com', '444-333-2222', '2022-06-30'),
('Michael', 'Green', 'michael.green@email.com', '111-999-8888', '2023-03-10');
select * from sch.customers;

INSERT INTO products (product_name, description, price, stock_quantity) VALUES
('Smartphone X', 'Latest model smartphone with advanced features', 799.99, 100),
('Laptop Pro', 'High-performance laptop for professionals', 1299.99, 50),
('Wireless Earbuds', 'True wireless earbuds with noise cancellation', 149.99, 200),
('4K Smart TV', '55-inch 4K resolution smart TV', 599.99, 30),
('Digital Camera', 'Mirrorless digital camera with 24MP sensor', 899.99, 25),
('Fitness Tracker', 'Waterproof fitness tracker with heart rate monitor', 79.99, 150),
('Gaming Console', 'Next-gen gaming console with 4K graphics', 499.99, 40),
('Bluetooth Speaker', 'Portable Bluetooth speaker with 20-hour battery life', 89.99, 100),
('Robot Vacuum', 'Smart robot vacuum with mapping technology', 299.99, 60),
('Coffee Maker', 'Programmable coffee maker with built-in grinder', 129.99, 75);

commit;

select * from sch.products;
select * from sch.orders o ;
