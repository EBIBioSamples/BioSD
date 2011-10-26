package uk.ac.ebi.biosd.client.query;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ebi.biosd.client.shared.AttributeReport;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AttributedImprint implements IsSerializable
{
 private List<AttributeReport> attr = new ArrayList<AttributeReport>();
 
 public void addAttribute(String nm, String val, boolean cust, int ord)
 {
  AttributeReport atr = new AttributeReport();
  
  atr.setName(nm);
  atr.setValue(val);
  atr.setOrder(ord);
  atr.setCustom(cust);
  
  attr.add(atr);
 }
 
 public List<AttributeReport> getAttributes()
 {
  return attr;
 }
}
