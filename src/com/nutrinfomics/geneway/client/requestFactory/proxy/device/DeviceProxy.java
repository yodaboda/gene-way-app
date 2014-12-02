package com.nutrinfomics.geneway.client.requestFactory.proxy.device;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.nutrinfomics.geneway.client.requestFactory.proxy.customer.CustomerProxy;
import com.nutrinfomics.geneway.server.domain.device.Device;

@ProxyFor(Device.class)
public interface DeviceProxy extends EntityProxy{

	public String getUuid();

	public void setUuid(String uuid);

	public CustomerProxy getCustomer();

	public void setCustomer(CustomerProxy customer);

}