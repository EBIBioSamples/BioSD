package uk.ac.ebi.biosd.client;

import uk.ac.ebi.biosd.client.query.Report;
import uk.ac.ebi.biosd.client.query.SampleList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("ageQueryGWT")
public interface QueryService extends RemoteService
{
 public static class Util
 {
  private static QueryServiceAsync instance;
  
  public static QueryServiceAsync getInstance()
  {
   if( instance != null )
    return instance;
   
   
   instance = (QueryServiceAsync) GWT.create(QueryService.class);
   return instance;
  }
 }
 
 
 Report selectSampleGroups(String value, boolean searchSmp, boolean searchGrp, boolean searchAttrNm, boolean searchAttrVl, boolean refOnly, int offs, int cnt);


 SampleList getSamplesByGroup(String grpID, int offs, int count);


 SampleList getSamplesByGroupAndQuery(String grpId, String query, boolean searchAtNames, boolean searchAtValues, int offs, int count);



}
