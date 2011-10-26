package uk.ac.ebi.biosd.client.ui.module;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;

public class WaitWindow extends Window
{
 static private WaitWindow ww = new WaitWindow(); 
 
 WaitWindow()
 {
  setTitle("Wait...");
  
  setHeight(100);
  setWidth(200);
  setShowMinimizeButton(false);  
  setIsModal(true);  
  setShowModalMask(true);  
  centerInPage();
//  setShowHeader(false);
  setShowTitle(false);
  setShowCloseButton(false);
  
  
  Label lb = new Label("<br><H2>Please wait</H2>");
  lb.setAlign(Alignment.CENTER);
  lb.setHeight(50);
  lb.setWidth100();
  lb.setValign(VerticalAlignment.CENTER);
  
  addItem(lb);
 }
 
 public static void showWait()
 {
  ww.centerInPage();
  ww.show();
 }
 
 public static void hideWait()
 {
  ww.hide();
 }
}
