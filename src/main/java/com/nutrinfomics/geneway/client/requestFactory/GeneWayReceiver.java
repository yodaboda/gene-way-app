package com.nutrinfomics.geneway.client.requestFactory;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

abstract public class GeneWayReceiver<T> extends Receiver<T> {

	@Override
	public void onFailure(ServerFailure error){
		super.onFailure(error);
	}

	@Override
	public void onConstraintViolation(Set<ConstraintViolation<?>> violations){
		Window.alert("constraint violation");
		if(! violations.isEmpty() ){
			Iterator<ConstraintViolation<?>> iterator = violations.iterator();
			String message = "";
			
			while(iterator.hasNext()){
				ConstraintViolation<?> next = iterator.next();
				message += next.getMessageTemplate() + " " + next.getMessage();
			}
			
			Window.alert(message);
		}
		
		super.onConstraintViolation(violations);
	}
}
