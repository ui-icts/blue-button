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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class CombinedDemographics {

	private List<Demographics> list;
	
	public CombinedDemographics(List<Demographics> list){
		this.list = list;
	}
	
	public Demographics getDempgraphics(){
		Demographics d = new Demographics();
		d.setCombinedSourceList(this.getCombinedSourceList());
		d.setDob(this.getDOB());
		d.setGender(this.getGender());
		d.setMarital_status(this.getMaritalStatus());
		d.setName(this.getName());
		d.setAddress(this.getAddress());
		d.setProvider(this.getProvider());
		return d;
	}
	
	private Provider getProvider() {
		for(Demographics d : this.list){
			if(d.getProvider() != null && d.getProvider().getAddress()!= null && d.getProvider().getAddress().getStreet() != null){return d.getProvider();}
			
		}
		return null;
	}

	private Location getAddress() {
		for(Demographics d : this.list){
			if(d.getAddress() != null && d.getAddress().getStreet() != null){return d.getAddress();}
		}
		return null;
	}

	private Name getName() {
		for(Demographics d : this.list){
			if(d.getName() != null){return d.getName();}
		}
		return null;
	}
	
	

	private String getMaritalStatus() {
		for(Demographics d : this.list){
			if(d.getMarital_status() != null){return d.getMarital_status();}
		}
		return null;
	}

	private String getGender() {
		for(Demographics d : this.list){
			if(d.getGender() != null){return d.getGender();}
		}
		return null;
	}

	private String getDOB() {
		for(Demographics d : this.list){
			if(d.getDob() != null){return d.getDob();}
		}
		return null;
	}
	
	

	private String getCombinedSourceList() {
		Set<String> set = new LinkedHashSet<String>();
		for(Demographics d : this.list){
			if(d.getDataSource() != null){
				if(set.size() == 0){
					set.add(d.getDataSource());
				}
				else if(set.size() ==1 && set.contains("unknown") && !d.getDataSource().equalsIgnoreCase("unknown")){
					set = new LinkedHashSet<String>();
					set.add(d.getDataSource());
				}
				else if(!d.getDataSource().equalsIgnoreCase("unknown")){
					set.add(d.getDataSource());
				}
			}
		} 
		if(set.size() >0){
			return StringUtils.join(set.toArray(), ", ");
		}
		return null;
	}
	
	
}
