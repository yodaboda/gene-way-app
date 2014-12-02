package com.nutrinfomics.geneway.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.nutrinfomics.geneway.client.about.AboutPlace;
import com.nutrinfomics.geneway.client.waiting.WaitingPlace;

public class GeneWayAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if(newPlace instanceof AboutPlace){
			return Animations.SLIDE_UP_REVERSE;
		}
		if(oldPlace instanceof WaitingPlace){
			return Animations.DISSOLVE;
		}
		return Animations.SLIDE;
	}

}
