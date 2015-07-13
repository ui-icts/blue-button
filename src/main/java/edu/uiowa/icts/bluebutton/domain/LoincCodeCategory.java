package edu.uiowa.icts.bluebutton.domain;

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

import java.util.Set;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Table;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.*;
import javax.persistence.CascadeType;
import edu.uiowa.icts.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * Generated by Protogen - www.icts.uiowa.edu/protogen 
 * @since 01/27/2015 10:53:12 AM CST
 */
@Entity( name = "edu_uiowa_icts_bluebutton_domain_loincCodeCategory" )
@Table( name = "loinc_code_category", schema = "bluebutton" )
public class LoincCodeCategory { 

	private static final Log log = LogFactory.getLog(LoincCodeCategory.class);

        private Integer id;
        private String loincCode;
        private String name;
        private String rootCategoryName;
        private String subrootCategoryName;


    /*****id*****/
    @javax.persistence.SequenceGenerator(  name="gen",  sequenceName="bluebutton.seqnum",allocationSize=1)
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO,generator="gen")
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    /*****loincCode*****/
    @Column(name = "loinc_code")
    public String getLoincCode(){
        return loincCode;
    }

    public void setLoincCode(String loincCode){
        this.loincCode = loincCode;
    }

    /*****name*****/
    @Column(name = "name")
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    /*****rootCategoryName*****/
    @Column(name = "root_category_name")
    public String getRootCategoryName(){
        return rootCategoryName;
    }

    public void setRootCategoryName(String rootCategoryName){
        this.rootCategoryName = rootCategoryName;
    }

    /*****subrootCategoryName*****/
    @Column(name = "subroot_category_name")
    public String getSubrootCategoryName(){	
        return subrootCategoryName;
    }

    public void setSubrootCategoryName(String subrootCategoryName){
        this.subrootCategoryName = subrootCategoryName;
    }


}
