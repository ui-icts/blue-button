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

import java.util.List;

public class CombinedSmokingStatus {

	private List<SmokingStatus> list;
	
	public CombinedSmokingStatus(List<SmokingStatus> list){
		this.list = list;
	}
	
	public SmokingStatus getSmokingStatus(){
		SmokingStatus ss = new SmokingStatus();
		ss.setName(this.getName());
		ss.setDate_range(this.getDateRange());
		ss.setDate(this.getDate());
		return ss;
	}
	
	private String getDate() {
		for(SmokingStatus ss : this.list){
			if(ss.getDate() != null){ return ss.getDate();}
		}
		return null;
	}

	private DateRange getDateRange() {
		for(SmokingStatus ss : this.list){
			return ss.getDate_range();
		}
		return new DateRange();
	}

	private String getName() {
		for(SmokingStatus ss : this.list){
			if(ss.getName() != null && ss.getName().equalsIgnoreCase("unknown")== false){return ss.getName();}
		}
		return "unknown";
	}
}
