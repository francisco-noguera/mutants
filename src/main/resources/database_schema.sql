CREATE DATABASE mutant;

USE mutant;

CREATE TABLE mutant(
mutant_id VARCHAR(36) NOT NULL,
dna TEXT NOT NULL,
is_mutant BOOLEAN NOT NULL,
PRIMARY KEY (mutant_id)
);