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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import edu.uiowa.icts.bluebutton.json.Lab;
import edu.uiowa.icts.bluebutton.json.LabResult;
import edu.uiowa.icts.bluebutton.json.Vitals;
import edu.uiowa.icts.bluebutton.json.VitalsResult;

public class FindLoincCodeList{

	private List<Lab> labList;
	private List<Vitals> vitalsList;
	
	public FindLoincCodeList(List<Lab> labList, List<Vitals> vitalsList){
		this.labList = labList;
		this.vitalsList = vitalsList;
	}
	
	public String getLoincCodeCsvList() {
		Set<String> loincCodeSet = new LinkedHashSet<String>();
		for (Vitals v : this.vitalsList) {
			for (VitalsResult vr : v.getResults()) {
				if (vr.getLoincCodeName() != null){
					loincCodeSet.add(vr.getLoincCodeName());
				}
			}
		}
		for (Lab lab : this.labList) {
			for (LabResult labResult : lab.getResults()) {
				if (labResult.getLoincCodeName() != null){
					loincCodeSet.add(labResult.getLoincCodeName());
				}
			}
		}
		return StringUtils.join(loincCodeSet, ",");
	}
	
	
}
