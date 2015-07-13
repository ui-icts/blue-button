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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
	<head>
		<tiles:insertAttribute name="head" />
	</head>
	<body>
		<tiles:insertAttribute name="nav" />
		<%-- <tiles:insertAttribute name="branding" /> --%>
		<div class="container-fluid" id="container">
			<%-- <hr/> --%>
			<div class="row" id="body">
				<c:set var="mainSpanClass" value="col-md-12" />
			<%-- <c:if test="${ not isMenuHidden }">
					<c:set var="mainSpanClass" value="col-md-10" />
					<div class="col-md-2 hidden-sm">
						<div class="well sidebar-nav">
							<tiles:insertAttribute name="menu" />
						</div>
					</div>
				</c:if>--%>	
				<div class="${ mainSpanClass }">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
			<div  id="footer">
					<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</body>
</html>