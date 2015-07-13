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
<%@ include file="/WEB-INF/include.jsp"%>

<div class="row">
	<div class="col-md-4 col-md-offset-4">

		<noscript>
			<div class="alert alert-danger">Please enable javascript, it is required for this application to function properly.</div>
		</noscript>
		<form class="form-horizontal" role="form" action="<c:url value="/login" />" method="post">
			<fieldset>
				
				<legend>Login</legend>
				
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">Username</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="username" name="username" placeholder="Username" autocomplete="off" autocapitalize="off" required/>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="password" name="password" placeholder="Password" autocomplete="off" autocapitalize="off" required/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label> 
						<input type="checkbox" id="remember-me" name="remember-me" value="true"/>Remember me
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Login</button>
			
					</div>
				</div>
	
				<c:if test="${ error }">
					<div class="alert alert-danger">
						Your login attempt was not successful, try again. <br />
						<c:out value="${ SPRING_SECURITY_LAST_EXCEPTION.message }" />
					</div>
				</c:if>
			</fieldset>

		</form>
	</div>
</div>