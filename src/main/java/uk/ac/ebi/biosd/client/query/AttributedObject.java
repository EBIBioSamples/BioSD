package uk.ac.ebi.biosd.client.query;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AttributedObject implements IsSerializable
{
 private String                    name;
 private String                    value;
 private AttributedObject          objectValue;
 private List<AttributedObject> attributes;

 public AttributedObject()
 {
 }

// public AttributedObject(String nm, Object v)
// {
//  name=nm;
//  value=v;
// }

 public String getFullName()
 {
  return name;
 }

 public String getName()
 {
  if( name.length() > 3 && name.charAt(2) == ':' )
   return name.substring(3);
  else
   return name;
 }

 public void setName(String name)
 {
  this.name = name;
 }

 public String getStringValue()
 {
  return value!= null?value:objectValue.getName();
 }

 public void setStringValue(String value)
 {
  this.value = value;
 }
 
 public AttributedObject getObjectValue()
 {
  return objectValue;
 }

 public void setObjectValue(AttributedObject value)
 {
  this.objectValue = value;
 }


 public List<AttributedObject> getAttributes()
 {
  return attributes;
 }

 public void setAttributes(List<AttributedObject> qualifiers)
 {
  this.attributes = qualifiers;
 }

 public String toString()
 {
  if( attributes == null || attributes.size() == 0 )
   return name;
  
  StringBuilder sb = new StringBuilder(255);
  
  for( AttributedObject atOb : attributes )
   sb.append(atOb.getName()).append(": ").append(atOb.getStringValue()).append(", ");
  
  sb.setLength(sb.length()-2);
  
  return sb.toString();
 }
}
