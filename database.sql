CREATE TABLE departments
(
	id serial PRIMARY KEY,
  name varchar(20)
);

CREATE TABLE teachers
(
	id serial PRIMARY KEY,
 	name varchar(20) NOT NULL,
	department_id int NOT NULL,
  CONSTRAINT fk_teacher_department FOREIGN KEY (department_id) REFERENCES departments (id)
);

CREATE TABLE addresses 
(
	id serial PRIMARY KEY,
  street VARCHAR(30) NOT NULL, 
  cep VARCHAR(9) NOT NULL,
  number int NOT NULL,
  type varchar(20) NOT NULL
);

CREATE TABLE students
(
	id serial PRIMARY KEY,
  name varchar(20) NOT NULL,
  cpf varchar(11) NOT NULL,
  address_id int NOT NUll,
  CONSTRAINT fk_student_address FOREIGN KEY(address_id) REFERENCES addresses (id)
);

CREATE TABLE student_teacher 
(
	id serial PRIMARY KEY,
  teacher_id int NOT NULL,
  student_id int NOT NULL,
  CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teachers (id),
  CONSTRAINT fk_student fOREIGN KEY (student_id) REFERENCES students (id)
);