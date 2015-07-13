package edu.uiowa.icts.bluebutton.json.view;

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
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import edu.uiowa.icts.bluebutton.json.Allergy;
import edu.uiowa.icts.bluebutton.json.AllergyList;
import edu.uiowa.icts.bluebutton.json.BlueButtonRecord;
import edu.uiowa.icts.bluebutton.json.DateMinMaxCalculator;
import edu.uiowa.icts.bluebutton.json.Demographics;
import edu.uiowa.icts.bluebutton.json.Encounter;
import edu.uiowa.icts.bluebutton.json.EncounterList;
import edu.uiowa.icts.bluebutton.json.IDateInMillis;
import edu.uiowa.icts.bluebutton.json.Immunization;
import edu.uiowa.icts.bluebutton.json.ImmunizationList;
import edu.uiowa.icts.bluebutton.json.Lab;
import edu.uiowa.icts.bluebutton.json.LabList;
import edu.uiowa.icts.bluebutton.json.LabResult;
import edu.uiowa.icts.bluebutton.json.LoincCode;
import edu.uiowa.icts.bluebutton.json.Medication;
import edu.uiowa.icts.bluebutton.json.MedicationList;
import edu.uiowa.icts.bluebutton.json.Problem;
import edu.uiowa.icts.bluebutton.json.ProblemList;
import edu.uiowa.icts.bluebutton.json.Procedure;
import edu.uiowa.icts.bluebutton.json.ProcedureList;
import edu.uiowa.icts.bluebutton.json.SmokingStatus;
import edu.uiowa.icts.bluebutton.json.Vitals;
import edu.uiowa.icts.bluebutton.json.VitalsList;
import edu.uiowa.icts.bluebutton.json.VitalsResult;

public class BlueButtonRecordView {
	private BlueButtonRecord bbr;
	private Long startDateInMillis;
	private Long endDateInMillis;
	private HashMap<String, LoincCode> loincCodeHashMap;
	
	public BlueButtonRecordView(BlueButtonRecord blueButtonRecord, HashMap<String, LoincCode> loincCodeHashMap) {
		this.bbr = blueButtonRecord;
		this.loincCodeHashMap = loincCodeHashMap;
		if(bbr.getStartDateInMillis() == null && bbr.getEndDateInMillis() == null){
			this.startDateInMillis = new DateTime(this.getTimeRanges().getMaxTime()).minusYears(1).getMillis();
			this.endDateInMillis = this.getTimeRanges().getMaxTime();
		}
		else{
			this.startDateInMillis = bbr.getStartDateInMillis();
			this.endDateInMillis = bbr.getEndDateInMillis();
		}
	}

	public MetaData getMetaData() {
		return new MetaData(this.bbr);
	}
	
	public List<VitalsResult> getVitalsGrid(){
		return new VitalsList(this.bbr.getVitals(), startDateInMillis, endDateInMillis, loincCodeHashMap).getVitals();
	}
	
	public List<LabResult> getLabResultsGrid(){
		return new LabList(this.bbr.getLabs(), startDateInMillis, endDateInMillis, loincCodeHashMap).getLabs();
	}
	
	public SmokingStatus getSmokingStatus(){
		return this.bbr.getSmoking_status();
	}

	public Demographics getDemographics(){
		return this.bbr.getDemographics(); 
	}
	
	public List<BlueButtonLocation> getLocations(){
		return new FindAddress(this.bbr).getLocations();
	}
	
	public String getAge(){
		return new Integer(this.bbr.getDemographics().getAge().intValue()).toString();
	}
	
	public String getGender(){
		return this.bbr.getDemographics().getGender();
	}
	
	public String getFullSource(){
		return this.bbr.getDemographics().getCombinedSourceList();
	}
	
	public List<Procedure> getProcedures(){
		return new ProcedureList(this.bbr.getProcedures(), startDateInMillis, endDateInMillis).getProcedures();
	}
	
	public List<Encounter> getEncounters(){
		return new EncounterList(this.bbr.getEncounters(), startDateInMillis, endDateInMillis).getEncounters();
	}
	
	public List<Problem> getProblems(){
		return new ProblemList(this.bbr.getProblems()).getProblems();
	}
	
	public List<Medication> getMedications(){
		return new MedicationList(this.bbr.getMedications(), startDateInMillis, endDateInMillis).getMedications();
	}
	
	public List<Immunization> getImmunizations(){
		return new ImmunizationList(this.bbr.getImmunizations()).getImmunizations();
	}
	
	public List<Allergy> getAllergies(){
		return new AllergyList(this.bbr.getAllergies()).getAllergies();
	}
	
	public Long getEndDateInMillis(){
		return this.endDateInMillis;
	}
	
	public Long getStartDateInMillis(){
		return this.startDateInMillis;
	}
	
	public DateMinMaxCalculator getTimeRanges(){
		List<IDateInMillis> datesFromAllSectionsList = new ArrayList<IDateInMillis>(); 

		for (Medication m : this.bbr.getMedications()) {
			datesFromAllSectionsList.add(m);
		}
		for(Vitals v : this.bbr.getVitals()){
			for(VitalsResult vr : v.getResults()){
				vr.setDate(v.getDate());
				datesFromAllSectionsList.add(vr);
			}
		}
		for(Lab l : this.bbr.getLabs()){
			for(LabResult lr : l.getResults()){
				datesFromAllSectionsList.add(lr);
			}
		}
		for (Immunization i : this.bbr.getImmunizations()) {
			datesFromAllSectionsList.add(i);
		}
		for (Problem p : this.bbr.getProblems()) {
			datesFromAllSectionsList.add(p);
		}
		for (Encounter e: this.bbr.getEncounters()){
			datesFromAllSectionsList.add(e);
		}
		for (Procedure p : this.bbr.getProcedures()){
			datesFromAllSectionsList.add(p);
		}
		for (Allergy a : this.bbr.getAllergies()){
			datesFromAllSectionsList.add(a);
		}
		DateMinMaxCalculator minMaxDateCalculator =   new DateMinMaxCalculator(datesFromAllSectionsList);
		return minMaxDateCalculator;
	}
}
