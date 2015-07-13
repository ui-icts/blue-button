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

<c:url value="/loinccodecategory/save" var="formActionUrl" />
<c:url value="/loinccodecategory/list" var="cancelUrl" />

<div class="row">
	<div class="col-xs-12 col-sm-8 col-md-6 col-lg-4">
		<form:form method="post" commandName="loincCodeCategory" action="${ formActionUrl }" role="form">
    		<fieldset>
    		
    			<legend>Loinc Code Category</legend>
   
    
  				<form:hidden path="id" />
      
    
      			<spring:bind path="loincCode">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="loincCode" class="control-label">Loinc Code</label>
    					<form:input path="loincCode"  class="form-control"/>
    				<form:errors path="loincCode" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="name">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="name" class="control-label">Name</label>
    					<form:input path="name"  class="form-control"/>
    				<form:errors path="name" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="rootCategoryName">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="rootCategoryName" class="control-label">Root Category Name</label>
    					<form:input path="rootCategoryName"  class="form-control"/>
    				<form:errors path="rootCategoryName" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="subrootCategoryName">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="subrootCategoryName" class="control-label">Subroot Category Name</label>
    					<form:input path="subrootCategoryName"  class="form-control"/>
    				<form:errors path="subrootCategoryName" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
       			
    			<input type="submit" value="Save" class="btn btn-primary" />
    			<a class="btn btn-default" href="${ cancelUrl }">Cancel</a>
    			
    		</fieldset>
		</form:form>
	</div>
</div>