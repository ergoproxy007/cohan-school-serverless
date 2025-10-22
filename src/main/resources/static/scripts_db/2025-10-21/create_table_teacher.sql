CREATE TABLE "school".teacher (
    id SERIAL PRIMARY KEY,
    dni VARCHAR(50) NOT NULL,
    name VARCHAR(256) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(256),
    salary DOUBLE PRECISION NOT NULL
);
