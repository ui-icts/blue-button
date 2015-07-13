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

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class Procedure extends Entry implements IBlueButtonObject{

	private Entry device;
	private Location performer;	
	private Entry specimen;
	private final String searchFeildName = "procedure";
	
	private List<String> keywords;
		
	public String getDevice() {
		return (this.device != null) ? device.getName() : null;
	}
	public void setDevice(Entry device) {
		this.device = device;
	}
	public Location getPerformer() {
		return performer;
	}
	public void setPerformer(Location performer) {
		this.performer = performer;
	}
	public String getSpecimen() {
		return (this.specimen != null)  ? specimen.getName() : null;
	}
	public void setSpecimen(Entry specimen) {
		this.specimen = specimen;
	}
	
	public String getOrganization(){
		return (this.performer != null)  ? this.performer.getOrganization() : null;
	}
	
	@Override
	public List<String> getKeywords(){
		this.keywords = new ArrayList<String>();
		if(this.getDisplayDate() != null){addKeyWord(this.getDisplayDate());}
		if(this.getName() != null){addKeyWord(this.getName());}
		if(this.device != null){addKeyWord(this.device.getName());}
		if(this.specimen != null){addKeyWord(this.specimen.getName());}
		if(this.source != null){addKeyWord(this.source);}
		if(this.performer != null){addKeyWord(this.performer.getOrganization());}
		return keywords;
	}
	
	private void addKeyWord(String keyword){
		if(keyword != null){
			keywords.add(keyword);
		}
	}
	
	@Override @JsonIgnore
	public List<String> getGroupBy() {
		List<String> list = new ArrayList<String>();
		if(this.name != null) {list.add(this.name);}
		return list;
	}
	
	@Override @JsonIgnore
	public String getSearchFieldName() {
		return searchFeildName;
	}
	
	@Override @JsonIgnore
	public ISubgroup shallowClone() {
		Procedure p = new Procedure();
		p.setName(name);
		p.setPerformer(performer);
		p.setSpecimen(specimen);
		p.setSource(source);
		p.setDate(date);
		return (ISubgroup) p;
	}
	
	@Override @JsonIgnore
	public Long getSorted() {
		return this.getDateInMillis();
	}
	
	@Override
	public List<Procedure> getSubGrid() {
		List<Procedure> list = new ArrayList<Procedure>();
		for(ISubgroup i : this.getSubgridInterface()){
			list.add((Procedure) i);
		}
		return list;
	}
}
