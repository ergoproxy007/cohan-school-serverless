CREATE TABLE "school".teacher (
    id SERIAL PRIMARY KEY,
    dni VARCHAR(50) NOT NULL,
    name VARCHAR(256) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(256),
    salary DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deactivated_at TIMESTAMP NULL,
    updated_by VARCHAR(20) DEFAULT 'system',
    CONSTRAINT uq_teacher_dni UNIQUE (dni)
);
