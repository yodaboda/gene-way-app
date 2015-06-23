package com.nutrinfomics.geneway.client.home.measurementsPanel;

import com.nutrinfomics.geneway.client.ProgressChartPanel;
import com.nutrinfomics.geneway.client.home.AbstractTabBarWidget;

public class MeasurementWidget extends AbstractTabBarWidget {
	public MeasurementWidget(){
		add(ProgressChartPanel.getInstance());
	}
}
