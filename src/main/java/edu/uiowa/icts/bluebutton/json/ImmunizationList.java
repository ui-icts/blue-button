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

public class ImmunizationList {

	private List<Immunization> list;
	
	public ImmunizationList(List<Immunization> list) {
		this.list = list;
	}
	
	public List<Immunization> getImmunizations(){
		List<ISubgroup> iList = new ArrayList<ISubgroup> ();
		List<Immunization> list = new ArrayList<Immunization>();
		if(this.list != null && this.list.size() >0){
			iList.addAll(this.list);			
			for(ISubgroup i : new FindSearchProperties(new CreateSubgrid(iList).getSubgrid()).getListWithSearchIds()){
				list.add((Immunization) i);
			}
		}
		return list;
	}
		
}
