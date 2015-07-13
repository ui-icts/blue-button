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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class BlueButtonRecords {
	
	private List<BlueButtonRecord> records  = new ArrayList<BlueButtonRecord>();
	private Long startDateInMillis;
	private Long endDateInMillis;
	
	public void setRecords(List<BlueButtonRecord> records) {
		this.records = records;
	}
	public List<BlueButtonRecord> getRecords() {		
		return records;
	}
	public Long getStartDateInMillis() {
		return startDateInMillis;
	}
	public void setStartDateInMillis(Long startDate) {
		this.startDateInMillis = startDate;
	}
	public Long getEndDateInMillis() {
		return endDateInMillis;
	}
	public void setEndDateInMillis(Long endDate) {
		this.endDateInMillis = endDate;
	}

}
