CREATE TABLE example_entities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    date_created TIMESTAMP,
    date_modified TIMESTAMP,
    user_created VARCHAR(255),
    user_modified VARCHAR(255)
);