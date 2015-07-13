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

<h2>LabTestRange</h2>
<h2> ${labTestRange.labTestRangeId} </h2>

<table class="table table-bordered table-hover">
    <tr>
        <th>LabTestRangeId</th>
        <td>
            ${labTestRange.labTestRangeId}
        </td>
    </tr>
    <tr>
        <th>Sex</th>
        <td>
            ${labTestRange.sex}
        </td>
    </tr>
    <tr>
        <th>MinAgeYears</th>
        <td>
            ${labTestRange.minAgeYears}
        </td>
    </tr>
    <tr>
        <th>MaxAgeYears</th>
        <td>
            ${labTestRange.maxAgeYears}
        </td>
    </tr>
    <tr>
        <th>MinNormal</th>
        <td>
            ${labTestRange.minNormal}
        </td>
    </tr>
    <tr>
        <th>MaxNormal</th>
        <td>
            ${labTestRange.maxNormal}
        </td>
    </tr>
    <tr>
        <th>LabTest</th>
        <td>
            ${labTestRange.labTest}
        </td>
    </tr>
</table>