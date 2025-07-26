
CREATE TABLE settings(
    id UUID UNIQUE PRIMARY KEY,
    workday_hours INTEGER NOT NULL,
    overtime_rate INTEGER NOT NULL
);