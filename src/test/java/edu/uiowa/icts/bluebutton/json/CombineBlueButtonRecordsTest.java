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

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.uiowa.icts.bluebutton.dao.LabResultSynonymService;
import edu.uiowa.icts.spring.AbstractSpringTestCase;

@WebAppConfiguration
public class CombineBlueButtonRecordsTest extends AbstractSpringTestCase{

	@Autowired
	private LabResultSynonymService synonymSearchService;
	
	private Demographics getDemographics(int x){
		Demographics d = new Demographics();
		Provider p = new Provider(); 
		p.setOrganization("Place ".concat(new Integer(x).toString()));
		d.setProvider(p);
		return d;
	}
	
	@Test
	public void combineBlueButoonRecordsShouldCombineAListOfBlueButtonRecordsForMedications() throws IOException{
		List<BlueButtonRecord> list = new ArrayList<BlueButtonRecord>();
		String name = "Pill ";
		for(int x=0; x<3; x++){
			BlueButtonRecord bbr = new BlueButtonRecord();
			bbr.setDemographics(this.getDemographics(x));
			List<Medication> mList = new ArrayList<Medication>();
			for(int y=0; y<3; y++){
				Product e = new Product();
				e.setName(name.concat(new Integer((3*x)+y).toString()));
				Medication m = new Medication();
				m.setProduct(e);
				mList.add(m);
			}
			bbr.setMedications(mList);
			list.add(bbr);
		}
		BlueButtonRecord masterBbr = new CombineBlueButtonRecords(list, null, null, synonymSearchService).getMasterBlueButtonRecord();
		assertEquals(9, masterBbr.getMedications().size());
		List<Medication> mList = masterBbr.getMedications();
		assertEquals("Pill 0", mList.get(0).getName());
		assertEquals("Pill 5", mList.get(5).getName());
		assertEquals("Pill 8", mList.get(8).getName());
		
		assertEquals("Place 0", mList.get(0).getSource());
		assertEquals("Place 1", mList.get(5).getSource());
		assertEquals("Place 2", mList.get(8).getSource());
		
		assertEquals("Place 0, Place 1, Place 2", masterBbr.getDemographics().getCombinedSourceList());


	}
	
	@Test
	public void combineBlueButoonRecordsShouldCombineAListOfBlueButtonRecordsForEncounters() throws IOException{
		List<BlueButtonRecord> list = new ArrayList<BlueButtonRecord>();
		String name = "Encounter";
		for(int x=0; x<3; x++){
			BlueButtonRecord bbr = new BlueButtonRecord();
			bbr.setDemographics(this.getDemographics(x));
			List<Encounter> eList = new ArrayList<Encounter>();
			for(int y=0; y<3; y++){
				Encounter e = new Encounter();
				e.setName(name.concat(new Integer((3*x)+y).toString()));
				eList.add(e);
			}
			bbr.setEncounters(eList);
			list.add(bbr);
		}
		BlueButtonRecord masterBbr = new CombineBlueButtonRecords(list, null, null, synonymSearchService).getMasterBlueButtonRecord();
		assertEquals(9, masterBbr.getEncounters().size());
		List<Encounter> eList = masterBbr.getEncounters();
		assertEquals(name.concat("0"), eList.get(0).getName());
		assertEquals(name.concat("5"), eList.get(5).getName());
		assertEquals(name.concat("8"), eList.get(8).getName());
		assertEquals("Place 0", eList.get(0).getSource());
		assertEquals("Place 1", eList.get(5).getSource());
		assertEquals("Place 2", eList.get(8).getSource());
		
		assertEquals("Place 0", eList.get(0).getSource());
		assertEquals("Place 1", eList.get(5).getSource());
		assertEquals("Place 2", eList.get(8).getSource());
		
		assertEquals("Place 0, Place 1, Place 2", masterBbr.getDemographics().getCombinedSourceList());

	}
	
	@Test
	public void combineBlueButoonRecordsShouldCombineAListOfBlueButtonRecordsForProcedures() throws IOException{
		List<BlueButtonRecord> list = new ArrayList<BlueButtonRecord>();
		String name = "Procedure";
		for(int x=0; x<3; x++){
			BlueButtonRecord bbr = new BlueButtonRecord();
			bbr.setDemographics(this.getDemographics(x));
			List<Procedure> testList = new ArrayList<Procedure>();
			for(int y=0; y<3; y++){
				Procedure t = new Procedure();
				t.setName(name.concat(new Integer((3*x)+y).toString()));
				testList.add(t);
			}
			bbr.setProcedures(testList);
			list.add(bbr);
		}
		BlueButtonRecord masterBbr = new CombineBlueButtonRecords(list, null, null, synonymSearchService).getMasterBlueButtonRecord();
		assertEquals(9, masterBbr.getProcedures().size());
		List<Procedure> testList = masterBbr.getProcedures();
		assertEquals(name.concat("0"), testList.get(0).getName());
		assertEquals(name.concat("5"), testList.get(5).getName());
		assertEquals(name.concat("8"), testList.get(8).getName());
		
		assertEquals("Place 0", testList.get(0).getSource());
		assertEquals("Place 1", testList.get(5).getSource());
		assertEquals("Place 2", testList.get(8).getSource());
		
		assertEquals("Place 0, Place 1, Place 2", masterBbr.getDemographics().getCombinedSourceList());
	}
	
	@Test
	public void combineBlueButoonRecordsShouldCombineAListOfBlueButtonRecordsForImmunizations() throws IOException{
		List<BlueButtonRecord> list = new ArrayList<BlueButtonRecord>();
		String name = "Immunization";
		for(int x=0; x<3; x++){
			BlueButtonRecord bbr = new BlueButtonRecord();
			bbr.setDemographics(this.getDemographics(x));
			List<Immunization> testList = new ArrayList<Immunization>();
			for(int y=0; y<3; y++){
				Product p = new Product();
				Immunization t = new Immunization();
				p.setName(name.concat(new Integer((3*x)+y).toString()));
				
				t.setProduct(p);;
				testList.add(t);
			}
			bbr.setImmunizations(testList);
			list.add(bbr);
		}
		BlueButtonRecord masterBbr = new CombineBlueButtonRecords(list, null, null, synonymSearchService).getMasterBlueButtonRecord();
		assertEquals(9, masterBbr.getImmunizations().size());
		List<Immunization> testList = masterBbr.getImmunizations();
		assertEquals(name.concat("0"), testList.get(0).getName());
		assertEquals(name.concat("5"), testList.get(5).getName());
		assertEquals(name.concat("8"), testList.get(8).getName());
		
		assertEquals("Place 0", testList.get(0).getSource());
		assertEquals("Place 1", testList.get(5).getSource());
		assertEquals("Place 2", testList.get(8).getSource());
		
		assertEquals("Place 0, Place 1, Place 2", masterBbr.getDemographics().getCombinedSourceList());
	}
	
	@Test
	public void combineBlueButoonRecordsShouldCombineAListOfBlueButtonRecordsForProblems() throws IOException{
		List<BlueButtonRecord> list = new ArrayList<BlueButtonRecord>();
		String name = "Problem";
		for(int x=0; x<3; x++){
			BlueButtonRecord bbr = new BlueButtonRecord();
			bbr.setDemographics(this.getDemographics(x));
			List<Problem> testList = new ArrayList<Problem>();
			for(int y=0; y<3; y++){
				Problem t = new Problem();
				t.setName(name.concat(new Integer((3*x)+y).toString()));
				testList.add(t);
			}
			bbr.setProblems(testList);
			list.add(bbr);
		}
		BlueButtonRecord masterBbr = new CombineBlueButtonRecords(list, null, null, synonymSearchService).getMasterBlueButtonRecord();
		assertEquals(9, masterBbr.getProblems().size());
		List<Problem> testList = masterBbr.getProblems();
		assertEquals(name.concat("0"), testList.get(0).getName());
		assertEquals(name.concat("5"), testList.get(5).getName());
		assertEquals(name.concat("8"), testList.get(8).getName());
		
		assertEquals("Place 0", testList.get(0).getSource());
		assertEquals("Place 1", testList.get(5).getSource());
		assertEquals("Place 2", testList.get(8).getSource());
		
		assertEquals("Place 0, Place 1, Place 2", masterBbr.getDemographics().getCombinedSourceList());
	}
	
	@Test
	public void combineBlueButoonRecordsShouldCombineAListOfBlueButtonRecordsForLabs() throws IOException{
		List<BlueButtonRecord> list = new ArrayList<BlueButtonRecord>();
		String name = "Lab";
		for(int x=0; x<3; x++){
			BlueButtonRecord bbr = new BlueButtonRecord();
			bbr.setDemographics(this.getDemographics(x));
			List<LabResult> testList = new ArrayList<LabResult>();
			for(int y=0; y<3; y++){
				LabResult t = new LabResult();
				t.setName(name.concat(new Integer((3*x)+y).toString()));
				testList.add(t);
			}
			Lab lab = new Lab(); lab.setResults(testList); List<Lab> labList = new ArrayList<Lab>(); labList.add(lab);
			bbr.setLabs(labList);
			list.add(bbr);
		}
		BlueButtonRecord masterBbr = new CombineBlueButtonRecords(list, null, null, synonymSearchService).getMasterBlueButtonRecord();
		assertEquals(3, masterBbr.getLabs().size());
		List<Lab> labList = masterBbr.getLabs();
		
		List<LabResult> testList = labList.get(0).getResults();
		assertEquals(3, testList.size());
		assertEquals(name.concat("0"), testList.get(0).getName());
		assertEquals("Place 0", testList.get(0).getSource());
		
		testList = labList.get(1).getResults();
		assertEquals(3, testList.size());
		assertEquals(name.concat("5"), testList.get(2).getName());
		assertEquals("Place 1", testList.get(2).getSource());
		
		testList = labList.get(2).getResults();
		assertEquals(3, testList.size());
		assertEquals(name.concat("8"), testList.get(2).getName());
		assertEquals("Place 2", testList.get(2).getSource());
		
		assertEquals("Place 0, Place 1, Place 2", masterBbr.getDemographics().getCombinedSourceList());

	}
	
	@Test
	public void combineBlueButoonRecordsShouldCombineAListOfBlueButtonRecordsForVitals() throws IOException{
		List<BlueButtonRecord> list = new ArrayList<BlueButtonRecord>();
		String name = "Vital";
		for(int x=0; x<3; x++){
			BlueButtonRecord bbr = new BlueButtonRecord();
			bbr.setDemographics(this.getDemographics(x));
			List<VitalsResult> testList = new ArrayList<VitalsResult>();
			for(int y=0; y<3; y++){
				VitalsResult t = new VitalsResult();
				t.setName(name.concat(new Integer((3*x)+y).toString()));
				testList.add(t);
			}
			Vitals vital = new Vitals(); vital.setResults(testList); List<Vitals> vitalsList = new ArrayList<Vitals>(); vitalsList.add(vital);
			bbr.setVitals(vitalsList);
			list.add(bbr);
		}
		BlueButtonRecord masterBbr = new CombineBlueButtonRecords(list, null, null, synonymSearchService).getMasterBlueButtonRecord();
		assertEquals(3, masterBbr.getVitals().size());
		List<Vitals> vitalsResultList = masterBbr.getVitals();
		
		List<VitalsResult> testList = vitalsResultList.get(0).getResults();
		assertEquals(3, testList.size());
		assertEquals(name.concat("0"), testList.get(0).getName());
		assertEquals("Place 0", testList.get(0).getSource());
		
		testList = vitalsResultList.get(1).getResults();
		assertEquals(3, testList.size());
		assertEquals(name.concat("5"), testList.get(2).getName());
		assertEquals("Place 1", testList.get(2).getSource());
		
		testList = vitalsResultList.get(2).getResults();
		assertEquals(3, testList.size());
		assertEquals(name.concat("8"), testList.get(2).getName());
		assertEquals("Place 2", testList.get(2).getSource());
		
		assertEquals("Place 0, Place 1, Place 2", masterBbr.getDemographics().getCombinedSourceList());
		
	}
	
}
