/*
 * #%L
 * blue-button Spring MVC Web App
 * %%
 * Copyright (C) 2014 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
var messageArr = [];
var showMessageOptions = {};
function showMessage( bin, message, time, addClose ){
	var ob = findMessage( messageArr, bin );
	if ( ob ) {
		if ( !ob.showing ) {
			var timeVar = ob.timeVar;
			ob.showing = true;
			if ( addClose == true ) {
				$( '#' + ob.bin ).html( message + '<a class="close pull-right" style="color: black; font-weight:bold;" onclick="hideMessage(\'' + ob.bin + '\')">&times;</a><div class="clearfix"></div>' );
			} else {
				$( '#' + ob.bin ).html( message );
			}
			$( '#' + ob.bin ).show( 'blind', showMessageOptions, 500 );
			if ( time ) {
				if ( timeVar ) {
					clearTimeout( timeVar );
				}
				ob.timeVar = setTimeout( function(){
					hideMessage( bin );
				}, time );
			}
		} else {
			if ( message ) {
				ob.message = message;
			}
			if ( addClose == true ) {
				$( '#' + ob.bin ).html( ob.message + '<a class="close pull-right" style="color: black; font-weight:bold;" onclick="hideMessage(\'' + ob.bin + '\')">&times;</a><div class="clearfix"></div>' );
			} else {
				$( '#' + ob.bin ).html( ob.message );
			}
			if ( ob.timeVar ) {
				clearTimeout( ob.timeVar );
			}
			if ( ob.time != undefined || time != undefined ) {
				if ( ob.time == undefined ) {
					ob.time = time;
				}
				if ( ob.time ) {
					ob.timeVar = setTimeout( function(){
						hideMessage( bin );
					}, ob.time );
				}
			}
		}
	} else {
		var mes = {
			"bin" : bin,
			"showing" : true,
			"message" : message,
			"time" : time
		};
		if ( addClose == true ) {
			$( '#' + bin ).html( message + '<a class="close pull-right" style="color: black; font-weight:bold;" onclick="hideMessage(\'' + bin + '\')">&times;</a><div class="clearfix"></div>' );
		} else {
			$( '#' + bin ).html( message );
		}
		$( '#' + bin ).show( 'blind', showMessageOptions, 500 );
		if ( time ) {
			mes.timeVar = setTimeout( function(){
				hideMessage( bin );
			}, time );
		}
		messageArr.push( mes );
	}
}

function hideMessage( bin ){
	var ob = findMessage( messageArr, bin );
	if ( ob ) {
		if ( ob.showing ) {
			if ( $( '#' + ob.bin ).is( ':visible' ) ) {
				$( '#' + ob.bin ).hide( 'blind', showMessageOptions, 500 );
			}
			ob.showing = false;
		}
	}
}

function removeFromArr( arr, index ){
	arr.splice( index, 1 );
}

function findMessage( arr, bin ){
	var ob = undefined;
	for ( var i = 0; i < arr.length; i++ ) {
		if ( arr[ i ].bin === bin ) {
			ob = arr[ i ];
			ob.index = i;
			return ob;
		}
	}
	return ob;
}