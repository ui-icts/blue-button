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
<div ng-controller="MainController">

<form:form method="post" commandName="clinicalDocument" action="confirm">
    <fieldset>
        <legend>Blue Button File</legend>
        
        <form:hidden path="clinicalDocumentId" />
         <form:textarea path="parsedJson" ng-model="parsedJson" style="display: none;"/>
 
        
        <spring:bind path="source">
	        <div class="form-group ${status.error ? 'has-error' : ''} ">
				<label for="source" class="control-label">Source</label>		  
				<form:input ng-model="source" path="source"  class="form-control" />
				<form:errors path="source" class="help-block"/>
			</div>        
        </spring:bind>
        
        <br/>
        
        <input type="submit" value="Confirm and View" class="btn btn-primary" />
        <a class="btn btn-default" href="list.html">Cancel</a>
        
    </fieldset>
</form:form>
<!-- Injected patient data -->
	   

</div>
<script id="xmlBBData" type="text/html">${clinicalDocument.xml}</script>
<script>

var app = angular.module('app', []).controller('MainController', ['$scope',  function ($scope) { 
	var bb =  BlueButton($("script#xmlBBData").text());
	$scope.parsedJson = JSON.stringify(bb.data);
	
	var d = bb.data.demographics;
	//this.getDemographics().getProvider().getOrganization();
	$scope.source = '${clinicalDocument.source}';
	if(!$scope.source && d && d.provider){
		$scope.source = d.provider.organization;
	}
	
}]);
angular.bootstrap(document, ['app']);
</script>