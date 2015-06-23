package com.nutrinfomics.geneway.client.requestFactory;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.nutrinfomics.geneway.client.ClientFactoryFactory;

abstract public class GeneWayReceiver<T> extends Receiver<T> {

	@Override
	public void onFailure(ServerFailure error){
		super.onFailure(error);
	}

	@Override
	public void onConstraintViolation(Set<ConstraintViolation<?>> violations){
		if(! violations.isEmpty() ){
			Iterator<ConstraintViolation<?>> iterator = violations.iterator();
			
			while(iterator.hasNext()){
				ConstraintViolation<?> next = iterator.next();
				if(next.getMessageTemplate().contains("session.sid")){
					Dialogs.alert(ClientFactoryFactory.getClientFactory().getConstants().sessionExpiredTitle(), 
								next.getMessage(), null);
					ClientFactoryFactory.getClientFactory().logout();
					iterator.remove();
				}
			}			
		}
		
		super.onConstraintViolation(violations);
	}
}
