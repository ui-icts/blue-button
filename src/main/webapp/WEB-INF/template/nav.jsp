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
<%@ include file="/WEB-INF/include.jsp"%>

<div class="navbar navbar-inverse navbar-static-top" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/" />">Blue Button</a>
		
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
<%-- 			<sec:authorize access="hasAnyRole('ROLE_BLUEBUTTON_ADMIN','ROLE_ICTS-DEVELOPERS')">
 --%>				<ul class="nav navbar-nav">
				    <li><a href="<c:url value="/person/" />" >People</a></li>
				    <li><a href="<c:url value="/clinicaldocument/" />" >Uploaded Files</a></li>
				     <li><a href="<c:url value="/labresultsynonym/" />" >Lab Result Synonyms</a></li>
				     <li><a href="<c:url value="/labtest/" />" >Lab Tests</a></li>
				     <li><a href="<c:url value="/labtestrange/" />" >Lab Tests Ranges</a></li>
				</ul>
<%-- 			</sec:authorize>
 --%>			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Welcome <sec:authorize access="isAuthenticated()">${ username } </sec:authorize><span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu">
						<sec:authorize access="!isAuthenticated()">
							<li><a href="<c:url value="/login" />">Login</a></li>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
							<li><a href="<c:url value="/logout" />">Logout</a></li>
						</sec:authorize>
					</ul>
				</li>
			</ul>
		</div>

	</div> 
</div>

