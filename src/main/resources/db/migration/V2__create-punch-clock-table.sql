
CREATE TABLE punch_clock(
    id UUID UNIQUE PRIMARY KEY,
    user_id UUID,
    type VARCHAR(20) NOT NULL,
    timestamp timestamp NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);