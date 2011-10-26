package uk.ac.ebi.biosd.server.service;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import uk.ac.ebi.age.admin.server.mng.AgeAdmin;
import uk.ac.ebi.age.admin.server.mng.AgeAdminException;
import uk.ac.ebi.age.admin.server.mng.Configuration;
import uk.ac.ebi.age.mng.AgeStorageManager;
import uk.ac.ebi.age.mng.AgeStorageManager.DB_TYPE;
import uk.ac.ebi.age.model.IdScope;
import uk.ac.ebi.age.parser.SyntaxProfile;
import uk.ac.ebi.age.parser.impl.ClassSpecificSyntaxProfileDefinitionImpl;
import uk.ac.ebi.age.parser.impl.SyntaxProfileDefinitionImpl;
import uk.ac.ebi.age.service.id.IdGenerator;
import uk.ac.ebi.age.service.id.impl.SeqIdGeneratorImpl;
import uk.ac.ebi.age.storage.AgeStorageAdm;

/**
 * Application Lifecycle Listener implementation class Init
 * 
 */
public class Init implements ServletContextListener
{
 private AgeAdmin adm;
 
 public Init()
 {
 }

 /**
  * @see ServletContextListener#contextInitialized(ServletContextEvent)
  */
 public void contextInitialized(ServletContextEvent arg0)
 {
  BioSDConfigManager cfg = new BioSDConfigManager( arg0.getServletContext() );

  BioSDConfigManager.setInstance(cfg);
  
  AgeStorageAdm storage;
  
  try
  {
   boolean master = cfg.isMaster();
   
   if( master )
    IdGenerator.setInstance( new SeqIdGeneratorImpl(cfg.getServicesPath()+"/SeqIdGen") );
   
   storage = AgeStorageManager.createInstance( DB_TYPE.AgeDB, cfg.getAgeDBPath(), master );

  }
  catch(Exception e)
  {
   e.printStackTrace();
   return;
  }
  
  Configuration conf = Configuration.getDefaultConfiguration();
  
  conf.setBaseDir( new File(cfg.getBasePath()) );
  conf.setTmpDir( new File(cfg.getTmpPath()) );

  SyntaxProfile sp = new SyntaxProfile();
  SyntaxProfileDefinitionImpl commSP = new SyntaxProfileDefinitionImpl();
  commSP.setClusterIdPrefix("$C:");
  commSP.setModuleIdPrefix("$M:");
  commSP.setGlobalIdPrefix("$G:");
  commSP.setDefaultIdScope(IdScope.GLOBAL);
  commSP.setPrototypeObjectId("<<ALL>>");
  commSP.setResetPrototype(true);
 
  ClassSpecificSyntaxProfileDefinitionImpl vertClasses = new ClassSpecificSyntaxProfileDefinitionImpl(commSP);
  vertClasses.setHorizontalBlockDefault(false);

  ClassSpecificSyntaxProfileDefinitionImpl vertModClasses = new ClassSpecificSyntaxProfileDefinitionImpl(commSP);
  vertModClasses.setHorizontalBlockDefault(false);
  vertModClasses.setDefaultIdScope(IdScope.MODULE);
  
  sp.setCommonSyntaxProfile(commSP);
  
  sp.addClassSpecificSyntaxProfile("Submission", vertClasses);
  sp.addClassSpecificSyntaxProfile("Group", vertClasses);

  sp.addClassSpecificSyntaxProfile("Term Source", vertModClasses);
  sp.addClassSpecificSyntaxProfile("Person", vertModClasses);
  sp.addClassSpecificSyntaxProfile("Publication", vertModClasses);
  sp.addClassSpecificSyntaxProfile("Organization", vertModClasses);
  sp.addClassSpecificSyntaxProfile("Database", vertModClasses);
  
  conf.setSyntaxProfile(sp);
  
  try
  {
   adm = new AgeAdmin(conf, storage);
  }
  catch(AgeAdminException e)
  {
   // TODO Auto-generated catch block
   e.printStackTrace();
   return;
  }
  
  BioSDServiceImpl biosd=null;
  try
  {
   biosd = new BioSDServiceImpl( storage );
  }
  catch(BioSDInitException e)
  {
   // TODO Auto-generated catch block
   e.printStackTrace();
   return;
  }
  
  Configuration.getDefaultConfiguration().getAuthDB().addSecurityChangedListener(biosd);
  
  BioSDService.setDefaultInstance( biosd );

 }

 /**
  * @see ServletContextListener#contextDestroyed(ServletContextEvent)
  */
 public void contextDestroyed(ServletContextEvent arg0)
 {
  BioSDService service = BioSDService.getInstance();

  
  if( service != null )
   service.shutdown();
  
  if( adm != null )
   adm.shutdown();

  if( BioSDConfigManager.instance().isMaster() )
   IdGenerator.getInstance().shutdown();
 }


 
}
