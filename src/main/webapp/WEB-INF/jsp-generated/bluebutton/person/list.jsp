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

<h2>Persons</h2>

<c:url value="/person/add" var="addUrl" />
<a href="${ addUrl }" class="btn btn-default">Add</a>

<div id="error_div" class="alert alert-error" style="display: none;">
    <%-- div for showing errors, see messager.js.showMessage --%>
</div>

<table id="personTable" class="table table-bordered table-striped table-hover">
    <%-- table filled by setDataTable call below --%>
</table>

<c:url value="/person/datatable" var="datatableUrl">
    <c:param name="display" value="list" />
</c:url>
<script type="text/javascript">
    var columns = [];
    columns.push({ "name": "urls", "title":"", "class":"", "sortable":false, "searchable": false });
    columns.push({ "name": "uuid", "title":"Uuid",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "firstName", "title":"First Name",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "middleName", "title":"Middle Name",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "lastName", "title":"Last Name",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "dateOfBirth", "title":"Date Of Birth",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "addressLineOne", "title":"Address Line One",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "addressLineTwo", "title":"Address Line Two",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "city", "title":"City",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "state", "title":"State",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "zipcode", "title":"Zipcode",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "country", "title":"Country",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "email", "title":"Email",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "registrationDate", "title":"Registration Date",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "firstLogin", "title":"First Login",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "lastLogin", "title":"Last Login",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "lastUpdated", "title":"Last Updated",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "signature", "title":"Signature",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "clinicalDocuments", "title":"Clinical Documents",	"class":"", "sortable":false, "searchable": false });
    var table = setDataTable({
        id : 'personTable',
        url : '${ datatableUrl }',
        columns : columns,
        individualSearching : true 
    });
</script><jsp:include page="/WEB-INF/jsp/angular-grid-rest-api.jsp" ><jsp:param name="restApiUrl" value="/api"/><jsp:param name="resourceName" value="person"/><jsp:param name="resourceId" value="personId"/></jsp:include>