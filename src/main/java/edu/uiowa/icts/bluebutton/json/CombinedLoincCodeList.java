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

public class CombinedLoincCodeList {
	
	List<LoincCode> categoryList= new ArrayList<LoincCode>();;
	List<LoincCode> rangeList = new ArrayList<LoincCode>();

	public CombinedLoincCodeList(List<LoincCode> categoryList, List<LoincCode> rangeList){
		this.categoryList = categoryList;
		this.rangeList =rangeList;
	}

	public HashMap<String, LoincCode> getCombinedHash(){
		HashMap<String, LoincCode> loincHash = new HashMap<String, LoincCode>();
		if(this.categoryList != null){
			for(LoincCode lc : this.categoryList){
				loincHash.put(lc.getCode(), lc);
			}
		}
		if(this.rangeList != null){
			for(LoincCode lc : this.rangeList){
				if(loincHash.containsKey(lc.getCode())){
					LoincCode newLoinc = loincHash.get(lc.getCode());
					newLoinc.setMax_normal(lc.getMax_normal());
					newLoinc.setMin_normal(lc.getMin_normal());
					loincHash.put(lc.getCode(),newLoinc);
				}else{
					loincHash.put(lc.getCode(), lc);
				}
			}
		}
		
		return loincHash;
	}
	
	
	
}
