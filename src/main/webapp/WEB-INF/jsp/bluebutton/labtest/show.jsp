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

<h2>LabTest</h2>
<h2> ${labTest.labTestId} </h2>

<table class="table table-bordered table-hover">
    <tr>
        <th>LabTestId</th>
        <td>
            ${labTest.labTestId}
        </td>
    </tr>
    <tr>
        <th>Name</th>
        <td>
            ${labTest.name}
        </td>
    </tr>
    <tr>
        <th>Description</th>
        <td>
            ${labTest.description}
        </td>
    </tr>
    <tr>
        <th>Units</th>
        <td>
            ${labTest.units}
        </td>
    </tr>
    <tr>
        <th>LoincCode</th>
        <td>
            ${labTest.loincCode}
        </td>
    </tr>
    <tr>
        <th>DateCreated</th>
        <td>
            ${labTest.dateCreated}
        </td>
    </tr>
    <tr>
        <th>DateUpdated</th>
        <td>
            ${labTest.dateUpdated}
        </td>
    </tr>
    <tr>
        <th>LabTestRanges</th>
        <td>
            <ul>
                <c:forEach items="${labTest.labTestRanges}" var="item" varStatus="itemStatus" >
                    <li><a href="../labtestrange/edit?labTestRangeId=${item.labTestRangeId}" > ${item.labTestRangeId}</a></li>
                </c:forEach>
            </ul>
        </td>
    </tr>
</table>