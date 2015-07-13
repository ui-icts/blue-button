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
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<noscript>
	<div class="alert alert-danger">Please enable javascript, it is required for this application to function properly.</div>
</noscript>
<div class="row">
  <div class="col-md-6">
<div class="panel panel-default">
	<div class="panel-heading">
		<h1 class="panel-title">Welcome to Blue Button by ICTS</h1>
	</div>
	<div class="panel-body"><p>This application takes a Blue Button personal health record as input (C-CDA.xml format), parses the data into JavaScript with <a href="https://github.com/blue-button/bluebutton.js/" target="_new">BlueButton.js</a>, and uses <a href="http://d3js.org/" target="_new">D3.js</a> to display interactive charts. 
	In addition, categories and normal ranges for lab results are mapped from data provided by <a href="https://loinc.org/downloads">LOINC.org</a>. This web application is designed to be used by medical professionals when visiting with their patients. The status of this application is <strong>demo</strong> and, therefore, all data is fictitious. 
	</p>
	<ul class="list-group"><h4 class="list-group-item-heading">HealthVault examples</h4>
	  <li class="list-group-item"><a href="<c:url value="/clinicaldocument/Jane Doe_healthvault.xml/file" />">Jane Doe with lab results</a> (August 2014)</li>
	  <li class="list-group-item"><a href="<c:url value="/clinicaldocument/Jane Doe_HV_CCD xml 06.03.14.xml/file" />">Jane Doe with lab results <strong>outside normal ranges</strong>, allergies, and immunizations</a> (June 2014)</li>
	</ul>
	<ul class="list-group"><h4 class="list-group-item-heading">UIHC MyChart Lucy examples</h4>
	  <p>A Lucy record is a portable copy of your allergies, medications, current health issues, procedures, test results, and immunizations from MyChart.</p>
	  <li class="list-group-item"><a href="<c:url value="/clinicaldocument/john_doe_uihc_my_chart.xml/file" />">John Doe with problems, allergies, and lab results</a> (March 2015)</li>
	</ul>
	<ul class="list-group"><h4 class="list-group-item-heading">Other examples</h4>
	  <li class="list-group-item"><a href="<c:url value="/clinicaldocument/nist_expected_ccda.xml/file" />">NIST.gov example with a full row of <strong>medications</strong> data</a></li>
	  <li class="list-group-item"><a href="<c:url value="/clinicaldocument/BlueButton-Sampledata.xml/file" />">Blue Button sample data with a little bit of everything</a></li>
	  <li class="list-group-item"><a href="<c:url value="/clinicaldocument/Vitera_CCDA_SMART_Sample.xml/file" />">Vitera example with lab results <strong>outside normal ranges</strong>, allergies, and <strong>medications</strong></a></li>
	</ul>
	<ul class="list-group"><h4 class="list-group-item-heading"><a href="http://onc-emerge.org/emerge/index.action" target="_new">EMERGE Data</a></h4>
	  <li class="list-group-item"><a href="<c:url value="/clinicaldocument/Patient-125.xml/file" />">5 year old with <strong>immunizations</strong></a></li>
	  <li class="list-group-item"><a href="<c:url value="/clinicaldocument/Patient-20.xml/file" />">71 year old female with <strong>vitals</strong></a></li>
	  <li class="list-group-item"><a href="<c:url value="/clinicaldocument/Patient-14.xml/file" />">25 year old female prenatal</a></li>
	</ul> 
	</div>
</div>

</div>
  <div class="col-md-6">
<div class="panel panel-default">
	<div class="panel-heading">
		<h1 class="panel-title">Types of Charts</h1>
	</div>
	<div class="panel-body">
	<h4>Force Chart</h4>
	<p>Displays clusters of data points (e.g., encounters, labs, medications, etc.) grouped together by the number of days between their occurrence.
	</p>
	<h4>Timeline Charts</h4>
	<p>The header section for each record features a condensed timeline and a date range slider for customizing chart timespans. 
	In addition, each record includes a second, expanded timeline that displays the full history for all event types.
	Lastly, each event type (e.g., procedure, lab, medication) has its own tab with a timeline along with a text list of all events since some are missing dates. 
	</p>
	<h4>Line Charts for Results (Vitals and Labs)</h4>
	<p>Results from vitals and labs are displayed as line charts grouped by units. Within each chart, groups of data points (e.g., systolic, diastolic) are uniquely colored. 
	<strong>Mouse over a data point</strong> to view its date and value along with the mean, range, standard deviation, and normal range (if we have it) for the data set.
	Data points inside normal ranges and two standard deviations have solid colors whereas data points <strong>outside of normal ranges and/or two standard deviations have animated colors</strong>. A 20 data point simple moving average line is displayed in black whereas the primary line is blue.</p>
	<h4>Interactive Features</h4>
	<ul class="list-group">
	  <li class="list-group-item"><strong>Date range slider</strong> that updates all charts</li>
	  <li class="list-group-item"><strong>Mouse over a data point</strong> (circles and rectangles) to view more detail</li>
	  <li class="list-group-item"><strong>Dynamic data grids</strong> for several sections that allow users to search and sort their data</li>
	  <li class="list-group-item">On line charts, <strong>customize results by selecting units and result type (e.g., glucose)</strong></li>
	  <li class="list-group-item">On the force chart, condensed timeline, and expanded timeline, <strong>click on a data point</strong> to view all related data points</li>
	</ul> 
	</div>
	
</div>

</div>
</div>