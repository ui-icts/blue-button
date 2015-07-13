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

<h2>Blue Button Files</h2>

<a href="add.html" class="btn btn-primary">Upload a new BlueButton file</a>

<div id="error_div" class="alert alert-error" style="display: none;">
    <%-- div for showing errors, see messager.js.showMessage --%>
</div>

<table id="clinicaldocumentTable" class="table table-bordered table-striped table-hover">
    <%-- table filled by setDataTable call below --%>
</table>

<c:url value="datatable" var="datatableUrl">
    <c:param name="display" value="list" />
</c:url>
<script type="text/javascript">
    var columns = [];
//    columns.push({ "name": "clinicalDocumentId", "title":"ClinicalDocumentId",	"class":"", "sortable":true, "searchable": true });
 //   columns.push({ "name": "document", "title":"Document",	"class":"", "sortable":true, "searchable": true });
 	columns.push({ "name": "urls", "title":"", "class":"", "sortable":false, "searchable": false });  	
 	columns.push({ "name": "person", "title":"Person",	"class":"", "sortable": true, "searchable": true });
    columns.push({ "name": "name", "title":"Name",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "description", "title":"Description",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "source", "title":"Source",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "dateUploaded", "title":"Date Uploaded",	"class":"", "sortable":true, "searchable": true });
    columns.push({ "name": "jsonParserVersion", "title":"Parser Version",	"class":"", "sortable":true, "searchable": true });
    
    var table = setDataTable({
        id : 'clinicaldocumentTable',
        url : '${ datatableUrl }',
        columns : columns,
        individualSearching : true 
    });
</script>