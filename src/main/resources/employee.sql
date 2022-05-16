CREATE TABLE IF NOT EXISTS employee_test
(
    id          	        VARCHAR(100) PRIMARY KEY,
    first_name              VARCHAR(200) NOT NULL,
    last_name  		        VARCHAR(200) NOT NULL,
    preferred_name 	        VARCHAR(200) NOT NULL,
    preferred_preset	    VARCHAR(200) NOT NULL,
    preferred_speed	        NUMERIC (2, 1) NOT NULL,
    voice_link		        VARCHAR(500) NOT NULL,
    created    		        TIMESTAMP NOT NULL,
    updated   		        TIMESTAMP NOT NULL,
    active                  BOOLEAN NOT NULL,
    version                 INTEGER NOT NULL
);


ALTER TABLE employee_test RENAME preferred_speed TO pf_old;
ALTER TABLE employee_test ADD preferred_speed NUMERIC (2, 1) NOT NULL;
UPDATE employee_test set preferred_speed = 1.0 where 1=1;
