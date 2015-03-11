package com.nutrinfomics.geneway.client.util.fieldsWidgetListView;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.nutrinfomics.geneway.client.DetailsView;

public interface FieldsWidgetListView extends DetailsView {
	public void constraintViolations(Set<ConstraintViolation<?>> violations);
}
