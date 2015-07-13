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

<h2>Delete Clinical Document</h2>

<c:url value="/clinicaldocument/delete" var="formActionUrl" />

<form method="post" action="${ formActionUrl }">
    <fieldset>
        <legend>Are you sure you want to delete this clinical document?</legend>
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>Clinical Document Id</th>
                    <th>Document</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Date Uploaded</th>
                    <th>Source</th>
                    <th>Parsed Json</th>
                    <th>Json Parser Version</th>
                    <th>Person</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        ${ clinicalDocument.clinicalDocumentId }
                    </td>
                    <td>
                        ${ clinicalDocument.document }
                    </td>
                    <td>
                        ${ clinicalDocument.name }
                    </td>
                    <td>
                        ${ clinicalDocument.description }
                    </td>
                    <td>
                        ${ clinicalDocument.dateUploaded }
                    </td>
                    <td>
                        ${ clinicalDocument.source }
                    </td>
                    <td>
                        ${ clinicalDocument.parsedJson }
                    </td>
                    <td>
                        ${ clinicalDocument.jsonParserVersion }
                    </td>
                    <td>
                        ${ clinicalDocument.person }
                    </td>
                </tr>
            </tbody>
        </table>

        <input type="submit" name="submit" value="Yes" class="btn btn-danger" />
        <input type="submit" name="submit" value="No" class="btn btn-default" />

        <input type="hidden" name="clinicalDocumentId" value="${ clinicalDocument.clinicalDocumentId }" />

    </fieldset>
</form>