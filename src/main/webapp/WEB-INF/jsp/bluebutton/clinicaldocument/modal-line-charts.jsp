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
<div class="modal fade" id="<c:out value="${param.name}"/>Modal" tabindex="-1" role="dialog" aria-labelledby="<c:out value="${param.name}"/>ModalLabel" aria-hidden="true">
  <div class="modal-dialog blueButton-modal-lg">
    <div class="modal-content">
       <div class="modal-header"><h4 class="modal-title">{{selectedDateRange}}</h4></div>
      <div class="modal-body" id="${param.name}ModalChartBody">
        <div class="<c:out value="${param.name}"/>Charts"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary btn-lg" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>