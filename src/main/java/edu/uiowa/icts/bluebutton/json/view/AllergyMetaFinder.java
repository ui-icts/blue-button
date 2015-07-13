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

import java.util.List;

import org.json.JSONObject;

import edu.uiowa.icts.bluebutton.json.Allergy;

public class AllergyMetaFinder {

	private List<Allergy> list;
	
	public AllergyMetaFinder(List<Allergy> list){
		this.list = list;
	}
		
	public String getSeverity(){
		String severity;
		int mild = 0, moderate= 0, severe = 0, exists = 0;
		for(Allergy a : this.list){
			if(a.getSeverity() != null){
				switch (a.getSeverity().toLowerCase()){
					case "mild": mild++; break;
					case "moderate" : moderate++; break;
					case "severe" : severe++; break;
					case "fatal" : severe++; break;
					case "moderate to severe" : severe++; break;
					case "mild to moderate" : moderate++; break;
					default : exists++;
				}
			}else{exists++;}
		}
		if(severe > 0){severity = (severe >1) ? "multiple severe":"severe";}
		else if(moderate > 0){severity= (moderate >1) ? "multiple moderate":"moderate";}
		else if(mild > 0){severity = (mild > 1) ? "multiple mild": "mild";}
		else if(exists > 0){severity =(exists > 1) ? "multiple" : "existing";}
		else{severity ="no";}
		return severity; 
	}
	
}
