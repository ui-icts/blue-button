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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetStatsByLoincCodeAndName {

	private List<IGetStats> list;
	
	public GetStatsByLoincCodeAndName(List<IGetStats> list){
		this.list = list;
	}
	
	public HashMap<String, StatsFinder> getUnits(){
		HashMap<String, StatsFinder> hash = new HashMap<String,StatsFinder>();
		for(IGetStats igs : this.list){
			if((igs.getName() != null || igs.getLoincCode() != null || igs.getLabName() != null) && igs.getDoubleValue() != null){
				if(igs.getLoincCode() != null && hash.containsKey(igs.getLoincCode())){
					List<IGetStats> list = hash.get(igs.getLoincCode()).getList();
					list.add(igs);
					hash.put(igs.getLoincCode(), new StatsFinder(list));
				}
				else if(igs.getName() != null && hash.containsKey(igs.getName())){
					List<IGetStats> list = hash.get(igs.getName()).getList();
					list.add(igs);
					hash.put(igs.getName(), new StatsFinder(list));
				}
				else if(igs.getLabName() != null && hash.containsKey(igs.getLabName())){
					List<IGetStats> list = hash.get(igs.getLabName()).getList();
					list.add(igs);
					hash.put(igs.getLabName(), new StatsFinder(list));
				}
				else{
					List<IGetStats> list = new ArrayList<IGetStats>();
					list.add(igs);
					hash.put((igs.getLoincCode() != null) ? igs.getLoincCode(): (igs.getName() != null) ? igs.getName() : igs.getLabName(), new StatsFinder(list));
				}
			}
		}
		return hash;
	}
	
}
