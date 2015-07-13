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

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.uiowa.icts.bluebutton.json.Location;

public class BlueButtonLocation{

	private String name;
	private String type;
	
	private Location location;
	
	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getAddress(){
		String s = "";
		if(this.location != null){
			if(this.location.getStreet() != null && this.location.getStreet().size()>0){s= this.addField(s, this.location.getStreet().get(0));}
			s = this.addField(s, this.location.getCity());
			s = this.addField(s, this.location.getState());
			s = this.addField(s, this.location.getZip());
			if(s.toCharArray().length>0){s = s.substring(0, s.length()-2);}
		}
		return s;
	}
	
	@JsonIgnore
	private String addField(String s, String stringToAdd){
		String newS = s;
		if(stringToAdd != null){
			newS = newS.concat(stringToAdd).concat(", ");
			return newS;
		}
		return newS;
		
	}	

}
