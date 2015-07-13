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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import edu.uiowa.icts.bluebutton.json.view.BlueButtonLocation;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class BlueButtonRecord {
	
	private Demographics demographics = new Demographics();
	private List<Vitals> vitals  = new ArrayList<Vitals>();
	private List<Immunization> immunizations = new ArrayList<Immunization>();
	private List<Lab> labs  = new ArrayList<Lab>();
	private List<Problem> problems = new ArrayList<Problem>();
	private List<Medication> medications = new ArrayList<Medication>();
	private List<Encounter> encounters = new ArrayList<Encounter>();
	private List<Procedure> procedures = new ArrayList<Procedure>();
	private List<Allergy> allergies = new ArrayList<Allergy>();
	private Long endDateInMillis;
	private Long startDateInMillis;
	private SmokingStatus smoking_status = new SmokingStatus();
	private List<BlueButtonLocation> locationList = new ArrayList<BlueButtonLocation>();	
	private String source;

	public Demographics getDemographics() {
		return demographics;
	}
	
	public void setDemographics(Demographics demographics) {
		this.demographics = demographics;
	}
	public List<Lab> getLabs() {
		return labs;
	}
	public void setLabs(List<Lab> labs) {
		this.labs = labs;
	}
	public List<Immunization> getImmunizations() {
		return immunizations;
	}
	public void setImmunizations(List<Immunization> immunizations) {
		this.immunizations = immunizations;
	}
	public List<Vitals> getVitals() {
		return vitals;
	}
	public void setVitals(List<Vitals> vitals) {
		this.vitals = vitals;
	}
	public List<Problem> getProblems() {
		return problems;
	}
	public void setProblems(List<Problem> problems) {
		this.problems = problems;
	}
	public List<Medication> getMedications() {
		return medications;
	}
	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}
	public List<Encounter> getEncounters() {
		return encounters;
	}
	
	public Long getEndDateInMillis() {
		return this.endDateInMillis;
	}
	
	public Long getStartDateInMillis() {
		return this.startDateInMillis;
	}
	
	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}

	public void setEndDateInMillis(Long endDateInMillis) {
		this.endDateInMillis = endDateInMillis;
	}
	public List<Procedure> getProcedures() {
		return procedures;
	}
	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}

	public void setStartDateInMillis(Long startDateInMillis) {
		this.startDateInMillis = startDateInMillis;
	}
	public List<Allergy> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<Allergy> allergies) {
		this.allergies = allergies;
	}

	public SmokingStatus getSmoking_status() {
		return smoking_status;
	}

	public void setSmoking_status(SmokingStatus smoking_status) {
		this.smoking_status = smoking_status;
	}

	public void setBlueButtonLocations(List<BlueButtonLocation> locations) {
		this.locationList = locations;
	}
	
	public List<BlueButtonLocation> getBlueButtonLocations(){
		return this.locationList;
	}

	public void setSource(String source) {
		if(this.demographics.getProvider() != null){
			this.demographics.getProvider().setOrganization(source);
		}
		else{
			Provider p = new Provider();
			p.setOrganization(source);
			this.demographics.setProvider(p);
		}
		this.source = source;
	}
	
	public String getSource() {
		if(this.source != null){return this.source;}
		if (this.getDemographics().getProvider() != null){
			return this.getDemographics().getProvider().getOrganization();
		} else {
			return null;
		}
	}
}
