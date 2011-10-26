package uk.ac.ebi.biosd.client.shared;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AttributeClassReport implements IsSerializable
{
 private String id;
 private String  name;
 private boolean custom;

 private Set<String> values;

 public String getId()
 {
  return id;
 }

 public void setId(String id)
 {
  this.id = id;
 }

 public String getName()
 {
  return name;
 }

 public void setName(String name)
 {
  this.name = name;
 }

 public boolean isCustom()
 {
  return custom;
 }

 public void setCustom(boolean custom)
 {
  this.custom = custom;
 }

 public void addValue(String attrval)
 {
  if( values == null )
   values = new HashSet<String>();
  
  values.add( attrval );
 }
 
 public Set<String> getValues()
 {
  return values;
 }
 
 public void clearValues()
 {
  values = null;
 }
}
