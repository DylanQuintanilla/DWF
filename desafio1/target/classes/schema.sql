-- Eliminamos las tablas si ya existen
DROP TABLE IF EXISTS persona;
DROP TABLE IF EXISTS ocupaciones;

-- Creación de la tabla ocupaciones
CREATE TABLE ocupaciones (
id_ocupacion INT PRIMARY KEY,
nombre_ocupacion VARCHAR(100) NOT NULL
);

-- Creación de la tabla persona
CREATE TABLE persona (
id_persona INT PRIMARY KEY AUTO_INCREMENT,
nombre_persona VARCHAR(100) NOT NULL,
edad_persona INT NOT NULL,
telefono_persona VARCHAR(9) NOT NULL,
sexo_persona VARCHAR(50) NOT NULL,
id_ocupacion INT NOT NULL,
fecha_nac DATE NOT NULL,
CONSTRAINT fk_ocupacion FOREIGN KEY (id_ocupacion) REFERENCES ocupaciones(id_ocupacion)
);
