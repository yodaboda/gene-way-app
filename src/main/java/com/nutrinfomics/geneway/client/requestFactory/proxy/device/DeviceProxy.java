package com.nutrinfomics.geneway.client.requestFactory.proxy.device;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.EntityBaseProxy;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.server.domain.EntityBaseLocator;
import com.nutrinfomics.geneway.server.domain.device.Device;

@ProxyFor(value = Device.class, locator = EntityBaseLocator.class)
public interface DeviceProxy extends EntityBaseProxy{

	public String getUuid();

	public void setUuid(String uuid);

	public CustomerProxy getCustomer();

	public void setCustomer(CustomerProxy customer);

	public void setPhonenumber(String phonenumber);

	public String getPhonenumber();
	
	public String getCode();

	public void setCode(String code);
}