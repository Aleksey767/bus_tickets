CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');
CREATE SEQUENCE user_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE "User" (
                        id INT PRIMARY KEY DEFAULT nextval('user_id_seq'),
                        name VARCHAR(255) NOT NULL,
                        creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE ticket_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE "Ticket" (
                          id INT PRIMARY KEY DEFAULT nextval('ticket_id_seq'),
                          user_id INT NOT NULL,
                          ticket_type ticket_type NOT NULL,
                          creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES "User" (id) ON DELETE CASCADE
);