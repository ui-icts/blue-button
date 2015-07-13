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

import edu.uiowa.icts.bluebutton.domain.LabResultSynonym;
import edu.uiowa.icts.bluebutton.json.view.IGetStats;
import edu.uiowa.icts.bluebutton.json.view.StatsFinder;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class LabResult  extends AbstractResult implements IBlueButtonObject, IGetStats{
	
	private LabResultReferenceRange reference_range;
	private String labName;
	private LoincCode loincCode= new LoincCode();
	private StatsFinder statsFinder;
	private String source;
	private LabResultSynonym synonym;
	private final String searchFeildName = "labs";
	
	public Double getNormalMin(){
		return this.loincCode.getMin_normal();
	}
	
	public Double getnormalMax(){
		return this.loincCode.getMax_normal();
	}
	
//	@JsonIgnore
//	public JSONObject getJson(){
//		JSONObject json = new JSONObject();
//		json.put("labName", this.getLabName());
//		json.put("name", this.getName());
//		json.put("loincCode", this.loincCode.getCode());
//		json.put("displayValue", this.getDisplayValue());
//		json.put("value", this.getDoubleValue());
//		json.put("unit", this.getUnit());
//		json.put("subCategory", this.loincCode.getSubRootName());
//		json.put("normalRange", this.loincCode.getNormalRange());
//		json.put("normalMin", this.loincCode.getMin_normal());
//		json.put("normalMax", this.loincCode.getMax_normal());
//		json.put("dateInMillis", this.getDateInMillis());
//		json.put("date", this.getDisplayDate());
//		json.put("referenceRange", this.getReferenceRangeText());
//		json.put("source", this.source);
//		json.put("keywords", this.getKeyWords());
//		json = new StatsInformation(this.statsFinder,this.getDoubleValue(), this.loincCode).getInfo(json);
//		return json;
//	}	
	
	@Override
	public List<String> getKeywords(){
		List<String> list= new ArrayList<String>();
		if(this.getLabName() != null){list.add(this.getLabName()); }
		if(this.getDisplayDate() != null){list.add(this.getDisplayDate());}
		if(this.getName() != null){list.add(this.getName()); }
		if(this.loincCode.getCode() != null){list.add(this.loincCode.getCode());}
		if(this.getDisplayValue() != null){list.add(this.getDisplayValue());}
		if(this.source != null){list.add(this.source);}
		return list;
	}
	
	public StatsInformation getStatsInformation(){
		return new StatsInformation(this.statsFinder,this.getDoubleValue(), this.loincCode);
	}
	
	@Override
	public String getDate(){
		return this.getDisplayDate();
	}
	
	public LabResultReferenceRange getReference_range() {
		return reference_range;
	}
	public void setReference_range(LabResultReferenceRange reference_range) {
		this.reference_range = reference_range;
	}	
	public String getLabName() {
		if(this.labName != null){
			return this.labName;
		}
		else if(this.synonym != null && this.synonym.getPanel() != null && this.synonym.getPanel() != new String()){
			return this.synonym.getPanel();
		}
		return null;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getReferenceRange(){
		return (this.reference_range != null) ? this.reference_range.getText() : null;
	}
	public String getLoincCode(){
		return (this.loincCode != null) ? this.loincCode.getCode() : null;
	}
	public void setLoincCode(LoincCode loincCode) {
		this.loincCode = loincCode;
	}
	public void setStatsFinder(StatsFinder statsFinder) {
		this.statsFinder = statsFinder;
	}
	public StatsFinder getStatsFinder(){
		return this.statsFinder;
	}
	
	@Override
	public String getName(){
		if(this.synonym != null && this.synonym.getOfficialName() != null && this.synonym.getOfficialName() != new String()){
			return this.synonym.getOfficialName();
		}
		return this.name;
	}
		
	public LabResultSynonym getSynonym() {
		return synonym;
	}

	public void setSynonym(LabResultSynonym synonym) {
		this.synonym = synonym;
	}

	@Override @JsonIgnore
	public List<String> getGroupBy() {
		List<String> list = new ArrayList<String>();
		if(this.name != null){ list.add(name);}
		if(this.loincCode.getCode() != null){list.add(this.loincCode.getCode());}
		if(this.getLabName() != null){list.add(this.getLabName());}
		return list;
	}

	@Override @JsonIgnore
	public String getSearchFieldName() {
		return searchFeildName;
	}

	@Override @JsonIgnore
	public ISubgroup shallowClone() {
		LabResult lr = new LabResult();
		lr.setDate(date);
		lr.setLabName(labName);
		lr.setName(this.getName());
		if(this.getDoubleValue() != null){lr.setValue(this.getDoubleValue().toString());}
		lr.setLoincCode(loincCode);
		lr.setStatsFinder(statsFinder);
		lr.setSynonym(synonym);
		lr.setUnit(unit);
		lr.setSource(this.getSource());		
		return (ISubgroup) lr;
	}

	@Override @JsonIgnore
	public Long getSorted() {
		return this.getDateInMillis();
	}

	@Override
	public List<LabResult> getSubGrid() {
		List<LabResult> list = new ArrayList<LabResult>();
		for(ISubgroup i : this.getSubgridInterface()){
			LabResult lr = (LabResult) i;
			lr.setSubgird(new ArrayList<ISubgroup>());
			list.add(lr);
		}
		return list;
	}
	
}
