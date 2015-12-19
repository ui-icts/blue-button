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

<nav class="navbar navbar-default navbar-fixed-bottom hidden-xs" role="navigation" style="background-color: white;">
	<hr style="margin: 0px;" />
	<div style="padding: 15px;">
		<ul class="nav navbar-nav hidden-phone" style="margin-right: 15px;">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"> Sites <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a target="_blank" title="link opens in new tab/window" href="http://www.icts.uiowa.edu">ICTS Home </a></li>
					<li><a target="_blank" title="link opens in new tab/window" href="http://www.uiowa.edu">University of Iowa Home</a></li>
					<li class="divider"></li>
					<li><a target="_blank" title="link opens in new tab/window" href="http://www.nih.gov">National Institutes of Health (NIH)</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"> Support <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<%--
					<li><a  href="#" class="jiraButton">Provide Feedback</a></li>
					--%>
					<li><a target="_blank" title="I-CART" href="https://i-cart.icts.uiowa.edu/">Request ICTS Service</a></li>
					<li><a target="_blank" title="link opens in new tab/window" href="http://www.icts.uiowa.edu/wiki">ICTS Wiki</a></li>
					<li><a target="_blank" title="link opens in new tab/window" href="https://www.icts.uiowa.edu/jira">ICTS Support </a></li>
					<li class="divider"></li>
					<li><a target="_blank" title="link opens in new tab/window" href="http://its.uiowa.edu/">ITS Support</a></li>
				</ul>
			</li>
		</ul>
		<a target="_blank" title="link opens in new tab/window" href="http://www.icts.uiowa.edu/">Institute for Clinical and Translational Science</a> | <a target="_blank" title="link opens in new tab/window" href="http://www.uiowa.edu">University of Iowa</a>
		<br /> 
		Supported in part by NIH grant U54 TR001356
	</div>
</nav>

<script type="text/javascript" src="<c:url value="/resources/bootstrapaccessibilityplugin/plugins/js/bootstrap-accessibility.min.js"/>"></script>


<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-18763416-12', 'auto');
  ga('send', 'pageview');

</script>