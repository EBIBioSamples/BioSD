package uk.ac.ebi.biosd.server.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.ebi.biosd.server.stat.BioSDStat;

public class DBStat extends HttpServlet
{

 private static final long serialVersionUID = -1L;

 public void doGet(HttpServletRequest req, HttpServletResponse resp)
 {
  BioSDStat stat = BioSDService.getInstance().getStatistics();
  
  try
  {
   PrintWriter out = resp.getWriter();
   
   out.print("var bioSDStat = {\n");
   sendStat(out,stat);
   out.print("}\n");
  }
  catch(IOException e)
  {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
 }
 
 private void sendStat( PrintWriter out, BioSDStat stat )
 {
 
   out.print("samples : "+stat.getSamples());
   out.print(",\ngroups : "+stat.getGroups());
   
   if( stat.getTopics() != null )
   {
    
    out.print(",\ntopics: {\n");
    
    boolean first = true;
    
    for(Map.Entry<String, BioSDStat> me  : stat.getTopics().entrySet() )
    {
     if( ! first )
      out.print(",\n");
     else
      first=false;
     
     out.print("\""+me.getKey()+"\": {\n");
     sendStat(out, me.getValue());
     out.print("}");
    }
    out.print("\n}\n");
   }
  
  out.print("\n");
 }
}
