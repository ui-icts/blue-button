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

<nav id="scroll-spy-view" class="navbar navbar-default navbar-fixed-top" role="navigation" >
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/" />">Blue Button</a>
			<ul class="nav navbar-nav collapse navbar-collapse" id="blueButtonTabs">
				
				 <li ><a  href="#dateRangeSelector"  >Date Range</a></li>
				  <li ><a  href="#searchInput"  >Search</a></li>
				 
			            <li ><a id="summaryTimelineDropDown" href="#summaryTimeline"  >Summary</a></li>
				        <li id="medicationsTab" ><a id="medicationsDropDown" href="#medicationsChartDisplay"  >Medications</a></li>
				        <li id="labResultsGridTab"  ><a id="labsDropDown" href="#labResultsGridChartDisplay"  >Labs</a></li>				     
                        <li id="vitalsGridTab"><a id="vitalsDropDown" href="#vitalsGridChartDisplay"  >Vitals</a></li>
                        <li id="problemsTab"><a id="problemsDropDown" href="#problemsChartDisplay"  >Problems</a></li>
                        <li id="allergiesTab"><a id="allergiesDropDown" href="#allergiesChartDisplay"  >Allergies</a></li>
                        <li id="immunizationsTab"><a id="immunizationsDropDown" href="#immunizationsChartDisplay"  >Immunizations</a></li>
 					    <li id="proceduresTab"><a id="proceduresDropDown" href="#proceduresChartDisplay"  >Procedures</a></li>
 					    <li id="encountersTab"><a id="encountersDropDown" href="#encountersChartDisplay"   >Encounters</a></li>
 					    <li id="mapTab"><a id="mapDropDown" href="#map">Map</a></li>
 					    <li id="forcechartTab"><a id="forcechartDropDown" href="#forceChart"  >Force Chart</a></li>
                </ul>
		</div>
	<%-- 	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	
			<ul class="nav navbar-nav navbar-right">
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
		</div>--%>
</nav>
