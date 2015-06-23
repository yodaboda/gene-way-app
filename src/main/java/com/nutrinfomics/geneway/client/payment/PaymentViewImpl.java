package com.nutrinfomics.geneway.client.payment;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.nutrinfomics.geneway.client.DetailsViewImpl;

public class PaymentViewImpl extends DetailsViewImpl implements PaymentView {

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
	}
}
