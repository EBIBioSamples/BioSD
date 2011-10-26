package uk.ac.ebi.biosd.client.ui.test;

import uk.ac.ebi.biosd.client.query.AttributedObject;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Window;

public class ObjectViewer extends Window
{
 public ObjectViewer( AttributedObject obj )
 {
  setTitle( "Value properties: "+obj.getStringValue() );  
  setWidth(750);  
 
  int height = 0;
  
  final int rowHeight = 28;
  
  for( AttributedObject at : obj.getAttributes() )
  {
   if( at.getObjectValue() == null )
    height+=rowHeight;
   else
    height += rowHeight * at.getObjectValue().getAttributes().size();
  }
  
  setHeight(height); 
  setCanDragReposition(true);  
  setCanDragResize(true);  
  setShowMinimizeButton(false);
  setIsModal(true);
  setShowModalMask(true);
  centerInPage();
  
  setAlign(VerticalAlignment.CENTER);

  addItem( new ObjectViewPanelHTML(obj) );
 }  
}
