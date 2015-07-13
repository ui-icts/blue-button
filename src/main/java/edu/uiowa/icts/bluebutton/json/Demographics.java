package edu.uiowa.icts.bluebutton.json;

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

import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class Demographics {
	
	private String gender; 
	private Name name;
	private String dob;
	private String marital_status;
	private Provider provider;
	private String CombinedSourceList;
	private Location address;
		
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}	
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public String getDataSource(){
		if(this.provider != null){
			if(this.provider.getOrganization() != null){
				return this.provider.getOrganization();
			}
		}
		return "unknown";
	}
		
	public String getHeSheGenderedPronoun(){
		if(this.gender != null){
			switch(this.gender.toLowerCase()){
				case "male" : return "He";
				case "female" : return "She";
				}
		}
		return null;	
	}
	
	public String getHisHerGenderedPronoun(){
		if(this.gender != null){
			switch(this.gender.toLowerCase()){
				case "male" : return "His";
				case "female" : return "Her";
				}
		}
		return null;	
	}
	
	@JsonIgnore
	public Double getAge() {
		if(this.dob != null ){
			return new Double(Years.yearsBetween(DateRange.PARSER_FORMAT.parseLocalDate(this.dob), new LocalDate()).getYears());
		}
		else{return new Double(-1);}
	}
	
	public String getCombinedSourceList() {
		return CombinedSourceList;
	}
	public void setCombinedSourceList(String combinedSourceList) {
		CombinedSourceList = combinedSourceList;
	}
	public Location getAddress() {
		return address;
	}
	public void setAddress(Location address) {
		this.address = address;
	}
	
		
}
