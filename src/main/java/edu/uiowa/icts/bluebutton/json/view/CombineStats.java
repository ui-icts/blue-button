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

import java.util.HashMap;
import java.util.List;

public class CombineStats {

	private List<IGetStats> list;
	private HashMap<String, StatsFinder> hash;
	
	public CombineStats(List<IGetStats> list, HashMap<String, StatsFinder> hash){
		this.list = list;
		this.hash = hash;
	}
	
	public List<IGetStats> getCombiedStats(){
		if(this.list == null){return null;}
		else if(this.hash == null){return this.list;}
		else{
			for(IGetStats igs : this.list){
				if(igs.getDoubleValue() != null){
					if(this.hash.containsKey(igs.getLoincCode())){
						igs.setStatsFinder(this.hash.get(igs.getLoincCode()));
					}
					else if(this.hash.containsKey(igs.getName())){
						igs.setStatsFinder(this.hash.get(igs.getName()));
					}
					else if(this.hash.containsKey(igs.getLabName())){
						igs.setStatsFinder(this.hash.get(igs.getLabName()));
					}
				}
			}
			return this.list;
		}
	}
	
	
}
