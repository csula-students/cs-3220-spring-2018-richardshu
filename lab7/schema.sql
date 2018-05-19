CREATE TABLE Events (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    trigger_at INTEGER
);

INSERT INTO Events VALUES 
    (null, "Clicker", "Clicker is now available", 10),
    (null, "Farmer", "The farmers have arrived", 100),
    (null, "Tractor", "Tractors for sale", 1000),
    (null, "SAMPLE EVENT", "This is a sample event. Please delete me", 99999);