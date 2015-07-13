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

<h2>Person List</h2>

<a href="add" class="btn btn-default">Add</a>

<div id="error_div" class="alert alert-error" style="display: none;">
    <%-- div for showing errors, see messager.js.showMessage --%>
</div>

<table class="table table-bordered table-striped table-hover table-datatable">
    <thead>
        <tr>
            <th>PersonId</th>
            <th>Uuid</th>
            <th>FirstName</th>
            <th>MiddleName</th>
            <th>LastName</th>
            <th>DateOfBirth</th>
            <th>AddressLineOne</th>
            <th>AddressLineTwo</th>
            <th>City</th>
            <th>State</th>
            <th>Zipcode</th>
            <th>Country</th>
            <th>Email</th>
            <th>RegistrationDate</th>
            <th>FirstLogin</th>
            <th>LastLogin</th>
            <th>LastUpdated</th>
            <th>Signature</th>
            <th>SecurityResponses</th>
            <th>ClinicalDocuments</th>
            <th>Credentialss</th>
            <th>Message</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${personList}" var="person"  varStatus="status">
            <tr>
                <td><a href="edit?personId=${person.personId}">${person.personId}</a></td>
                <td>${person.uuid}</td>
                <td>${person.firstName}</td>
                <td>${person.middleName}</td>
                <td>${person.lastName}</td>
                <td>${person.dateOfBirth}</td>
                <td>${person.addressLineOne}</td>
                <td>${person.addressLineTwo}</td>
                <td>${person.city}</td>
                <td>${person.state}</td>
                <td>${person.zipcode}</td>
                <td>${person.country}</td>
                <td>${person.email}</td>
                <td>${person.registrationDate}</td>
                <td>${person.firstLogin}</td>
                <td>${person.lastLogin}</td>
                <td>${person.lastUpdated}</td>
                <td>${person.signature}</td>
                <td>securityResponses</td>
                <td>clinicalDocuments</td>
                <td>credentialss</td>
                <td>${person.message.messageId}</td>
                <td>
                    <a href="edit?personId=${person.personId}">edit</a> 
                    <a href="show?personId=${person.personId}">view</a>
                    <a href="delete?personId=${person.personId}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>