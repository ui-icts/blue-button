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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.uiowa.icts.bluebutton.dao.LabResultSynonymService;
import edu.uiowa.icts.bluebutton.domain.LabResultSynonym;
import edu.uiowa.icts.spring.AbstractSpringTestCase;

@WebAppConfiguration
public class SynonymCombineTest extends AbstractSpringTestCase{

	@Autowired
	private LabResultSynonymService synonymSearchService;

    @Before
    public void setUp() throws Exception {
    	super.setUp();
    	 //Save labsysnonym data
    	int id = 0;
        this.synonymSearchService.save(new LabResultSynonym(id++, "CO2", "Carbon Dioxide", "Electrolyte"));
    }

    @After
    public void tearDown() throws Exception {
    	super.tearDown();
    }
	
	@Test
	public void synonymCombineShouldCombineLabSynonyms(){
		List<LabResult> list = new ArrayList<LabResult>();
		LabResult lr = new LabResult();
		lr.setName(new String("Carbon Dioxide"));
		list.add(lr);
		List<Lab> labList = new ArrayList<Lab>();
		Lab lab = new Lab(); lab.setResults(list);
		labList.add(lab);
		SynonymCombine sc = new SynonymCombine(labList, synonymSearchService);
		List<Lab> labsList = sc.getCobinedSynonym();
		assertEquals(labsList.size(), 1);
		assertEquals(labsList.get(0).getResults().size(), 1);
		LabResult lr2 = labsList.get(0).getResults().get(0);
		assertNotNull(lr2.getSynonym());
		assertEquals("CO2", lr2.getName());
		assertEquals("Electrolyte", lr2.getLabName());
		}
	
	@Test
	public void synonymCombineShouldCombineLabSynonymsWithMixedCase(){
		List<LabResult> list = new ArrayList<LabResult>();
		LabResult lr = new LabResult();
		lr.setName(new String("CarBon dioxide"));
		list.add(lr);
		List<Lab> labList = new ArrayList<Lab>();
		Lab lab = new Lab(); lab.setResults(list);
		labList.add(lab);
		SynonymCombine sc = new SynonymCombine(labList, synonymSearchService);
		List<Lab> labsList = sc.getCobinedSynonym();
		assertEquals(labsList.size(), 1);
		assertEquals(labsList.get(0).getResults().size(), 1);
		LabResult lr2 = labsList.get(0).getResults().get(0);
		assertNotNull(lr2.getSynonym());
		assertEquals("CO2", lr2.getName());
		assertEquals("Electrolyte", lr2.getLabName());
		}
	
	@Test
	public void synonymCombineShouldCombineLabSynonymsWithOuterWhiteSpaces(){
		List<LabResult> list = new ArrayList<LabResult>();
		LabResult lr = new LabResult();
		lr.setName(new String("  Carbon Dioxide  "));
		list.add(lr);
		List<Lab> labList = new ArrayList<Lab>();
		Lab lab = new Lab(); lab.setResults(list);
		labList.add(lab);
		SynonymCombine sc = new SynonymCombine(labList, synonymSearchService);
		List<Lab> labsList = sc.getCobinedSynonym();
		assertEquals(labsList.size(), 1);
		assertEquals(labsList.get(0).getResults().size(), 1);
		LabResult lr2 = labsList.get(0).getResults().get(0);
		assertNotNull(lr2.getSynonym());
		assertEquals("CO2", lr2.getName());
		assertEquals("Electrolyte", lr2.getLabName());
	}
	
	@Test
	public void synonymCombineShouldReturnEmptyListWhenRequestIsEmpty(){
		List<Lab> labList = new ArrayList<Lab>();	
		List<Lab> sc = new SynonymCombine(labList, synonymSearchService).getCobinedSynonym();
		assertEquals(sc, new ArrayList<Lab>());
	}
	
}
