package uk.ac.ebi.biosd.server.service;


public class BioSDInitException extends Exception
{

 private static final long serialVersionUID = 1L;

 public BioSDInitException( String msg )
 {
  super(msg);
 }
 
 public BioSDInitException( String msg, Throwable t )
 {
  super(msg, t);
 }
}
