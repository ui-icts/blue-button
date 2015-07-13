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

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class DateRange {

	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private static DateTimeFormatter displayFormat = DateTimeFormat.forPattern("yyyy-MM-dd");	
	
	private String start;
	private String end;
	
	public String getStart() {
		return start;
	}
	public void setStart(String startDate) {
		this.start = startDate;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String endDate) {
		this.end = endDate;
	}
	
	@JsonIgnore
	public Long getStartDateInMillis(){
		if(this.start != null){
			return format.parseDateTime(this.start).getMillis();
		}
		else{return null;}
	}
	
	@JsonIgnore
	public Long getEndDateInMillis(){
		if(this.end != null){
			return format.parseDateTime(this.end).getMillis();
		}
		else{return null;}
	}
	
	@JsonIgnore
	public String getStartDateDisplay(){
		if(this.start != null){
			return ""+displayFormat.print(format.parseDateTime(this.start)); 
		}
		else{return null;}
	}
	
	@JsonIgnore
	public String getEndDateDisplay(){
		if(this.end != null){
			return ""+displayFormat.print(format.parseDateTime(this.end)); 
		}
		else{return null;}
	}
	@JsonIgnore
	public boolean isActiveIntheLastYear() {
		if (this.end == null && this.start != null){
			DateTime now = new DateTime();
			DateTime startDateTime = format.parseDateTime(this.start);
			Period period = new Period(startDateTime, now);
			if (period.getYears() > 0){
				return false;
			} else {
				return true;
			}
		}
		else {
			return false;
		}
	}
	
}
