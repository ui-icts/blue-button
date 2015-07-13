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

public class EncounterList {

	private List<Encounter> list;
	private Long startDateinMillis;
	private Long endDateinMillis;

	
	public EncounterList(List<Encounter> list, Long startDateinMillis, Long endDateinMillis) {
		this.list = list;
		this.startDateinMillis = startDateinMillis;
		this.endDateinMillis = endDateinMillis;
	}
	
	public List<Encounter> getEncounters(){
		
		List<Encounter> mlist = new ArrayList<Encounter>();
		if(this.list != null){
			List<IDateInMillis> unfilteredList = new ArrayList<IDateInMillis>();
			unfilteredList.addAll(this.list);
			
			List<IDateInMillis> filteredList = new ArrayList<IDateInMillis>();
			filteredList = new TimeFilter(unfilteredList,this.startDateinMillis, this.endDateinMillis).getFillteredList();
					
			for(IDateInMillis im : filteredList){
				mlist.add((Encounter) im);
			}
		}
		List<Encounter> list = new ArrayList<Encounter>();
		if(mlist.size() >0){
			List<ISubgroup> iList = new ArrayList<ISubgroup> ();
			iList.addAll(mlist);	
			for(ISubgroup i : new FindSearchProperties(new CreateSubgrid(iList).getSubgrid()).getListWithSearchIds()){
				list.add((Encounter) i);
			}
		}
		return list;
	}
		
}