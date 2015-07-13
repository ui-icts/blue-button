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

<h2>Clinical Document</h2>

<h2> ${clinicalDocument.clinicalDocumentId} </h2>

<table class="table table-bordered table-hover">
    <tr>
        <th>Clinical Document Id</th>
        <td>
            ${clinicalDocument.clinicalDocumentId}
        </td>
    </tr>
    <tr>
        <th>Document</th>
        <td>
            ${clinicalDocument.document}
        </td>
    </tr>
    <tr>
        <th>Name</th>
        <td>
            ${clinicalDocument.name}
        </td>
    </tr>
    <tr>
        <th>Description</th>
        <td>
            ${clinicalDocument.description}
        </td>
    </tr>
    <tr>
        <th>Date Uploaded</th>
        <td>
            ${clinicalDocument.dateUploaded}
        </td>
    </tr>
    <tr>
        <th>Source</th>
        <td>
            ${clinicalDocument.source}
        </td>
    </tr>
    <tr>
        <th>Parsed Json</th>
        <td>
            ${clinicalDocument.parsedJson}
        </td>
    </tr>
    <tr>
        <th>Json Parser Version</th>
        <td>
            ${clinicalDocument.jsonParserVersion}
        </td>
    </tr>
    <tr>
        <th>Person</th>
        <td>
            ${clinicalDocument.person}
        </td>
    </tr>
</table>