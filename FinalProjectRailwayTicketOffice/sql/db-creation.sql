USE final_project; 
DROP TABLE IF EXISTS train_station;

CREATE TABLE train_station (
	id INT PRIMARY KEY AUTO_INCREMENT ,
	name VARCHAR(12) UNIQUE NOT NULL 
);




