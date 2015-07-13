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

import java.util.List;

public class FindSearchProperties {

	private List<ISubgroup> list;
	
	public FindSearchProperties(List<ISubgroup> list){
		this.list = list;
	}
	
	public List<ISubgroup> getListWithSearchIds() {
		Integer counter = 0;
		List<ISubgroup> list = this.list;
		for(ISubgroup i : list){
			if(i.getSubgridInterface().size() >0){
				i.setId(i.getSearchFieldName().concat(counter.toString()));
				for(ISubgroup iSub : i.getSubgridInterface()){
					iSub.setParent(i.getId());
					iSub.setId(i.getSearchFieldName().concat((++counter).toString()));
				}
			}
			else{
				i.setId(i.getSearchFieldName().concat(counter.toString()));
				counter++;
			}
		}
		return list;
	}

}
