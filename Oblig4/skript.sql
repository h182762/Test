DROP SCHEMA IF EXISTS deltakerliste CASCADE;
CREATE SCHEMA deltakerliste;
SET search_path = deltakerliste;

CREATE TABLE person
(
   fornavn         CHARACTER VARYING (40),
   etternavn  CHARACTER VARYING (40),
   mobilnr           CHARACTER UNIQUE (8),
   passord			CHARACTER VARYING,
   kjonn			CHARACTER(1),
   PRIMARY KEY (mobilnr)
   
);

	
	






