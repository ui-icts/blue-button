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

public abstract class AbstractResult extends Entry{

	protected String value;
	protected String unit;

	public Double getValue() {
		return this.getDoubleValue();
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@JsonIgnore
	public String getLoincCodeName() {
		if (this.code_system_name == null || this.code == null) {
			return null;
		}
		if (this.code_system_name.equals("LOINC")) {
			if (this.code.startsWith("X")) {
				return this.code.substring(1);
			} else {
				return this.code;
			}
		} else {
			return null;
		}
	}

	
	public String getDisplayValue() {
		if(this.value == null && this.unit== null){return null;}
		else if(this.unit == null){return this.value;}
		else if (this.value == null){return this.unit;}
		else{return new StringBuilder().append(this.value).append(this.unit).toString();}
		
	}
	@JsonIgnore
	public Double getDoubleValue() {
		if (this.value != null){
			return new Double(this.value);
		} else {
			return null;
		}
	}
}
