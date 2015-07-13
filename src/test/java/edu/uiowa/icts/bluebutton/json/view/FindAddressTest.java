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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.uiowa.icts.bluebutton.json.BlueButtonRecord;
import edu.uiowa.icts.bluebutton.json.Demographics;
import edu.uiowa.icts.bluebutton.json.Encounter;
import edu.uiowa.icts.bluebutton.json.Entry;
import edu.uiowa.icts.bluebutton.json.Location;
import edu.uiowa.icts.bluebutton.json.Name;
import edu.uiowa.icts.bluebutton.json.Procedure;
import edu.uiowa.icts.bluebutton.json.Provider;

public class FindAddressTest {

	@Test
	public void findAddressShouldReturnListOfAddressObjectsGivenListOfBlueButtonRecordsProviderDemographics(){
		Location l = new Location();
		l.setCity("Iowa City");
		l.setCountry("US");
		l.setState("Iowa");
		l.setStreet(new ArrayList<String>(Arrays.asList("123 Fake St.")));
		l.setZip("52240");
		
		Provider p = new Provider();
		p.setAddress(l);
		p.setOrganization("Super Clinic");
		
		Demographics d = new Demographics();
		d.setProvider(p);
		
		BlueButtonRecord bbr = new BlueButtonRecord();
		bbr.setDemographics(d);
				
		List<BlueButtonLocation> locations = new FindAddress(bbr).getLocations();
		assertNotNull(locations);
		assertEquals(1,locations.size());
		
		BlueButtonLocation bbl = locations.get(0);
		assertEquals("123 Fake St., Iowa City, Iowa, 52240",bbl.getAddress());
		assertEquals("Super Clinic", bbl.getName());
		assertEquals("Provider", bbl.getType());
	}
	
	@Test
	public void findAddressShouldReturnEmptyListOfAddressObjectsGivenOnlyNullDemographics(){	
		Demographics d = null;
		
		BlueButtonRecord bbr = new BlueButtonRecord();
		bbr.setDemographics(d);
				
		List<BlueButtonLocation> locations = new FindAddress(bbr).getLocations();
		assertNotNull(locations);
		assertEquals(0,locations.size());	
	}
	
	@Test
	public void findAddressShouldReturnEmptyListOfAddressObjectsGivenOnlyNullEncounters(){	
		Encounter e = new Encounter();
		
		BlueButtonRecord bbr = new BlueButtonRecord();
		List<Encounter> eList = new ArrayList<Encounter>();
		eList.add(e);
		bbr.setEncounters(eList);
		
		List<BlueButtonLocation> locations = new FindAddress(bbr).getLocations();
		assertNotNull(locations);
		assertEquals(0,locations.size());	
	}
	
	@Test
	public void findAddressShouldReturnEmptyListOfAddressObjectsGivenOnlyNullProcedures(){	
		Procedure p = new Procedure();
		
		BlueButtonRecord bbr = new BlueButtonRecord();
		List<Procedure> pList = new ArrayList<Procedure>();
		pList.add(p);
		bbr.setProcedures(pList);		
		
		List<BlueButtonLocation> locations = new FindAddress(bbr).getLocations();
		assertNotNull(locations);
		assertEquals(0,locations.size());	
	}
	
	@Test
	public void findRecordShouldFindLocationsFromEncounterData(){
		Location l = new Location();
		l.setCity("Iowa City");
		l.setCountry("US");
		l.setState("Iowa");
		l.setStreet(new ArrayList<String>(Arrays.asList("123 Fake St.")));
		l.setZip("52240");
		
		Encounter e = new Encounter();
		e.setLocation(l);
		Entry performer = new Entry();
		performer.setName("Dr.JUnitTest");
		e.setPerformer(performer);
		
		List<Encounter> eList = new ArrayList<Encounter>();
		eList.add(e);
		
		BlueButtonRecord bbr = new BlueButtonRecord();
		bbr.setEncounters(eList);
				
		List<BlueButtonLocation> locations = new FindAddress(bbr).getLocations();
		assertNotNull(locations);
		assertEquals(1,locations.size());
		
		BlueButtonLocation bbl = locations.get(0);
		assertEquals("123 Fake St., Iowa City, Iowa, 52240",bbl.getAddress());
		assertEquals("Dr.JUnitTest", bbl.getName());
		assertEquals("Encounter", bbl.getType());
	}
	
	@Test
	public void findRecordShouldFindLocationsFromProcedureData(){
		Location l = new Location();
		l.setCity("Iowa City");
		l.setCountry("US");
		l.setState("Iowa");
		l.setOrganization("TestOrg");
		l.setStreet(new ArrayList<String>(Arrays.asList("123 Fake St.")));
		l.setZip("52240");
		
		Procedure p = new Procedure();
		p.setPerformer(l);
		
		List<Procedure> pList = new ArrayList<Procedure>();
		pList.add(p);
		
		BlueButtonRecord bbr = new BlueButtonRecord();
		bbr.setProcedures(pList);
		
		
		List<BlueButtonLocation> locations = new FindAddress(bbr).getLocations();
		assertNotNull(locations);
		assertEquals(1,locations.size());
		
		BlueButtonLocation bbl = locations.get(0);
		assertEquals("123 Fake St., Iowa City, Iowa, 52240",bbl.getAddress());
		assertEquals("TestOrg", bbl.getName());
		assertEquals("Procedure", bbl.getType());
	}
	
	@Test
	public void findRecordShouldFindHomeInformation(){
		Location l = new Location();
		l.setCity("Iowa City");
		l.setCountry("US");
		l.setState("Iowa");
		l.setStreet(new ArrayList<String>(Arrays.asList("123 Fake St.", "apt #101")));
		l.setZip("52240");
		
		Demographics d = new Demographics();
		Name name = new Name();
		name.setGiven(new ArrayList<String>(Arrays.asList("Cassio")));
		name.setFamily("Is Great");
		d.setName(name);
		d.setAddress(l);
		
		BlueButtonRecord bbr = new BlueButtonRecord();
		bbr.setDemographics(d);
		
		List<BlueButtonLocation> locations = new FindAddress(bbr).getLocations();
		assertNotNull(locations);
		assertEquals(1,locations.size());
		
		BlueButtonLocation bbl = locations.get(0);
		assertEquals("123 Fake St., Iowa City, Iowa, 52240",bbl.getAddress());
		assertEquals("Home", bbl.getType());
		assertEquals("Cassio Is Great", bbl.getName());
		
	}
	
	@Test
	public void findAddressShouldReturnEmptyListWhenNoDataGiven(){
		List<BlueButtonLocation> bbl = new FindAddress(null).getLocations();
		assertNotNull(bbl);
		assertEquals(0, bbl.size());
		
		BlueButtonRecord bbr = new BlueButtonRecord();
		bbl = new FindAddress(bbr).getLocations();
		
		assertNotNull(bbl);
		assertEquals(0, bbl.size());
		
		bbr = new BlueButtonRecord();
		Demographics d = new Demographics();
		bbr.setDemographics(d);
		bbl = new FindAddress(bbr).getLocations();
		
		assertNotNull(bbl);
		assertEquals(0, bbl.size());
		
		bbr = new BlueButtonRecord();
		d = new Demographics();
		Provider p = new Provider();
		d.setProvider(p);
		bbr.setDemographics(d);
		bbl = new FindAddress(bbr).getLocations();
		
		assertNotNull(bbl);
		assertEquals(0, bbl.size());
		
		bbr = new BlueButtonRecord();
		d = new Demographics();
		p = new Provider();
		p.setAddress(new Location());
		d.setProvider(p);
		bbr.setDemographics(d);
		bbl = new FindAddress(bbr).getLocations();
		
		assertNotNull(bbl);
		assertEquals(0, bbl.size());
		
	}
	
	
}
