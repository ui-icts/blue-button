package edu.uiowa.icts.bluebutton.json.view;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StatsFinder {

	private List<IGetStats> list;
	
	public StatsFinder(List<IGetStats> list){
		this.list = list;
	}
	
	public Double getStandardDeviation(){
		return (findStats().getN() != 0L) ? findStats().getStandardDeviation() : null;
	}

	public Double getMean() {		
		return (findStats().getN() != 0L) ? findStats().getMean() : null;
	}

	public Double getVariance() {			
		return (findStats().getN() != 0L) ? findStats().getPopulationVariance() : null;
	}
	
	private SummaryStatistics findStats(){
		SummaryStatistics stats = new SummaryStatistics();
		stats.setVarianceImpl(new Variance(false));
		for(IGetStats d : this.list){
			if(d.getDoubleValue() != null){stats.addValue(d.getDoubleValue());}
		}
		return stats;
	}
	
	public String getDataSetRange(){
		return (this.getMin().toString()).concat(" to ").concat(this.getMax().toString());
	}
	
	private List<Double> getDoubleList(){
		List<Double> dList = new ArrayList<Double>();
		for(IGetStats igs : this.list){
			if(igs.getDoubleValue() != null){dList.add(igs.getDoubleValue());}
		}
		return dList;
	}
	
	public Double getMax(){
		List<Double> dList = this.getDoubleList();
		Double max =Collections.max(dList, new Comparator<Double>(){
			public int compare(Double d, Double d2) {
				if(d>d2){return 1;}
				else if(d<d2){return -1;}
				return 0;
			}
		});
		return max;
	}
	
	public Double getMin(){
		List<Double> dList = this.getDoubleList();
		Double min =Collections.min(dList, new Comparator<Double>(){
			public int compare(Double d, Double d2) {
				if(d>d2){return 1;}
				else if(d<d2){return -1;}
				return 0;
			}
		});
		return min;
	}

	@JsonIgnore
	public List<IGetStats> getList() {
		return list;
	}
	
}
