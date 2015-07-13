<%--
  #%L
  blue-button Spring MVC Web App
  %%
  Copyright (C) 2014 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
  %%
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
       http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  #L%
  --%>
<%@ include file="/WEB-INF/include.jsp"  %>

<div id="mainmenu" class="list-group">
	<a class="list-group-item" href="<c:url value="/person/list" />" >Persons</a>
	<a class="list-group-item" href="<c:url value="/clinicaldocument/list" />" >Clinical Documents</a>
	<a class="list-group-item" href="<c:url value="/labtest/list" />" >Lab Tests</a>
	<a class="list-group-item" href="<c:url value="/labtestrange/list" />" >Lab Test Ranges</a>
	<a class="list-group-item" href="<c:url value="/loinccodecategory/list" />" >Loinc Code Categorys</a>
	<a class="list-group-item" href="<c:url value="/labresultsynonym/list" />" >Lab Result Synonyms</a>
</div>
