package uk.ac.ebi.biosd.client.shared;

public class Int
{
 private int value;

 public Int()
 {
  value = 0;
 }
 
 public Int(int v)
 {
  value=v;
 }

 public void inc()
 {
  value++;
 }
 
 public void add( int inc )
 {
  value+=inc;
 }

 
 public int getValue()
 {
  return value;
 }

 public void setValue(int value)
 {
  this.value = value;
 }
 
 
 
}
