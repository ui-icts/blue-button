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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class Schedule {
	 private String type;
     private String period_value;
     private String period_unit;
     
	public void setType(String type) {
		this.type = type;
	}
	public void setPeriod_value(String period_value) {
		this.period_value = period_value;
	}
	public void setPeriod_unit(String period_unit) {
		this.period_unit = period_unit;
	}
	@JsonIgnore
	public String getDisplayValue() {
		String returnValue = "";
		if(this.type != null){
			returnValue += this.type + ": ";
		}
		if (this.period_value != null){
			returnValue += this.period_value;
		}
		if (this.period_unit != null){
			returnValue += this.period_unit;
		}
		return returnValue;
	}
     
     
}
