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
<form:form method="post" commandName="labTestRange" action="save" role="form">
    <fieldset>
    <legend>LabTestRange</legend>

   
    
  	<form:hidden path="labTestRangeId" />
   
   
    
      <spring:bind path="sex">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="sex">Sex</label>
	 	      <form:input path="sex"  class="form-control"/>
	 	      <form:errors path="sex" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="minAgeYears">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="minAgeYears">MinAgeYears</label>
	 	      <form:input path="minAgeYears"  class="form-control"/>
	 	      <form:errors path="minAgeYears" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="maxAgeYears">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="maxAgeYears">MaxAgeYears</label>
	 	      <form:input path="maxAgeYears"  class="form-control"/>
	 	      <form:errors path="maxAgeYears" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="minNormal">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="minNormal">MinNormal</label>
	 	      <form:input path="minNormal"  class="form-control"/>
	 	      <form:errors path="minNormal" class="help-block"/>
	     </div>
	</spring:bind>	
   
   
    
      <spring:bind path="maxNormal">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="maxNormal">MaxNormal</label>
	 	      <form:input path="maxNormal"  class="form-control"/>
	 	      <form:errors path="maxNormal" class="help-block"/>
	     </div>
	</spring:bind>	
   
     
    
      <spring:bind path="labTest.labTestId">        
	     <div class="form-group ${status.error ? 'has-error' : ''}">
	      <label for="labTest.labTestId">LabTest</label>
	 	      <form:select path="labTest.labTestId" items="${labTestList}" itemValue="labTestId" itemLabel="labTestId" class="form-control"/>
	 	      <form:errors path="labTest.labTestId" class="help-block"/>
	     </div>
	</spring:bind>	
       <input type="submit" value="Save" class="btn btn-primary" />
    <a class="btn btn-default" href="list">Cancel</a>
    </fieldset>
</form:form>
</div>
</div>