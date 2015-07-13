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

public class AddSearchProperties {

	private JSONArray array;
	private String name;
	
	public AddSearchProperties(JSONArray array, String name) {
		this.array = array;
		this.name = name;
	}

	public JSONArray getJsonArray() {
		int counter = 0;
		JSONArray array = new JSONArray();
		for(int x = 0; x<this.array.length(); x++){
			JSONObject json = this.array.getJSONObject(x);
			if(json.has("subGrid")){
				json.put("id", this.name+"-"+counter);
				JSONArray subGrid = json.getJSONArray("subGrid");
				for(int y=0; y<subGrid.length(); y++){
					JSONObject childJson = subGrid.getJSONObject(y);
					childJson.put("parent", json.get("id"));
					childJson.put("id", this.name+"-"+counter++);
				}
			}
			else{
				json.put("id", this.name+"-"+counter++);
			}
			array.put(json);
		}
		return array;	}

	
}
