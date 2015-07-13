package edu.uiowa.icts.bluebutton.dao;

/*
 * #%L
 * blue-button Spring MVC Web App
 * %%
 * Copyright (C) 2014 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import edu.uiowa.icts.spring.*;
import edu.uiowa.icts.bluebutton.domain.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import edu.uiowa.icts.util.SortColumn;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.criterion.Restrictions;

/**
 * Generated by Protogen 
 * @since 10/29/2014 09:18:02 CDT
 */
@Repository("edu_uiowa_icts_bluebutton_dao_LabTestRangeHome")
@Transactional
public class LabTestRangeHome extends GenericDao<LabTestRange> implements LabTestRangeService {

    private static final Log log = LogFactory.getLog( LabTestRangeHome.class );

    public LabTestRangeHome() {
        setDomainName( "edu.uiowa.icts.bluebutton.domain.LabTestRange" );
    }

    public LabTestRange findById( Integer id ) {
        return (LabTestRange) this.sessionFactory.getCurrentSession().get( LabTestRange.class, id );
    }

	@Override
	public void importCSV(InputStream fileInputStream) throws IOException {
		Reader in  = new BufferedReader(new InputStreamReader(fileInputStream));
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("LAB_TEST_RANGES_UID","LAB_TEST_ID","SEX","MIN_AGE_YEARS","MAX_AGE_YEARS","MIN_NORMAL","MAX_NORMAL").withSkipHeaderRecord(true).parse(in);
		for (CSVRecord record : records) {
			LabTestRange labTestRange = new LabTestRange();
			labTestRange.setLabTestRangeId(new Integer(record.get("LAB_TEST_RANGES_UID")));
			
			LabTest labTest = (LabTest) this.sessionFactory.getCurrentSession().get( LabTest.class, new Integer(record.get("LAB_TEST_ID")));
			labTestRange.setLabTest(labTest); 
//			labTest.getLabTestRanges().add(labTestRange);
//			this.sessionFactory.getCurrentSession().save(labTest);	
			
			labTestRange.setSex(record.get("SEX"));
			labTestRange.setMinAgeYears(new Double(record.get("MIN_AGE_YEARS")));
			labTestRange.setMaxAgeYears(new Double(record.get("MAX_AGE_YEARS")));
			labTestRange.setMinNormal(new Double(record.get("MIN_NORMAL")));
			labTestRange.setMaxNormal(new Double(record.get("MAX_NORMAL")));

			this.sessionFactory.getCurrentSession().save(labTestRange);	
			this.sessionFactory.getCurrentSession().flush();
			this.sessionFactory.getCurrentSession().clear();
		}
		
	}

}