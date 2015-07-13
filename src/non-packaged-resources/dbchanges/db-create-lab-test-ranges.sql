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

