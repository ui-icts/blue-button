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

<h2>LoincCodeCategory</h2>
<h2> ${loincCodeCategory.id} </h2>

<table class="table table-bordered table-hover">
    <tr>
        <th>Id</th>
        <td>
            ${loincCodeCategory.id}
        </td>
    </tr>
    <tr>
        <th>LoincCode</th>
        <td>
            ${loincCodeCategory.loincCode}
        </td>
    </tr>
    <tr>
        <th>Name</th>
        <td>
            ${loincCodeCategory.name}
        </td>
    </tr>
    <tr>
        <th>RootCategoryName</th>
        <td>
            ${loincCodeCategory.rootCategoryName}
        </td>
    </tr>
    <tr>
        <th>SubrootCategoryName</th>
        <td>
            ${loincCodeCategory.subrootCategoryName}
        </td>
    </tr>
</table>