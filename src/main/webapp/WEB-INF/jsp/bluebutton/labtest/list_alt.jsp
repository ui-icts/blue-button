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

<h2>LabTest List</h2>

<a href="add" class="btn btn-default">Add</a>

<div id="error_div" class="alert alert-error" style="display: none;">
    <%-- div for showing errors, see messager.js.showMessage --%>
</div>

<table class="table table-bordered table-striped table-hover table-datatable">
    <thead>
        <tr>
            <th>LabTestId</th>
            <th>Name</th>
            <th>Description</th>
            <th>Units</th>
            <th>LoincCode</th>
            <th>DateCreated</th>
            <th>DateUpdated</th>
            <th>LabTestRanges</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${labTestList}" var="labTest"  varStatus="status">
            <tr>
                <td><a href="edit?labTestId=${labTest.labTestId}">${labTest.labTestId}</a></td>
                <td>${labTest.name}</td>
                <td>${labTest.description}</td>
                <td>${labTest.units}</td>
                <td>${labTest.loincCode}</td>
                <td>${labTest.dateCreated}</td>
                <td>${labTest.dateUpdated}</td>
                <td>labTestRanges</td>
                <td>
                    <a href="edit?labTestId=${labTest.labTestId}">edit</a> 
                    <a href="show?labTestId=${labTest.labTestId}">view</a>
                    <a href="delete?labTestId=${labTest.labTestId}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>