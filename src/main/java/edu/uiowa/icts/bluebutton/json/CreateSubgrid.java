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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTimeComparator;

public class CreateSubgrid {

	public List<ISubgroup> list;
	
	public CreateSubgrid(List<ISubgroup> list){
		this.list = list;
	}
	
	private List<ISubgroup> sorted(){
		List<ISubgroup> list = new ArrayList<ISubgroup>(this.list);
		if(list.size()>0){
			Collections.sort(list, new Comparator<ISubgroup>(){
				public int compare(ISubgroup o, ISubgroup o2) {
					if(o2.getSorted() == null){return -1;}
					if(o.getSorted() == null){return 1;}
					DateTimeComparator dtc = DateTimeComparator.getDateOnlyInstance();
					return dtc.compare(o2.getSorted(), o.getSorted());
				}
			});
		}
		return list;
	}
	
	public List<ISubgroup> getSubgrid(){
		HashMap<String, List<ISubgroup>> hash = new HashMap<String, List<ISubgroup>>();
		List<ISubgroup> groupedList = new ArrayList<ISubgroup>();
		for(ISubgroup n : this.sorted()){
			if(n.getGroupBy() != null && n.getGroupBy().size()>0){
				boolean inHash= false;
				for(String s : n.getGroupBy()){
					if(hash.containsKey(s)){inHash =true; break;}
				}
				if(inHash){
					for(String s : n.getGroupBy()){
						if(hash.containsKey(s)){
							List<ISubgroup> subList = hash.get(s);
							subList.add(n);
							break;
						}
					}
				}
				else{
					List<ISubgroup> subList = new ArrayList<ISubgroup>();
					ISubgroup i = n.shallowClone();
					subList.add(i);
					n.setSubgird(subList);
					hash.put(n.getGroupBy().get(0), subList);
					groupedList.add(n);
				}	
			}
			else{
				List<ISubgroup> subList = new ArrayList<ISubgroup>();
				ISubgroup i = n.shallowClone();
				subList.add(i);
				n.setSubgird(subList);
				groupedList.add(n);
			}
		}
		return groupedList;
	}
}
