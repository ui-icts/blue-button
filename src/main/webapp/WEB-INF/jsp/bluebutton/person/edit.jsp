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
<div class="row">
  <div class="col-xs-12 col-sm-8 col-md-6 col-lg-4">
<form:form method="post" commandName="person" action="save" role="form">
    <fieldset>
    <legend>Person</legend>

   
    
  	<form:hidden path="personId" />
   
 
   
   
    
      <spring:bind path="firstName">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="firstName">FirstName</label>
	 	      <form:input path="firstName"  class="form-control"/>
	 	      <form:errors path="firstName" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
    <%--   <spring:bind path="middleName">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="middleName">MiddleName</label>
	 	      <form:input path="middleName"  class="form-control"/>
	 	      <form:errors path="middleName" class="help-block"/>
	     </div>
	</spring:bind>	--%>
   
   
    
      <spring:bind path="lastName">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="lastName">LastName</label>
	 	      <form:input path="lastName"  class="form-control"/>
	 	      <form:errors path="lastName" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    <%-- 
      <spring:bind path="dateOfBirth">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="dateOfBirth">DateOfBirth</label>
	 	      <form:input path="dateOfBirth"  class="form-control dateinput "/>
	 	      <form:errors path="dateOfBirth" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="addressLineOne">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="addressLineOne">AddressLineOne</label>
	 	      <form:input path="addressLineOne"  class="form-control"/>
	 	      <form:errors path="addressLineOne" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="addressLineTwo">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="addressLineTwo">AddressLineTwo</label>
	 	      <form:input path="addressLineTwo"  class="form-control"/>
	 	      <form:errors path="addressLineTwo" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="city">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="city">City</label>
	 	      <form:input path="city"  class="form-control"/>
	 	      <form:errors path="city" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="state">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="state">State</label>
	 	      <form:input path="state"  class="form-control"/>
	 	      <form:errors path="state" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="zipcode">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="zipcode">Zipcode</label>
	 	      <form:input path="zipcode"  class="form-control"/>
	 	      <form:errors path="zipcode" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="country">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="country">Country</label>
	 	      <form:input path="country"  class="form-control"/>
	 	      <form:errors path="country" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="email">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="email">Email</label>
	 	      <form:input path="email"  class="form-control"/>
	 	      <form:errors path="email" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="registrationDate">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="registrationDate">RegistrationDate</label>
	 	      <form:input path="registrationDate"  class="form-control dateinput "/>
	 	      <form:errors path="registrationDate" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="firstLogin">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="firstLogin">FirstLogin</label>
	 	      <form:input path="firstLogin"  class="form-control dateinput "/>
	 	      <form:errors path="firstLogin" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="lastLogin">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="lastLogin">LastLogin</label>
	 	      <form:input path="lastLogin"  class="form-control dateinput "/>
	 	      <form:errors path="lastLogin" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="lastUpdated">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="lastUpdated">LastUpdated</label>
	 	      <form:input path="lastUpdated"  class="form-control dateinput "/>
	 	      <form:errors path="lastUpdated" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="signature">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="signature">Signature</label>
	 	      <form:input path="signature"  class="form-control"/>
	 	      <form:errors path="signature" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    --%>
     
  
       <input type="submit" value="Save" class="btn btn-primary" />
    <a class="btn btn-default" href="list">Cancel</a>
    </fieldset>
</form:form>
</div>
</div>