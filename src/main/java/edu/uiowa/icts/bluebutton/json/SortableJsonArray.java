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

import org.json.JSONArray;
import org.json.JSONObject;

public class SortableJsonArray extends JSONArray{

	private JSONArray JSONArray;
	
	public SortableJsonArray(JSONArray jsonArray) {
		this.JSONArray = jsonArray;
	}
	

	//Will return a sorted JSONArray
	public void sortByLong(String sortBy){
			JSONArray array= this.JSONArray;
			JSONArray newArray = new JSONArray();
			for(int i=0; i< array.length(); i++){
				JSONObject json = array.getJSONObject(i);
				boolean added = false;
				if(newArray.length()>0){
					if(json.has(sortBy)){
						for(int x=0; x<newArray.length(); x++){
							if(((JSONObject) newArray.get(x)).has(sortBy)){
								if(((JSONObject) newArray.get(x)).getLong(sortBy) < json.getLong(sortBy)){
									addWithPushBack(newArray,json,x);
									added=true;
									break;
								}
							}else{addWithPushBack(newArray,json,x); added=true; break;}
						}
					}else{newArray.put(json); added=true;}
				}else{
					newArray.put(json);
					added=true;
				}
				
				if(!added){newArray.put(json);}
											
			}
			this.JSONArray= newArray;
		}
		
	public void sortByInt(String sortBy){
		JSONArray array= this.JSONArray;
		JSONArray newArray = new JSONArray();
		for(int i=0; i< array.length(); i++){
			JSONObject json = array.getJSONObject(i);
			boolean added = false;
			if(newArray.length()>0){
				if(json.has(sortBy)){
					for(int x=0; x<newArray.length(); x++){
						if(((JSONObject) newArray.get(x)).has(sortBy)){
							if(((JSONObject) newArray.get(x)).getInt(sortBy) < json.getInt(sortBy)){
								addWithPushBack(newArray,json,x);
								added=true;
								break;
							}
						}else{addWithPushBack(newArray,json,x); added=true; break;}
					}
				}else{newArray.put(json); added=true;}
			}else{
				newArray.put(json);
				added=true;
			}
			
			if(!added){newArray.put(json);}
										
		}
		this.JSONArray= newArray;
	}
	
	
	//Will add jsonObject to JSONArray and push back the next values	
	private JSONArray addWithPushBack(JSONArray array, JSONObject json, int index){
		int l = array.length();
		for(int i=index; i<l; i++){
				JSONObject movedJson = array.getJSONObject(i);
				array.put(i, json);
				json=movedJson;
		} 
		array.put(json);
		return array; 
	} 

public JSONArray getJSONArray() {
	return JSONArray;
}

public void setJSONArray(JSONArray jSONArray) {
	this.JSONArray = jSONArray;
}
	
}
