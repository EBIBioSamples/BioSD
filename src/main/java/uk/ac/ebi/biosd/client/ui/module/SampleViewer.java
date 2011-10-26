package uk.ac.ebi.biosd.client.ui.module;

import java.util.List;

import uk.ac.ebi.biosd.client.shared.AttributeClassReport;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class SampleViewer  extends Window
{
 public SampleViewer(List<AttributeClassReport> header, ListGridRecord rec )
 {
  
//  setAutoSize(true);  
  setTitle("Sample "+rec.getAttributeAsString("__id") );  
  setWidth(750);  
//  setLeft(offsetLeft);  
  setCanDragReposition(true);  
  setCanDragResize(true);  
  setShowMinimizeButton(false);
  setIsModal(true);
  setShowModalMask(true);
  centerInPage();
  
  DataSource ds = new DataSource();
  ds.setClientOnly(true);

  int height=38;
  
  for( AttributeClassReport cls : header )
  {
   ds.addField(new DataSourceTextField(cls.getId(), cls.isCustom()?cls.getName():("<b>"+cls.getName()+"</b>") ));
   
   height+=23;
   
   String val = rec.getAttributeAsString(cls.getId());
   
   if( val != null )
   {
    height+= (val.length()/100)*16;
   }
  }
  
  if( height > 600 )
   height = 600;

  setHeight(height); 
  
  ds.addData(rec);
  
  DetailViewer dv = new DetailViewer();
  dv.setWidth("100%");
  dv.setHeight("100%");
  dv.setCanSelectText(true);
  dv.setDataSource(ds);
  dv.setStyleName("sampleDetails");
  dv.setAutoFetchData(true);

  dv.setMargin(3);
  
  centerInPage();
  
  addItem(dv);
 }

}
