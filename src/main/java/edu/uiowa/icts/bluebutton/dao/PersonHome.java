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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.uiowa.icts.bluebutton.domain.Person;
import edu.uiowa.icts.spring.GenericDao;

/**
 * Generated by Protogen 
 * @since 07/31/2014 09:37:03 CDT
 */
@Repository("edu_uiowa_icts_bluebutton_dao_PersonHome")
@Transactional
public class PersonHome extends GenericDao<Person> implements PersonService {

    private static final Log log = LogFactory.getLog( PersonHome.class );

    public PersonHome() {
        setDomainName( "edu.uiowa.icts.bluebutton.domain.Person" );
    }

    public Person findById( Integer id ) {
        return (Person) this.sessionFactory.getCurrentSession().get( getDomainName(), id );
    }

}