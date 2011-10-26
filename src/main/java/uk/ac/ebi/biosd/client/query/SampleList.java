package uk.ac.ebi.biosd.client.query;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ebi.biosd.client.shared.AttributeClassReport;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SampleList implements IsSerializable
{
 private List< AttributedObject > samples = new ArrayList<AttributedObject>(25);
 private List<AttributeClassReport> header;
 private int totalRecords;

 public SampleList()
 {}
 
 public List< AttributedObject > getSamples()
 {
  return samples;
 }

 public List<AttributeClassReport> getHeader()
 {
  return header;
 }

 public void addSample(AttributedObject smp)
 {
  samples.add(smp);
 }

 public void setHeader(List<AttributeClassReport> clsLst)
 {
  header=clsLst;
 }

 public int getTotalRecords()
 {
  return totalRecords;
 }

 public void setTotalRecords(int totalRecords)
 {
  this.totalRecords = totalRecords;
 }

}
