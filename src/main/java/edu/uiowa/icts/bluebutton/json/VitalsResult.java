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
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import edu.uiowa.icts.bluebutton.json.view.IGetStats;
import edu.uiowa.icts.bluebutton.json.view.StatsFinder;

@JsonIgnoreProperties(ignoreUnknown = true)
// ignore unused JSON data
public class VitalsResult extends AbstractResult implements IBlueButtonObject,IGetStats{

	private LoincCode loincCode= new LoincCode();
	private StatsFinder statsFinder;
	private final String searchFeildName = "vitals";
	
//	@JsonIgnore
//	public JSONObject getJson(){
//		JSONObject json = new JSONObject();
//		json.put("name", this.getName());
//		json.put("value", this.getDoubleValue());
//		json.put("displayValue", this.getDisplayValue());
//		json.put("dateInMillis", this.getDateInMillis());
//		json.put("date", this.getDisplayDate());
//		json.put("loincCode", this.getLoincCodeName());
//		json.put("normalMin", this.loincCode.getMin_normal());
//		json.put("normalMax", this.loincCode.getMax_normal());
//		json.put("unit", this.getUnit());
//		json.put("source", this.source);
//		json = new StatsInformation(this.statsFinder,this.getDoubleValue(), this.loincCode).getInfo(json);
//		json.put("keywords", this.getKeywords());
//		return json;
//	}
	
	public Double getNormalMin(){
		return this.loincCode.getMin_normal();
	}
	
	public Double getNormalMax(){
		return this.loincCode.getMax_normal();
	}
	
	public StatsInformation getStatsInformation(){
		return new StatsInformation(this.statsFinder,this.getDoubleValue(), this.loincCode);
	}
	
	@Override
	public String getDate(){
		return this.getDisplayDate();
	}
	
	
	public List<String> getKeywords(){
		List<String> list = new ArrayList<String>();
		if(this.getName() != null){list.add(this.getName());}
		if(this.getDisplayDate() != null){list.add(this.getDisplayDate());}
		if(this.getDisplayValue() != null){list.add(this.getDisplayValue());}
		if(this.source != null){list.add(this.source);}
		if(this.getLoincCodeName() != null){list.add(this.getLoincCodeName());}
		return (list.size()>0) ? list : null;
	}
	
	public StatsFinder getStatsFinder() {
		return statsFinder;
	}

	public void setLoincCode(LoincCode loincCode) {
		this.loincCode = loincCode;
	}

	public String getLoincCode() {
		return this.getLoincCodeName();
	}

	public void setStatsFinder(StatsFinder sf) {
		this.statsFinder = sf;
	}

	@Override @JsonIgnore
	public List<String> getGroupBy() {
		List<String> list = new ArrayList<String>();
		if(this.getName() != null){list.add(this.getName());}
		if(this.getLoincCodeName() != null){list.add(this.getLoincCodeName());}
		return list;
	}

	@Override @JsonIgnore
	public String getSearchFieldName() {
		return searchFeildName;
	}

	@Override
	public ISubgroup shallowClone() {
		VitalsResult vr = new VitalsResult();
		vr.setDate(date);
		vr.setName(this.getName());
		if(this.getDoubleValue() != null){vr.setValue(this.getDoubleValue().toString());}
		vr.setLoincCode(loincCode);
		vr.setStatsFinder(statsFinder);
		vr.setUnit(unit);
		vr.setSource(this.getSource());	
		return vr;
	}
		

	@Override @JsonIgnore
	public Long getSorted() {
		return this.getDateInMillis();
	}

	@Override
	public List<VitalsResult> getSubGrid() {
		List<VitalsResult> list = new ArrayList<VitalsResult>();
		for(ISubgroup i : this.getSubgridInterface()){
			list.add((VitalsResult) i);
		}
		return list;
	}

	@Override @JsonIgnore
	public String getLabName() {
		return null;
	}

}
