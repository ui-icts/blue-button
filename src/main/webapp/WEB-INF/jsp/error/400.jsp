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
<%@ page import="java.io.*" isErrorPage="true" %>

<h1>Error</h1>

<div class="well">
	<h2>
		<span class="jira-error-summary">
			Error while processing page <%= request.getAttribute( "javax.servlet.error.request_uri" ) %>
			<br/>
			<small><%= request.getAttribute( "javax.servlet.error.status_code" ) %> : <%= request.getAttribute( "javax.servlet.error.message" ) %></small>
		</span>
	</h2>
</div>