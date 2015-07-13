package edu.uiowa.icts.bluebutton.json;

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

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeComparator;

public class TimeFilter {

		private Long startDateInMillis;
		private Long endDateInMillis;
		private List<IDateInMillis> list;
		
		public TimeFilter(List<IDateInMillis> list, Long startDateInMillis, Long endDateInMillis){
			this.list = list;
			this.startDateInMillis = startDateInMillis;
			this.endDateInMillis = endDateInMillis;
		}

		public List<IDateInMillis> getFillteredList() {
			List<IDateInMillis> list = new ArrayList<IDateInMillis>();
			DateTimeComparator dtc = DateTimeComparator.getDateOnlyInstance();
			for(IDateInMillis igj: this.list){
				Long startDate = (igj.getStartDateInMillis() != null) ? igj.getStartDateInMillis() : igj.getDateInMillis();
				Long endDate = (igj.getEndDateInMillis() != null) ? igj.getEndDateInMillis() : igj.getDateInMillis();
				if((this.startDateInMillis == null || startDate== null || dtc.compare(startDate, this.startDateInMillis) >= 0) 
						&& (this.endDateInMillis == null || endDate == null || dtc.compare(endDate, this.endDateInMillis) <= 0)){
					list.add(igj);
				}
			}
			return list;
		}
}
