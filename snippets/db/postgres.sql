CREATE TABLE Promoter
(
  Promoter_id INTEGER PRIMARY KEY,
  Login VARCHAR(30),
  Password VARCHAR (30),
  Email VARCHAR(50),
  Name VARCHAR(20),
  Surname VARCHAR(30)
);

CREATE TABLE Student
(
  Student_id INTEGER PRIMARY KEY,
  Login VARCHAR(30),
  Password VARCHAR (30),
  Email VARCHAR(50),
  Name VARCHAR(20),
  Surname VARCHAR(30),
  Index_nr INTEGER,
  Major VARCHAR(30),
  Degree VARCHAR(30)
);

CREATE TABLE Consultations
(
	Consultations_id INTEGER PRIMARY KEY,
	Promoter_id INTEGER,
	Student_id INTEGER,
	Date DATE,
	Accepted BOOLEAN,
	Note VARCHAR(255)
);

CREATE TABLE Diploma
(
	Diploma_id INTEGER PRIMARY KEY,
	Subject VARCHAR(50),
	Toc JSON,
	Student_id INTEGER,
	Promoter_id INTEGER,
	Sources JSON,
	Chapters JSON,
	Tasks JSON
);

ALTER TABLE Consultations
ADD FOREIGN KEY(Promoter_id)
REFERENCES Promoter(Promoter_id);

ALTER TABLE Consultations
ADD FOREIGN KEY(Student_id)
REFERENCES Student(Student_id);

ALTER TABLE Diploma
ADD FOREIGN KEY(Promoter_id)
REFERENCES Promoter(Promoter_id);

ALTER TABLE Diploma
ADD FOREIGN KEY(Student_id)
REFERENCES Student(Student_id);

-----------JSON
CREATE TABLE products (id INTEGER, product_name text);

SELECT DISTINCT product_type, 
				data->>'brand' as Brand, 
				data->>'available' as Availability
	FROM json_data
	JOIN products
		ON (products.product_type=json_data->>'name')
	WHERE json_data->>'available'=true;
	
CREATE INDEX author_index
	ON books
	USING GIN ((jsondata -> 'authors'));

SELECT * FROM books WHERE jsondata -> 'authors' ? 'Carl Bernstein'