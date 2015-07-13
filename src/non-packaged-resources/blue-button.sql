---
-- #%L
-- blue-button Spring MVC Web App
-- %%
-- Copyright (C) 2014 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
-- %%
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- 
--      http://www.apache.org/licenses/LICENSE-2.0
-- 
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
-- #L%
---
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

CREATE TABLE bluebutton.lab_test (
       lab_test_id INT NOT NULL
     , name TEXT NOT NULL
     , description TEXT NOT NULL
     , units TEXT NOT NULL
     , loinc_code TEXT NOT NULL
     , date_created DATE NOT NULL
     , date_updated DATE
     , PRIMARY KEY (lab_test_id)
);

CREATE TABLE bluebutton.loinc_code_category (
       id INT NOT NULL
     , loinc_code TEXT NOT NULL
     , name TEXT NOT NULL
     , root_category_name TEXT
     , subroot_category_name TEXT
     , PRIMARY KEY (id)
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
     , uuid TEXT
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
     , registration_date TIMESTAMP
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
     , enabled BOOLEAN DEFAULT true
     , locked BOOLEAN DEFAULT false
     , expired BOOLEAN DEFAULT false
     , temporary_username BOOLEAN DEFAULT false
     , temporary_password BOOLEAN
     , password_reset_key TEXT
     , password_reset_date TIMESTAMP
     , PRIMARY KEY (person_id)
     , CONSTRAINT FK_credentials_1 FOREIGN KEY (person_id)
                  REFERENCES bluebutton.person (person_id)
);

CREATE TABLE bluebutton.lab_test_range (
       lab_test_range_id INT NOT NULL
     , lab_test_id INT NOT NULL
     , sex TEXT NOT NULL
     , min_age_years DOUBLE PRECISION NOT NULL
     , max_age_years DOUBLE PRECISION NOT NULL
     , min_normal DOUBLE PRECISION NOT NULL
     , max_normal DOUBLE PRECISION NOT NULL
     , PRIMARY KEY (lab_test_range_id)
     , CONSTRAINT FK_lab_test_range_1 FOREIGN KEY (lab_test_id)
                  REFERENCES bluebutton.lab_test (lab_test_id)
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

