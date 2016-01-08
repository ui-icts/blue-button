package edu.uiowa.icts.bluebutton.json.view;

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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import edu.uiowa.icts.bluebutton.json.Encounter;
public class EncountersMetaFinder {

	private List<Encounter> list;
	
	public EncountersMetaFinder(List<Encounter> list){
		this.list = list;
	}

	public String getRecentEncounters() {
		for(Encounter e : this.list){
			if(e.getDateInMillis() != null && e.getDateInMillis() >= new DateTime().getMillis() -31556952000L){
				if(this.getOrganizations() != null){
					return "had medical encounters, from: ".concat(this.getOrganizations());
				}else{return "had medical encounters";}
			}
		}
		return "did not have any medical encounters";
	}

	public String getOrganizations() {
		TreeSet<String> orgSet = new TreeSet<String>();
		for(Encounter e : this.list){
			if(e.getLocation() != null && e.getLocation().getOrganization() != null){
				orgSet.add(e.getLocation().getOrganization());
			}
		}
		if(orgSet.size() > 0){return StringUtils.join(orgSet, ", ");}
		return null;
	}
	
}
