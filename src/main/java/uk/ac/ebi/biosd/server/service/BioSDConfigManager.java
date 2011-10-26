package uk.ac.ebi.biosd.server.service;

import javax.servlet.ServletContext;

import uk.ac.ebi.age.admin.server.mng.AgeAdminConfigManager;


public class BioSDConfigManager extends AgeAdminConfigManager
{
 public static final String SAMPLE_CLASS_NAME = "Sample";
 public static final String SAMPLEGROUP_CLASS_NAME = "Group";
 
 public static final String SAMPLE_NAME_FIELD_NAME = "sname";
 public static final String SAMPLE_VALUE_FIELD_NAME = "svalue";
 public static final String GROUP_NAME_FIELD_NAME = "gname";
 public static final String GROUP_VALUE_FIELD_NAME = "gvalue";
 public static final String GROUP_REFERENCE_FIELD_NAME = "gref";
 public static final String GROUP_ID_FIELD_NAME = "gid";
 public static final String SECTAGS_FIELD_NAME = "sectags";
 public static final String OWNER_FIELD_NAME = "owner";

 public static final String SAMPLEINGROUP_REL_CLASS_NAME = "belongsTo";
 
 public static final String REFERENCE_ATTR_CLASS_NAME = "Submission Reference Layer";
 public static final String DESCRIPTION_ATTR_CLASS_NAME = "Submission Description";
 public static final String COMMENT_ATTR_CLASS_NAME = "Comment";
 public static final String HAS_PUBLICATION_REL_CLASS_NAME = "hasPublication";
 public static final String CONTACT_OF_REL_CLASS_NAME = "contactOf";
 public static final String DATASOURCE_ATTR_CLASS_NAME = "Data Source";
 public static final String PUBLICATIONS_ATTR_CLASS_NAME = "Publications";
 public static final String PUBMEDID_ATTR_CLASS_NAME = "Publication PubMed ID";
 public static final String PUBDOI_ATTR_CLASS_NAME = "Publication DOI";

 private static BioSDConfigManager instance = null;
 
 public BioSDConfigManager(ServletContext servletContext)
 {
  super(servletContext);
 }
 
 public static void setInstance( BioSDConfigManager inst )
 {
  instance=inst;
 }

 
 public static BioSDConfigManager instance()
 {
  return instance;
 }

}
