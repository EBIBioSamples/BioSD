package uk.ac.ebi.biosd.client.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Pair<T1,T2> implements IsSerializable
{
 private T1 first;
 private T2 second;
 
 Pair()
 {}
 
 public Pair(T1 f, T2 s)
 {
  first = f;
  second = s;
 }

 public T1 getFirst()
 {
  return first;
 }

 public void setFirst(T1 first)
 {
  this.first = first;
 }

 public T2 getSecond()
 {
  return second;
 }

 public void setSecond(T2 second)
 {
  this.second = second;
 }
 
}
