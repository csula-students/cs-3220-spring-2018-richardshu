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

CREATE TABLE Generators (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    rate INTEGER,
    base_cost INTEGER,
    unlock_at INTEGER
);

INSERT INTO Generators VALUES 
    (null, "Clicker", "Click click click! Harvest strawberries one click at a time", 5, 10, 10),
    (null, "Farmer", "Old McDonald had a strawberry farm...", 10, 100, 100),
    (null, "Tractor", "Vroom vroom...tractors coming through!", 20, 1000, 1000),
    (null, "SAMPLE GENERATOR", "This is a sample generator, Please delete me", 999, 9999, 99999);