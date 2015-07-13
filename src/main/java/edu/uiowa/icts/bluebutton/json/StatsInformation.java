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

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.uiowa.icts.bluebutton.json.view.StatsFinder;

public class StatsInformation {

	public static final String outOfRangeText= "Value is outside LOINC normal range";
	public static final String outOfSDRange= "Greater than 2 standard deviations";
	public static final String bothOutOfRange= StatsInformation.outOfRangeText.concat(" and ").concat(StatsInformation.outOfSDRange.toLowerCase());
	
	private StatsFinder sf;
	private Double value;
	private LoincCode loincCode;
	
	public StatsInformation(StatsFinder sf, Double value, LoincCode loincCode){
		this.sf = sf;
		this.value = value;
		this.loincCode = loincCode;
	}
	
//	@JsonIgnore
//	public JSONObject getInfo(JSONObject json){
//		json.put("normal", this.getNormal());
//		json.put("normalDisplay", (this.getNormal() != null) ?  (this.getNormal() ? "Yes" : "No") : null);
//		json.put("normalSD", this.getNormalSD());
//		json.put("SDDisplay", (this.getNormalSD() != null) ?  (this.getNormalSD() ? "Yes" : "No") : null);
//		json.put("normalRange", this.getNormalRange());
//		json.put("inNormalRange", this.getInNormalRange());
//		json.put("rangeDisplay", (this.getInNormalRange() != null) ?  (this.getInNormalRange() ? "Yes" : "No") : null);
//		json.put("mean", this.getMean());
//		json.put("range", this.getRange());
//		json.put("standardDeviation", this.getStandardDeviation());
//		json.put("displayText", this.getDisplayText());
//		return (json.keySet().size() > 0) ? json : null;
//	}
//	
	public Double getStandardDeviation(){
		return (this.sf != null) ? this.sf.getStandardDeviation() : null;
	}
	
	public Double getMean(){
		return (this.sf != null) ? this.sf.getMean() : null;
	}
	
	public String getRange(){
		return (this.sf != null) ? this.sf.getDataSetRange() : null;

	}
	
	public String getRangeDisplay(){
		return (this.getInNormalRange() != null) ? (this.getInNormalRange() ? "Yes" : "No") : null;
	}
	
	public String getNormalDisplay(){
		return (this.getNormal() != null) ?  (this.getNormal() ? "Yes" : "No") : null;
	}
	
	
	public Boolean getInNormalRange() {
		if(this.loincCode != null && this.loincCode.getMax_normal() != null && this.loincCode.getMin_normal() != null && this.value != null){
			if(this.value >= this.loincCode.getMin_normal() && this.value <= this.loincCode.getMax_normal()){
				return true;
			}
			return false;
		}
		return null;
	}
	
	public String getNormalRange(){
		if(this.loincCode != null && this.loincCode.getMax_normal() != null && this.loincCode.getMin_normal() != null){
			return this.loincCode.getMin_normal().toString().concat(" to ").concat(this.loincCode.getMax_normal().toString());
		}
		return null;
	}
	
	
	public Boolean getNormalSD(){
		if(this.sf != null && this.value != null){
			Double mean = this.sf.getMean();
			Double sd = this.sf.getStandardDeviation();
			return (this.value>= mean-(sd*2) && this.value<= mean+(sd*2)) ? true : false;
		}
		return null;
	}
	
	public Boolean getNormal(){
		if(this.getInNormalRange() != null || this.getNormalSD() != null){
			if(this.getInNormalRange() != null && this.getInNormalRange().equals(false)){return false;}
			else if(this.getNormalSD() != null && this.getNormalSD().equals(false)){return false;}
			else{ return true;}
		}
		return null;
	}

	public String getDisplayText() {
		if(this.getInNormalRange() != null && this.getNormalSD() != null && !this.getInNormalRange() && !this.getNormalSD()){
			return StatsInformation.bothOutOfRange;
		}
		else if(this.getInNormalRange() != null && !this.getInNormalRange()){return StatsInformation.outOfRangeText;}
		else if(this.getNormalSD() != null && !this.getNormalSD()){return StatsInformation.outOfSDRange;}
		return null;
	}
	
}
