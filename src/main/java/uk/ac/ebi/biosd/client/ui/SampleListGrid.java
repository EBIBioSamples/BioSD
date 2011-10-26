package uk.ac.ebi.biosd.client.ui;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class SampleListGrid extends ListGrid
{
 private static final String AELinkFieldName = "AE Link";
 private static final String PRIDELinkFieldName = "PRIDE Link";

 private static final String AELinkIcon = "AE Link";
 private static final String PRIDELinkIcon = "PRIDE Link";
 private static final String OtherLinkIcon = "PRIDE Link";

 public SampleListGrid()
 {
  setShowRecordComponents(true);          
  setShowRecordComponentsByCell(true);
  
  setWidth("98%");
 }
 
// protected Canvas getCellHoverComponentX(Record record, Integer rowNum, Integer colNum)
// {
// } 
 
 @Override
 protected Canvas createRecordComponent(final ListGridRecord record, final Integer colNum) 
 {
  String title = getField(colNum).getTitle();
  
  String linkIcon = null;
  
  if( title.equals(AELinkFieldName) )
   linkIcon = AELinkIcon;
  else if( title.equals(PRIDELinkFieldName) )
   linkIcon = PRIDELinkIcon;
  else
  {
   String val = record.getAttribute(getFieldName(colNum));
   if( val!=null && val.length() > 11 )
   {
    String beg = val.substring(0,8);
    if( beg.equalsIgnoreCase("http://") )
     linkIcon = OtherLinkIcon;
   }
  } 
   
  if(linkIcon != null )
  {
   ImgButton aeImg = new ImgButton();
   aeImg.setShowDown(false);
   aeImg.setShowRollOver(false);
   aeImg.setAlign(Alignment.CENTER);
   aeImg.setSrc(linkIcon);
   aeImg.setPrompt("Follow the link");
   aeImg.setHeight(22);
   aeImg.setWidth(16);
   aeImg.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler()
   {

    @Override
    public void onClick(com.smartgwt.client.widgets.events.ClickEvent event)
    {
     Window.open(record.getAttribute(getFieldName(colNum)), "_blank", "");
//     SC.say("Chart Icon Clicked for country : " + record.getAttribute(getFieldName(colNum)));
    }
   });
   
   return aeImg;
  }

  return null;
 }
}
