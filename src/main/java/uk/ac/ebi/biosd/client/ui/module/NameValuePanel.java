package uk.ac.ebi.biosd.client.ui.module;

import java.util.List;

import uk.ac.ebi.biosd.client.shared.Pair;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class NameValuePanel extends Window
{
 public NameValuePanel(List<Pair<String, String>> otherInfoList )
 {
  setAutoSize(true);  
  setTitle("Other information");  
  setWidth(700);  
  setHeight(200);  
//  setLeft(offsetLeft);  
  setCanDragReposition(true);  
  setCanDragResize(true);  
  setShowMinimizeButton(false);
  setIsModal(true);
  setShowModalMask(true);
  centerInPage();

  DataSource ds = new DataSource();
  
  ds.setClientOnly(true);
  
  ListGridRecord rec = new ListGridRecord();

  int i=0;
  for( Pair<String,String> p : otherInfoList )
  {
   String atNm="Attr"+(i++);
   
   ds.addField(new DataSourceTextField(atNm, p.getFirst()));
  
   rec.setAttribute(atNm, p.getSecond());
  }

  ds.addData(rec);
  
  DetailViewer dv = new DetailViewer();
  dv.setWidth("100%");
  dv.setHeight("100%");
  dv.setCanSelectText(true);
  dv.setDataSource(ds);
  dv.setStyleName("groupDetails");
  dv.setAutoFetchData(true);

  
  
  addItem(dv);
 }
}
