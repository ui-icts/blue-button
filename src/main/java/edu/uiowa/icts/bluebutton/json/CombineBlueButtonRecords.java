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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.uiowa.icts.bluebutton.dao.LabResultSynonymService;

public class CombineBlueButtonRecords {

	private List<BlueButtonRecord> records  = new ArrayList<BlueButtonRecord>();
	private Long startDate;
	private Long endDate;
	private LabResultSynonymService synonymSearchService;
	
	public CombineBlueButtonRecords(List<BlueButtonRecord> records, Long startDate, Long endDate, LabResultSynonymService synonymSearchService){
		this.records = records;
		this.startDate= startDate;
		this.endDate = endDate;
		this.synonymSearchService = synonymSearchService;
	}

	public BlueButtonRecord getMasterBlueButtonRecord() throws IOException{
		BlueButtonRecord masterBbr = new BlueButtonRecord();
		masterBbr.setStartDateInMillis(this.startDate);
		masterBbr.setEndDateInMillis(this.endDate);
		masterBbr.setDemographics(this.getDemographics());
		masterBbr.setSmoking_status(this.getSmokingStatus());
		masterBbr.setAllergies(this.getAllergyList());
		masterBbr.setEncounters(this.getEncountersList());
		masterBbr.setVitals(this.getVitalsList());
		masterBbr.setLabs(this.getLabList());
		masterBbr.setProcedures(this.getProceduresList());
		masterBbr.setMedications(this.getMedicationsList());
		masterBbr.setImmunizations(this.getImmunizationList());
		masterBbr.setProblems(this.getProblemsList());
		return masterBbr;
	}
	
	
	
	private SmokingStatus getSmokingStatus() {
		List<SmokingStatus> list = new ArrayList<SmokingStatus>();
		for(BlueButtonRecord bbr : this.records){
			if(bbr.getSmoking_status() != null){
				list.add(bbr.getSmoking_status());
			}
		}
		return new CombinedSmokingStatus(list).getSmokingStatus();
	}

	private List<Allergy> getAllergyList(){
		List<Allergy> aList = new ArrayList<Allergy>();
		for(BlueButtonRecord bbr : this.records){
			for(Allergy a : bbr.getAllergies()){
				a.setSource(bbr.getSource());
				aList.add(a);
			}
		}
		return aList;
	}
	private List<Encounter> getEncountersList(){
		List<Encounter> eList = new ArrayList<Encounter>();
		for(BlueButtonRecord bbr : this.records){
			for(Encounter e : bbr.getEncounters()){
				e.setSource(bbr.getSource());
				eList.add(e);
			}
		}
		return eList;
	}
	private List<Vitals> getVitalsList(){
		List<Vitals> vList = new ArrayList<Vitals>();
		for(BlueButtonRecord bbr : this.records){
			for(Vitals v : bbr.getVitals()){
				for(VitalsResult vr : v.getResults()){
					vr.setSource(bbr.getSource());
				}
				
				vList.add(v);
			}
		}
		return vList;
	}
	private List<Lab> getLabList() throws IOException{
		List<Lab> lList = new ArrayList<Lab>();
		for(BlueButtonRecord bbr : this.records){
			for(Lab l : bbr.getLabs()){
				for(LabResult lr : l.getResults()){
					lr.setSource(bbr.getSource());
				}
				lList.add(l);
			}
		}
		return new SynonymCombine(lList, synonymSearchService).getCobinedSynonym();
	}
	private List<Procedure> getProceduresList(){
		List<Procedure> pList = new ArrayList<Procedure>();
		for(BlueButtonRecord bbr : this.records){
			for(Procedure p : bbr.getProcedures()){
				p.setSource(bbr.getSource());
				pList.add(p);
			}
		}
		return pList;
	}
	private List<Medication> getMedicationsList(){
		List<Medication> mList = new ArrayList<Medication>();
		for(BlueButtonRecord bbr : this.records){
			for(Medication m : bbr.getMedications()){
				m.setSource(bbr.getSource());
				mList.add(m);
			}
		}
		return mList;
	}
	
	private List<Immunization> getImmunizationList(){
		List<Immunization> iList = new ArrayList<Immunization>();
		for(BlueButtonRecord bbr : this.records){
			for(Immunization i : bbr.getImmunizations()){
				i.setSource(bbr.getSource());
				iList.add(i);
			}
		}
		return iList;
	}
	
	private List<Problem> getProblemsList(){
		List<Problem> pList = new ArrayList<Problem>();
		for(BlueButtonRecord bbr : this.records){
			for(Problem p : bbr.getProblems()){
				p.setSource(bbr.getSource());
				pList.add(p);
			}
		}
		return pList;
	}
	
	private Demographics getDemographics(){
		List<Demographics> list = new ArrayList<Demographics>();
		for(BlueButtonRecord bbr : this.records){
			list.add(bbr.getDemographics());
		}
		return new CombinedDemographics(list).getDempgraphics();
		
	}
	
}
