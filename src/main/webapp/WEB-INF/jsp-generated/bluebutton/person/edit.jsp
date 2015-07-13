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

<c:url value="/person/save" var="formActionUrl" />
<c:url value="/person/list" var="cancelUrl" />

<div class="row">
	<div class="col-xs-12 col-sm-8 col-md-6 col-lg-4">
		<form:form method="post" commandName="person" action="${ formActionUrl }" role="form">
    		<fieldset>
    		
    			<legend>Person</legend>
   
    
  				<form:hidden path="personId" />
      
    
      			<spring:bind path="uuid">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="uuid" class="control-label">Uuid</label>
    					<form:input path="uuid"  class="form-control"/>
    				<form:errors path="uuid" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="firstName">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="firstName" class="control-label">First Name</label>
    					<form:input path="firstName"  class="form-control"/>
    				<form:errors path="firstName" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="middleName">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="middleName" class="control-label">Middle Name</label>
    					<form:input path="middleName"  class="form-control"/>
    				<form:errors path="middleName" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="lastName">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="lastName" class="control-label">Last Name</label>
    					<form:input path="lastName"  class="form-control"/>
    				<form:errors path="lastName" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="dateOfBirth">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="dateOfBirth" class="control-label">Date Of Birth</label>
    					<form:input path="dateOfBirth"  class="form-control dateinput" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date-autoclose="true"/>
    				<form:errors path="dateOfBirth" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="addressLineOne">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="addressLineOne" class="control-label">Address Line One</label>
    					<form:input path="addressLineOne"  class="form-control"/>
    				<form:errors path="addressLineOne" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="addressLineTwo">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="addressLineTwo" class="control-label">Address Line Two</label>
    					<form:input path="addressLineTwo"  class="form-control"/>
    				<form:errors path="addressLineTwo" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="city">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="city" class="control-label">City</label>
    					<form:input path="city"  class="form-control"/>
    				<form:errors path="city" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="state">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="state" class="control-label">State</label>
    					<form:input path="state"  class="form-control"/>
    				<form:errors path="state" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="zipcode">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="zipcode" class="control-label">Zipcode</label>
    					<form:input path="zipcode"  class="form-control"/>
    				<form:errors path="zipcode" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="country">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="country" class="control-label">Country</label>
    					<form:input path="country"  class="form-control"/>
    				<form:errors path="country" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="email">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="email" class="control-label">Email</label>
    					<form:input path="email"  class="form-control"/>
    				<form:errors path="email" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="registrationDate">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="registrationDate" class="control-label">Registration Date</label>
    					<form:input path="registrationDate"  class="form-control dateinput" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date-autoclose="true"/>
    				<form:errors path="registrationDate" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="firstLogin">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="firstLogin" class="control-label">First Login</label>
    					<form:input path="firstLogin"  class="form-control dateinput" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date-autoclose="true"/>
    				<form:errors path="firstLogin" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="lastLogin">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="lastLogin" class="control-label">Last Login</label>
    					<form:input path="lastLogin"  class="form-control dateinput" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date-autoclose="true"/>
    				<form:errors path="lastLogin" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="lastUpdated">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="lastUpdated" class="control-label">Last Updated</label>
    					<form:input path="lastUpdated"  class="form-control dateinput" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date-autoclose="true"/>
    				<form:errors path="lastUpdated" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
      			<spring:bind path="signature">        
	     			<div class="form-group ${status.error ? 'has-error' : ''}">
	      				<label for="signature" class="control-label">Signature</label>
    					<form:input path="signature"  class="form-control"/>
    				<form:errors path="signature" class="help-block" element="span" />
    	     			</div>
				</spring:bind>	
      
    
         			
    			<input type="submit" value="Save" class="btn btn-primary" />
    			<a class="btn btn-default" href="${ cancelUrl }">Cancel</a>
    			
    		</fieldset>
		</form:form>
	</div>
</div>