package com.nutrinfomics.geneway.client;

/*
 * Copyright (C) 2019 Firas Swidan†
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.annotationchart.AnnotationChart;
import com.googlecode.gwt.charts.client.annotationchart.AnnotationChartOptions;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.nutrinfomics.geneway.client.util.DateUtils;

public class ProgressChartPanel extends FlexPanel {
	static private ProgressChartPanel progressChartPanel;

	static public ProgressChartPanel getInstance(){
		if(progressChartPanel == null){
			progressChartPanel = new ProgressChartPanel();
		}
		return progressChartPanel;
	}

	private AnnotationChart annotationChart;
	
	private ProgressChartPanel(){
		ChartLoader chartLoader = new ChartLoader(ChartPackage.ANNOTATIONCHART);
		 chartLoader.loadApi(new Runnable() {
		        @Override
		        public void run() {
		        	annotationChart = new AnnotationChart();
		        	add(annotationChart);
		        	draw();
		                // call method to create and show the chart
		        }
		 });

	}
	
	private void draw() {
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.DATE, "Country");
		dataTable.addColumn(ColumnType.NUMBER, "Kepler-22b mission");
		dataTable.addColumn(ColumnType.STRING, "Kepler title");
		dataTable.addColumn(ColumnType.STRING, "Kepler text");
		dataTable.addColumn(ColumnType.NUMBER, "Gliese 163 mission");
		dataTable.addColumn(ColumnType.STRING, "Gliese title");
		dataTable.addColumn(ColumnType.STRING, "Gliese text");
		dataTable.addRows(6);
		dataTable.setValue(0, 0, DateUtils.getDate(15, 2, 2314));
		dataTable.setValue(0, 1, 12400);
		dataTable.setValue(0, 4, 10645);
		dataTable.setValue(1, 0, DateUtils.getDate(16, 2, 2314));
		dataTable.setValue(1, 1, 24045);
		dataTable.setValue(1, 2, "Lalibertines");
		dataTable.setValue(1, 3, "First encounter");
		dataTable.setValue(1, 4, 12374);
		dataTable.setValue(2, 0, DateUtils.getDate(17, 2, 2314));
		dataTable.setValue(2, 1, 35022);
		dataTable.setValue(2, 2, "Lalibertines");
		dataTable.setValue(2, 3, "They are very tall");
		dataTable.setValue(2, 4, 15766);
		dataTable.setValue(2, 5, "Gallantors");
		dataTable.setValue(2, 6, "First Encounter");
		dataTable.setValue(3, 0, DateUtils.getDate(18, 2, 2314));
		dataTable.setValue(3, 1, 12284);
		dataTable.setValue(3, 2, "Lalibertines");
		dataTable.setValue(3, 3, "Attack on our crew!");
		dataTable.setValue(3, 4, 34334);
		dataTable.setValue(3, 5, "Gallantors");
		dataTable.setValue(3, 6, "Statement of shared principles");
		dataTable.setValue(4, 0, DateUtils.getDate(19, 2, 2314));
		dataTable.setValue(4, 1, 8476);
		dataTable.setValue(4, 2, "Lalibertines");
		dataTable.setValue(4, 3, "Heavy casualties");
		dataTable.setValue(4, 4, 66467);
		dataTable.setValue(4, 5, "Gallantors");
		dataTable.setValue(4, 6, "Mysteries revealed");
		dataTable.setValue(5, 0, DateUtils.getDate(20, 2, 2314));
		dataTable.setValue(5, 1, 0);
		dataTable.setValue(5, 2, "Lalibertines");
		dataTable.setValue(5, 3, "All crew lost");
		dataTable.setValue(5, 4, 79463);
		dataTable.setValue(5, 5, "Gallantors");
		dataTable.setValue(5, 6, "Omniscience achieved");

		// Set options
		AnnotationChartOptions options = AnnotationChartOptions.create();
		options.setDisplayAnnotations(true);

		// Draw the chart
		annotationChart.draw(dataTable, options);
	}	
}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */