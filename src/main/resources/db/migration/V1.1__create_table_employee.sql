CREATE TABLE Employees(
    -- table columns
    id CHAR(64) PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    gender VARCHAR(64),
    address VARCHAR(128)
);