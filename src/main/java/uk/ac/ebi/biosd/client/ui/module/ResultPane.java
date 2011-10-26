package uk.ac.ebi.biosd.client.ui.module;

import uk.ac.ebi.age.ui.client.LinkManager;
import uk.ac.ebi.age.ui.client.module.PagingRuler;
import uk.ac.ebi.biosd.client.query.GroupImprint;
import uk.ac.ebi.biosd.client.query.Report;
import uk.ac.ebi.biosd.client.shared.AttributeReport;
import uk.ac.ebi.biosd.client.ui.ResultRenderer;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.ExpansionMode;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordCollapseEvent;
import com.smartgwt.client.widgets.grid.events.RecordCollapseHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class ResultPane extends VLayout implements ResultRenderer
{
 public final static int MAX_GROUPS_PER_PAGE=25;
 public final static int MAX_SAMPLES_PER_PAGE=20;
 
 private String query;
// private boolean searchSamples;
// private boolean searchGroups;
 private boolean searchAtNames;
 private boolean searchAtValues;
 
 private ListGrid resultGrid = new GroupsList();
 private PagingRuler pagingRuler = new PagingRuler("groupPage");
 private HTMLFlow statusBar = new HTMLFlow();
 
 public ResultPane()
 {
  setHeight("100%");
  
  setMargin(5);
  
  setShowEdges(true);
  setEdgeSize(6);
  setEdgeImage("gnframe.gif");
  setEdgeMarginSize(10);
  
  resultGrid.setShowAllRecords(true);  
  resultGrid.setWrapCells(true);
  resultGrid.setFixedRecordHeights(false);
  resultGrid.setCellHeight(20);
  resultGrid.setCanDragSelectText(true);
  resultGrid.setCanDrag(false);
 
//  resultGrid.setBodyOverflow(Overflow.VISIBLE);
  resultGrid.setOverflow(Overflow.VISIBLE);
  
  resultGrid.setStyleName("reportGrid");
  
//  setBaseStyle("reportGrid");
  
  DataSource ds = new DataSource();
  
  ds.setClientOnly(true);
  
  ds.addField(new DataSourceTextField("id", "ID"));
  ds.addField(new DataSourceTextField("sampCnt", "Samples", 100));
  ds.addField(new DataSourceTextField("desc", "Description", 2000));
  ds.addField(new DataSourceIntegerField("prop", "Property", 111));
  
//  setDataSource(ds);
  
  resultGrid.setCanExpandRecords(true); 
  
  ListGridField idField = new ListGridField("id","ID", 200);  
  ListGridField sCntField = new ListGridField("sampCnt","Samples",60);
  ListGridField descField = new ListGridField("desc","Description");
//  ListGridField propField = new ListGridField("prop","AdditionalProp");
  
//  propField.setHidden(true);
  
//  idField.setWidth(200);
     
  resultGrid.setFields(idField,sCntField, descField );
  resultGrid.setExpansionMode(ExpansionMode.DETAIL_FIELD);
  
  pagingRuler.setVisible(true);
  
  statusBar.setWidth100();
  statusBar.setHeight(22);
  
  addMember(pagingRuler);
  addMember(resultGrid);
//  addMember(statusBar);
  
  resultGrid.addRecordCollapseHandler( new RecordCollapseHandler()
  {
   
   @Override
   public void onRecordCollapse(RecordCollapseEvent event)
   {
    Canvas pnl = (Canvas)event.getRecord().getAttributeAsObject("_panel");

    if( pnl !=null )
    {
     pnl.destroy();
     event.getRecord().setAttribute("_panel", (Object)null);
    }
    
    LinkManager.getInstance().removeLinkClickListener(event.getRecord().getAttribute("id"));
   }
  });
//  ListGridRecord rec = new ListGridRecord();
//  
//  rec.setAttribute("id", "SBM00000X");
//  rec.setAttribute("desc", "This is my first submission description\nline 2 This is my first submission description\\nline 2This is my first submission description\\nline 2This is my first submission description\\nline 2This is my first submission description\\nline 2");
//  rec.setAttribute("prop", 100);
//  
//  addData(rec);
  
//  rec = new ListGridRecord();
//  
//  rec.setAttribute("id", "SBM00000X");
//  rec.setAttribute("desc", "This is my first submission description");
//  rec.setAttribute("prop", 100);
//
//  ds.addData(rec);
  
//  rec.setDetailDS(ds);
  
//  setAlign(Alignment.CENTER);
//  
//  Label lb = new Label("Query result is empty");
//  
//  lb.setAlign(Alignment.CENTER);
//  
//  addMember(lb);
 }
 
 public void showResult( Report res, String qry, boolean sSmp, boolean sGrp, boolean sAtrNm, boolean sAtrVl, int cpage )
 {
  query = qry;
//  searchSamples=sSmp;
//  searchGroups=sGrp;
  searchAtNames=sAtrNm;
  searchAtValues=sAtrVl;
  
  resultGrid.selectAllRecords();
  resultGrid.removeSelectedData();
  
  int firstGrp = (cpage-1)*MAX_GROUPS_PER_PAGE+1;
  int lastGrp = firstGrp+MAX_GROUPS_PER_PAGE-1;
  
  if( lastGrp > res.getTotalGroups() )
   lastGrp = res.getTotalGroups();
  
  pagingRuler.setContents( cpage, res.getTotalGroups(), MAX_GROUPS_PER_PAGE,
    "Groups: "+res.getTotalGroups()+"&nbsp;&nbsp;Samples: "+res.getTotalSamples()
    +".&nbsp;&nbsp;Displaying groups "+firstGrp+" to "+lastGrp+".", null);
  
//  "Groups: "+res.getTotalGroups()+" Samples: "+res.getTotalSamples()
//  if( res.getTotalGroups() > MAX_GROUPS_PER_PAGE )
//  {
//   pagingRuler.setPagination(cpage, res.getTotalGroups(), MAX_GROUPS_PER_PAGE);
//   pagingRuler.setVisible(true);
//  }
//  else
//   pagingRuler.setVisible(false);
  
  for( GroupImprint sgr : res.getObjects() )
  {
   ListGridRecord rec = new ListGridRecord();
   
   rec.setAttribute("id", sgr.getId());
   rec.setAttribute("sampCnt", sgr.getRefCount());
   rec.setAttribute("desc", sgr.getDescription());

   Record det = new ListGridRecord();
   
   for( AttributeReport ar : sgr.getAttributes() )
   {
//    System.out.println("Attr: "+ar.getName()+" "+ar.getValue());
    det.setAttribute( ar.getName(), ar.getValue() );
   }
  
   if( sgr.getOtherInfo() != null )
    det.setAttribute("__other", sgr.getOtherInfo());

   if( sgr.getAttachedClasses() != null )
   {
    for( String atCls : sgr.getAttachedClasses() )
     det.setAttribute("__$att$"+atCls, sgr.getAttachedObjects(atCls) );
   }
   
//   if( sgr.getPublications() != null )
//    det.setAttribute("__publ", sgr.getPublications() );
//
//   if( sgr.getContacts() != null )
//    det.setAttribute("__contact", sgr.getContacts() );
   
   det.setAttribute("__total", sgr.getRefCount());
   det.setAttribute("__matched", sgr.getMatchedCount());
//   det.setAttribute("__summary", String.valueOf(sgr.getRefCount())+"/"+sgr.getMatchedCount());
//   det.setAttribute("Selected samples", sgr.getMatchedCount());
  
   rec.setAttribute("details", new Record[]{det});
   
   resultGrid.addData(rec);
  }
  
  statusBar.setContents( "Groups: "+res.getTotalGroups()+" Samples: "+res.getTotalSamples() );
 }


 
// private void renderResultList(final VLayout lay, final Report smpls)
// {
//  if( smpls.getObjects().size() < 50 )
//  {
//   renderSampleList(lay,smpls);
//   return;
//  }
//  
//  final Window waitW = new Window();
//  
//  waitW.setHeight(100);
//  waitW.setWidth(250);
//  waitW.setShowMinimizeButton(false);  
//  waitW.setIsModal(true);  
//  waitW.setShowModalMask(true);  
//  waitW.centerInPage();
//
//  Label msg = new Label("<b>Please wait for result rendering</b>");
//  msg.setAlign(Alignment.CENTER);
//  
//  waitW.addItem(msg);
//  
//  waitW.addDrawHandler(new DrawHandler()
//  {
//   @Override
//   public void onDraw(DrawEvent event)
//   {
//    DeferredCommand.addCommand(new Command()
//    {
//     @Override
//     public void execute()
//     {
//      renderSampleList(lay,smpls);
//      waitW.destroy();
//     }
//    });
//   }
//  });
//  
//  waitW.show();
//  
// }

 
 
 private class GroupsList extends ListGrid
 {
  protected Canvas getExpansionComponent(final ListGridRecord record)
  {
   Canvas details = new GroupDetailsPanel(record.getAttributeAsRecordArray("details")[0], query, searchAtNames, searchAtValues );
   
   record.setAttribute("_panel", details);
   
   return details;
  }
 }
 
}
