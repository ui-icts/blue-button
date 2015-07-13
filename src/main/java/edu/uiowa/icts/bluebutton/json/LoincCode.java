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

import org.json.JSONObject;

public class LoincCode {

	private String code;
	private String subRootName;
	private Double min_normal;
	private Double max_normal;
	
	public LoincCode(String code, Double min_normal, Double max_normal){
		this.code = code;
		this.min_normal = min_normal;
		this.max_normal = max_normal;
	}	
	
	public LoincCode() {
		
	}
	
	public String getNormalRange(){
		if(this.max_normal != null && this.min_normal != null){
			return this.min_normal.toString().concat(" - ").concat(this.max_normal.toString());
		}
		return null;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSubRootName() {
		return subRootName;
	}
	public void setSubRootName(String subRootName) {
		this.subRootName = subRootName;
	}
	public Double getMin_normal() {
		return min_normal;
	}
	public void setMin_normal(Double min_normal) {
		this.min_normal = min_normal;
	}
	public Double getMax_normal() {
		return max_normal;
	}
	public void setMax_normal(Double max_normal) {
		this.max_normal = max_normal;
	}
	
}
