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

<h2>Lab Tests</h2>

<c:url value="/labtest/add" var="addUrl" />
<a href="${ addUrl }" class="btn btn-default">Add</a>

<div id="error_div" class="alert alert-error" style="display: none;">
    <%-- div for showing errors, see messager.js.showMessage --%>
</div>

<table id="labtestTable" class="table table-bordered table-striped table-hover">
    <%-- table filled by setDataTable call below --%>
</table>

<c:url value="/labtest/datatable" var="datatableUrl">
    <c:param name="display" value="list" />
</c:url>
<script type="text/javascript">
    var columns = [];
    columns.push({ "name": "urls", "title":"", "class":"", "sortable":false, "searchable": false });
    columns.push({ "name": "name", "title":"Name",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "description", "title":"Description",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "units", "title":"Units",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "loincCode", "title":"Loinc Code",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "dateCreated", "title":"Date Created",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "dateUpdated", "title":"Date Updated",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "labTestRanges", "title":"Lab Test Ranges",	"class":"", "sortable":false, "searchable": false });
    var table = setDataTable({
        id : 'labtestTable',
        url : '${ datatableUrl }',
        columns : columns,
        individualSearching : true 
    });
</script><jsp:include page="/WEB-INF/jsp/angular-grid-rest-api.jsp" ><jsp:param name="restApiUrl" value="/api"/><jsp:param name="resourceName" value="labtest"/><jsp:param name="resourceId" value="labTestId"/></jsp:include>