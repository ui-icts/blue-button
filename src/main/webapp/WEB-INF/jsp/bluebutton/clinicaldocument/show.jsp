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
<div  ng-controller="MainController">
        <!-- start modals -->
       	  <jsp:include page="/WEB-INF/jsp/bluebutton/clinicaldocument/modal-line-charts.jsp"><jsp:param name="name" value="vitals"/></jsp:include>
       	  <jsp:include page="/WEB-INF/jsp/bluebutton/clinicaldocument/modal-line-charts.jsp"><jsp:param name="name" value="labsline"/></jsp:include>
       	  <jsp:include page="/WEB-INF/jsp/bluebutton/clinicaldocument/modal-timeline.jsp"><jsp:param name="name" value="Problems"/></jsp:include>
       	  <jsp:include page="/WEB-INF/jsp/bluebutton/clinicaldocument/modal-timeline.jsp"><jsp:param name="name" value="Allergies"/></jsp:include>
       	  <jsp:include page="/WEB-INF/jsp/bluebutton/clinicaldocument/modal-timeline.jsp"><jsp:param name="name" value="Medications"/></jsp:include>
       	  <jsp:include page="/WEB-INF/jsp/bluebutton/clinicaldocument/modal-timeline.jsp"><jsp:param name="name" value="Immunizations"/></jsp:include>
       	  <jsp:include page="/WEB-INF/jsp/bluebutton/clinicaldocument/modal-timeline.jsp"><jsp:param name="name" value="Procedures"/></jsp:include>
       	  <jsp:include page="/WEB-INF/jsp/bluebutton/clinicaldocument/modal-timeline.jsp"><jsp:param name="name" value="Encounters"/></jsp:include>
       	  <jsp:include page="/WEB-INF/jsp/bluebutton/clinicaldocument/modal-timeline.jsp"><jsp:param name="name" value="Labs"/></jsp:include>
		<!-- end modals -->
				
		      	<div class="panel panel-default" id="dateRangeSelector" style="margin-top: {{scrollSpyHeight-60}}px">
				<div class="panel-heading"><h2 class="panel-title">Date Range Selector</h2></div>
				  <div class="panel-body">
				  <div class="sliderPadding">  <div id="dateRangeSlider" ></div></div>
				  </div>
				  </div>	
				  
		      	<div class="panel panel-default" id="searchInput">
		      	<div class="panel-heading"><h2 class="panel-title">Search</h2></div>
		      	 <div class="panel-body">
		      	<nit-advanced-searchbox id="searchBox" ng-model="searchParams" parameters="availableSearchParams" placeholder="Search..."></nit-advanced-searchbox>
		      	 </div>		     
		      	</div> 	
		      	
         		<div class="panel panel-info" id="summaryTimeline">
				  <!-- Default panel contents -->
				  <div class="panel-heading"><h1 class="panel-title"><a data-toggle="collapse"  href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Blue Button Health Record for 
				  {{bbData.demographics.name.fullName}}</a> from {{selectedDateRange}}</h1>  <span id="error-message"></span></div>
                  <div id="collapseOne" class="panel-collapse collapse in" >
					  <div class="panel-body timelineCondensedChart" ></div>
					  <div class="panel-footer">{{bbData.demographics.heSheGenderedPronoun}} is a {{bbData.age}} year old {{bbData.demographics.gender}} with
		                    <strong>{{bbData.metaData.allergies.severity}} allergies</strong> and a provider organization of <strong>{{bbData.demographics.combinedSourceList}}</strong>.
		                        In the last year, {{bbData.demographics.heSheGenderedPronoun | lowercase}} has <strong>{{bbData.metaData.medications.recentMedications}} </strong> and <strong>{{bbData.metaData.encounters.recentEncounters}}</strong>. {{bbData.demographics.hisHerGenderedPronoun}} smoking status is <strong>{{bbData.smokingStatus.name}}</strong>.
		                  <%-- <span id="specificDemographics">
		                        {{bbData.demographics.genderedPronoun}} is {{bbData.demographics.race | lowercase}}, {{bbData.demographics.marital_status | lowercase }}, <span id="bbLanguage"></span>
		                    </span> --%> 
	                  </div>
				  </div>
				</div>	
           <div class="panel panel-default" id="medicationsChartDisplay">
			<div class="panel-heading" id="medicationsGridHeading"><h2 class="panel-title">Medications</h2></div>
				  <div class="panel-body">
			      	<div id="mGrid" class="blueButtonUIParentGridLarge" ui-grid="medicationsGrid" ui-grid-auto-resize style="height: {{getTableHeight(bbData.medications, medicationsGrid)}};" ui-grid-expandable external-scopes="$scope"></div>
				</div>
				</div>
			<div class="panel panel-default" id="labResultsGridChartDisplay">
				<div class="panel-heading" id="labsGridHeading"><h2 class="panel-title">Labs</h2></div>
				  <div class="panel-body">
				  <button type="button" class="btn btn-success" ng-click="selectAllLabs()">Select All</button>
      			  <button type="button" class="btn btn-success" ng-click="clearAllLabs()">Clear All</button>
      			  <button type="button" class="btn btn-success" ng-click="collapseAllLabs()">Collapse All</button>
				  <button type="button" class="btn btn-primary" ng-click="viewLabsCharts()" data-toggle="modal" data-target="#labslineModal">View Selected Charts</button>
			      	<div id="lGrid" class="blueButtonUIParentGridLarge" ui-grid="labsGrid" ui-grid-selection  ui-grid-auto-resize ui-grid-expandable external-scopes="$scope"  style="height: {{getTableHeight(bbData.labResultsGrid, labsGrid)}};"></div>
				</div>
				</div>
	
			 <div class="panel panel-default" id="vitalsGridChartDisplay">
			 	<div class="panel-heading" id="vitalsGridHeading"><h2 class="panel-title">Vitals</h2></div>
				  <div class="panel-body">
				  <button type="button" class="btn btn-success" ng-click="selectAllVitals()">Select All</button>
      			  <button type="button" class="btn btn-success" ng-click="clearAllVitals()">Clear All</button>
      			  <button type="button" class="btn btn-success" ng-click="collapseAllVitals()">Collapse All</button>
				  <button type="button" class="btn btn-primary" ng-click="viewVitalsCharts()" data-toggle="modal" data-target="#vitalsModal">View Selected Charts</button>
			      	<div id="vGrid" class="blueButtonUIParentGridLarge" ui-grid="vitalsGrid" ui-grid-auto-resize  style="height: {{getTableHeight(bbData.vitalsGrid, vitalsGrid)}};" ui-grid-selection  ui-grid-expandable external-scopes="$scope"></div>
				</div>
				</div>	

			<div class="panel panel-default" id="problemsChartDisplay">
			 <div class="panel-heading" id="problemsGridHeading"><h2 class="panel-title">Problems</h2></div>
				  <div class="panel-body">
			   <div class="alert alert-info" role="alert">The complete list of Problems data is always displayed and not affected by the "Date Range Selector".</div>
			    <!-- Problems grid -->
				 <div id="pGrid" class="blueButtonUIParentGridSmall" ui-grid="problemsGrid" ui-grid-auto-resize ui-grid-expandable external-scopes="$scope"  style="height: {{getTableHeight(bbData.problems, problemsGrid)}};"></div>
				</div>
				</div>
			
			<div class="panel panel-default" id="allergiesChartDisplay">
			<div class="panel-heading" id="allergiesGridHeading"><h2 class="panel-title">Allergies</h2></div>
				  <div class="panel-body">
			     <div class="alert alert-info" role="alert">The complete list of Allergies data is always displayed and not affected by the "Date Range Selector".</div>
			   <!-- Allergies grid -->
				 <div id="aGrid" class="blueButtonUIParentGridSmall" ui-grid-auto-resize style="height: {{getTableHeight(bbData.allergies, allergiesGrid)}};" ui-grid="allergiesGrid" ui-grid-expandable external-scopes="$scope"></div>			
				</div>
				 </div>
			<div class="panel panel-default" id="immunizationsChartDisplay">
				<div class="panel-heading" id="immunizationsGridHeading"><h2 class="panel-title">Immunizations</h2></div>
				  <div class="panel-body">
				<!--Immunizations grid-->
					  <div id="iGrid" class="blueButtonUIParentGridSmall" ui-grid-auto-resize style="height: {{getTableHeight(bbData.immunizations, immunizationsGrid)}};" ui-grid="immunizationsGrid" ui-grid-expandable external-scopes="$scope" ></div>
				</div>
  			  </div>
			<div class="panel panel-default" id="proceduresChartDisplay">
				<div class="panel-heading" id="proceduresGridHeading"><h2 class="panel-title">Procedures</h2></div>
				  <div class="panel-body">
				 <!-- Procedures grid -->
				 <div id="prGrid" class="blueButtonUIParentGridSmall" ui-grid-auto-resize style="height: {{getTableHeight(bbData.procedures, proceduresGrid)}};" ui-grid="proceduresGrid" ui-grid-expandable external-scopes="$scope"></div>
				</div>
  			  </div>


			<div class="panel panel-default" id="encountersChartDisplay">
             <div class="panel-heading" id="encountersGridHeading"><h2 class="panel-title">Encounters</h2></div>
				  <div class="panel-body">
              <!--encounters grid-->
					  <div id="eGrid" class="blueButtonUIParentGridSmall" ui-grid-auto-resize style="height: {{getTableHeight(bbData.encounters, encountersGrid)}};" ui-grid="encountersGrid" ui-grid-expandable external-scopes="$scope" ></div>
				</div>
  			  </div>
<!-- Google Maps -->
		      	<script src="https://maps.googleapis.com/maps/api/js?client=gme-universityofiowa&v=3.20"></script>
		      	
		      	<div id="map" class="panel panel-default" style="width:100%;height:100%">
				  <div class="panel-heading"  id="mapHeading">
				    <h3 class="panel-title">Map of Provider Addresses</h3>
				    <div id="directionsPanel" style="float:right;width:30%;height:100%"></div>
				  </div>
				  <div class="panel-body" id="map-canvas" style="height: 500px;">
				  </div>
				  
				</div>
				
				
				<script type="text/javascript">
				var rendererOptions = {
						  preserveViewport: true,
						  draggable: true
						};
						var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);;
						var directionsService = new google.maps.DirectionsService();
						
				function initializeGoogleMap(locations) {
				  //"1940 TimberWolf Drive, North Liberty, IA 52317", "200 Hawkins Dr,Iowa City, IA 52242"

/* 				  var locations = [
		                 { type: "Home",
		                     address: "1940 TimberWolf Drive, North Liberty, IA 52317",
		                     name: "Dan H"},
		                     { type: "Provider",
			                     address: "200 Hawkins Dr,Iowa City, IA 52242",
			                     name: "UIHC"}
		             
		               ]; */
					   var bounds = new google.maps.LatLngBounds();
		               var map = new google.maps.Map(document.getElementById('map-canvas'), {
		                 zoom: 10,
		                 center: new google.maps.LatLng(43.253205,-80.480347),
		                 mapTypeId: google.maps.MapTypeId.ROADMAP
		               });
		               
		               var trafficLayer = new google.maps.TrafficLayer();
		               trafficLayer.setMap(map);
		               
		               directionsDisplay.setMap(map);
		               directionsDisplay.setPanel(document.getElementById('directionsPanel'));	               
		               
		               var infowindow = new google.maps.InfoWindow();
		               var geocoder = new google.maps.Geocoder();
		               var bounds = new google.maps.LatLngBounds();
		               var marker, i;
		               for (i = 0; i < locations.length; i++) {
		                 geocodeAddress(locations[i]);
		               }
		            
		           function geocodeAddress(location) {
					if(location.address){
		             geocoder.geocode( { 'address': location.address}, function(results, status) {
		               if (status == google.maps.GeocoderStatus.OK) {
		                // map.setCenter(results[0].geometry.location);
		                 createMarker(results[0].geometry.location,location);
		               }
		               else
		               {
		            	   console.log("some problem in geocode" + status);
		               }
		             }); 
		             }
		           }
		           
		           var home;
		           var LocationLat;
		           var LocationLng;
		           var Location;
		           var destinationLocation;
	               
	               function calcRoute() {

	            	   var request = {
	            	    origin: destinationLocation,
	            	    destination: Location,
	            	     travelMode: google.maps.TravelMode.DRIVING
	            	   };
	            	   directionsService.route(request, function(response, status) {
	            	     if (status == google.maps.DirectionsStatus.OK) {
	            	       directionsDisplay.setDirections(response);
	            	     }
	            	   });
	            	 }
		           
		           function createMarker(latlng,location){
		        	   var image;
		        	   if(location.type == "Home"){
		        		   image = "http://maps.google.com/mapfiles/kml/pal3/icon56.png"
		        	   }else{
		        		   image = "http://maps.google.com/mapfiles/kml/pal3/icon46.png"
		        	   }

		             var marker = new google.maps.Marker({
		               position: latlng,
		               map: map,
		               icon: image
		             }); 

		             var markers = [];
		             markers.push(marker);
		             google.maps.event.addListener(marker, 'mouseover', function() {
		            	 if(location.name == null){
		            		 infowindow.setContent(location.address);
		            	 }
		            	 else{
		            		 infowindow.setContent(location.name+"<br>"+location.address);
		            	 }
		               infowindow.open(map, marker);
		             });
		           		
		             google.maps.event.addListener(marker, 'mouseout', function() { 
		               infowindow.close();
		             });
		             
		             function getLatLngFromString(latLongString) { 
		            	var latlong = latLongString.replace(/[()]/g,''); 
		            	var latlng = latlong.split(','); 
		             	locate = new google.maps.LatLng(parseFloat(latlng[0]) , parseFloat(latlng[1])); 
		             	return locate;
		             }
		             
		             google.maps.event.addListener(marker, 'click', function() { 
			               LocationLat = String(marker.getPosition().lat());
			               LocationLng = String(marker.getPosition().lng());
			               var newLocation = LocationLat + ", " + LocationLng;
			               Location = getLatLngFromString(newLocation);
			               if(destinationLocation == null){
			            	   destinationLocation = Location;
			               }
			               calcRoute(); 
			               destinationLocation = Location;
			             });
		             google.maps.event.addListener(map, 'rightclick', function() { 
		            	 directionsDisplay.set('directions', null);
			             });
		             for(i = 0; i < markers.length; i++) {
		              bounds.extend(markers[i].getPosition());
		             }
		             map.fitBounds(bounds);
		           }

				}
				
				
			    </script>
				<!-- End Google Maps -->
				<div id="forceChart" class="panel panel-info">
				  <div class="panel-heading"  id="forceChartHeading">
				    <h3 class="panel-title">Force Chart: # of Days Between Connected Clusters: <span id="forceChartSliderDaysValue"></span>.  Hover over a circle to view more detail or click it to see similar records.</h3>
				  </div>
				  <div class="panel-body">
				   	<div class="sliderPadding"><div id="forceChartSlider"></div></div>
          			<div class="forceChart"></div>
				  </div>
				</div>
<br/><br/><br/><br/><br/><br/><br/>				
<br/><br/><br/><br/><br/><br/><br/>				

</div>

<!-- Define global varaiables -->
<script>
var minChartWidth = parseInt(d3.select("#collapseOne").style("width"))-35;
var lineChartHeight = 300;
var toolTipFormat = d3.time.format("%m-%d-%Y");
var sortableDateFormat = d3.time.format("%Y-%m-%d");
var parseDate = d3.time.format("%m/%d/%Y").parse;
</script>


<!-- vitals charts, and labs line charts -->
<script>
Date.prototype.addDays = function(days){
    var dat = new Date(this.valueOf());
    dat.setDate(dat.getDate() + days);
    return dat;
};

function vitalsCharts(timeSpanFromRangeSlider, arrayOfVitalsNames) {  	
    var data = fullData.vitalsGrid;
	var masterArray = [];
	
	for(var x=0; x<data.length; x++){
	  for(var y=0; y<data[x].subGrid.length; y++){
		  var dataPoint = data[x].subGrid[y];
		  if(dataPoint.dateInMillis && dataPoint.value && dataPoint.name && dataPoint.unit){
			  masterArray.push(dataPoint);
		  }
	  }
	}
	masterArray = masterArray.filter( function (d) { return $.inArray(d.name, arrayOfVitalsNames) > -1; } );	
//	masterArray.sort(function (a, b) { return b.dateInMillis - a.dateInMillis; });
	$( ".vitalsCharts" ).empty();
    displayLineCharts(".vitalsCharts", masterArray,timeSpanFromRangeSlider);
}
</script>

<!-- Line Charts -->
<script>
//Will accept an array of arrays and display line charts 
function displayLineCharts(htmlElementToAppendTo, chartArray, timeSpanFromRangeSlider){
	// group by units first
	//var nest = d3.nest().key(function(d) { return d.unit.toLowerCase(); }).entries(chartArray);	
	var colorCategory = d3.scale.category10(); 
	function glow() {
		  var circle = d3.select(this);
		  (function repeat() {
		    circle = circle.transition()
		        .style("opacity", ".3")
		      	.transition()
		        .style("opacity", 1)
		        .each("end", repeat);
		  })();
		}    
	var margin = {top: 10, right: 25, bottom: 25, left: 50},
	height = lineChartHeight - margin.top - margin.bottom;
	//Loops through chartArray
	for (var a=0;a<chartArray.length;a++) {
		var chart = chartArray[a];
		var	responsiveWidth = $(document).width()*0.9;
		 		
		var x = d3.time.scale().range([0, responsiveWidth-(margin.right+margin.left)]);
		var y = d3.scale.linear().range([height, 0]);
		var xAxis = d3.svg.axis().scale(x).orient("bottom");
		var yAxis = d3.svg.axis().scale(y).orient("left");
		// determine the x-axis range from the date range slider
		var xMinMax = d3.extent(chart.data, function(d) { return d.dateInMillisInMillis;});
		if (timeSpanFromRangeSlider){
				xMinMax = [];
				xMinMax.push(timeSpanFromRangeSlider.minTime);
				xMinMax.push(timeSpanFromRangeSlider.maxTime);
		}
		x.domain(xMinMax);
		// determine the y-axis range based on values 
		var valuesRange = d3.extent(chart.data, function(d) { return d.value; });
		
		if(chart.range.min && chart.range.max){
			valuesRange[0] = (chart.range.min<valuesRange[0]) ? chart.range.min : valuesRange[0];
			valuesRange[1] = (chart.range.max>valuesRange[1]) ? chart.range.max : valuesRange[1];
		}
		
		y.domain(valuesRange);
		// pad the domain range	
		function padDomain(hash,scale){return (hash[0]==hash[1]) ? [hash[0]-(hash[0]*(scale*scale)), hash[1]+(hash[0]*(scale*scale))] : [hash[0]-((hash[1]-hash[0])*scale), hash[1]+((hash[1]-hash[0])*scale)];}
		//x domain must be converted from date to date in mills
		x.domain(padDomain([new Date(x.domain()[0]).getTime(),new Date(x.domain()[1]).getTime()], 0.1));
		y.domain(padDomain(y.domain(), 0.3)); 
		y.domain().nice;
		
		var line = d3.svg.line().x(function(d) { return x(d.dateInMillis); }).y(function(d) { return y(d.value); });
		d3.select(htmlElementToAppendTo).append("h4").text(chart.data[0].name);
		var legend = d3.select(htmlElementToAppendTo)
			.append("svg").attr("width", responsiveWidth-(margin.right+margin.left)).attr("height", 30)
			.append("g");
		
		legend.append("circle") 
			.attr("r", 7)
			.attr("class", "node nodeOutOfRange")
			.style("opacity", ".3")
			.attr("cx", 20)
			.attr("cy", 9)
			.style("fill", function(d) { return "#d62728"; })
			.transition().duration(1000).each(glow);
		legend.append("text").attr("x",35).attr("y",15).text("Value is outside a range (LOINC normal and/or 2 standard deviations)").style("font-size", "16px");
				
		var svg = d3.select(htmlElementToAppendTo).classed("svg-container", true).append("svg")
		.attr("preserveAspectRatio", "xMinYMin meet").attr("viewBox", "0 0 "+ (responsiveWidth-(margin.right+margin.left)) + " " + (height + margin.top + margin.bottom)).classed("svg-content-responsive", true) 
		.append("g").datum(data).attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
		// x axis  
		svg.append("g").attr("class", "x axis").attr("transform", "translate(0," + height + ")").call(xAxis);
		// y axis
		svg.append("g").attr("class", "y axis").call(yAxis)
		  .append("text").attr("transform", "rotate(-90)").attr("y", 6).attr("dy", ".71em").style("text-anchor", "end").text(chart.data.unit);
		
		var data = chart.data;
		data.sort(function (a, b) { return a.date - b.date; });
		data.forEach(function(d) {
		    d.value = +d.value.toFixed(2);
		  });  
		
		// don't try to display a line for one data point
		if (data.length > 1){
			svg.append("path")
		    .datum(data)
		    .attr("class", "line")
		    .attr("style", "stroke: " + colorCategory(data[0].name) + ";")
		    .attr("d", line);
		} 
		
		function createDottedLine(value){
			var lineData = [{"x":x.domain()[0],"y":value},
			                {"x":x.domain()[1],"y":value}];
			 svg.append("path")
			    .datum(lineData)
			    .attr("class", "line dotted")
			    .attr("style", "stroke: red;")
			    .attr("stroke-dasharray","10,10")
			    .attr("d", d3.svg.line().x(function(d){return x(d.x)}).y(function(d){return y(d.y)}));
		}
		if(chart.range.min || chart.range.max){
			legend.append("path")
			    .attr("class", "line dotted")
			    .attr("style", "stroke: red;")
			    .attr("stroke-dasharray","10,10")
			    .attr("d", "M540.00221787764912,9.9354838709678L590.1016826853703,9.9354838709678");
			legend.append("text").attr("x",600).attr("y",15).text("Normal LOINC Bounds").style("font-size", "16px");
			if(chart.range.min){createDottedLine(chart.range.min);}
			if(chart.range.max){createDottedLine(chart.range.max);}
		}
		
		
		var dataWithinTwoStandardDeviations = data.filter(function (d) {if (!d.statsInformation.normal){return false;} else {return true;}});
		
			var circlesInRange = svg.selectAll("svg").data(dataWithinTwoStandardDeviations);
			
			circlesInRange.enter().append("circle") 
			  .attr("r", 7)
			  .attr("class", "node nodeInRange")
		      .attr("cx", function(d) { return x(d.dateInMillis); })
		      .attr("cy", function(d) { return y(d.value); })
		      .style("fill", function(d) { return colorCategory(data[0].name); })
		      .attr("qtip-title", function(d) { return d.name ; })
		      .attr("title", function(d) { 
			  var code = d.loincCode;
			  return d.value + d.unit + " on " + d.date +
			    ((d.statsInformation.mean) ? "<br/>Your Mean: " +  d.statsInformation.mean.toFixed(2) : "") + 
			  	"<br/>Your Range: " + d.statsInformation.range + 
			  	((d.statsInformation.standardDeviation) ? "<br/>Your Standard Deviation: " + d.statsInformation.standardDeviation.toFixed(2) : "") +
			  	+ ((d.loincCode && d.statsInformation.normalRange) ? "<br/>Normal range for LOINC: "+ d.loincCode+" is "+d.statsInformation.normalRange  : ""); 			
			})
			var dataOutsideTwoStandardDeviations = data.filter(
		 		    function (d) {
		 		    	if (d.statsInformation.normal){
			 		        return false;
		 		        } else {
		 		        	return true;
		 		        }
		 		    }
		 		);
			 	
		    var circlesOutsideRange = svg.selectAll("svg").data(dataOutsideTwoStandardDeviations);
		    circlesOutsideRange.enter().append("circle") 
			  .attr("r", 7)
			  .attr("class", "node nodeOutOfRange")
			  .style("opacity", ".3")
		      .attr("cx", function(d) { return x(d.dateInMillis); })
		      .attr("cy", function(d) { return y(d.value); })
		      .style("fill", function(d) { return "#d62728"; })
		      .attr("qtip-title", function(d) { return d.name ; })
		      .attr("title", function(d) { 
			  return d.value + d.unit + " on " + d.date + ((d.statsInformation.displayText != null) ?
			  	"<br/><strong class=\"text-danger\">"+ d.statsInformation.displayText+"</strong>"  : "")+ 
			    ((d.statsInformation.mean) ? "<br/>Your Mean: " +  d.statsInformation.mean.toFixed(2) : "") + 
			  	"<br/>Your Range: " + d.statsInformation.range + 
			  	((d.statsInformation.standardDeviation) ? "<br/>Your Standard Deviation: " + d.statsInformation.standardDeviation.toFixed(2) : "") +
			  	((d.loincCode && d.statsInformation.normalRange) ? "<br/>Normal range for LOINC "+ d.loincCode+" is "+d.statsInformation.normalRange  : ""); 
		   })
		  .transition()
		    .duration(1000)
		    .each(glow);
		
			circlesOutsideRange.exit().remove();
			
		// add a moving average line if data set is large enough
		 if (data.length > 5){
		    svg.append("path")
		    .datum(data)
		    .attr("class", "line movingAverageLine")
		    .attr("style", "stroke: black;")
		    .attr("d", d3.svg.line()
		    		.x(function(d,i) { return x(d.dateInMillis); })
		    	    .y(function(d,i) { return y(d3.mean(data.slice(((i>19)? i-20 : 0), i+1), function(d2) { return d2.value; })); }));
		 }
	}	    
    drawQtips();
}
</script>


<!-- medications data-->
<script>
function processMedicationsData(timeSpanHash, dataHash, color, link, labelOverride){
	var colorCategory = d3.scale.category20();
	 var sourceData = fullData.medications;
	 
	 processTimeLineDataRect(sourceData, colorCategory, labelOverride,dataHash, color, link ,timeSpanHash, "medications");
}

function medicationsTimeline(timeSpanFromRangeSlider){ 
     var dataHash = {};
     processMedicationsData(timeSpanFromRangeSlider, dataHash, "#1f77b4");
     getChart(dataHash, "medicationsCharts", timeSpanFromRangeSlider);
}

</script>

<!-- problems data -->
<script>
function processProblemsData(timeSpanHash, dataHash, color, link, labelOverride){
	var colorCategory = d3.scale.category20();
	 var sourceData = fullData.problems;
	 
	 processTimeLineDataRect(sourceData, colorCategory, labelOverride,dataHash, color, link ,timeSpanHash, "problems");
}

function problemsTimeline(timeSpanFromRangeSlider){ 
    var dataHash = {};
    processProblemsData(timeSpanFromRangeSlider, dataHash, "#ff7f0e");	  
    getChart(dataHash, "problemsCharts", timeSpanFromRangeSlider);
}
</script>

<!-- allergies data -->
<script>
function processAllergiesData(timeSpanHash, dataHash, color, link, labelOverride){
	var colorCategory = d3.scale.category20();
	 var sourceData = fullData.allergies;
	 
	 processTimeLineDataRect(sourceData, colorCategory, labelOverride,dataHash, color, link ,timeSpanHash, "allergen");

}
function allergiesTimeline(timeSpanFromRangeSlider){ 
     var dataHash = {};
    processAllergiesData(timeSpanFromRangeSlider, dataHash,"#d62728");	  
    getChart(dataHash, "allergiesCharts", timeSpanFromRangeSlider);
}
</script>

<!-- procedure data -->
<script>
function processProceduresData(timeSpanHash, dataHash, color, link, labelOverride){
	var colorCategory = d3.scale.category20();
	 var sourceData = fullData.procedures;
	 
	 processTimeLineDataCircle(sourceData, colorCategory, labelOverride, dataHash, color, link, timeSpanHash, "procedures");
	 
}
function proceduresTimeline(timeSpanFromRangeSlider){ 
     var dataHash = {};
     processProceduresData(timeSpanFromRangeSlider, dataHash,"#9467bd");
     getChart(dataHash, "proceduresCharts", timeSpanFromRangeSlider);
}
</script>

<!-- immunizations data -->
<script>
function processImmunizationsData(timeSpanHash, dataHash, color, link, labelOverride){
	var colorCategory = d3.scale.category20();
	 var sourceData = fullData.immunizations;	 
	 
	 processTimeLineDataCircle(sourceData, colorCategory, labelOverride, dataHash, color, link, timeSpanHash, "immunizations");
}
function immunizationsTimeline(timeSpanFromRangeSlider){ 
     var dataHash = {};
     processImmunizationsData(timeSpanFromRangeSlider, dataHash,"#17becf");
     getChart(dataHash, "immunizationsCharts", timeSpanFromRangeSlider);
}
</script>

<!-- labs data -->
<script>
function processLabsData(timeSpanHash, dataHash, color, link, labelOverride){
	var colorCategory = d3.scale.category20();
	 var sourceData = fullData.labResultsGrid;     
	 processTimeLineDataCircle(sourceData, colorCategory, labelOverride, dataHash, color, link, timeSpanHash, "labs");
}

function labsTimeline(timeSpanFromRangeSlider){ 
    var dataHash = {};
    processLabsData(timeSpanFromRangeSlider, dataHash,"#bcbd22");
	getChart(dataHash, "labsCharts", timeSpanFromRangeSlider);
}

</script>

<!--  Condensed Timeline -->
<script>
function condensedTimeline(timeSpanFromRangeSlider){ 
	var timeSpanHash = timeSpanFromRangeSlider;   
	var data=[];
	
    var dataHash = {};
    processProblemsData(timeSpanHash, dataHash, "#ff7f0e", "#problemsModal", "Problems");	 		
    processAllergiesData(timeSpanHash, dataHash, "#d62728", "#allergiesModal", "Allergies");  	    
    processMedicationsData(timeSpanHash, dataHash, "#1f77b4", "#medicationsModal", "Medications");  
    processImmunizationsData(timeSpanHash, dataHash, "#17becf", "#immunizationsModal", "Immunizations");  
    processProceduresData(timeSpanHash, dataHash, "#9467bd", "#proceduresModal", "Procedures");  		
    processEncountersData(timeSpanHash, dataHash, "#2ca02c", "#encountersModal", "Encounters");  		
    processLabsData(timeSpanHash, dataHash, "#bcbd22", "#labsModal", "Labs");  					    
    
	Object.keys(dataHash).forEach(function (key) { 
		   dataHash[key].times.sort(function (a, b) { return b.starting_time - a.starting_time; });	
		 data.push(dataHash[key]);
		});
	
	data.sort(function (a, b) { return b.times[0].starting_time - a.times[0].starting_time; });
		
    d3.select("#timelineCondensedChartSVG").remove();
    
    var chart = d3.timeline().width(minChartWidth).stack()
        .beginning(timeSpanHash.minTime).ending(timeSpanHash.maxTime)
        .tickFormat(calculateTickFormat(timeSpanHash))
        .margin({left:chartMarginLeft+25, right:chartMarginRight, top:0, bottom:0})
        .onclick(function (d, i, datum) { return "$('"+datum.times[i].link+"').modal({})";})
        .displayShape(function (d, i, datum) { return datum.times[i].displayShape; })  
        ;
 
    var svg = d3.select(".timelineCondensedChart").classed("svg-container", true).append("svg")
		.attr("preserveAspectRatio", "xMinYMin meet").attr("id","timelineCondensedChartSVG").attr("viewBox", "0 0 "+ (minChartWidth) + " " + ((data.length*30)+30)).classed("svg-content-responsive", true) 
		.append("svg").datum(data).call(chart);
    
    return timeSpanFromRangeSlider;
}
</script>

<!-- ecounters data -->
<script>
function processEncountersData(timeSpanHash, dataHash, color, link, labelOverride){
	var colorCategory = d3.scale.category20();
	 var sourceData = fullData.encounters;
	 
	 processTimeLineDataCircle(sourceData, colorCategory, labelOverride, dataHash, color, link, timeSpanHash, "encounters");
}

function encountersTimeline(timeSpanFromRangeSlider){ 
    var dataHash = {};
    processEncountersData(timeSpanFromRangeSlider, dataHash,"#2ca02c");
	getChart(dataHash, "encountersCharts", timeSpanFromRangeSlider);
}
</script>

<!-- force chart -->
<script>

//global varaiables for lab test range remote call
var labTestRangesHash;

// https://github.com/mbostock/d3/wiki/Force-Layout
// http://bl.ocks.org/mbostock/4062045
function forceChart(groupDaysByInterval, timeSpanFromRangeSlider){ 
	// update display value
	$("#forceChartSliderDaysValue").text(groupDaysByInterval.toFixed(0));
	
	var timeSpanHash = { 'minTime' : new Date().getTime(), 'maxTime' : 0 };   
     var dataHash = {};
     processProblemsData(timeSpanHash, dataHash, "#ff7f0e", "#problemsDropDown");	 		
     processMedicationsData(timeSpanHash, dataHash, "#1f77b4", "#medicationsDropDown");  	
     processProceduresData(timeSpanHash, dataHash, "#9467bd", "#proceduresDropDown");  	
     processAllergiesData(timeSpanHash, dataHash, "#d62728", "#allergiesDropDown");  	    
     processImmunizationsData(timeSpanHash, dataHash, "#17becf", "#immunizationsDropDown"); 
     processEncountersData(timeSpanHash, dataHash, "#2ca02c", "#encountersDropDown");  		
     processLabsData(timeSpanHash, dataHash, "#bcbd22", "#labsDropDown");  					

	var times = [] ;
	Object.keys(dataHash).forEach(function (key) { 
		dataHash[key].times.sort(function (a, b) { return a.starting_time - b.starting_time; });
		times = times.concat(dataHash[key].times);
	});	
	times.sort(function (a, b) { return a.starting_time - b.starting_time; });
	
	// limit data set to be within date range slider
	if (timeSpanFromRangeSlider){
		times = times.filter(
 		    function (d) {
 		        return (d.starting_time >= timeSpanFromRangeSlider.minTime && d.starting_time <= timeSpanFromRangeSlider.maxTime) ? true : false;
 		    }
 		);
	}
	var nest = d3.nest().key(function(d) { return toolTipFormat(new Date(d.starting_time)); }).entries(times);	
	var graph = { "nodes":[	], "links":[ ]}; 
	for (var x=0;x<nest.length;x++) {
		var centerNodeIndex = 1;
		var centerNode = {'name' : { 'header' : nest[x].key, 'content' : "  ", 'color' : "black" , 'link' : ""}};
		var previousNodeDatePlusInterval = "";
		if (x > 0){
			previousNodeDatePlusInterval = toolTipFormat.parse(nest[x-1].key);
			previousNodeDatePlusInterval = previousNodeDatePlusInterval.addDays(groupDaysByInterval);
		}
		if (nest[x].values.length > 1){ 
			graph.nodes.push(centerNode);
			if (graph.nodes.length > 1 && previousNodeDatePlusInterval >= toolTipFormat.parse(nest[x].key)) {
				if (nest[x-1].values.length > 1) {
					// link center node to preceding center node
					var link = {"source":graph.nodes.length - 2 - nest[x-1].values.length,"target":graph.nodes.length-1,"value":1};
					graph.links.push(link);
				} else {
					// link center node to preceding individual event
					var link = {"source":graph.nodes.length-2,"target":graph.nodes.length-1,"value":1};
					graph.links.push(link);
				}
			}
			centerNodeIndex = graph.nodes.length; 
		}
		for (var y=0;y<nest[x].values.length;y++) {
			var node = {'name' : nest[x].values[y] };
			graph.nodes.push(node);
			if (nest[x].values.length > 1){ 
				// link event to its center node
				var link = {"source":centerNodeIndex-1,"target":graph.nodes.length-1,"value":1};
				graph.links.push(link);
			} else if (graph.nodes.length > 1 && previousNodeDatePlusInterval >= toolTipFormat.parse(nest[x].key)) {
				if (x > 0 && nest[x-1].values.length > 1) {
					// link individual event to preceding center node
					var link = {"source":graph.nodes.length - 2 - nest[x-1].values.length,"target":graph.nodes.length-1,"value":1};
					graph.links.push(link);
				} else {
					// link individual event to preceding individual event
					var link = {"source":graph.nodes.length-2,"target":graph.nodes.length-1,"value":1};
					graph.links.push(link);
				}
			}
		}
	}	    
	
	var width = 960, height = 500;

var force = d3.layout.force()
    .charge(-120)
    .gravity(0.2)
    .linkDistance(40)
    .size([width, height]);

if(d3.select("#forceChartSVG")){
	d3.select("#forceChartSVG").remove();
}

 var svg = d3.select(".forceChart").append("svg").attr("id", "forceChartSVG")
    .attr("width", width)
    .attr("height", height); 

  force.nodes(graph.nodes).links(graph.links).start();

  var link = svg.selectAll(".link")
      .data(graph.links)
    .enter().append("line")
      .attr("class", "link")
      .style("stroke-width", function(d) { return Math.sqrt(d.value); });

  var node = svg.selectAll(".node")
      .data(graph.nodes)
    .enter().append("circle")
      .attr("class", "node")
      .attr("r", 7)
      .attr("qtip-title", function(d) { return d.name.header; })
      .attr("title", function(d) { 	return d.name.content; })
    //  .attr("ondblclick", function(d, i) { return "$('#blueButtonTabs a[href=\""+d.name.link+"\"]').tab('show');"; })
      .attr("onclick", function(d, i) { return "$('"+d.name.link+"').click();"; })
      .style("fill", function(d) { return d.name.color; })
      .call(force.drag);
  
  force.on("tick", function() {
    link.attr("x1", function(d) { return d.source.x; })
        .attr("y1", function(d) { return d.source.y; })
        .attr("x2", function(d) { return d.target.x; })
        .attr("y2", function(d) { return d.target.y; });

    node.attr("cx", function(d) { return d.x; })
        .attr("cy", function(d) { return d.y; });
  });
  
}
</script>

<!-- angular -->
<script>

function searchScroll(link,parent){
	var button = document.getElementById('button-'+parent);
	if(button.className.indexOf("glyphicon-chevron-right")>-1){
		button.click();
	}
	$('html, body').animate({ scrollTop: $("#"+link).offset().top -60 }, 1000);
}

var bbArray = [];
var app = angular.module('app', ['ui.grid','ui.grid.resizeColumns', 'ui.grid.selection', 'ui.grid.autoResize','ui.grid.expandable','angular-advanced-searchbox']);

//var fullData;
app.controller('MainController', ['$scope', '$http', 'uiGridConstants',  function ($scope, $http, uiGridConstants) {
	$scope.scrollSpyHeight= $('#scroll-spy-view').height();
	
	angular.element(window).on("resize", function() {
		$scope.scrollSpyHeight = $('#scroll-spy-view').height();
    });
		
	$('.navbar li a').click(function(event) {
	    event.preventDefault();
	    $($(this).attr('href'))[0].scrollIntoView();
	    scrollBy(0, -$scope.scrollSpyHeight);
	});
	
	$scope.availableSearchParams= [{name:"Medications", key:"m"},{name:"Labs", key:"l"},
	                               {name:"Vitals", key:"v"},{name:"Problems", key:"pr"},
	                               {name:"Allergies", key:"a"},{name:"Immunizations", key:"i"},
	                               {name:"Procedures", key:"proc"},{name:"Encounters", key:"e"}];	
			
	var body = document.getElementsByTagName("body")[0];
	var darkness = document.createElement('div');
	darkness.setAttribute("id","darkness");
	body.appendChild(darkness);
	
	// for use by expandable rows in grid
	$scope.$scope = $scope;
	
	$scope.getTableHeight = function(data, y){
		if(data){
			var count = data.length;
			var listOfOpenSubGrids = [];
			var rowHeight = 36; var openCount=0;
			var headerHeight = 92;
			var subGridHeight = 0;
			for(var x=0; x<data.length; x++){
				var button = document.getElementById("button-"+data[x].id);
				if(button && button.className.indexOf("glyphicon-chevron-down")>-1){
					listOfOpenSubGrids.push(data[x].subGrid.length);
				}
			}
			if(listOfOpenSubGrids.length>0){
				listOfOpenSubGrids = listOfOpenSubGrids.sort(function(a,b){return b-a;});
				y.expandableRowHeight = (listOfOpenSubGrids[0]*(rowHeight-6))+62;
				subGridHeight=y.expandableRowHeight*listOfOpenSubGrids.length;
			}
			
			return ((count*rowHeight)+ headerHeight+subGridHeight) +"px";
		}
	};
	
  $scope.configureSubGrids = function(timeSpanHash){
	//immunizations grid
	if($scope.bbData.immunizations){
		for (var x=0;x<$scope.bbData.immunizations.length;x++) {
		   	$scope.bbData.immunizations[x].displaySubGridCount = function() { return ((this.subGrid) ? this.subGrid.length : 1);}		  	       
		   	// add sub grid
			$scope.bbData.immunizations[x].subGridOptions = { enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER,
				enableSorting : true, enableFiltering : true,
	  	        columnDefs: [  { name:'date', field: "displayDate", headerCellClass: 'bg-info', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>',
					sort: { direction: uiGridConstants.DESC, priority: 0}, 
					//sortingAlgorithm: function(a, b) { return medDateSort(a,b); },
					enableFiltering: false },{field: "source", width: '20%', headerCellClass: 'bg-info'}],    						          
	  	        data: $scope.bbData.immunizations[x].subGrid
	  	     	};	
		 	} 
			$scope.immunizationsGrid.data = $scope.bbData.immunizations;		   			
	   	}
	//labs grid
	if ($scope.bbData.labResultsGrid){
		// add sub grid definition
	   	for (var x=0;x<$scope.bbData.labResultsGrid.length;x++) {
	   		$scope.bbData.labResultsGrid[x].displaySubGridCount = function() { return ((this.subGrid) ? this.subGrid.length : 1);}
			    $scope.bbData.labResultsGrid[x].subGridOptions = {
			    		enableSorting : true, enableFiltering : true, enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER,
 	            		columnDefs: [  { name:'date', headerCellClass: 'bg-info',cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>',
							sort: { direction: uiGridConstants.DESC, priority: 0}, 
							//sortingAlgorithm: function(a, b) { return medDateSort(a,b); },
							enableFiltering: false },
			            	{ name: 'value', field: 'displayValue', headerCellClass: 'bg-info', cellTemplate: '<div class="ui-grid-cell-contents ng-binding ng-scope" ng-class ="{ \'text-danger bold\' : row.entity.statsInformation.inNormalRange==false}"">{{row.entity[col.field]}}</div>'},
							{field: "normalRange", name: "referenceRange", headerCellClass: 'bg-info'},{field: "source", width: '20%', headerCellClass: 'bg-info'}],
 	              		data: $scope.bbData.labResultsGrid[x].subGrid
 	            };
	   		}
		   	$scope.labsGrid.data = $scope.bbData.labResultsGrid;
		}
	// load Vitals grid
	if ($scope.bbData.vitalsGrid){
		// add sub grid definition
		for (var x=0;x<$scope.bbData.vitalsGrid.length;x++) {
			$scope.bbData.vitalsGrid[x].displaySubGridCount = function() { return ((this.subGrid) ? this.subGrid.length : 1);}
		  	// add sub grid
			$scope.bbData.vitalsGrid[x].subGridOptions = { enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 	
				enableSorting : true, enableFiltering : true,
	  	        columnDefs: [  { name:'date', headerCellClass: 'bg-info', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', 
					sort: { direction: uiGridConstants.DESC, priority: 0}, 
					//sortingAlgorithm: function(a, b) { return medDateSort(a,b); },
					enableFiltering: false },
				    { name: 'value', field: 'displayValue', headerCellClass: 'bg-info', cellTemplate: '<div class="ui-grid-cell-contents ng-binding ng-scope" ng-class ="{ \'text-danger bold\' : row.entity.statsInformation.inNormalRange==false}"">{{row.entity[col.field]}}</div>'},
				    {name: 'labTestRange', field: 'normalRange', headerCellClass: 'bg-info'},{field: "source", width: '20%', headerCellClass: 'bg-info'}
				    ],
	  	            data: $scope.bbData.vitalsGrid[x].subGrid
	  	        };
		   	}		   		 
		$scope.vitalsGrid.data = $scope.bbData.vitalsGrid;
		}
	// load Medications grid
	if ($scope.bbData.medications){
		// add sub grid definition
		for (var x=0;x<$scope.bbData.medications.length;x++) {
			$scope.bbData.medications[x].displaySubGridCount = function() { return ((this.subGrid) ? this.subGrid.length : 1);}
		  	// add sub grid
			$scope.bbData.medications[x].subGridOptions = { enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 	
				enableSorting : true, enableFiltering : true,
	  	        columnDefs: [  { name:'startDate', headerCellClass: 'bg-info', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', 
					sort: { direction: uiGridConstants.DESC, priority: 0}, 
					//sortingAlgorithm: function(a, b) { return medDateSort(a,b); },
					enableFiltering: false },{field: "endDate", headerCellClass: 'bg-info'},{field: "dose", headerCellClass: 'bg-info'},
					{field: "rate", headerCellClass: 'bg-info'},{field: "schedule.name", headerCellClass: 'bg-info'},{field: "precondition.name", headerCellClass: 'bg-info'},{field: "reason.name", headerCellClass: 'bg-info'},{field: "source", width: '20%', headerCellClass: 'bg-info'}
					],
	  	            data: $scope.bbData.medications[x].subGrid
	  	        };
		   	}		   		 
		$scope.medicationsGrid.data = $scope.bbData.medications;
		
		}
	 
	//load encounters
	if ($scope.bbData.encounters){
		// add sub grid definition
		for (var x=0;x<$scope.bbData.encounters.length;x++) {
			$scope.bbData.encounters[x].displaySubGridCount = function() { return ((this.subGrid) ? this.subGrid.length : 1);}
		  	// add sub grid
			$scope.bbData.encounters[x].subGridOptions = { enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 	
				enableSorting : true, enableFiltering : true,
	  	        columnDefs: [  { name:'date', field: "displayDate", headerCellClass: 'bg-info', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', 
					sort: { direction: uiGridConstants.DESC, priority: 0}, 
					//sortingAlgorithm: function(a, b) { return medDateSort(a,b); },
					enableFiltering: false },
				    { field: 'findings', headerCellClass: 'bg-info'},{field: 'performer',headerCellClass: 'bg-info'},{field: 'organization',headerCellClass: 'bg-info'},{field: "source", width: '20%', headerCellClass: 'bg-info'}],
	  	            data: $scope.bbData.encounters[x].subGrid
	  	        };
		   	}		   		 
		$scope.encountersGrid.data = $scope.bbData.encounters;
		}
	
	//load procedures
	if ($scope.bbData.procedures){
		// add sub grid definition
		for (var x=0;x<$scope.bbData.procedures.length;x++) {
			$scope.bbData.procedures[x].displaySubGridCount = function() { return ((this.subGrid) ? this.subGrid.length : 1);}
		  	// add sub grid
			$scope.bbData.procedures[x].subGridOptions = { enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 	
				enableSorting : true, enableFiltering : true,
	  	        columnDefs: [{name : 'date', field: 'displayDate', headerCellClass: 'bg-info', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', 
					sort: { direction: uiGridConstants.DESC, priority: 0}, 
					//sortingAlgorithm: function(a, b) { return medDateSort(a,b); },
					enableFiltering: false },
				    { field: 'device', headerCellClass: 'bg-info'},{field: 'specimen',headerCellClass: 'bg-info'},{field: 'organization',headerCellClass: 'bg-info'},{field: "source", width: '20%', headerCellClass: 'bg-info'}],
	  	            data: $scope.bbData.procedures[x].subGrid
	  	        };
		   	}	 		 
		$scope.proceduresGrid.data = $scope.bbData.procedures;
		}
	
	//load allergies
	if ($scope.bbData.allergies){
		// add sub grid definition
		for (var x=0;x<$scope.bbData.allergies.length;x++) {
			$scope.bbData.allergies[x].displaySubGridCount = function() { return ((this.subGrid) ? this.subGrid.length : 1);}
		  	// add sub grid
			$scope.bbData.allergies[x].subGridOptions = { enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 	
				enableSorting : true, enableFiltering : true,
	  	        columnDefs: [{name : 'date', field: 'startDate', headerCellClass: 'bg-info', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>',
					sort: { direction: uiGridConstants.DESC, priority: 0}, 
					//sortingAlgorithm: function(a, b) { return medDateSort(a,b); },
					enableFiltering: false },{field: "endDate", headerCellClass: 'bg-info'},{ field: 'reactionName', headerCellClass: 'bg-info'},
					{field: 'reactionType',headerCellClass: 'bg-info'},{field: 'severity', headerCellClass: 'bg-info'}, {field: "status", headerCellClass: 'bg-info'},{field: "source", width: '20%', headerCellClass: 'bg-info'}],
	  	        data: $scope.bbData.allergies[x].subGrid
	  	        };
		   	}		   		 
		$scope.allergiesGrid.data = $scope.bbData.allergies;
		}
	
	//load problems
	if ($scope.bbData.problems){
		// add sub grid definition
		for (var x=0;x<$scope.bbData.problems.length;x++) {
			$scope.bbData.problems[x].displaySubGridCount = function() { return ((this.subGrid) ? this.subGrid.length : 1);}
		  	// add sub grid
			$scope.bbData.problems[x].subGridOptions = { enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 	
				enableSorting : true, enableFiltering : true,
	  	        columnDefs: [{field: 'startDate', headerCellClass: 'bg-info', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>',
					sort: { direction: uiGridConstants.DESC, priority: 0}, 
					//sortingAlgorithm: function(a, b) { return medDateSort(a,b); },
					enableFiltering: false },{field: "endDate", headerCellClass: 'bg-info'},{field: "status", headerCellClass: 'bg-info'},{field: "source", width: '20%', headerCellClass: 'bg-info'}],
	  	        data: $scope.bbData.problems[x].subGrid
	  	        };
		   	}	   		 
		$scope.problemsGrid.data = $scope.bbData.problems;
		}
	
  	}
  
  // load all blue button records into an array to be sent to the server
  $("script#xmlBBData").each(function() {
	  var bb =  BlueButton($(this).text());
	  bbArray.push(bb.data)
	});
    $("#forceChartSlider").noUiSlider({start: 30,step: 1,connect: "lower",range: {'min': 0, 'max': 365}});
    	
    $("#forceChartSlider").on({
    	change: function(){
			var timeSpanHash = { 'minTime' : $("#dateRangeSlider").val()[ 0 ], 'maxTime' : $("#dateRangeSlider").val()[ 1 ] };   
   			forceChart(+$("#forceChartSlider").val(), timeSpanHash); drawQtips();}
    	});
    	
    $("#forceChartSlider").noUiSlider_pips({mode: 'values',values: [0,30, 60, 90, 120, 150, 180, 210,240,270,300,330,365],density: 5});

	$("#dateRangeSlider").on({
		    /*
		     * Update sections after slider date change
		     */
	  		change: function(){
	  			var timeSpanHash = { 'minTime' : $("#dateRangeSlider").val()[ 0 ], 'maxTime' : $("#dateRangeSlider").val()[ 1 ] }; 
	  			// update display value for modals and page header
	  			$scope.selectedDateRange = toolTipFormat(new Date(+timeSpanHash.minTime)) + " to " + toolTipFormat(new Date(+timeSpanHash.maxTime));
	  			// create date range for server side call
	  			var hash = {}; 
	  			hash.records= bbArray; // bbArray is a global variable
	  		  	hash.startDateInMillis = new Date(+timeSpanHash.minTime).getTime();
	  			hash.endDateInMillis = new Date(+timeSpanHash.maxTime).getTime();
	  			var dateRange = "&startDateInMillis="+hash.startDateInMillis+"&endDateInMillis="+hash.endDateInMillis;
	  			$http.post("<c:url value='/blue-button/byPerson?personId=${personId}"+dateRange+"'/>").success(function(response) {
	  				labTestRangesHash = response.ranges;
	  		   	    //Slider updaters
	  			    $scope.bbData=response;
	  		   	    fullData = response;
	  		   	    // show/hide sections
	  		   	    showHideSections($scope.bbData);
		  			$scope.proceduresGrid.data = $scope.bbData.procedures;		 
		  			$scope.configureSubGrids(timeSpanHash);
		  			// update timelines	
	   		    	condensedTimeline(timeSpanHash);  	
	   		    	problemsTimeline(timeSpanHash);
	   		    	medicationsTimeline(timeSpanHash);
	   		    	allergiesTimeline(timeSpanHash);
	   		    	proceduresTimeline(timeSpanHash);
	   		    	immunizationsTimeline(timeSpanHash);
	   		    	labsTimeline(timeSpanHash);
			    	encountersTimeline(timeSpanHash);
	   		    	forceChart(+$("#forceChartSliderDaysValue").text(), timeSpanHash);
	   		    	drawQtips();
	  			});
	  		}
	  	});
	    /*
	     * initialize all sections
	     */
	// define medications grid
	$scope.medicationsGrid = { 
		expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions" class="blueButtonUISubGrid"></div>',
		rowHeight: 36,
		expandableRowHeight: 200, // want this value to match that of .blueButtonUISubGrid
		enableExpandableRowHeader: false, showColumnFooter: true,  enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 
		enableColumnResizing : true, showGridFooter : false, showColumnFooter : true, enableSorting : true, enableFiltering : true, 
		onRegisterApi : function( gridApi ) { $scope.mGrid = gridApi; }, 
		columnDefs: [{name:'count', field: 'displaySubGridCount()', width: 80,  
	 		cellTemplate: '<button id="button-{{row.entity.id}}" class="btn btn-primary" ng-class="{ \'glyphicon glyphicon-chevron-right\' : !row.isExpanded, \'glyphicon glyphicon-chevron-down\' : row.isExpanded }"  ng-click="$scope.ghmgh(); grid.api.expandable.toggleRowExpansion(row.entity);"> {{COL_FIELD}}</button>'},
	 		{ field: 'name',name: "Medication Name", width: '20%', aggregationType: uiGridConstants.aggregationTypes.count},
			{ field: 'startDate', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>',  sort: { direction: uiGridConstants.DESC, priority: 0},
	 			//sortingAlgorithm: function(a, b) { return medDateSort(a,b); } 
			},
			{ field: 'endDate', sort: { direction: uiGridConstants.DESC, priority: 1}, 
	 				//sortingAlgorithm: function(a, b) { return medDateSort(a,b); } 
			},
			{ field: 'current'},{ field: 'dose' },{field: "source", width: '20%'}]
		};
	    		    
	//define Procedures grid
	$scope.proceduresGrid={
		expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions" class="blueButtonUISubGrid"></div>',
		expandableRowHeight: 200, // want this value to match that of .blueButtonUISubGrid
		rowHeight: 36,
		enableExpandableRowHeader: false,
		showColumnFooter: true, enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 
		enableColumnResizing : true, showGridFooter : false, showColumnFooter : true, enableSorting : true, enableFiltering : true, 
		onRegisterApi : function( gridApi ) { $scope.prGrid = gridApi; }, 
		columnDefs: [{name:'count', field: 'displaySubGridCount()', width: 80,  
	 		cellTemplate: '<button id="button-{{row.entity.id}}" class="btn btn-primary" ng-class="{ \'glyphicon glyphicon-chevron-right\' : !row.isExpanded, \'glyphicon glyphicon-chevron-down\' : row.isExpanded }"  ng-click="grid.api.expandable.toggleRowExpansion(row.entity)"> {{COL_FIELD}}</button>'},{ name: "proceduresName",field: 'name', aggregationType: uiGridConstants.aggregationTypes.count},
			{ name: "date", cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', field: 'displayDate', sort: { direction: uiGridConstants.DESC, priority: 0}, 
	 			//sortingAlgorithm: function(a, b) { return medDateSort(a,b); } 
			},
			{ field : 'device'}, { field: 'specimen'}, {field: 'organization'},{field: "source", width: '20%'}
		]};
	
	//define Allergies grid
	$scope.allergiesGrid={ 
			expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions" class="blueButtonUISubGrid"></div>',
		    expandableRowHeight: 200,	// want this value to match that of .blueButtonUISubGrid
		    rowHeight: 36,
		    enableExpandableRowHeader: false,	
		    showColumnFooter: true,
				 enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 
				   enableColumnResizing : true, showGridFooter : false, showColumnFooter : true, enableSorting : true, enableFiltering : true, 
					   onRegisterApi : function( gridApi ) { $scope.aGrid = gridApi; }, 
					  columnDefs: [{name:'count', field: 'displaySubGridCount()', width: 80,  
					   	 		cellTemplate: '<button id="button-{{row.entity.id}}" class="btn btn-primary" ng-class="{ \'glyphicon glyphicon-chevron-right\' : !row.isExpanded, \'glyphicon glyphicon-chevron-down\' : row.isExpanded }"  ng-click="grid.api.expandable.toggleRowExpansion(row.entity)"> {{COL_FIELD}}</button>'},{ name: "allergiesName", field: 'name', aggregationType: uiGridConstants.aggregationTypes.count},
					  			   { field: 'startDate', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', sort: { direction: uiGridConstants.DESC, priority: 0}, 
					   	 			//sortingAlgorithm: function(a, b) { return medDateSort(a,b); } 
					  			   },
		               			   { field: 'endDate', sort: { direction: uiGridConstants.DESC, priority: 1}, 
					  				   //sortingAlgorithm: function(a, b) { return medDateSort(a,b); } 
		               			   },
					  			   { field : 'reactionName'},{ field : 'reactionType'}, {field : 'severity'},{field: "source", width: '20%'}
				]};
		
	//define Encounters
	$scope.encountersGrid={ showColumnFooter: true,
		expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions" class="blueButtonUISubGrid"></div>',
		expandableRowHeight: 200, // want this value to match that of .blueButtonUISubGrid
		rowHeight: 36,
	    enableExpandableRowHeader: false,
		enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 
		enableColumnResizing : true, showGridFooter : false, showColumnFooter : true, enableSorting : true, enableFiltering : true,  
		onRegisterApi : function( gridApi ) { $scope.eGrid = gridApi; }, 
		columnDefs: [{name:'count', field: 'displaySubGridCount()', width: 80,  
			 		cellTemplate: '<button id="button-{{row.entity.id}}" class="btn btn-primary" ng-class="{ \'glyphicon glyphicon-chevron-right\' : !row.isExpanded, \'glyphicon glyphicon-chevron-down\' : row.isExpanded }"  ng-click="grid.api.expandable.toggleRowExpansion(row.entity)"> {{COL_FIELD}}</button>' 
			 		},{name: 'encounterName', field: 'name', aggregationType: uiGridConstants.aggregationTypes.count},
					{ field: 'displayDate',cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', name: 'date', sort: { direction: uiGridConstants.DESC, priority: 0}, 
			 			//sortingAlgorithm: function(a, b) { return medDateSort(a,b); } 
					},
	   				{ field: 'findings'},{ field : 'performer'},{field: 'organization'},{field: "source", width: '20%'}
					]};
	
    // define lab grid even if it doesn't get populated
	$scope.labsGrid = { 
			expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions" class="blueButtonUISubGrid"></div>',
		    expandableRowHeight: 200, // want this value to match that of .blueButtonUISubGrid
		    enableExpandableRowHeader: false,	
		    showColumnFooter: true, enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 
		                enableColumnResizing : true, showGridFooter : false, showColumnFooter : true, enableSorting : true, enableFiltering : true,
		                enableRowSelection: true,  enableSelectAll: true, selectionRowHeaderWidth: 35, rowHeight: 36,
		                onRegisterApi : function( gridApi ) { $scope.lGrid = gridApi;  }, 
						columnDefs: [ {name:'', field: 'viewChartButton', width: 100, enableFiltering: false, enableSorting : false,
	            			 			cellTemplate: ' <button type="button" ng-class="{ \'btn btn-default\' : !row.isSelected, \'btn btn-primary\' : row.isSelected }" ng-click="grid.api.selection.toggleRowSelection(row.entity); grid.appScope.viewSingleLabChart(row.entity);"  data-toggle="modal" data-target="#labslineModal">View Chart</button>'},   
	            					    {name:'count', field: 'displaySubGridCount()', width: 80,  
				            			 cellTemplate: '<button id="button-{{row.entity.id}}" class="btn btn-primary" ng-class="{ \'glyphicon glyphicon-chevron-right\' : !row.isExpanded, \'glyphicon glyphicon-chevron-down\' : row.isExpanded }"  ng-click="grid.api.expandable.toggleRowExpansion(row.entity)"> {{COL_FIELD}}</button>' 
				           				 },
						               { name: "Lab/Panel Name",field: 'labName', width: '20%', aggregationType: function(visibleRows, self) { return "selected: " + self.grid.api.selection.getSelectedRows().length; }},
						               { name: "resultName",field: 'name', width: '15%'}, 
						               { name:'date', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', sort: { direction: uiGridConstants.DESC, priority: 0},
						            	   //sortingAlgorithm: function(a, b) { return medDateSort(a,b); },
						            	   enableFiltering: false },
									   { name: 'value', field: 'displayValue', cellTemplate: '<div class="ui-grid-cell-contents ng-binding ng-scope important" ng-class ="{ \'text-danger bold\' : row.entity.statsInformation.inNormalRange==false}"">{{row.entity[col.field]}}</div>'},{field: "source", width: '20%'}]
    }; 
    
    // define vitals grid even if it doesn't get populated
	$scope.vitalsGrid = { 
			expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions" class="blueButtonUISubGrid"></div>',
		    expandableRowHeight: 200,	// want this value to match that of .blueButtonUISubGrid
		    enableExpandableRowHeader: false,
		    showColumnFooter: true, enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 
		                enableColumnResizing : true, showGridFooter : false, showColumnFooter : true, enableSorting : true, enableFiltering : true,
		                enableRowSelection: true,  enableSelectAll: true, selectionRowHeaderWidth: 35, rowHeight: 36,
		                onRegisterApi : function( gridApi ) { $scope.vGrid = gridApi; }, 
						columnDefs: [ {name:'', field: 'viewChartButton', width: 100, enableFiltering: false, enableSorting : false,
			    			 			cellTemplate: ' <button type="button" ng-class="{ \'btn btn-default\' : !row.isSelected, \'btn btn-primary\' : row.isSelected }" ng-click="grid.api.selection.toggleRowSelection(row.entity); grid.appScope.viewSingleVitalChart(row.entity);"  data-toggle="modal" data-target="#vitalsModal">View Chart</button>'},   
			           				 {name:'count', field: 'displaySubGridCount()',width: 80,
	            			 			cellTemplate:'<button id="button-{{row.entity.id}}" class="btn btn-primary" ng-class="{ \'glyphicon glyphicon-chevron-right\' : !row.isExpanded, \'glyphicon glyphicon-chevron-down\' : row.isExpanded }"  ng-click="grid.api.expandable.toggleRowExpansion(row.entity)"> {{COL_FIELD}}</button>'},
						               { name: 'name', width: '30%', aggregationType: uiGridConstants.aggregationTypes.count},
						               { name:'date', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', aggregationType: function(visibleRows, self) { return "selected: " + self.grid.api.selection.getSelectedRows().length; },
						            	   sort: { direction: uiGridConstants.DESC, priority: 0}, 
						            	   //sortingAlgorithm: function(a, b) { return medDateSort(a,b); },
						            	   enableFiltering: false },
									   { name: 'value', field: 'displayValue', cellTemplate: '<div class="ui-grid-cell-contents ng-binding ng-scope" ng-class ="{ \'text-danger bold\' : row.entity.statsInformation.inNormalRange==false}"">{{row.entity[col.field]}}</div>'},{field: "source", width: '20%'}
									   ] 
					   }; 
    
	//define immunizations
   	$scope.immunizationsGrid={ showColumnFooter: true,
   		expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions" class="blueButtonUISubGrid"></div>',
		expandableRowHeight: 200, // want this value to match that of .blueButtonUISubGrid
		rowHeight: 36,
		enableExpandableRowHeader: false,
   		enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 
		enableColumnResizing : true, showGridFooter : false, showColumnFooter : true, enableSorting : true, enableFiltering : true,  
		onRegisterApi : function( gridApi ) { $scope.iGrid = gridApi; }, 
		columnDefs: [
			{name:'count', field: 'displaySubGridCount()',width: 80,
			cellTemplate:'<button id="button-{{row.entity.id}}" class="btn btn-primary" ng-class="{ \'glyphicon glyphicon-chevron-right\' : !row.isExpanded, \'glyphicon glyphicon-chevron-down\' : row.isExpanded }"  ng-click="grid.api.expandable.toggleRowExpansion(row.entity);"> {{COL_FIELD}}</button>'},
			{ name: 'immunizationName', field: 'name', aggregationType: uiGridConstants.aggregationTypes.count},
			{ field: 'displayDate',name: "Date", cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', sort: { direction: uiGridConstants.DESC, priority: 0}, sortingAlgorithm: function(a, b) { return medDateSort(a,b); } },{field: "source", width: '20%'}
			]
	};
	
	//define problems
   	$scope.problemsGrid={ showColumnFooter: true,
   			expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions" class="blueButtonUISubGrid"></div>',
   			expandableRowHeight: 200, // want this value to match that of .blueButtonUISubGrid
   			rowHeight: 36,
   			enableExpandableRowHeader: false,
			 enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER, 
			 enableColumnResizing : true, showGridFooter : false, showColumnFooter : true, enableSorting : true, enableFiltering : true,  
			 onRegisterApi : function( gridApi ) { $scope.pGrid = gridApi; }, 
		     columnDefs: [{name:'count', field: 'displaySubGridCount()', width: 80,  
			 		cellTemplate: '<button id="button-{{row.entity.id}}" class="btn btn-primary" ng-class="{ \'glyphicon glyphicon-chevron-right\' : !row.isExpanded, \'glyphicon glyphicon-chevron-down\' : row.isExpanded }"  ng-click="grid.api.expandable.toggleRowExpansion(row.entity)"> {{COL_FIELD}}</button>'},
			 			  { field: 'name',name: 'problemsName', aggregationType: uiGridConstants.aggregationTypes.count},
				  		  { field: 'startDate', cellTemplate: '<div id={{row.entity.id}}>{{row.entity[col.field]}}</div>', sort: { direction: uiGridConstants.DESC, priority: 0}, sortingAlgorithm: function(a, b) { return medDateSort(a,b); } },
	               		  { field: 'endDate', sort: { direction: uiGridConstants.DESC, priority: 1}, sortingAlgorithm: function(a, b) { return medDateSort(a,b); } },
				  		  { field : 'status'},{field: "source", width: '20%'}
			]};
    //View controlsers for labs and vitals
	$scope.selectAllLabs = function() {$scope.lGrid.selection.selectAllRows();};
    $scope.clearAllLabs = function() { $scope.lGrid.selection.clearSelectedRows();};
    $scope.collapseAllLabs = function() {$scope.lGrid.expandable.collapseAllRows(); }
    $scope.selectAllVitals = function() { $scope.vGrid.selection.selectAllRows();};
    $scope.clearAllVitals = function() {$scope.vGrid.selection.clearSelectedRows(); };
    $scope.collapseAllVitals = function() {$scope.vGrid.expandable.collapseAllRows();}
    
    function getNormalMinMax(data){
    	var range= {}; var min, max;
    	for(var x=0; x<data.length; x++){
    		if(data[x].normalMin){range.min=data[x].normalMin; min=true;}
    		if(data[x].normalMax){range.max=data[x].normalMax;max=true;}
    		if(min && max){break;}
    	}
    	return range;
    }
    
    // set up View Individual Chart button
    $scope.viewSingleLabChart = function(lab) {
    	var timeSpanHash = { 'minTime' : $("#dateRangeSlider").val()[ 0 ], 'maxTime' : $("#dateRangeSlider").val()[ 1 ] };   
		var listOfRows = []; 
		var labNamesArray = [];
		for(var x=0; x<lab.subGrid.length; x++){
			var labRow = lab.subGrid[x];
			if(labRow.dateInMillis && labRow.value && labRow.unit){
				listOfRows.push(labRow);
			}
		}
		if(listOfRows.length>0){
			var chart = {};
			chart.data =listOfRows;
			chart.range = getNormalMinMax(chart.data);
			labNamesArray.push(chart);
		}
		$( ".labslineCharts" ).empty();
		displayLineCharts(".labslineCharts", labNamesArray,timeSpanHash);
	   	};
	   	
   	// set up View Charts button
    $scope.viewLabsCharts = function() {
	   	var timeSpanHash = { 'minTime' : $("#dateRangeSlider").val()[ 0 ], 'maxTime' : $("#dateRangeSlider").val()[ 1 ] };   
   	    // get list of all keys (i.e., lab names)
   	    var rows = $scope.lGrid.selection.getSelectedRows($scope.labsGrid);
		var labNamesArray = [];
		for (var x=0;x<rows.length;x++) {
			var listOfRows = [];
			for(var y=0; y<rows[x].subGrid.length; y++){
				var labRow = rows[x].subGrid[y];
				if(labRow.dateInMillis && labRow.value && labRow.unit){
					listOfRows.push(labRow);
				}
			}
			if(listOfRows.length>0){
				var chart = {};
				chart.data =listOfRows;
				chart.range = getNormalMinMax(chart.data);
				labNamesArray.push(chart);
			}
		}
		$( ".labslineCharts" ).empty();
		displayLineCharts(".labslineCharts", labNamesArray,timeSpanHash);
   	  };
 	  	  
	// set up View Individual Chart button
	$scope.viewSingleVitalChart = function(vital) {
		var timeSpanHash = { 'minTime' : $("#dateRangeSlider").val()[ 0 ], 'maxTime' : $("#dateRangeSlider").val()[ 1 ] };   
		var listOfRows = [];
		var vitalsNamesArray = [];
		for(var x=0; x<vital.subGrid.length; x++){
			var vitalRow = vital.subGrid[x];
			if(vitalRow.dateInMillis && vitalRow.value && vitalRow.unit){
				listOfRows.push(vitalRow);
			}
		}
		if(listOfRows.length>0){
			var chart = {};
			chart.data =listOfRows;
			chart.range = getNormalMinMax(chart.data);
			vitalsNamesArray.push(chart);
		}
		$( ".vitalsCharts" ).empty();
		displayLineCharts(".vitalsCharts", vitalsNamesArray,timeSpanHash); 
		};    
	   	  // set up View Charts button
	$scope.viewVitalsCharts = function() {
		var timeSpanHash = { 'minTime' : $("#dateRangeSlider").val()[ 0 ], 'maxTime' : $("#dateRangeSlider").val()[ 1 ] };   
   	    // get list of all keys (i.e., lab names)
   	    var rows = $scope.vGrid.selection.getSelectedRows($scope.vitalsGrid);
		var vitalsNamesArray = [];
		for (var x=0;x<rows.length;x++) {
			var listOfRows = [];
			for(var y=0; y<rows[x].subGrid.length; y++){
				var vitalRow = rows[x].subGrid[y];
				if(vitalRow.dateInMillis && vitalRow.value && vitalRow.unit){
					listOfRows.push(vitalRow);
				}
			}
			if(listOfRows.length>0){
				var chart = {};
				chart.data =listOfRows;
				chart.range = getNormalMinMax(chart.data);
				vitalsNamesArray.push(chart);
			}
		}
		$( ".vitalsCharts" ).empty();
		displayLineCharts(".vitalsCharts", vitalsNamesArray,timeSpanHash);		  	   
	   	};
	   	
   	var hash = {}; 
	hash.records= bbArray; // bbArray is a global variable
	var dateRange = "&startDateInMillis=-1&endDateInMillis=-1";
		$http.post("<c:url value='/blue-button/byPerson?personId=${personId}"+dateRange+"'/>").success(function(response) {
   		labTestRangesHash = response.ranges;
   		google.maps.event.addDomListener(window, 'load', initializeGoogleMap(response.locations));
   	   $scope.bbData=response;
   	   fullData = response;
   	    // show/hide sections
   	    showHideSections($scope.bbData);
   	    
   	   var lowestTime = (response.timeRanges.maxTime -(86400000*365) >response.timeRanges.minTime) ? response.timeRanges.maxTime -(86400000*365) : response.timeRanges.minTime;
   	   var initialTimeSpanHash = { earliestTime: response.timeRanges.minTime, minTime: lowestTime , maxTime: response.timeRanges.maxTime };
       	$("#dateRangeSlider").noUiSlider({
    		start: [initialTimeSpanHash.minTime, initialTimeSpanHash.maxTime],
    		connect: true,
    		range: {
    			'min': initialTimeSpanHash.earliestTime,
    			'max': initialTimeSpanHash.maxTime
    		}	
    	});
   	   
   	$("#dateRangeSlider").noUiSlider_pips({
		mode: 'positions',
		values: [0,17,33,49,67,83,100],
		density: 5,
		format: {
			  to: function ( value ) { return toolTipFormat(new Date(value)); },
			  from: function ( value ) { return toolTipFormat.parse(value).getTime(); }
			}   	
		});
   	   
   	condensedTimeline(initialTimeSpanHash);	
    problemsTimeline(initialTimeSpanHash);
    medicationsTimeline(initialTimeSpanHash);
    allergiesTimeline(initialTimeSpanHash);
    encountersTimeline(initialTimeSpanHash);
    proceduresTimeline(initialTimeSpanHash);
    immunizationsTimeline(initialTimeSpanHash);
    labsTimeline(initialTimeSpanHash);
    forceChart(30,initialTimeSpanHash);
    drawQtips();
	$scope.medicationsGrid.data = $scope.bbData.medications;
	$scope.problemsGrid.data = $scope.bbData.problems; // don't filter by date, always display all records
	$scope.proceduresGrid.data = $scope.bbData.procedures;
	$scope.allergiesGrid.data = $scope.bbData.allergies; // don't filter by date, always display all records
	$scope.configureSubGrids(initialTimeSpanHash);
	$scope.selectedDateRange = toolTipFormat(new Date(initialTimeSpanHash.minTime)) + " to " + toolTipFormat(new Date(initialTimeSpanHash.maxTime));	
	/* refresh all of the grids to they respond to the size of the window */

	var displaySearch= function(container,tableName, conf){
		var that = {}; var table, pos;
		var c = document.getElementById(container);
		var box = document.getElementById('searchInput');
		c.addEventListener('click', function(val){
			var id = val.target.id;
			if(!inList(id) && id != "searchCell" && id!= "searchResults"){
				d.update(s.search($scope.bbData));
				}
			},true);
		var zIndex = c.style.zIndex;
		function update(data){
			listOfSearchCats=[];
			if(document.getElementById(tableName)){
				document.getElementById(tableName).remove();
			}
			if(data){
				$("#darkness").fadeTo(200,1);
				box.style.zIndex=1032;
				pos = c.getBoundingClientRect();
				box.style.position = 'relative';
				table = document.createElement("div");
				table.setAttribute("class", "panel panel-default")
				table.id = tableName;
				table.width = pos.width;
				table.style.left = conf.xOffSet;
				table.style.top = (pos.height+conf.yOffSet)+"px";
				table.style.position = "absolute";
				table.style.zIndex = 1032;
				c.appendChild(table);
		
				for(var x=0; x<data.length; x++){
					addCatagory(data[x]);
				}
			}
			else{
				box.style.zIndex='';
				box.style.position = '';
				$('#darkness').hide();
			}
		}
		
		function addCatagory(object){
			var catagory = document.createElement('div');
			var id = object.name.toLowerCase()+"SearchResult";
			catagory.setAttribute("id", id);
			catagory.style.cursor = "default";
			catagory.setAttribute("class","panel-heading")
			var text = document.createElement('div');
			text.innerHTML = object.name; 
			text.setAttribute("id",id);
			text.setAttribute("onclick","$(\'html, body\').animate({ scrollTop: $('"+object.link+"').offset().top "+(-$scope.scrollSpyHeigh)+" }, 1000)");
			text.style.cursor="pointer";
			text.setAttribute("class","panel-title, bold-on-hover");
			catagory.appendChild(text);
			catagory.style.width = pos.width+"px";
			listOfSearchCats.push(id);
			//takes keyword list and CSV list of found keywords 
			function listToCSV(s, n){
				var endString="";
				for(var x=0; x<s.length; x++){
					//if this element in keywords was a found keyword
					if(x == s.length-1){
						if(n.indexOf(x.toString()) >-1){endString = endString+"<b id='searchCell'>"+s[x]+"</b>";}
						else{endString = endString+s[x];}
						}
					else{
						if(n.indexOf(x.toString()) >-1){endString = endString+"<b id='searchCell'>"+s[x]+"</b>, ";}
						else{endString = endString+s[x]+", ";}
						}
				}
				return endString;
			}
			var ul = document.createElement('ul');
			ul.setAttribute("class","list-group");
			for(var x=0; x<object.data.length; x++){
				var d = object.data[x];
				var li=document.createElement('li');
				li.innerHTML=listToCSV(d.keywords,d.searchData.foundWords);
				li.setAttribute("class","list-group-item underline-on-hover");
				li.setAttribute("id","searchCell");
				li.style.cursor="pointer";
				li.style.width = "100%";
				li.style.color = "black";
				li.setAttribute("onclick","searchScroll('"+d.id+"','"+d.parent+"')");
				ul.appendChild(li);
			}
			catagory.appendChild(ul);
			table.appendChild(catagory);
	
		}
		that.update = update;		
		
		function remove(){
			//code needed here to remove search table from screen
		}
		that.remove = remove;
	
		return that;
	};
		
	var search = function(val){
		var that ={};
		var list;
		var data;
		function search(d){
			list=[];
			data = d;
			searchMeds();
			searchLabs();
			searchVitals();
			searchProblems();
			searchAllergies();
			searchImmunizations();
			searchProcedures();
			searchEncounters();
			if(list.length > 0){
				list.sort(function(a,b){return b.maxCount-a.maxCount});
				return list;
			}
			return null;
		}
		that.search = search;
		
		function removeBlank(array){
			for(var x=0; x<array.length; x++){
				if(array[x]==""){delete array[x];}
			}
			return array;
		}
		
		//splits string on spaces and returns a lowercased array
		function querySplit(){return (val.query) ? removeBlank(val.query.toLowerCase().split(" ")) : [] ;}
		//combines searchs of total query and catagory query
		function combineSplit(s){return removeBlank(unique(s.toLowerCase().split(" "),(val.query) ? val.query.toLowerCase().split(' ') : []));}
		
		function getFromList(d,s){
			var list =[];
				for(var x=0; x<d.length; x++){
					for(var y=0; y<d[x].subGrid.length; y++){
						//for every search paramater
						var hitCounter=0; var foundWords;
						for(var a=0; a<s.length; a++){
							var row= d[x].subGrid[y]; var keywordList = row.keywords;
							//for each keyword
								for(var z=0; z<keywordList.length; z++){
									//if match
									if(keywordList[z]!= null && keywordList[z].toLowerCase().indexOf(s[a]) >-1){
										if(hitCounter==0){hitCounter++; foundWords=z.toString();}
										else{hitCounter++; foundWords=foundWords+" "+z;}
									}
								}
							}
							if(hitCounter>0){
								row.searchData={};
								row.searchData.foundWords = foundWords;
								row.searchData.hitCounter = hitCounter;
								list.push(row);
							}
					}
				}
			if(list.length >0){
				list.sort(function(a,b){return b.searchData.hitCounter-a.searchData.hitCounter});
				return list;}
			return null;
		}
		
		function searchLabs(){
			var s = (val.l) ? combineSplit(val.l) : querySplit();
			var labList = getFromList(data.labResultsGrid,s);
			if(labList){
				var labs = addCatagory(labList, "Labs");
				labs.link = "#labResultsGridChartDisplay"
				list.push(labs);
				}
			}
		
		function searchMeds(){
			var s= (val.m) ? combineSplit(val.m) : querySplit();
			var medsList = getFromList(data.medications,s);
			if(medsList){list.push(addCatagory(medsList, "Medications"));}
			}	

		function searchProblems(){
			var s = (val.pr) ? combineSplit(val.pr) : querySplit();
			var problemsList = getFromList(data.problems,s);
			if(problemsList){list.push(addCatagory(problemsList,"Problems"));}
			}
		
		function searchImmunizations(){
			var s = (val.i) ? combineSplit(val.i) : querySplit();
			var immunizationsList = getFromList(data.immunizations,s);
			if(immunizationsList){list.push(addCatagory(immunizationsList, "Immunizations"));}
			}
		
		function searchAllergies(){
			var s  = (val.a) ? combineSplit(val.a) : querySplit();
			var allergiesList = getFromList(data.allergies,s);
			if(allergiesList){list.push(addCatagory(allergiesList,"Allergies"));}
			}
		
		function searchProcedures(){
			var s = (val.proc) ? combineSplit(val.proc) : querySplit();
			var proceduresList = getFromList(data.procedures,s);
			if(proceduresList){list.push(addCatagory(proceduresList,"Procedures"));}
			}
		
		function searchEncounters(){
			var s = (val.e) ? combineSplit(val.e) : querySplit();
		 	var encountersList = getFromList(data.encounters,s);
		 	if(encountersList){list.push(addCatagory(encountersList,"Encounters"));}
			}
		
		function searchVitals(){
			var s = (val.v) ? combineSplit(val.v) : querySplit();
			var vitalsList = getFromList(data.vitalsGrid,s);
			if(vitalsList){
				var vitals = addCatagory(vitalsList, "Vitals");
				vitals.link = "#vitalsGridChartDisplay";
				list.push(vitals);
			}
		}
		
		function addCatagory(d, name){
			var object ={};
			object.data = d;
			object.name = name;
			object.maxCount = d[0].searchData.hitCounter;
			object.link = "#"+name.toLowerCase()+"ChartDisplay";
			return object;
		}
		
		//removes duplicate values from two lists
		function unique(list1,list2){
			var a = list1.concat(list2);
			for(var x=0; x<a.length; ++x){
				for(var y=x+1; y<a.length; ++y) {
		            if(a[x] === a[y])
		                a.splice(x--, 1);
		        }
			}
			return a;
		}
		return that;
	}
	
	var listOfSearchCats=[]; var s = search({}); var oldVal={};
	var d= displaySearch("searchBox","searchResults", {yOffSet : 20, xOffSet : 0});
	//Search box will refresh the results when the search parameters change
	$scope.$watch("searchParams", function(val){
		if(!hashEquals(val, oldVal)){
			s = search(val); 
			d.update(s.search($scope.bbData));
			oldVal = val;
		}
	});
	
	function hashEquals(v1, v2){
		if(Object.keys(v1).length != Object.keys(v2).length){return false;}
		else{
			var equal=true
			Object.keys(v1).forEach(function(key){
				if(!v2[key]){equal= false;}
				if(v1[key] != v2[key]){equal= false;}		
			});
			return equal;
		}
	}
	
	
	//removes the search table if user clicks off the table
	document.addEventListener('click', function(val){
		var table = document.getElementById("searchResults");
		var id = val.target.id; var name = val.target.name;
		if(table && id != 'searchInput' && name != 'searchbox'){
			table.parentNode.removeChild(table);
			$('#darkness').fadeTo(200,0,function(){
				$('#darkness').hide();
				var box = document.getElementById('searchInput');
				box.style.zIndex='';
				box.style.position = '';
			});
			}
	},true);
	

	function inList(val){
		for(var x=0; x<listOfSearchCats.length; x++){
			if(listOfSearchCats[x]==val){return true;}
		}
		return false;
	}
	
   	});
		
}]);



</Script>

<!-- Utilities -->
<Script>
var timeWidthOfBar = 86400000 * 20; //milliseconds in a day * # of days
var chartMarginLeft = 100;
var chartMarginRight = 15;

function showHideSections(bb){
    var dataSections = ['demographics','allergies','immunizations','encounters','labResultsGrid','problems','procedures','vitalsGrid','medications'];
    	// loop over list of sections and hide ones without data
    for (var x=0;x<dataSections.length;x++) {
    	if((bb[dataSections[x]] && $.isArray(bb[dataSections[x]]) && bb[dataSections[x]].length > 0 ) || (bb[dataSections[x]] && !$.isArray(bb[dataSections[x]]))){
    	   $("#"+dataSections[x] + "Tab").show();
     	   $("#"+dataSections[x] + "ChartDisplay").show();
    	} else {
    	   $("#"+dataSections[x] + "Tab").hide();
    	   $("#"+dataSections[x] + "ChartDisplay").hide();
    	}
    }
	
}

function calculateTickFormat(timeSpanHash){
	 var years = ((timeSpanHash.maxTime - timeSpanHash.minTime) / (86400000*365));
	 if (years > 10){
    	 return { format: d3.time.format("%Y"), tickTime: d3.time.years, tickInterval: 5, tickSize: 10 };
     } else if (years > 3){
    	 return { format: d3.time.format("%Y"), tickTime: d3.time.years, tickInterval: 1, tickSize: 10 };
     } else if (years > 1){
    	 return { format: d3.time.format("%b %Y"), tickTime: d3.time.months, tickInterval: 4, tickSize: 10};
     } else if (years > 0.2){
    	 return { format: d3.time.format("%b %Y"), tickTime: d3.time.months, tickInterval: 1, tickSize: 10 };
     } else {
    	 return { format: d3.time.format("%b %e %Y"), tickTime: d3.time.days, tickInterval: 8, tickSize: 10 };
     }
}
function drawQtips(){
	 // add a qtip for each bar
    $('rect, circle').each(function() { 
       $(this).qtip({
       	content: {
               title: { text: $(this).attr("qtip-title") }
           },
       	position: {
       		my: "bottom center",
               at: "top center"
           },
           style: { classes: 'qtip-bootstrap' }
       });
   });  
}

function medDateSort (a, b) { 
	if (b && a && sortableDateFormat.parse(b) && sortableDateFormat.parse(a)){
	 	return sortableDateFormat.parse(a).getTime() - sortableDateFormat.parse(b).getTime(); 
	 } else if (!b && !a){
		 return 0;
	 } else if (!b){
		 return 1;
	 } else if (!a){
		 return -1;
	 }
}

</script>

<!-- Get TimeLine Charts Chart -->
<script>
function getChart(dataHash, lable, timeSpanHash){
	 var data = [];
	 Object.keys(dataHash).forEach(function (key) { 
		   dataHash[key].times.sort(function (a, b) { return b.starting_time - a.starting_time; });
		   data.push(dataHash[key]);
		});
	 
	d3.select("#"+lable+"SVG").remove();
	
	data.sort(function (a, b) { return b.times[0].starting_time - a.times[0].starting_time; });
	
	for(var x=0; x<data.length; x++){
		if(data[x].label){
			var newLabel = data[x].label;
			if(newLabel.length > 33){
				newLabel = newLabel.trim().substring(0,32)+"...";
				data[x].label = newLabel;
			}
		}
			
	}
	
	var leftOffset = findMaxLengthString(data);
	
    var chart = d3.timeline().width(minChartWidth).stack().display("circle")
	    .beginning(timeSpanHash.minTime).ending(timeSpanHash.maxTime)
        .tickFormat(calculateTickFormat(timeSpanHash))
        .margin({left:chartMarginLeft+leftOffset, right:chartMarginRight, top:0, bottom:0})
        .qtipTitle(function (d, i, datum) { return datum.times[i].header; }) 
        .qtipContent(function (d, i, datum) { return datum.times[i].content; })
        .displayShape(function (d, i, datum) { return datum.times[i].displayShape; })  
        ;
	
    var height = (data.length*25)+100;
    d3.select("."+lable).classed("svg-container", true).append("svg")
    .attr("preserveAspectRatio", "xMinYMin meet").attr("id",lable+"SVG").attr("viewBox", "0 0 "+ (minChartWidth) + " " + height).classed("svg-content-responsive", true) 
    //.attr("width", width + margin.left + margin.right).attr("height", customHeight + margin.top + margin.bottom)
    .append("svg").datum(data).call(chart);
    
}
</script>

<!-- Process Timeline Data -->
<script>
function processTimeLineDataRect(sourceData, colorCategory, labelOverride, dataHash, color, link, timeSpanHash, codeLable, timesHash){
	
	function findHash(data){
		 var code = (labelOverride) ? labelOverride : codeLable +"-"+ data.name;
		 if (!(code in dataHash)){
			dataHash[code] = { 'label' : (labelOverride) ? labelOverride : data.name, 'times' : [] };
		 }
	     if(!timesHash){
			 var timesHash = { 'header' : data.name + ((data.status) ? " ["+data.status+"]" : ""),
	                			'content' : ("Start: "+ ((data.startDate) ? toolTipFormat(new Date(data.startDate)) :"no data") 
	                           + "<br/>End: " + ((data.endDate) ? toolTipFormat(new Date(data.endDate)) :"no data")),
					    		 'starting_time' : new Date(data.dateInMillis).getTime(),
					    		 'ending_time': (data.endDateInMillis) ? new Date(data.endDateInMillis).getTime() : timeSpanHash.maxTime,
					    		 'status': data.status,
					    		 'color' : ((color) ? color : colorCategory(x)) , 
					    		 'link' : link,
					    		 'displayShape': "rect"};	
	     }
		 dataHash[code]['times'].push(timesHash);
	 }
	if(sourceData){
		 for (var x=0;x<sourceData.length;x++) {
			 if (sourceData[x].startDate){
				 if(sourceData[x].subGrid){
					 for(var y=0; y<sourceData[x].subGrid.length; y++){
						 if((sourceData[x].subGrid[y].dateInMillis != null || sourceData[x].subGrid[y].endDateInMillis != null)){
						 	findHash(sourceData[x].subGrid[y]);
						 	}
					 }
				 }else if(sourceData[x].dateInMillis != null || sourceData[x].endDateInMillis != null){findHash(sourceData[x]);}
			 }
		  } 
	 }
}

function processTimeLineDataCircle(sourceData, colorCategory, labelOverride, dataHash, color, link, timeSpanHash, codeLable){
	function findHash(data){
		 var label = (labelOverride) ? labelOverride : codeLable+"-" + data.name;
		 if (!(label in dataHash)){
				dataHash[label] = { 'label' : (labelOverride) ? labelOverride : data.name, 'times' : [] };
			 }
		 var timesHash = {   'header' : data.name,
                'content' : "Date: "+ toolTipFormat(new Date(data.displayDate)),
	    		 'starting_time' : data.dateInMillis,
	    		 'ending_time': data.dateInMillis + timeWidthOfBar,
	    		 'color' : ((color) ? color : colorCategory(x)) , 
	    		 'link' : link,
	    		 'displayShape': "circle"};		     
		dataHash[label]['times'].push(timesHash);		 
	 }
	
	 for (var x=0;x<sourceData.length;x++) {
		 if (sourceData[x].displayDate){
			 if(sourceData[x].subGrid){
				 for(var y=0; y<sourceData[x].subGrid.length; y++){
					 findHash(sourceData[x].subGrid[y]);
					 
				 }
			 }else{findHash(sourceData[x]);}
		 }
	  } 
}

</script>

<!-- findMaxLengthString-->
<script>
//returns length of longest string inside of data under the header
function findMaxLengthString(data){
	var count = 0, max = 50;
	for(var x=0; x<data.length; x++){
		if(data[x].label){
			var l = data[x].label.length;
			count = (l > count) ? l : count;
			if(count == max){break;}
			}
		}	
	return (count*4)+60;
}
</script>
