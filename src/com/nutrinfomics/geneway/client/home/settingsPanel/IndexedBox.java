package com.nutrinfomics.geneway.client.home.settingsPanel;

import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;

public class IndexedBox extends MListBox {
	private int index;
	
	public IndexedBox(int index){
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
