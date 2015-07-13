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

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class Problem extends Entry implements IBlueButtonObject{

	private Entry translation;
	private String comment;
	private String status;
	private final String searchFeildName = "problem";
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Entry getTranslation() {
		return translation;
	}
	public void setTranslation(Entry translation) {
		this.translation = translation;
	}
	
	@Override
	public String getName(){
		return (this.translation != null && this.translation.getName() != null) ? this.translation.getName() : this.name;
	}
	
	@Override
	public List<String> getKeywords(){
		List<String> list = new ArrayList<String>();
		if(this.date_range.getStartDateDisplay() != null){list.add(this.date_range.getStartDateDisplay());}
		if(this.date_range.getEndDateDisplay() != null){list.add(this.date_range.getEndDateDisplay());}
		list.add(this.getName());
		if(this.getStatus() != null){list.add(this.getStatus());}
		if(this.source != null){list.add(this.source);}
		return list;
	}
	@Override @JsonIgnore
	public List<String> getGroupBy() {
		List<String> list = new ArrayList<String>();
		list.add(this.getName());
		return list;
	}
	@Override @JsonIgnore
	public String getSearchFieldName() {
		return searchFeildName;
	}
	@Override @JsonIgnore
	public ISubgroup shallowClone() {
		Problem p = new Problem();
		p.setTranslation(translation);
		p.setName(this.getName());
		p.setSource(source);
		p.setDate_range(date_range);
		p.setComment(comment);
		p.setStatus(status);
		return (ISubgroup) p;
	}
	@Override @JsonIgnore
	public Long getSorted() {
		return this.date_range.getStartDateInMillis();
	}
	@Override
	public List<Problem> getSubGrid() {
		List<Problem> list = new ArrayList<Problem>();
		if(this.getSubgridInterface() != null){
			for(ISubgroup i : this.getSubgridInterface()){
				list.add((Problem) i);
			}
		}
		return list;
	}

}
