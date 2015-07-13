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
import java.util.List;

import edu.uiowa.icts.bluebutton.json.BlueButtonRecord;
import edu.uiowa.icts.bluebutton.json.Demographics;
import edu.uiowa.icts.bluebutton.json.Encounter;
import edu.uiowa.icts.bluebutton.json.Procedure;

public class FindAddress {

	private BlueButtonRecord record;
	
	public FindAddress(BlueButtonRecord record){
		this.record = record;
	}
	
	public List<BlueButtonLocation> getLocations(){
		List<BlueButtonLocation> list = new ArrayList<BlueButtonLocation>();
		if(this.record != null){
			if(record.getDemographics() != null ){
				Demographics d = record.getDemographics();
				if(d.getProvider() != null && d.getProvider().getAddress() != null){
					BlueButtonLocation bbl = new BlueButtonLocation();
					bbl.setLocation(d.getProvider().getAddress());
					bbl.setType("Provider");
					bbl.setName(record.getDemographics().getProvider().getOrganization());
					if(bbl.getAddress().length() > 0){
						list.add(bbl);
					}
				}
				if(d.getAddress() != null){
					BlueButtonLocation bbl = new BlueButtonLocation();
					bbl.setType("Home");
					bbl.setLocation(d.getAddress());
					bbl.setName(d.getName().getFullName());
					if(bbl.getAddress().length() > 0){
						list.add(bbl);
					}
				}
			
			
			if(record.getEncounters() != null){
				for(Encounter e : record.getEncounters()){
					if(e.getLocation() != null){
						BlueButtonLocation bbl = new BlueButtonLocation();
						bbl.setLocation(e.getLocation());
						bbl.setType("Encounter");
						bbl.setName(e.getName());
						if(bbl.getAddress().length() > 0){
							list.add(bbl);
						}
					}
				}
			}
			if(record.getProcedures() != null){
				for(Procedure p : record.getProcedures()){
					if(p.getPerformer() != null){
						BlueButtonLocation bbl = new BlueButtonLocation();
						bbl.setLocation(p.getPerformer());
						bbl.setName(p.getPerformer().getOrganization());
						bbl.setType("Procedure");
						if(bbl.getAddress().length() > 0){
							list.add(bbl);
						}
					}
				}
			}
			
		}
	} 

		return list;
	}
	
	
	
	
}
