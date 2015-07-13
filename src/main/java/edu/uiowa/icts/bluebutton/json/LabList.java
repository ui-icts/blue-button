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
import java.util.List;

import edu.uiowa.icts.bluebutton.json.view.CombineStats;
import edu.uiowa.icts.bluebutton.json.view.GetStatsByLoincCodeAndName;
import edu.uiowa.icts.bluebutton.json.view.IGetStats;

public class LabList {

	private List<Lab> list;
	private Long startDateinMillis;
	private Long endDateinMillis;
	private HashMap<String, LoincCode> loincCodeHashMap;
	

	
	public LabList(List<Lab> list, Long startDateinMillis, Long endDateinMillis, HashMap<String, LoincCode> loincCodeHashMap) {
		this.list = list;
		this.startDateinMillis = startDateinMillis;
		this.endDateinMillis = endDateinMillis;
		this.loincCodeHashMap = loincCodeHashMap;
	}
	
	public List<LabResult> getLabs(){		
		List<LabResult> unfilteredLabList = new ArrayList<LabResult>();
		if(list != null){
			for(Lab l : list){
				for(LabResult lr : l.getResults()){
					lr.setLabName(l.getName());
					if(this.loincCodeHashMap.containsKey(lr.getLoincCodeName())){
						lr.setLoincCode(loincCodeHashMap.get(lr.getLoincCodeName()));
					}
					unfilteredLabList.add(lr);
				}
			}
			
		}
		List<LabResult> filteredLabList = new ArrayList<LabResult>();
		if(unfilteredLabList.size() > 0){
			List<IDateInMillis> unfilteredList = new ArrayList<IDateInMillis>();
			unfilteredList.addAll(unfilteredLabList);
			
			List<IDateInMillis> filteredList = new TimeFilter(unfilteredList,this.startDateinMillis, this.endDateinMillis).getFillteredList();
					
			for(IDateInMillis im : filteredList){
				filteredLabList.add((LabResult) im);
			}
		}
				
		List<IGetStats> statsList = new ArrayList<IGetStats>();
		statsList.addAll(filteredLabList);

		GetStatsByLoincCodeAndName gls = new GetStatsByLoincCodeAndName(statsList);
		CombineStats cLabStats = new CombineStats(statsList, gls.getUnits());
		
		List<LabResult> dataAddedLabResultList = new ArrayList<LabResult>();
		for(IGetStats  i : cLabStats.getCombiedStats()){
			dataAddedLabResultList.add((LabResult) i);
		}
		
		List<LabResult> list = new ArrayList<LabResult>();
		if(filteredLabList.size() >0){
			List<ISubgroup> iList = new ArrayList<ISubgroup> ();
			iList.addAll(filteredLabList);
			for(ISubgroup i : new FindSearchProperties(new CreateSubgrid(iList).getSubgrid()).getListWithSearchIds()){
				list.add((LabResult) i);
			}
		}
		
		return list;
	}
			
}