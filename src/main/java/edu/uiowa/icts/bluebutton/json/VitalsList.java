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

public class VitalsList {

	private List<Vitals> list;
	private Long startDateinMillis;
	private Long endDateinMillis;
	private HashMap<String, LoincCode> loincCodeHashMap;
	

	
	public VitalsList(List<Vitals> list, Long startDateinMillis, Long endDateinMillis, HashMap<String, LoincCode> loincCodeHashMap) {
		this.list = list;
		this.startDateinMillis = startDateinMillis;
		this.endDateinMillis = endDateinMillis;
		this.loincCodeHashMap = loincCodeHashMap;
	}
	
	public List<VitalsResult> getVitals(){		
		List<VitalsResult> unfilteredVitalList = new ArrayList<VitalsResult>();
		if(list != null){
			for(Vitals v : list){
				for(VitalsResult vr : v.getResults()){
					vr.setDate(v.getDate());
					if(loincCodeHashMap.containsKey(vr.getLoincCodeName())){
						vr.setLoincCode(loincCodeHashMap.get(vr.getLoincCodeName()));
					}
					unfilteredVitalList.add(vr);
				}
			}
			
		}
		List<VitalsResult> filteredVitalList = new ArrayList<VitalsResult>();
		if(unfilteredVitalList.size() > 0){
			List<IDateInMillis> unfilteredList = new ArrayList<IDateInMillis>();
			unfilteredList.addAll(unfilteredVitalList);
			
			List<IDateInMillis> filteredList = new TimeFilter(unfilteredList,this.startDateinMillis, this.endDateinMillis).getFillteredList();
					
			for(IDateInMillis im : filteredList){
				filteredVitalList.add((VitalsResult) im);
			}
		}
				
		List<IGetStats> statsList = new ArrayList<IGetStats>();
		statsList.addAll(filteredVitalList);

		GetStatsByLoincCodeAndName gls = new GetStatsByLoincCodeAndName(statsList);
		CombineStats cLabStats = new CombineStats(statsList, gls.getUnits());
		
		List<VitalsResult> dataAddedLabResultList = new ArrayList<VitalsResult>();
		for(IGetStats  i : cLabStats.getCombiedStats()){
			dataAddedLabResultList.add((VitalsResult) i);
		}
		
		List<VitalsResult> list = new ArrayList<VitalsResult>();
		if(filteredVitalList.size() >0){
			List<ISubgroup> iList = new ArrayList<ISubgroup> ();
			iList.addAll(filteredVitalList);
			for(ISubgroup i : new FindSearchProperties(new CreateSubgrid(iList).getSubgrid()).getListWithSearchIds()){
				list.add((VitalsResult) i);
			}
		}
		return list;
	}
			
}