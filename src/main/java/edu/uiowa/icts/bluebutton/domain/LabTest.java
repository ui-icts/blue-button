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
 * @since 10/29/2014 10:57:15 AM CDT
 */
@Entity( name = "edu_uiowa_icts_bluebutton_domain_labTest" )
@Table( name = "lab_test", schema = "bluebutton" )
public class LabTest { 

	private static final Log log = LogFactory.getLog(LabTest.class);

        private Integer labTestId;
        private String name;
        private String description;
        private String units;
        private String loincCode;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreated;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateUpdated;
        private Set<LabTestRange> labTestRanges = new HashSet<LabTestRange>(0);

    public LabTest() {
        this.labTestId = null;
        this.name = "";
        this.description = "";
        this.units = "";
        this.loincCode = "";
        this.dateCreated = null;
        this.dateUpdated = null;
        this.labTestRanges = new HashSet<LabTestRange>(0);
    }

    /*****labTestId*****/
//    @javax.persistence.SequenceGenerator(  name="gen",  sequenceName="bluebutton.seqnum",allocationSize=1)
    @Id
//    @GeneratedValue( strategy=GenerationType.AUTO,generator="gen")
    @Column(name = "lab_test_id", unique = true, nullable = false)
    public Integer getLabTestId(){
        return labTestId;
    }

    public void setLabTestId(Integer labTestId){
        this.labTestId = labTestId;
    }

    /*****name*****/
    @Column(name = "name")
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    /*****description*****/
    @Column(name = "description")
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    /*****units*****/
    @Column(name = "units")
    public String getUnits(){
        return units;
    }

    public void setUnits(String units){
        this.units = units;
    }

    /*****loincCode*****/
    @Column(name = "loinc_code")
    public String getLoincCode(){
        return loincCode;
    }

    public void setLoincCode(String loincCode){
        this.loincCode = loincCode;
    }

    /*****dateCreated*****/
    @Column(name = "date_created")
    public Date getDateCreated(){
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated){
        this.dateCreated = dateCreated;
    }

    public void setDateCreated(String dateCreated){
        try{
            DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
            formatter.setLenient(true);
            this.dateCreated = formatter.parse(dateCreated);
        } catch (ParseException e) { 
            log.error(" ParseException setting date for DateCreated", e);
        }
    }

    /*****dateUpdated*****/
    @Column(name = "date_updated")
    public Date getDateUpdated(){
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated){
        this.dateUpdated = dateUpdated;
    }

    public void setDateUpdated(String dateUpdated){
        try{
            DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
            formatter.setLenient(true);
            this.dateUpdated = formatter.parse(dateUpdated);
        } catch (ParseException e) { 
            log.error(" ParseException setting date for DateUpdated", e);
        }
    }

    /*****labTestRanges*****/
    @OneToMany(fetch = FetchType.LAZY,   mappedBy = "labTest", targetEntity=LabTestRange.class, cascade=CascadeType.REMOVE)
    public Set<LabTestRange> getLabTestRanges(){
        return labTestRanges;
    }

    public void setLabTestRanges(Set<LabTestRange> labTestRanges){
        this.labTestRanges = labTestRanges;
    }


}
