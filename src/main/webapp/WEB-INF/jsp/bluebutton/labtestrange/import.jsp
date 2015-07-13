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

<form:form method="post" commandName="labtest" action="import" enctype="multipart/form-data">
    <fieldset>
        <legend>Import Lab Test Range CSV file</legend>
     
	        <div class="form-group ${status.error ? 'has-error' : ''} ">
		        <label for="fileName" class="control-label">Upload File</label>
		        <input type="file" name="file" class="form-control" required/>
       			<form:errors path="fileName" class="help-block"/>
	        </div>
          
        
        <input type="submit" value="Upload" class="btn btn-primary" />
        <a class="btn btn-default" href="list">Cancel</a>
    </fieldset>
</form:form>