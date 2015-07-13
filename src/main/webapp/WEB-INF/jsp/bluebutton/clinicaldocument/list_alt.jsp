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

<h2>ClinicalDocument List</h2>

<a href="add.html" class="btn btn-default">Add</a>

<div id="error_div" class="alert alert-danger" style="display: none;">
    <%-- div for showing errors, see messager.js.showMessage --%>
</div>

<table class="table table-bordered table-striped table-hover">
    <thead>
        <tr>
            <th>ClinicalDocumentId</th>
            <th>Document</th>
            <th>Name</th>
            <th>Description</th>
            <th>DateUploaded</th>
            <th>Person</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${clinicalDocumentList}" var="clinicalDocument"  varStatus="status">
            <tr>
                <td><a href="edit.html?clinicalDocumentId=${clinicalDocument.clinicalDocumentId}">${clinicalDocument.clinicalDocumentId}</a></td>
                <td>${clinicalDocument.document}</td>
                <td>${clinicalDocument.name}</td>
                <td>${clinicalDocument.description}</td>
                <td>${clinicalDocument.dateUploaded}</td>
                <td>${clinicalDocument.person.personId}</td>
                <td>
                    <a href="edit?clinicalDocumentId=${clinicalDocument.clinicalDocumentId}">edit</a> 
                    <a href="show?clinicalDocumentId=${clinicalDocument.clinicalDocumentId}">view</a>
                    <a href="delete?clinicalDocumentId=${clinicalDocument.clinicalDocumentId}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>