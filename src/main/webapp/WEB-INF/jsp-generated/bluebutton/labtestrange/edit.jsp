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

<c:url value="/labtestrange/save" var="formActionUrl" />
<c:url value="/labtestrange/list" var="cancelUrl" />

<div class="row">
	<div class="col-xs-12 col-sm-8 col-md-6 col-lg-4">
		<form:form method="post" commandName="labTestRange" action="${ formActionUrl }" role="form">
    		<fieldset>
    		
    			<legend>Lab Test Range</legend>
   
    
  				<form:hidden path="labTestRangeId" />
      
    
      			<spring:bind path="sex">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="sex" class="control-label">Sex</label>
    					<form:input path="sex"  class="form-control"/>
    				<form:errors path="sex" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="minAgeYears">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="minAgeYears" class="control-label">Min Age Years</label>
    					<form:input path="minAgeYears"  class="form-control"/>
    				<form:errors path="minAgeYears" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="maxAgeYears">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="maxAgeYears" class="control-label">Max Age Years</label>
    					<form:input path="maxAgeYears"  class="form-control"/>
    				<form:errors path="maxAgeYears" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="minNormal">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="minNormal" class="control-label">Min Normal</label>
    					<form:input path="minNormal"  class="form-control"/>
    				<form:errors path="minNormal" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="maxNormal">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="maxNormal" class="control-label">Max Normal</label>
    					<form:input path="maxNormal"  class="form-control"/>
    				<form:errors path="maxNormal" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
        
    
      			<spring:bind path="labTest.labTestId">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="labTest.labTestId" class="control-label">Lab Test</label>
    					<form:select path="labTest.labTestId" items="${labTestList}" itemValue="labTestId" itemLabel="labTestId" class="form-control"/>
					<form:errors path="labTest" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
       			
    			<input type="submit" value="Save" class="btn btn-primary" />
    			<a class="btn btn-default" href="${ cancelUrl }">Cancel</a>
    			
    		</fieldset>
		</form:form>
	</div>
</div>