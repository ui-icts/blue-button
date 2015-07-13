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
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/include.jsp"  %>

<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<meta name="description" content="Blue Button" />
<meta name="keywords" content="Blue Button" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-site-verification" content="MqQwB2T_SQgk924Mg-Dq-UuGCx-xWoX2r19oJ6CndQo" />

<link rel="shortcut icon" href="<c:url value="/resources/favicon.ico" />" type="image/vnd.microsoft.icon" />

<title>${ pageTitle }<c:if test="${ not empty pageTitle }"> : </c:if>Blue Button</title>

<!-- jquery -->
<script type="text/javascript" src="<c:url value="/resources/jquery/dist/jquery.min.js"/>"></script>

<!-- bootstrap with paypal accessibility and datepicker-->
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/bootstrap/dist/css/bootstrap.min.css"/>" />
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/bootstrap-datepicker/dist/css/bootstrap-datepicker3.standalone.css"/>" />
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/bootstrapaccessibilityplugin/plugins/css/bootstrap-accessibility.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"/>"></script>
<!-- moved to footer so it will run automatically 
<script type="text/javascript" src="<c:url value="/resources/bootstrapaccessibilityplugin/plugins/js/bootstrap-accessibility.min.js"/>"></script>
-->

<!-- data tables and responsive data tables --->
<script type="text/javascript" src="<c:url value="/resources/datatables/media/js/jquery.dataTables.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/datatables-responsive/js/dataTables.responsive.js" />"></script>
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/datatables/media/css/jquery.dataTables.min.css" />" />
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/datatables-responsive/css/dataTables.responsive.css" />" />

<!-- angular js -->
<script type="text/javascript" src="<c:url value="/resources/angular/angular.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/angular-ui-grid/ui-grid.min.js" />"></script>
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/angular-ui-grid/ui-grid.min.css"/>" />
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/angular-advanced-searchbox/dist/angular-advanced-searchbox.min.css"/>">
<script type = "text/javascript" src="<c:url value="/resources/angular-advanced-searchbox/dist/angular-advanced-searchbox-tpls.min.js" />"></script>

<!-- D3 stuff -->
<script type="text/javascript" src="<c:url value="/resources/d3/d3.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/d3-timeline.js" />"></script>


<!-- no ui slider and qtip2 -->
<script type="text/javascript" src="<c:url value="/resources/qtip2/jquery.qtip.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/nouislider/distribute/jquery.nouislider.all.min.js" />"></script>
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/qtip2/jquery.qtip.min.css"/>" />
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/nouislider/distribute/jquery.nouislider.min.css"/>" />
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/nouislider/distribute/jquery.nouislider.pips.min.css"/>" />

<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/css/d3.css"/>" />

<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/css/blue-button-color-coding.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/bluebutton/js/ejs.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bluebutton/js/isoLangs.js" />"></script> 
<script type="text/javascript" src="<c:url value="/resources/bluebutton/js/bluebutton-build.js" />"></script>

<!-- application css and js -->
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/css/layout.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/js/utils.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/messager.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/init.js" />"></script>

<script type="text/javascript">
	$(function(){
		intializePage(); // see resources/js/init.js 
	});
</script>