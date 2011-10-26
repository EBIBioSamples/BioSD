package uk.ac.ebi.biosd.client.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AttributeReport implements IsSerializable
{
 private String name;
 private String value;
 private boolean isCustom;
 private int order;
 
 public String getName()
 {
  return name;
 }
 
 public void setName(String name)
 {
  this.name = name;
 }
 
 public String getValue()
 {
  return value;
 }
 
 public void setValue(String value)
 {
  this.value = value;
 }
 
 public boolean isCustom()
 {
  return isCustom;
 }
 
 public void setCustom(boolean isCustom)
 {
  this.isCustom = isCustom;
 }

 public int getOrder()
 {
  return order;
 }

 public void setOrder(int order)
 {
  this.order = order;
 }
}
