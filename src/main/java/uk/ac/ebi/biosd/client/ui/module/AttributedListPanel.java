package uk.ac.ebi.biosd.client.ui.module;

import java.util.List;

import uk.ac.ebi.biosd.client.query.AttributedImprint;
import uk.ac.ebi.biosd.client.shared.AttributeReport;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.viewer.DetailViewer;


public class AttributedListPanel  extends Window
{
 public AttributedListPanel(String title, List<AttributedImprint> pubs )
 {
//  setAutoSize(true);  
  setTitle(title);  
  setWidth(700);  
  setHeight(550); 
//  setLeft(offsetLeft);  
  setCanDragReposition(true);  
  setCanDragResize(true);  
  setShowMinimizeButton(false);
  setIsModal(true);
  setShowModalMask(true);
  centerInPage();

  VLayout layout = new VLayout();
  layout.setWidth100();
  layout.setHeight100();
  layout.setMargin(5);
  layout.setMembersMargin(5);
  
  for( AttributedImprint pub : pubs )
  {
   DataSource ds = new DataSource();
   
   ds.setClientOnly(true);
   
   ListGridRecord rec = new ListGridRecord();

   int i=0;
   for( AttributeReport attr : pub.getAttributes() )
   {
    String atNm="Attr"+(i++);
    
    ds.addField(new DataSourceTextField(atNm, attr.getName()));
   
    if("PubMed ID".equals(attr.getName()))
     rec.setAttribute(atNm, "<a target='_blank' border=0 href='http://www.ncbi.nlm.nih.gov/pubmed/"+attr.getValue()+"'>"+attr.getValue()+"</a>");
    else if("DOI".equals(attr.getName()))
     rec.setAttribute(atNm, "<a target='_blank' border=0 href='http://dx.doi.org/"+attr.getValue()+"'>"+attr.getValue()+"</a>");
    else
     rec.setAttribute(atNm, attr.getValue());
   }

   ds.addData(rec);
   
   DetailViewer dv = new DetailViewer();
   dv.setWidth("100%");
   dv.setHeight("100%");
   dv.setCanSelectText(true);
   dv.setDataSource(ds);
   dv.setStyleName("groupDetails");
   dv.setAutoFetchData(true);

   
   
   layout.addMember(dv);
  }
  
  addItem(layout);

 }
}
