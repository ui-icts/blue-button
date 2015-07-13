CREATE SEQUENCE bluebutton.seqnum
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE bluebutton.security_question (
       question_id INT NOT NULL
     , question TEXT
     , PRIMARY KEY (question_id)
);

CREATE TABLE bluebutton.country (
       iso_numeric_code TEXT NOT NULL
     , name TEXT
     , iso_alpha_2_code TEXT
     , iso_alpha_3_code TEXT
     , fips TEXT
     , capital TEXT
     , area TEXT
     , population INT
     , continent TEXT
     , tld TEXT
     , currency_code TEXT
     , currency_name TEXT
     , phone TEXT
     , postal_code_format TEXT
     , postal_code_regex TEXT
     , languages TEXT
     , geoname_id TEXT
     , neighbours TEXT
     , equivalent_fips_code TEXT
     , PRIMARY KEY (iso_numeric_code)
);

CREATE TABLE bluebutton.system_setting (
       name VARCHAR(30) NOT NULL
     , value TEXT
     , active BOOL
     , notes TEXT
     , last_updated_by TEXT
     , last_updated TIMESTAMP
     , PRIMARY KEY (name)
);

CREATE TABLE bluebutton.message (
       message_id INT NOT NULL
     , message_name TEXT NOT NULL
     , message_type TEXT NOT NULL
     , version INT NOT NULL
     , message_text TEXT NOT NULL
     , description TEXT
     , irb_approved BOOL
     , irb_approval_start_date TIMESTAMP
     , irb_approval_end_date TIMESTAMP
     , PRIMARY KEY (message_id)
);

CREATE TABLE bluebutton.person (
       person_id INT NOT NULL
     , first_name TEXT
     , middle_name TEXT
     , last_name TEXT
     , date_of_birth DATE
     , address_line_one TEXT
     , address_line_two TEXT
     , city TEXT
     , state TEXT
     , zipcode INT4
     , country TEXT
     , email TEXT
     , first_login DATE
     , last_login DATE
     , last_updated DATE
     , signature TEXT
     , consent_message INT
     , PRIMARY KEY (person_id)
     , CONSTRAINT FK_person_1 FOREIGN KEY (consent_message)
                  REFERENCES bluebutton.message (message_id)
);

CREATE TABLE bluebutton.region (
       region_id INT NOT NULL
     , iso_numeric_code TEXT
     , region_name TEXT
     , PRIMARY KEY (region_id)
     , CONSTRAINT FK_region_1 FOREIGN KEY (iso_numeric_code)
                  REFERENCES bluebutton.country (iso_numeric_code)
);

CREATE TABLE bluebutton.clinical_document (
       clinical_document_id INT NOT NULL
     , person_id INT NOT NULL
     , document BYTEA
     , name TEXT
     , description TEXT
     , date_uploaded TIMESTAMP
     , PRIMARY KEY (clinical_document_id)
     , CONSTRAINT FK_clinical_data_file_1 FOREIGN KEY (person_id)
                  REFERENCES bluebutton.person (person_id)
);

CREATE TABLE bluebutton.credentials (
       person_id INT NOT NULL
     , username TEXT
     , password TEXT
     , last_updated DATE
     , PRIMARY KEY (person_id)
     , CONSTRAINT FK_credentials_1 FOREIGN KEY (person_id)
                  REFERENCES bluebutton.person (person_id)
);

CREATE TABLE bluebutton.security_response (
       person_id INT NOT NULL
     , question_id INT NOT NULL
     , response TEXT
     , date_updated DATE
     , PRIMARY KEY (person_id, question_id)
     , CONSTRAINT FK_security_response_1 FOREIGN KEY (question_id)
                  REFERENCES bluebutton.security_question (question_id)
     , CONSTRAINT FK_security_response_2 FOREIGN KEY (person_id)
                  REFERENCES bluebutton.person (person_id)
);

