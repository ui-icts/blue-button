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

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unused JSON data
public class Immunization  extends Entry implements IBlueButtonObject{

	private Product product;
	private final String searchFieldName = "immunization";
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String getName(){
		return (this.product != null) ? this.product.getDisplayName() : null;
	}
	
//	@JsonIgnore
//	public JSONObject getJson() {
//		JSONObject json = new JSONObject();
//		json.put("dateInMillis", this.getDateInMillis());
//		json.put("date", this.getDisplayDate());
//		json.put("source", this.source);
//		if (this.product != null){
//			json.put("name", this.product.getDisplayName());
//		}
//		json.put("keywords", this.getKeywords());
//		return json;
//	}
	
	@Override
	public List<String> getKeywords() {
		List<String> list = new ArrayList<String>();
		if (this.product != null && this.product.getDisplayName() != null){list.add(this.product.getDisplayName());}
		if(this.getDisplayDate() != null){list.add(this.getDisplayDate());}
		if(this.source != null){list.add(this.source);}
		return list;
	}
	
	public List<Immunization> getSubGrid(){
		List<Immunization> list = new ArrayList<Immunization>();
		if(this.getSubgridInterface() != null){
			for(ISubgroup i : this.getSubgridInterface()){
				list.add((Immunization) i);
			}
		}
		return list;
	}
	
	@Override
	public List<String> getGroupBy() {
		return (this.product != null) ? new ArrayList<String>(Arrays.asList(this.product.getDisplayName())): new ArrayList<String>();
	}
	@Override
	public String getSearchFieldName() {
		return this.searchFieldName;
	}
	@Override
	public ISubgroup shallowClone() {
		Immunization i = new Immunization();
		i.setProduct(product);
		i.setDate(this.getDate());
		i.setSource(source);
		return (ISubgroup) i;
	}
	@Override
	public Long getSorted() {
		return this.getDateInMillis();
	}	

}
