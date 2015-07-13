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

<c:url value="/clinicaldocument/save" var="formActionUrl" />
<c:url value="/clinicaldocument/list" var="cancelUrl" />

<div class="row">
	<div class="col-xs-12 col-sm-8 col-md-6 col-lg-4">
		<form:form method="post" commandName="clinicalDocument" action="${ formActionUrl }" role="form">
    		<fieldset>
    		
    			<legend>Clinical Document</legend>
   
    
  				<form:hidden path="clinicalDocumentId" />
      
    
      			<spring:bind path="document">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="document" class="control-label">Document</label>
    					<form:input path="document"  class="form-control"/>
    				<form:errors path="document" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="name">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="name" class="control-label">Name</label>
    					<form:input path="name"  class="form-control"/>
    				<form:errors path="name" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="description">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="description" class="control-label">Description</label>
    					<form:input path="description"  class="form-control"/>
    				<form:errors path="description" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="dateUploaded">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="dateUploaded" class="control-label">Date Uploaded</label>
    					<form:input path="dateUploaded"  class="form-control dateinput" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date-autoclose="true"/>
    				<form:errors path="dateUploaded" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="source">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="source" class="control-label">Source</label>
    					<form:input path="source"  class="form-control"/>
    				<form:errors path="source" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="parsedJson">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="parsedJson" class="control-label">Parsed Json</label>
    					<form:input path="parsedJson"  class="form-control"/>
    				<form:errors path="parsedJson" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="jsonParserVersion">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="jsonParserVersion" class="control-label">Json Parser Version</label>
    					<form:input path="jsonParserVersion"  class="form-control"/>
    				<form:errors path="jsonParserVersion" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
        
    
      			<spring:bind path="person.personId">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="person.personId" class="control-label">Person</label>
    					<form:select path="person.personId" items="${personList}" itemValue="personId" itemLabel="personId" class="form-control"/>
					<form:errors path="person" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
       			
    			<input type="submit" value="Save" class="btn btn-primary" />
    			<a class="btn btn-default" href="${ cancelUrl }">Cancel</a>
    			
    		</fieldset>
		</form:form>
	</div>
</div>