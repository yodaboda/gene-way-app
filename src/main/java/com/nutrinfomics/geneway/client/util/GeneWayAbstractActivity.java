package com.nutrinfomics.geneway.client.util;

import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;
import com.nutrinfomics.geneway.shared.constants.GeneWayConstants;

public abstract class GeneWayAbstractActivity extends MGWTAbstractActivity {
	protected GeneWayConstants constants = ClientFactoryFactory.getClientFactory().getConstants();
}
