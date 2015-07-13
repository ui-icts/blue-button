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

<h2>Delete Loinc Code Category</h2>

<c:url value="/loinccodecategory/delete" var="formActionUrl" />

<form method="post" action="${ formActionUrl }">
    <fieldset>
        <legend>Are you sure you want to delete this loinc code category?</legend>
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Loinc Code</th>
                    <th>Name</th>
                    <th>Root Category Name</th>
                    <th>Subroot Category Name</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        ${ loincCodeCategory.id }
                    </td>
                    <td>
                        ${ loincCodeCategory.loincCode }
                    </td>
                    <td>
                        ${ loincCodeCategory.name }
                    </td>
                    <td>
                        ${ loincCodeCategory.rootCategoryName }
                    </td>
                    <td>
                        ${ loincCodeCategory.subrootCategoryName }
                    </td>
                </tr>
            </tbody>
        </table>

        <input type="submit" name="submit" value="Yes" class="btn btn-danger" />
        <input type="submit" name="submit" value="No" class="btn btn-default" />

        <input type="hidden" name="id" value="${ loincCodeCategory.id }" />

    </fieldset>
</form>