package com.nutrinfomics.geneway.client;

public class ClientFactoryFactory {
	static private ClientFactory clientFactory = new ClientFactoryImpl();
	static public ClientFactory getClientFactory(){
		return clientFactory;
	}
}
