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
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.base.AbstractDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class Allergy extends Entry implements IBlueButtonObject{

	private String status;
	private String severity;
	private Entry reaction = new Entry();
	private Entry reaction_type = new Entry();
	private Entry allergen = new Entry();
	private final String searchName = "allergy";
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public Entry getReaction() {
		return reaction;
	}
	public void setReaction(Entry reaction) {
		this.reaction = reaction;
	}
	public Entry getReaction_type() {
		return reaction_type;
	}
	public void setReaction_type(Entry reaction_type) {
		this.reaction_type = reaction_type;
	}
	public Entry getAllergen() {
		return allergen;
	}
	public void setAllergen(Entry allergen) {
		this.allergen = allergen;
	}
	
	@Override
	public String getName(){
		return this.allergen.name;
	}

	@JsonIgnore
	public List<String> getGroupBy() {
		return new ArrayList<String>(Arrays.asList(this.allergen.getName()));
	}
	
	public List<Allergy> getSubGrid(){
		List<Allergy> list = new ArrayList<Allergy>();
		if(subgrid != null){
			for(ISubgroup i : subgrid){
				list.add((Allergy) i);
			}
		}
		return list;
	}

	@Override @JsonIgnore
	public Long getSorted() {
		return this.getStartDateInMillis();
	}

	@Override @JsonIgnore
	public String getSearchFieldName() {
		return this.searchName;
	}
	
	public ISubgroup shallowClone(){
		Allergy a = new Allergy();
		a.setAllergen(allergen);
		a.setDate_range(date_range);
		a.setReaction(reaction);
		a.setSource(source);
		a.setStatus(status);
		a.setReaction_type(reaction_type);
		return a;
	}
	
	public List<String> getKeywords() {
		List<String> list = new ArrayList<String>();
		if(this.date_range.getStartDateDisplay() != null){list.add(this.date_range.getStartDateDisplay());}
		if(this.date_range.getEndDateDisplay() != null){list.add(this.date_range.getEndDateDisplay());}
		if(this.allergen.getName() != null){list.add(this.allergen.getName());}
		if(this.reaction_type.getName() != null){list.add(this.reaction_type.getName());}
		if(this.reaction.getName() != null){list.add(this.reaction.getName());}
		if(this.source != null){list.add(this.source);}
		
		return list;
	}
	
	
	

}
