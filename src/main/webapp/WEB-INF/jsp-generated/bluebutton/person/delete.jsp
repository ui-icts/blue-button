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

<h2>Delete Person</h2>

<c:url value="/person/delete" var="formActionUrl" />

<form method="post" action="${ formActionUrl }">
    <fieldset>
        <legend>Are you sure you want to delete this person?</legend>
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>Person Id</th>
                    <th>Uuid</th>
                    <th>First Name</th>
                    <th>Middle Name</th>
                    <th>Last Name</th>
                    <th>Date Of Birth</th>
                    <th>Address Line One</th>
                    <th>Address Line Two</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zipcode</th>
                    <th>Country</th>
                    <th>Email</th>
                    <th>Registration Date</th>
                    <th>First Login</th>
                    <th>Last Login</th>
                    <th>Last Updated</th>
                    <th>Signature</th>
                    <th>Clinical Documents</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        ${ person.personId }
                    </td>
                    <td>
                        ${ person.uuid }
                    </td>
                    <td>
                        ${ person.firstName }
                    </td>
                    <td>
                        ${ person.middleName }
                    </td>
                    <td>
                        ${ person.lastName }
                    </td>
                    <td>
                        ${ person.dateOfBirth }
                    </td>
                    <td>
                        ${ person.addressLineOne }
                    </td>
                    <td>
                        ${ person.addressLineTwo }
                    </td>
                    <td>
                        ${ person.city }
                    </td>
                    <td>
                        ${ person.state }
                    </td>
                    <td>
                        ${ person.zipcode }
                    </td>
                    <td>
                        ${ person.country }
                    </td>
                    <td>
                        ${ person.email }
                    </td>
                    <td>
                        ${ person.registrationDate }
                    </td>
                    <td>
                        ${ person.firstLogin }
                    </td>
                    <td>
                        ${ person.lastLogin }
                    </td>
                    <td>
                        ${ person.lastUpdated }
                    </td>
                    <td>
                        ${ person.signature }
                    </td>
                    <td>
                        <ul>
                            <c:forEach items="${ person.clinicalDocuments }" var="item" varStatus="itemStatus">
                                <li>${ item.clinicalDocumentId }</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </tbody>
        </table>

        <input type="submit" name="submit" value="Yes" class="btn btn-danger" />
        <input type="submit" name="submit" value="No" class="btn btn-default" />

        <input type="hidden" name="personId" value="${ person.personId }" />

    </fieldset>
</form>