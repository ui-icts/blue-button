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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.DateTimeComparator;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class DateMinMaxCalculator {
	
	private List<IDateInMillis> dateInMillisList;
	
	public DateMinMaxCalculator(List<IDateInMillis> dateInMillisList){
		this.dateInMillisList = dateInMillisList;
	}

	public Long getMaxTime() {
		if (this.getLongList() != null && this.getLongList().size() > 0){
			Long max =Collections.max(this.getLongList(), new Comparator<Long>(){
				public int compare(Long l, Long l2) {
					DateTimeComparator dtc = DateTimeComparator.getInstance();
						return dtc.compare(l, l2) ;
					}
			});
			return max;
		}
		return null;
	}
	
	public Long getMinTime() {
		if (this.getLongList() != null && this.getLongList().size() > 0){
			Long min =Collections.min(this.getLongList(), new Comparator<Long>(){
				public int compare(Long l, Long l2) {
					DateTimeComparator dtc = DateTimeComparator.getInstance();
						return dtc.compare(l, l2) ;
					}
			});
			return min;
		} 
		return null;
	}
	
	private List<Long> getLongList(){
		List<Long> list = new ArrayList<Long>();
		if(this.dateInMillisList != null){
			for(IDateInMillis i : this.dateInMillisList){
				if(i.getDateInMillis() != null){
					list.add(i.getDateInMillis());
				}
				if (i.getEndDateInMillis() != null){
					list.add(i.getEndDateInMillis());
				}
				if(i.getStartDateInMillis() != null){
					list.add(i.getStartDateInMillis());
				}
			}
			return list;
		}
		return null;
	}
	
	
	
}
