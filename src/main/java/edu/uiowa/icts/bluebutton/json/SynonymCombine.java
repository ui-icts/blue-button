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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.uiowa.icts.bluebutton.dao.LabResultSynonymService;
import edu.uiowa.icts.bluebutton.domain.LabResultSynonym;

public class SynonymCombine {

	private List<Lab> list;
	private LabResultSynonymService synonymSearchService;
	
	public SynonymCombine(List<Lab> list, LabResultSynonymService synonymSearchService){
		this.list = list;
		this.synonymSearchService = synonymSearchService;
	}

	public List<Lab> getCobinedSynonym(){
		List<Lab> labList = this.list;
		HashMap<String, LabResultSynonym> hash = this.getHash();
		for(Lab lab : labList){
			for(LabResult lr : lab.getResults()){
				if(lr.getName() != null && hash.containsKey(lr.getName())){
					lr.setSynonym(hash.get(lr.getName()));
				}
			}
		}
		return labList;
	}
	
	private HashMap<String,LabResultSynonym> getHash(){
		HashMap<String,LabResultSynonym> hash = new HashMap<String,LabResultSynonym>();
		Set<String> set = new HashSet<String>();
		for(Lab lab : this.list){
			for(LabResult lr : lab.getResults()){
				if(lr.getName() != null){
					set.add(lr.getName());
				}
			}
		}
		List<LabResultSynonym> synonymList = this.synonymSearchService.getSynonym(set);
		for(String labName : set){
			for(LabResultSynonym lrs : synonymList){
				if(labName.trim().equalsIgnoreCase(lrs.getUnofficialName())){hash.put(labName, lrs);}
			}
		}
		return hash;
	}
}
