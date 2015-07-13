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
<%@page import="java.io.*"%>

<h1>Error</h1>

<% Throwable e = (Throwable) request.getAttribute( "javax.servlet.error.exception" ); %>

<div class="well">
<h2>
	<span class="jira-error-summary">Error while processing page <%= request.getAttribute( "javax.servlet.error.request_uri" ) %></span>
	<br/>
	<small class="jira-error-description"><% if( e != null ){ out.print( e.getMessage() ); } %></small>
</h2>

<%-- <a href="#" class="jiraButton btn btn-danger hidden-phone">Report Error</a> --%>
<button id="button" class="btn btn-danger" onclick="$('#details').show(); $('#button').hide();" style="margin-left: 0px;">details</button>

<div id="details" style="display: none; margin-top: 15px;">
<pre>
<%
if( e != null ){
	Writer result = new StringWriter();
	PrintWriter printWriter = new PrintWriter( result );
	e.printStackTrace( printWriter );
	out.print( result.toString() );
}
%>
</pre>
</div>
</div>