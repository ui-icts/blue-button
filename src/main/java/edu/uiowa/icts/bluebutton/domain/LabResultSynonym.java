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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.constraints.NotBlank;

/** 
 * Generated by Protogen - www.icts.uiowa.edu/protogen 
 * @since 04/14/2015 11:40:13 AM CDT
 */
@Entity( name = "edu_uiowa_icts_bluebutton_domain_labResultSynonym" )
@Table( name = "lab_result_synonym", schema = "bluebutton" )
public class LabResultSynonym { 

	private static final Log log = LogFactory.getLog(LabResultSynonym.class);

    private Integer labResultSynonymId;
    @NotBlank
    private String officialName;
    @NotBlank
    private String unofficialName;
    private String panel;

    public LabResultSynonym(){}
        
    public LabResultSynonym(Integer labResultSynonymId, String officalName,String unofficalName, String panel){
    	this.labResultSynonymId = labResultSynonymId;
        this.officialName = officalName;
        this.unofficialName = unofficalName;
        this.panel = panel;
     }

    /*****labResultSynonymId*****/
    @javax.persistence.SequenceGenerator(  name="gen",  sequenceName="bluebutton.seqnum",allocationSize=1)
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO,generator="gen")
    @Column(name = "lab_result_synonym_id", unique = true, nullable = false)
    public Integer getLabResultSynonymId(){
        return labResultSynonymId;
    }

    public void setLabResultSynonymId(Integer labResultSynonymId){
        this.labResultSynonymId = labResultSynonymId;
    }

    /*****officialName*****/
    @Column(name = "official_name")
    public String getOfficialName(){
        return officialName;
    }

    public void setOfficialName(String officialName){
        this.officialName = officialName;
    }

    /*****unofficialName*****/
    @Column(name = "unofficial_name")
    public String getUnofficialName(){
        return unofficialName;
    }

    public void setUnofficialName(String unofficialName){
        this.unofficialName = unofficialName;
    }
    
    /*****panel*****/
    @Column(name = "panel")
    public String getPanel(){
        return panel;
    }

    public void setPanel(String panel){
        this.panel = panel;
    }

}
