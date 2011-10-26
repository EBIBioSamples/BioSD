package uk.ac.ebi.biosd.server.service;

import uk.ac.ebi.biosd.server.stat.BioSDStat;

public class UserCacheObject
{
 private String userName;
 
 private BioSDStat statistics;
 
 private String allowTags;
 
 private String denyTags;

 public String getUserName()
 {
  return userName;
 }

 public void setUserName(String userName)
 {
  this.userName = userName;
 }

 public BioSDStat getStatistics()
 {
  return statistics;
 }

 public void setStatistics(BioSDStat statistics)
 {
  this.statistics = statistics;
 }

 public String getAllowTags()
 {
  return allowTags;
 }

 public void setAllowTags(String allowTags)
 {
  this.allowTags = allowTags;
 }

 public String getDenyTags()
 {
  return denyTags;
 }

 public void setDenyTags(String denyTags)
 {
  this.denyTags = denyTags;
 }
 
 
}
