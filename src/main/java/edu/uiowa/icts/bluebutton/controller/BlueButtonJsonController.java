package edu.uiowa.icts.bluebutton.controller;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uiowa.icts.bluebutton.domain.ClinicalDocument;
import edu.uiowa.icts.bluebutton.json.BlueButtonRecord;
import edu.uiowa.icts.bluebutton.json.BlueButtonRecords;
import edu.uiowa.icts.bluebutton.json.CombineBlueButtonRecords;
import edu.uiowa.icts.bluebutton.json.CombinedLoincCodeList;
import edu.uiowa.icts.bluebutton.json.Demographics;
import edu.uiowa.icts.bluebutton.json.LoincCode;
import edu.uiowa.icts.bluebutton.json.view.BlueButtonRecordView;
import edu.uiowa.icts.bluebutton.json.view.FindLoincCodeList;

/**
 * Generated by Protogen 
 * @since 07/31/2014 09:37:03 CDT
 */
@RestController
@RequestMapping( "/blue-button/*" )
public class BlueButtonJsonController extends AbstractBluebuttonController {

	@RequestMapping( value = { "", "/" } , method = RequestMethod.POST, produces = "application/json" )
	public BlueButtonRecordView index(@RequestBody BlueButtonRecords records) throws IOException {
		//Merge all blue-button records
		CombineBlueButtonRecords cbbr = new CombineBlueButtonRecords(records.getRecords(), records.getStartDateInMillis(), records.getEndDateInMillis(), this.bluebuttonDaoService.getLabResultSynonymService());
    	BlueButtonRecord bbr = cbbr.getMasterBlueButtonRecord();
    	
		return new BlueButtonRecordView(bbr, this.getLoincHash(bbr));
	}
	
	@RequestMapping(value = "byPerson", method = RequestMethod.POST, produces = "application/json" )
	public BlueButtonRecordView getById(@RequestParam("personId") Integer personId, @RequestParam("startDateInMillis") Long startDateInMillis, @RequestParam("endDateInMillis") Long endDateInMillis) throws JsonParseException, JsonMappingException, IOException{
		
		List<ClinicalDocument> cList = this.bluebuttonDaoService.getClinicalDocumentService().findByPersonId(personId);
		List<BlueButtonRecord> bList = new ArrayList<BlueButtonRecord>();
		for(ClinicalDocument cd : cList){
			//convert blue button byte[] to pojo
			if(cd.getParsedJson() != null){
				ObjectMapper objectMapper = new ObjectMapper();
				BlueButtonRecord bbr = (BlueButtonRecord) objectMapper.readValue(cd.getParsedJson(), BlueButtonRecord.class);
				if(cd.getSource() != null){ bbr.setSource(cd.getSource());}
				bList.add(bbr);
			}
		}
		//Merge all blue-button records
		CombineBlueButtonRecords cbbr = new CombineBlueButtonRecords(bList, (startDateInMillis!=-1) ? startDateInMillis : null, (endDateInMillis != -1) ? endDateInMillis : null, this.bluebuttonDaoService.getLabResultSynonymService());
		BlueButtonRecord bbr = cbbr.getMasterBlueButtonRecord();	
		return new BlueButtonRecordView(bbr, this.getLoincHash(bbr));
	}
	
	private HashMap<String, LoincCode> getLoincHash(BlueButtonRecord bbr){
		//Find csv list of loinc codes from bluebutton labs and vitals
		String csvLonicCodeList = new FindLoincCodeList(bbr.getLabs(), bbr.getVitals()).getLoincCodeCsvList();
		//get a list of loinc codes from from database using csv list
		List<LoincCode>  loincCategoryList= this.bluebuttonDaoService.getLoincCodeCategoryService().findByLoincCodes(csvLonicCodeList);
		Demographics d = bbr.getDemographics();
		//get loinc code normal ranges from loinc code range database taking gender and age into account
		List<LoincCode>  loincRangeList=this.bluebuttonDaoService.getLabTestService().getRangesByGenderAgeAndLoincCodes((d != null) ? d.getGender() : null, (d != null) ? d.getAge() : null, csvLonicCodeList);
		//return hash of loinc codes and loinc code objects with there correct normal ranges
		return new CombinedLoincCodeList(loincCategoryList,loincRangeList).getCombinedHash();
	}
	
	
	
}