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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class Encounter extends AbstractResult implements IBlueButtonObject{
	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private static DateTimeFormatter displayFormat = DateTimeFormat.forPattern("yyyy-MM-dd");

	private Entry translation= new Entry();
	private Location location = new Location();
	private List<Entry> findings = new ArrayList<Entry>();
	private Entry performer;
	private final String searchFeildName= "encounter";
	
	public Entry getTranslation() {
		return translation;
	}
	public void setTranslation(Entry translation) {
		this.translation= translation;
	}
	
	public String getPerformer() {
		if(this.translation.getName() != null){
			return this.translation.getName();
		}
		else if(this.performer != null){
			return performer.getName();
			}
		return super.getName();
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}	
	public String getFindings() {
		if(this.findings.size() == 0){
			return null;
		}
		else{
			Set<String> findingNameSet = new LinkedHashSet<String>();
			for(Entry f : this.findings){
				findingNameSet.add(f.getName());
			} 
			return StringUtils.join(findingNameSet.toArray(), ", ");
		}
	}
	public void setFindings(List<Entry> findings) {
		this.findings = findings;
	}

	@Override 
	public String getName(){
		if(this.translation.getName() != null){
			return this.translation.getName();
		}
		else if(this.performer != null && this.performer.getName() != null){
			return performer.getName();
			}
		return super.getName();
	}
		
	public String getOrganization(){
		return this.location.getOrganization();
	}
	
	@Override
	public List<String> getKeywords(){
		List<String> list = new ArrayList<String>();
		if(this.getDisplayDate() != null){list.add(this.getDisplayDate());}
		if(this.getName() != null){list.add(this.getName());}
		if(this.location.getOrganization() != null){list.add(this.location.getOrganization());}
		if(this.findings.size()>0){
			for(Entry e : this.findings){
				if(e.getName() != null){list.add(e.getName());}
			}
		}
		if(this.source != null){list.add(this.source);}
		return list ;
	}
	@Override
	public List<String> getGroupBy() {
		List<String> list = new ArrayList<String>();
		list.add(this.getName());
		return list;
	}
	@Override
	public String getSearchFieldName() {
		return searchFeildName;
	}
	@Override
	public ISubgroup shallowClone() {
		Encounter e = new Encounter();
		e.setLocation(location);
		e.setPerformer(performer);
		e.setFindings(findings);
		e.setSource(source);
		e.setUnit(unit);
		e.setValue(value);
		e.setDate(date);
		e.setTranslation(translation);
		return (ISubgroup) e;
	}
	@Override
	public Long getSorted() {
		return this.getDateInMillis();
	}
	@Override
	public List<Encounter> getSubGrid() {
		List<Encounter> list = new ArrayList<Encounter>();
		if(this.getSubgridInterface() != null){
			for(ISubgroup i : this.getSubgridInterface()){
				list.add((Encounter)i);
			}
		}
		return list;
	}
	public void setPerformer(Entry performer) {
		this.performer = performer;
	}

}
