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

<c:url value="/labresultsynonym/save" var="formActionUrl" />
<c:url value="/labresultsynonym/list" var="cancelUrl" />

<div class="row">
	<div class="col-xs-12 col-sm-8 col-md-6 col-lg-4">
		<form:form method="post" commandName="labResultSynonym" action="${ formActionUrl }" role="form">
    		<fieldset>
    		
    			<legend>Lab Result Synonym</legend>
   
    
  				<form:hidden path="labResultSynonymId" />
      
    
      			<spring:bind path="officialName">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="officialName" class="control-label">Official Name</label>
    					<form:input path="officialName"  class="form-control"/>
    				<form:errors path="officialName" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="unofficialName">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="unofficialName" class="control-label">Unofficial Name</label>
    					<form:input path="unofficialName"  class="form-control"/>
    				<form:errors path="unofficialName" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="panel">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="panel" class="control-label">Panel</label>
    					<form:input path="panel"  class="form-control"/>
    				<form:errors path="panel" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
       			
    			<input type="submit" value="Save" class="btn btn-primary" />
    			<a class="btn btn-default" href="${ cancelUrl }">Cancel</a>
    			
    		</fieldset>
		</form:form>
	</div>
</div>