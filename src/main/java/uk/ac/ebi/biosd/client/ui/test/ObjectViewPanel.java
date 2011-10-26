package uk.ac.ebi.biosd.client.ui.test;

import uk.ac.ebi.biosd.client.query.AttributedObject;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ObjectViewPanel extends ListGrid
{
 public ObjectViewPanel( AttributedObject obj )
 {
  setWidth("100%");
//  setAutoHeight();
  setHeight("100%");
  setCanSelectText(true);
  setStyleName("objectView");
  setShowHeader(true);
//  setAutoFetchData(true);
//  setOverflow(Overflow.VISIBLE);
//  setBodyOverflow(Overflow.VISIBLE);
//  setWrapCells(true);
  setFixedRecordHeights(false);

//  setShowRecordComponents(true);
//  setShowRecordComponentsByCell(true);


  setCanExpandRecords(true);
  setCanExpandRecordProperty("hasDetails");
  
  ListGridField nameField = new ListGridField("Name","Name", 200);
  nameField.setAlign(Alignment.RIGHT);
  nameField.setBaseStyle("nameColumn");

  ListGridField valField = new ListGridField("Value","Value");
  valField.setBaseStyle("valueColumn");

  setFields(nameField,valField);
//  setExpansionMode(ExpansionMode.DETAIL_RELATED);

//  ds.addField(new DataSourceBooleanField("hasDetails", "hasDetails"));
//  ds.addField(new DataSource("hasDetails", "hasDetails"));

  
  for( AttributedObject at : obj.getAttributes() )
  {
   ListGridRecord rec = new ListGridRecord();
   
   rec.setAttribute( "Name", at.getName()+":&nbsp" );
   rec.setAttribute( "Value", at.getStringValue() );
   rec.setAttribute( "__attr", at );
   
   rec.setAttribute( "hasDetails", at.getObjectValue() != null );

   addData(rec);
   
//   expandRecord(rec);
  }
  
 // setDataSource(ds);
 }
 
 public void onDraw()
 {
  for(ListGridRecord r : getRecords() )
  {
   if( r.getAttributeAsBoolean("hasDetails")  )
    expandRecord(r);
  }
 }
 
 protected Canvas createRecordComponentXXX(ListGridRecord record,   Integer colNum)
 {
  if( colNum != 1 )
   return null;
  
  AttributedObject at = (AttributedObject)record.getAttributeAsObject("__attr");
  
  if( at.getObjectValue() == null )
   return null;
  
  Canvas c = new ObjectViewPanel(at.getObjectValue());
  c.setHeight("100%");
  c.setOverflow(Overflow.VISIBLE);
  return c;
 }

 protected Canvas getExpansionComponent(final ListGridRecord record)
 {
  AttributedObject at = (AttributedObject)record.getAttributeAsObject("__attr");
  
  if( at.getObjectValue() == null )
   return null;

  ObjectViewPanel c = new ObjectViewPanel(at.getObjectValue());
//  c.setHeight(100);
  c.setOverflow(Overflow.VISIBLE);
  c.setBodyOverflow(Overflow.VISIBLE);
  c.setBorder("1px solid gray");
  c.setMargin(10);
  c.setShowHeader(false);

//  c.setShowEdges(true);
  
  return c;
 }
}
