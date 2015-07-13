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

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class Entry implements IDateInMillis{

	public static DateTimeFormatter FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private static DateTimeFormatter displayFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	protected String name;
	protected String code;
	protected String code_system;
	protected String code_system_name;
	protected String date;
	protected DateRange date_range = new DateRange();
	protected String source;
	
	protected List<ISubgroup> subgrid;
	protected String parent;
	protected String id;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode_system() {
		return code_system;
	}
	
	
	@JsonIgnore
	public List<ISubgroup> getSubgridInterface() {
		return (subgrid != null) ? subgrid : new ArrayList<ISubgroup>();
	}
	
	public void setSubgird(List<ISubgroup> list) {
		this.subgrid = list;
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public void setCode_system(String code_system) {
		this.code_system = code_system;
	}

	public String getCode_system_name() {
		return code_system_name;
	}

	public void setCode_system_name(String code_system_name) {
		this.code_system_name = code_system_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public DateRange getDate_range() {
		return date_range;
	}

	public void setDate_range(DateRange date_range) {
		this.date_range = date_range;
	}

	
	@JsonIgnore
	public Long getStartDateInMillis(){
		if (this.date != null){
			return this.getDateInMillis();
		} else {
			return this.date_range.getStartDateInMillis();
		}
	}
	
	
	public Long getEndDateInMillis(){
		if (this.date != null){
			return this.getDateInMillis();
		} else {
			return this.date_range.getEndDateInMillis();
			}
		
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
	public Long getDateInMillis() {
		if(this.date != null){
			return FORMAT.parseDateTime(this.date).getMillis();
		}
		return this.date_range.getStartDateInMillis();
	}
	
	public String getStartDate(){
		return (this.date_range != null) ? this.date_range.getStartDateDisplay() : null;
	}
	
	public String getEndDate(){
		return (this.date_range != null) ? this.date_range.getEndDateDisplay() : null;
	}

	
	public String getDisplayDate() {
		if(this.date != null){
			return ""+displayFormat.print(FORMAT.parseDateTime(this.date));
		}
		else{
			return null;
		}
	}
	
}
