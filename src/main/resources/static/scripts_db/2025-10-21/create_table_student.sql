CREATE TABLE "school".student (
    id SERIAL PRIMARY KEY,
    dni VARCHAR(50) NOT NULL,
    name VARCHAR(256) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(256),
    number INTEGER,
    average_mark DOUBLE PRECISION,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deactivated_at TIMESTAMP NULL,
    updated_by VARCHAR(20) DEFAULT 'system',
    CONSTRAINT uq_student_dni UNIQUE (dni)
);
