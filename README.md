# Blue Button: Java, Spring MVC, AngularJS Grids, D3 Charts
===============================================

# Background
This Java Spring web application takes a Blue Button personal health record as input (C-CDA.xml format) and parses the data into JSON with [BlueButton.js](https://github.com/blue-button/bluebutton.js/). It uses [D3.js](http://d3js.org/) to display interactive charts, [angular-ui-grid](http://ui-grid.info/) to display table views of enhanced records, and [angular-advanced-searchbox](https://github.com/dnauck/angular-advanced-searchbox) for keyword searching.
	In addition, categories and normal ranges for lab results are mapped from data provided by [LOINC.org](https://loinc.org/downloads) along with a custom database of synonyms for lab result names. This web application is designed to be used by medical professionals when visiting with their patients. View the [demo site](https://research.icts.uiowa.edu/blue-button/).

## Build and Installation

The is a maven project that requires Java 1.7 and PostgreSQL 9.3. It uses Jetty 9.2.5 and Spring 4.0.6, and should be built using:

    mvn clean install
    
To create the database schema run:

    mvn -p BuildDatabase    

To access the application run:

    mvn jetty:run

Start Browser, and go to [http://localhost:8080/](http://localhost:8080/):

## Load Supplemental Data
### Lab Result Name Synonyms
Lab result synonyms are loaded by liquibase with the aforementioned: mvn -p BuildDatabase 

### Loinc Code Categories
Import [LOINC Multiaxial Hierarchy File](https://loinc.org/downloads) at /loinccodecategory/import 

### Lab Test Ranges (parent table)
Import CSV files at /labtest/import

	LAB_TEST_ID,LAB_TEST_NAME,TEST_DESCRIPTION,UNITS,LOINC_NUM,DATETIME_CREATED,DATETIME_LASTMODIFIED
	1,"Porphobilinogen,Spot Urine","Porphobilinogen,Spot Urine",Positive,2809-2,29-Sep-03,29-Sep-03
	2,"Nitrogen,Blood","Nitrogen,Blood",Positive,C0440-8,29-Sep-03,29-Sep-03
	3,"Nitrogen,Spot Urine","Nitrogen,Spot Urine",Positive,C0441-6,29-Sep-03,29-Sep-03
	4,"Nitrogen,Fluid","Nitrogen,Fluid",Positive,C0443-2,29-Sep-03,29-Sep-03

### Lab Test Ranges (child table)
Import CSV files at /labtestrange/import

	LAB_TEST_RANGES_UID,LAB_TEST_ID,SEX,MIN_AGE_YEARS,MAX_AGE_YEARS,MIN_NORMAL,MAX_NORMAL
	1,1,FM,0,127,0,0
	2,8,FM,0,127,0,5
	3,9,FM,0,127,0,0
	4,10,FM,0,127,0,0
	5,11,FM,0,127,0,0
	6,13,FM,0,127,0,0
	7,14,FM,0,127,0,0
	8,15,FM,0,127,0,0
	9,16,M,0,127,0,0

## Logging
Update [log4j.properties](src/main/resources/log4j.properties) to change location or log levels

## License
The project is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
