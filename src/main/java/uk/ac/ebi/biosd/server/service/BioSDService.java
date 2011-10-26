package uk.ac.ebi.biosd.server.service;

import uk.ac.ebi.biosd.client.query.Report;
import uk.ac.ebi.biosd.client.query.SampleList;
import uk.ac.ebi.biosd.server.stat.BioSDStat;

public abstract class BioSDService
{
 private static BioSDService service;
 
 public static BioSDService getInstance()
 {
  return service;
 }
 
 public static void setDefaultInstance( BioSDService srv )
 {
  service=srv;
 }

 public abstract Report selectSampleGroups(String value, boolean searchSmp, boolean searchGrp, boolean searchAttrNm, boolean searchAttrVl, boolean refOnly, int offset, int count);

 public abstract void shutdown();

 public abstract SampleList getSamplesByGroup(String grpID, int offset, int count);

 public abstract SampleList getSamplesByGroupAndQuery(String grpId, String query, boolean searchAtNames, boolean searchAtValues, int offset, int count);

 public abstract BioSDStat getStatistics();

}
