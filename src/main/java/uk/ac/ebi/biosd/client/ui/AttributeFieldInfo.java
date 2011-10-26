package uk.ac.ebi.biosd.client.ui;


public class AttributeFieldInfo implements Comparable<AttributeFieldInfo>
{
 private String title;
 private String field;
 private int order;

 public AttributeFieldInfo(String name, String fname, int int1)
 {
  title=name;
  field=fname;
  order=int1;
 }

 @Override
 public int compareTo(AttributeFieldInfo o)
 {
  if( order != o.order )
   return order-o.order;

  return title.compareTo(o.title);
 }

 public String getTitle()
 {
  return title;
 }

 public void setTitle(String title)
 {
  this.title = title;
 }

 public String getField()
 {
  return field;
 }

 public void setField(String field)
 {
  this.field = field;
 }

 public int getOrder()
 {
  return order;
 }

 public void setOrder(int order)
 {
  this.order = order;
 }

 public void add(int order2)
 {
  order+=order2;
 }
}
