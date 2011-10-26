package uk.ac.ebi.biosd.client.ui.module;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class QueryFace extends VLayout
{
 private ResultPane resultPane;
 private QueryPanel queryPanel;
 
 public QueryFace()
 {
  setWidth100();
  setHeight100();

  resultPane = new ResultPane();

  queryPanel = new QueryPanel(resultPane);

  // qp.setHeight(200);
  queryPanel.setWidth("100%");

  resultPane.setWidth100();
  resultPane.setHeight100();

//  Label banPan = new Label("<div class=\"headerdiv\" id=\"headerdiv\" style=\"z-index: 1;\">"
//        +"<iframe src=\"/inc/head.html\" name=\"head\" id=\"head\" frameborder=\"0\" marginwidth=\"0px\" marginheight=\"0px\" scrolling=\"no\"  width=\"100%\" style=\"z-index: 1; height: 57px;\">Your browser does not support inline frames or is currently configured not to display inline frames. Content can be viewed at actual source page: http://www.ebi.ac.uk/inc/head.html</iframe>"
//    +"</div>");

//  Label banPan = new Label("<div class=\"headerdiv\" id=\"headerdiv\"\r\n" + 
//  		"    style=\"position: absolute; z-index: 1;\"><iframe\r\n" + 
//  		"    src=\"/inc/head.html\" name=\"head\" id=\"head\" frameborder=\"0\"\r\n" + 
//  		"    marginwidth=\"0px\" marginheight=\"0px\" scrolling=\"no\" width=\"100%\"\r\n" + 
//  		"    style=\"position: absolute; z-index: 1; height: 57px;\"></iframe></div>");
  
  Label banPan = new Label();
 
  banPan.setWidth100();
  banPan.setHeight(57);
  
  addMember(banPan);
  addMember(queryPanel);
  addMember(resultPane);

  Label footPan = new Label("<iframe src=\"/inc/foot.html\" name=\"foot\"\r\n" + 
  		"    frameborder=\"0\" marginwidth=\"0px\" marginheight=\"0px\" scrolling=\"no\"\r\n" + 
  		"    height=\"22px\" width=\"100%\" style=\"z-index: 2\"></iframe>");
  
  footPan.setWidth100();
  footPan.setHeight(22);

  addMember( footPan );
  
  Scheduler.get().scheduleDeferred(new ScheduledCommand()
  {
   @Override
   public void execute()
   {
    queryPanel.executeQuery();
    
//    QueryService.Util.getInstance().getAllGroups(0, ResultPane.MAX_GROUPS_PER_PAGE, new AsyncCallback<Report>()
//    {
//
//     @Override
//     public void onFailure(Throwable arg0)
//     {
//      arg0.printStackTrace();
//      SC.say("Query error: " + arg0.getMessage());
//     }
//
//     @Override
//     public void onSuccess(Report resLst)
//     {
//      resultPane.showResult(resLst, null, false, false, false, false,1);
//     }
//    });
   }
  });

 }
}
