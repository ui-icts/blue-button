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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class Medication extends Entry implements IBlueButtonObject{

	private Product product;
	private Quantity dose_quantity;
	private Quantity rate_quantity;
	private Schedule schedule;
	private Entry precondition;
	private Entry reason;
	private Entry route;
	private final String searchName = "medication";
	
	public Quantity getDose_quantity() {
		return dose_quantity;
	}

	public void setDose_quantity(Quantity dose_quantity) {
		this.dose_quantity = dose_quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Quantity getRate_quantity() {
		return rate_quantity;
	}

	public void setRate_quantity(Quantity rate_quantity) {
		this.rate_quantity = rate_quantity;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Entry getPrecondition() {
		return precondition;
	}

	public void setPrecondition(Entry precondition) {
		this.precondition = precondition;
	}

	public Entry getReason() {
		return reason;
	}

	public void setReason(Entry reason) {
		this.reason = reason;
	}

	public Entry getRoute() {
		return route;
	}

	public void setRoute(Entry route) {
		this.route = route;
	}
	
	@Override
	public String getName(){
		if(this.product != null){return this.product.getDisplayName();}
		return this.name;
	}

	public List<String> getKeywords() {
		List<String> list = new ArrayList<String>();
		if(this.product != null && this.product.getDisplayName() != null){list.add(this.product.getDisplayName());}
		if (this.date_range.getStart() != null || this.date_range.getEnd() != null){
			if(this.date_range.getStartDateDisplay() != null){list.add(this.date_range.getStartDateDisplay());}
			if(this.date_range.getEndDateDisplay() != null){list.add(this.date_range.getEndDateDisplay());}
		}
		if(this.source != null){list.add(this.source);}
		return list;
	}

	public List<Medication> getSubGrid(){
		List<Medication> list = new ArrayList<Medication>();
		if(this.getSubgridInterface() != null){
			for(ISubgroup i : this.getSubgridInterface()){
				list.add((Medication) i);
			}
		}
		return list;
	}
	
	@Override @JsonIgnore
	public Long getSorted() {
		return this.getStartDateInMillis();
	}

	@Override @JsonIgnore
	public List<String> getGroupBy() {
		return (this.product != null) ? new ArrayList<String>(Arrays.asList(this.getName())) : new ArrayList<String>();
	}


	@Override @JsonIgnore
	public String getSearchFieldName() {
		return this.searchName;
	}


	@Override
	public ISubgroup shallowClone() {
		Medication m = new Medication();
		m.setProduct(product);
		m.setDate_range(date_range);
		m.setDose_quantity(dose_quantity);
		m.setReason(reason);
		m.setRoute(route);
		m.setSource(source);
		return m;
	}

	

}
