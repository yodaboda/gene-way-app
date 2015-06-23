package com.nutrinfomics.geneway.client.personalDetails;

import java.util.Date;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.nutrinfomics.geneway.client.DetailsView;
import com.nutrinfomics.geneway.shared.Gender;

public interface PersonalDetailsView extends DetailsView {

	public HasTapHandlers getNextButton();
	
	public HasTapHandlers getDemoButton();

	public Date getBirthdayDate();

	public Gender getGender();

	public float getHeight();

	public float getWeight();

}
