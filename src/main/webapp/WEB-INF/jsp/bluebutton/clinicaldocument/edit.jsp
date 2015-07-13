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

<form:form method="post" enctype="multipart/form-data" commandName="clinicalDocument" action="save.html">
    <fieldset>
        <legend>Blue Button File</legend>
        
        <form:hidden path="clinicalDocumentId" />
        
        <spring:bind path="document">
	        <div class="form-group ${status.error ? 'has-error' : ''} ">
		        <label for="document" class="control-label">File name</label>
		        <input type="file" name="file" class="form-control"/>
       			<form:errors path="document" class="help-block"/>
	        </div>
        </spring:bind>
        
        <spring:bind path="description">
	        <div class="form-group ${status.error ? 'has-error' : ''} ">
				<label for="description" class="control-label">Description</label>		  
				<form:input path="description"  class="form-control" />
				<form:errors path="description" class="help-block"/>
			</div>        
        </spring:bind>        
        
        <label for="person.personId">Person</label>
        <form:select path="person.personId">
        	<c:forEach items="${ personList }" var="person">
        		<form:option value="${ person.personId }">${ person.lastName }, ${ person.firstName }</form:option>
        	</c:forEach>
        </form:select>
        <br/>
        
        <input type="submit" value="Upload and Confirm" class="btn btn-primary" />
        <a class="btn btn-default" href="list.html">Cancel</a>
        
    </fieldset>
</form:form>