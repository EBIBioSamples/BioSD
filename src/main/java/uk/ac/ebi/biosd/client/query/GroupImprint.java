package uk.ac.ebi.biosd.client.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ebi.biosd.client.shared.Pair;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GroupImprint extends AttributedImprint implements IsSerializable
{
 private String id;
 private String descr;
 
 private int matchedCount;
 
 private Collection<String> matchedSamples = new ArrayList<String>(50);
 private Collection<String> allSamples = new ArrayList<String>(50);
 private List<Pair<String,String>> othr ;
 private int refCount;
 
 private Map<String,List<AttributedImprint>> attachedObjects;
 
// private List<AttributedImprint> publications;
// private List<AttributedImprint> contacts;
 
 

 public void addMatchedSample(String obj)
 {
  matchedSamples.add( obj );
 }

 public void addSample( String obj )
 {
  allSamples.add( obj );
 }
 
 public void setId(String id)
 {
  this.id = id;
 }

 public void setDescription(String string)
 {
  descr = string;
 }

 public String getDescription()
 {
  return descr;
 }

 public String getId()
 {
  return id;
 }

 public Collection<String> getMatchedSamples()
 {
  return matchedSamples;
 }

 public Collection<String> getAllSamples()
 {
  return allSamples;
 }



 public void setRefCount(int sCount)
 {
  refCount = sCount;
 }
 
 public int getRefCount()
 {
  return refCount;
 }

 public int getMatchedCount()
 {
  return matchedCount;
 }

 public void setMatchedCount(int matchedCount)
 {
  this.matchedCount = matchedCount;
 }

 public void addOtherInfo(String name, String val)
 {
  if( othr == null )
   othr = new ArrayList<Pair<String,String>>(5);
  
  othr.add( new Pair<String, String>(name, val) );
 }
 
 public List< Pair<String, String> > getOtherInfo()
 {
  return othr;
 }

 public void attachObjects( String cls, AttributedImprint obj )
 {
  List<AttributedImprint> objList=null;
  
  if( attachedObjects == null )
  {
   attachedObjects = new HashMap<String, List<AttributedImprint>>();
   attachedObjects.put(cls, objList = new ArrayList<AttributedImprint>(4) );
  }
  else
  {
   objList = attachedObjects.get(cls);
   
   if( objList == null  )
    attachedObjects.put(cls, objList = new ArrayList<AttributedImprint>(4) );
  }
  
  objList.add(obj);
 }
 
 public Collection<String> getAttachedClasses()
 {
  if( attachedObjects == null )
   return null;
  
  return attachedObjects.keySet();
 }
 
 public List<AttributedImprint> getAttachedObjects( String cls )
 {
  return attachedObjects.get(cls);
 }
}
