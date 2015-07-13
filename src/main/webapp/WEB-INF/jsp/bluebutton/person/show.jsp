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

<h2>Person</h2>
<h2> ${person.personId} </h2>

<table class="table table-bordered table-hover">
    <tr>
        <th>PersonId</th>
        <td>
            ${person.personId}
        </td>
    </tr>

    <tr>
        <th>FirstName</th>
        <td>
            ${person.firstName}
        </td>
    </tr>
    <tr>
        <th>MiddleName</th>
        <td>
            ${person.middleName}
        </td>
    </tr>
    <tr>
        <th>LastName</th>
        <td>
            ${person.lastName}
        </td>
    </tr>
    <tr>
        <th>DateOfBirth</th>
        <td>
            ${person.dateOfBirth}
        </td>
    </tr>
    <tr>
        <th>AddressLineOne</th>
        <td>
            ${person.addressLineOne}
        </td>
    </tr>
    <tr>
        <th>AddressLineTwo</th>
        <td>
            ${person.addressLineTwo}
        </td>
    </tr>
    <tr>
        <th>City</th>
        <td>
            ${person.city}
        </td>
    </tr>
    <tr>
        <th>State</th>
        <td>
            ${person.state}
        </td>
    </tr>
    <tr>
        <th>Zipcode</th>
        <td>
            ${person.zipcode}
        </td>
    </tr>
    <tr>
        <th>Country</th>
        <td>
            ${person.country}
        </td>
    </tr>
    <tr>
        <th>Email</th>
        <td>
            ${person.email}
        </td>
    </tr>
    <tr>
        <th>RegistrationDate</th>
        <td>
            ${person.registrationDate}
        </td>
    </tr>
    <tr>
        <th>FirstLogin</th>
        <td>
            ${person.firstLogin}
        </td>
    </tr>
    <tr>
        <th>LastLogin</th>
        <td>
            ${person.lastLogin}
        </td>
    </tr>
    <tr>
        <th>LastUpdated</th>
        <td>
            ${person.lastUpdated}
        </td>
    </tr>
    <tr>
        <th>Signature</th>
        <td>
            ${person.signature}
        </td>
    </tr>
    <tr>
        <th>SecurityResponses</th>
        <td>
            not implemented<br/><br/>
        </td>
    </tr>
    <tr>
        <th>ClinicalDocuments</th>
        <td>
            <ul>
                <c:forEach items="${person.clinicalDocuments}" var="item" varStatus="itemStatus" >
                    <li><a href="../clinicaldocument/edit?clinicalDocumentId=${item.clinicalDocumentId}" > ${item.clinicalDocumentId}</a></li>
                </c:forEach>
            </ul>
        </td>
    </tr>

    <tr>
        <th>Message</th>
        <td>
            ${person.message}
        </td>
    </tr>
</table>