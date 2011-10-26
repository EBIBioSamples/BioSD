package uk.ac.ebi.biosd.client.query;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Report implements IsSerializable
{
 private List<GroupImprint> objects;
 private int totalRecords;
 private int totalSamples;
 private int totalMatchedSamples=-1;
 
 public List<GroupImprint> getObjects()
 {
  return objects;
 }
 
 public void setObjects(List<GroupImprint> objects)
 {
  this.objects = objects;
 }
 
 public int getTotalGroups()
 {
  return totalRecords;
 }
 
 public void setTotalGroups(int totalRecords)
 {
  this.totalRecords = totalRecords;
 }

 public int getTotalSamples()
 {
  return totalSamples;
 }

 public void setTotalSamples(int totalSamples)
 {
  this.totalSamples = totalSamples;
 }

 public int getTotalMatchedSamples()
 {
  return totalMatchedSamples;
 }

 public void setTotalMatchedSamples(int totalMatchedSamples)
 {
  this.totalMatchedSamples = totalMatchedSamples;
 }
}
