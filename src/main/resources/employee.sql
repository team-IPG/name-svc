CREATE TABLE IF NOT EXISTS employee_test
(
    id          	        VARCHAR(100) PRIMARY KEY,
    first_name              VARCHAR(200) NOT NULL,
    last_name  		        VARCHAR(200) NOT NULL,
    preferred_name 	        VARCHAR(200) NOT NULL,
    preferred_preset	    VARCHAR(200) NOT NULL,
    preferred_speed	        INTEGER NOT NULL,
    voice_link		        VARCHAR(500) NOT NULL,
    created    		        TIMESTAMP NOT NULL,
    updated   		        TIMESTAMP NOT NULL,
    active                  BOOLEAN NOT NULL,
    version                 INTEGER NOT NULL
);
