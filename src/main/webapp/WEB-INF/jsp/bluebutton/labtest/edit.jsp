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
<form:form method="post" commandName="labTest" action="save" role="form">
    <fieldset>
    <legend>LabTest</legend>

   
    
  	<form:hidden path="labTestId" />
   
   
    
      <spring:bind path="name">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="name">Name</label>
	 	      <form:input path="name"  class="form-control"/>
	 	      <form:errors path="name" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="description">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="description">Description</label>
	 	      <form:input path="description"  class="form-control"/>
	 	      <form:errors path="description" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="units">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="units">Units</label>
	 	      <form:input path="units"  class="form-control"/>
	 	      <form:errors path="units" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="loincCode">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="loincCode">LoincCode</label>
	 	      <form:input path="loincCode"  class="form-control"/>
	 	      <form:errors path="loincCode" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="dateCreated">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="dateCreated">DateCreated</label>
	 	      <form:input path="dateCreated"  class="form-control dateinput " data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date-autoclose="true"/>
	 	      <form:errors path="dateCreated" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="dateUpdated">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="dateUpdated">DateUpdated</label>
	 	      <form:input path="dateUpdated"  class="form-control dateinput " data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date-autoclose="true"/>
	 	      <form:errors path="dateUpdated" class="help-block"/>
	     </div>
	</spring:bind>	
       <input type="submit" value="Save" class="btn btn-primary" />
    <a class="btn btn-default" href="list">Cancel</a>
    </fieldset>
</form:form>
</div>
</div>