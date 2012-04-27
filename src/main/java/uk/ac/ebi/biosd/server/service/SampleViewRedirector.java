package uk.ac.ebi.biosd.server.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.ebi.age.admin.server.mng.Configuration;
import uk.ac.ebi.age.admin.server.service.ServiceServlet;
import uk.ac.ebi.age.authz.ACR.Permit;
import uk.ac.ebi.age.authz.PermissionManager;
import uk.ac.ebi.age.authz.Session;
import uk.ac.ebi.age.ext.authz.SystemAction;
import uk.ac.ebi.age.model.AgeObject;
import uk.ac.ebi.biosd.client.shared.MaintenanceModeException;

public class SampleViewRedirector extends ServiceServlet
{

 private static final long serialVersionUID = 1L;

 @Override
 protected void service(HttpServletRequest req, HttpServletResponse resp, Session sess) throws ServletException, IOException
 {
  BioSDService biosd = BioSDService.getInstance();
  
  if( biosd == null )
  {
   resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
   return;
  }
  
  System.out.println("SampleReq: "+req.getPathInfo());
  
  String sampleId = null;
  
  if( req.getPathInfo() != null)
   sampleId=req.getPathInfo().substring(1);
  
  AgeObject sample = null;
  
  if( sampleId != null )
  {
   try
   {
    sample = biosd.getSample(sampleId);
   }
   catch(MaintenanceModeException e)
   {
   }
  }
  
  if( sample == null )
  {
   System.out.println("Sample '"+sampleId+"' not found");
   return;
  }
  
  PermissionManager pm = Configuration.getDefaultConfiguration().getPermissionManager();
  
  
  
  if( pm.checkPermission(SystemAction.READ, sample) != Permit.ALLOW )
  {
   System.out.println("Not Allowed");
   return;
  }
   
  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SampleViewer.jsp?");
  req.setAttribute("Object",sample);
  dispatcher.forward(req,resp);
  
//  PermissionManager pm = Configuration.getDefaultConfiguration().getPermissionManager();
//  
//  if( pm.checkSystemPermission(SystemAction.EXPORT_GRAPH_DATA, sess.getUser()) != Permit.ALLOW )
//  {
//   resp.sendError(HttpServletResponse.SC_FORBIDDEN);
//   return;
//  }
  
//  resp.setContentType("text/xml; charset=UTF-8");
//  resp.setHeader("Content-Disposition", "attachment; filename=\"biosamples.xml\"");
//
//  biosd.exportData( resp.getWriter() );
 }
}