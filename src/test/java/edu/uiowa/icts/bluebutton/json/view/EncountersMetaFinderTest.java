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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import edu.uiowa.icts.bluebutton.json.Encounter;
import edu.uiowa.icts.bluebutton.json.Location;

public class EncountersMetaFinderTest {

	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	@Before
    public void setup() throws FileNotFoundException, IOException {
		// freeze time
        DateTime dt= format.parseDateTime("2015-02-04");
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
    }
    
    @After
    public void tearDown() throws Exception {
    	// unfreeze time
    	DateTimeUtils.setCurrentMillisSystem();
    }
	
	@Test
	public void encosMetaFinderShouldReturnIfEncosAreRecent() {
		List<Encounter> list = new ArrayList<Encounter>();
		Encounter e = new Encounter();
		e.setDate("2015-01-31T05:00:00.000Z");
		list.add(e);
		EncountersMetaFinder emf = new EncountersMetaFinder(list);
		assertEquals("had medical encounters",emf.getRecentEncounters());
		
	}
	
	@Test
	public void encMetaFinderShouldReturnIfEncosAreNotRecent() {
		List<Encounter> list = new ArrayList<Encounter>();
		Encounter e = new Encounter();
		e.setDate("2013-01-31T05:00:00.000Z");
		list.add(e);
		EncountersMetaFinder mmf = new EncountersMetaFinder(list);
		assertEquals("did not have any medical encounters",mmf.getRecentEncounters());
		
	}
	
	@Test
	public void encMetaFinderShouldReturnIfNoEncos() {
		List<Encounter> list = new ArrayList<Encounter>();
		EncountersMetaFinder mmf = new EncountersMetaFinder(list);
		assertEquals("did not have any medical encounters",mmf.getRecentEncounters());
	}
	
	@Test
	public void encMetaFinderShouldGiveUnknowWhenNoLocationOrgsFound(){
		List<Encounter> list = new ArrayList<Encounter>();
		Encounter e = new Encounter();
		list.add(e);
		EncountersMetaFinder emf = new EncountersMetaFinder(list);
		assertNull(emf.getOrganizations());
	}
	
	@Test
	public void encMetaFinderShouldGiveLocationOrgName(){
		List<Encounter> list = new ArrayList<Encounter>();
		Encounter e = new Encounter();
		Location l = new Location();
		l.setOrganization("Provider's Office");
		e.setLocation(l);
		list.add(e);
		EncountersMetaFinder emf = new EncountersMetaFinder(list);
		assertEquals("Provider's Office", emf.getOrganizations());
	}
	
	@Test
	public void encMetaFinderShouldGiveLocationOrgNameShouldJoinMultipleOrgs(){
		List<Encounter> list = new ArrayList<Encounter>();
		Encounter e = new Encounter();
		Location l = new Location();
		l.setOrganization("Provider's Office");
		e.setLocation(l);
		list.add(e);
		Encounter e2 = new Encounter();
		Location l2 = new Location();
		l2.setOrganization("Provider Office");
		e2.setLocation(l2);
		list.add(e2);
		EncountersMetaFinder emf = new EncountersMetaFinder(list);
		assertEquals("Provider Office, Provider's Office", emf.getOrganizations());
	}
	
	@Test
	public void encMetaFinderShouldGiveLocationOrgNameShouldJoinMultipleOrgsWithoutDuplcation(){
		List<Encounter> list = new ArrayList<Encounter>();
		Encounter e = new Encounter();
		Location l = new Location();
		l.setOrganization("Provider's Office");
		e.setLocation(l);
		list.add(e);
		Encounter e2 = new Encounter();
		Location l2 = new Location();
		l2.setOrganization("Provider's Office");
		e2.setLocation(l2);
		list.add(e2);
		EncountersMetaFinder emf = new EncountersMetaFinder(list);
		assertEquals("Provider's Office", emf.getOrganizations());
	}
	
	@Test
	public void encMetaFinderShouldGiveLocationOrgNameAndEncounterInformation(){
		List<Encounter> list = new ArrayList<Encounter>();
		Encounter e = new Encounter();
		Location l = new Location();
		l.setOrganization("Provider's Office");
		e.setDate("2015-01-31T05:00:00.000Z");
		e.setLocation(l);
		list.add(e);
		EncountersMetaFinder emf = new EncountersMetaFinder(list);
		assertEquals("had medical encounters, from: Provider's Office", emf.getRecentEncounters());
	}
	
	
}