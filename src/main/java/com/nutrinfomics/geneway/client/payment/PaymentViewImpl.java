package com.nutrinfomics.geneway.client.payment;

/*
 * Copyright (C) 2019 Firas Swidan†
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.nutrinfomics.geneway.client.DetailsViewImpl;
import com.nutrinfomics.geneway.client.style.Styles;
import com.nutrinfomics.geneway.client.util.fieldsWidgetListView.FieldsWidgetListViewImpl;

public class PaymentViewImpl extends FieldsWidgetListViewImpl implements PaymentView {

	private Button demoButton;

	public PaymentViewImpl(){
		HTML html = new HTML("<form action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\" target=\"_top\">"
							+ "<input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\">"
							+ "<input type=\"hidden\" name=\"hosted_button_id\" value=\"G5DUHABRTXNXS\">"
							+ "<INPUT TYPE=\"hidden\" NAME=\"return\" value=\"" + GWT.getHostPageBaseURL() + "\">"
							+ "<table>"
							+ "<tr><td><input type=\"hidden\" name=\"on0\" value=\"Plans\">Plans</td></tr><tr><td><select name=\"os0\">"
							+ "<option value=\"Single person - 3 months\">Single person - 3 months ₪1,500.00 ILS</option>"
							+ "<option value=\"Two persons - 3 months\">Two persons - 3 months ₪2,700.00 ILS</option>"
							+ "<option value=\"Single person - 6 months\">Single person - 6 months ₪3,000.00 ILS</option>"
							+ "</select> </td></tr>"
							+ "</table>"
							+ "<input type=\"hidden\" name=\"currency_code\" value=\"ILS\">"
							+ "<input type=\"image\" src=\"https://www.paypalobjects.com/en_US/IL/i/btn/btn_buynowCC_LG.gif\" border=\"0\" name=\"submit\" alt=\"PayPal - The safer, easier way to pay online!\">"
							+ "<img alt=\"\" border=\"0\" src=\"https://www.paypalobjects.com/en_US/i/scr/pixel.gif\" width=\"1\" height=\"1\">"
							+ "</form>"
						);
		add(html);
		
		demoButton = new Button(constants.demo());
	    toggleButtonAppearance(demoButton);
	    demoButton.setSmall(true);
	    demoButton.getElement().getStyle().setColor(Styles.GREEN_DARK);
	    
	    add(new FlexSpacer());
	    add(demoButton);
	}

	@Override
	public Button getDemoButton() {
		return demoButton;
	}
}

/*
 * †Dr Firas Swidan, PhD. frsswdn@gmail.com. firas.swidan@icloud.com.
 * https://www.linkedin.com/in/swidan
 * POBox  8125,  Nazareth  16480, Israel.
 * Public key: AAAAB3NzaC1yc2EAAAADAQABAAACAQD6Lt98LolwuA/aOcK0h91ECdeiyG3QKcUOT/CcMEPV64cpkv3jrLLGoag7YtzESZ3j7TLEd0WHZ/BZ9d+K2kRfzuuCdMMhrBwqP3YObbTbSIM6NjUNwbH403LLb3FuYApUt1EvC//w64UMm7h3fTo0vdyVuMuGnkRZuM6RRAXcODM4tni9ydd3ZQKN4inztkeH/sOoM77FStk8E2VYbljUQdY39zlRoZwUqNdKzwD3T2G00tmROlTZ6K5L8i68Zqt6s0XNS6XQvS3zXe0fI6UwuetnDrcVr1Yb8y2T8lfjMG9+9L2aKPoUOlOMMcyqM+oKVvRUOSdrzmtKOljnYC7TqzvsKrfXHvHlqHxxhPp1K7B/YWrHwCDbqp02dXdIaXkkHCIqKFNaY06HEWt4obDxppVhC8IabSb55LQVCCT7J4TFbwp6rID2+Y1L7NEvR3v3oaWSlQIZ+WSG04mwh9/7gRCt7XUoqmEXCCPoHqZXq5sWv193XA57pD5gKoX7Rf2i6UdbduNTMIhQMqcWIaPMBFwxUv/LRQCHnS+mlW2GnIHIHHGS/S46MurZ6BMvcb7fEz/NorVxvh3DbUaVTteMYcikH0y5sPmGECB1d99ENBBSEX6diI+PneFp2sOouQ6gOBWy6WAt3spGfLTOFMPo3bMV/UpktkQPpXkmfd1esQ==
 */