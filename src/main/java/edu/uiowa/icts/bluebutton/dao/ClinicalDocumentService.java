package edu.uiowa.icts.bluebutton.dao;

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
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.uiowa.icts.bluebutton.domain.ClinicalDocument;
import edu.uiowa.icts.bluebutton.json.BlueButtonRecord;
import edu.uiowa.icts.spring.GenericDaoInterface;

/**
 * Generated by Protogen 
 * @since 07/31/2014 09:37:03 CDT
 */
public interface ClinicalDocumentService extends GenericDaoInterface<ClinicalDocument> {

    public ClinicalDocument findById( Integer id );
    
	public List<ClinicalDocument> findByPersonId(Integer personId);

}