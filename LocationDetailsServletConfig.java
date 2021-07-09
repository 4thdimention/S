package core.servlets;

import org.osgi.service.metatype.annotations.AttributeDefination;
import org.osgi.service.metatype.annotations.ObjectClassDefination; 
//sling/servlet/default

public @interface LocationDetailsServletConfig{
	@AttributeDefination(name="Location Servlet", description="get list of locatios for the careers page")
	String sling_servlet_paths() default "bin/location/details";
	
	@AttributeDefination(name="Property sling.servlet.resourceTypes", description="Description for sling.servlet.resouceType")
	String sling_servlet_resourceType() default "sling/servlet/default";
	
	@AttributeDefination(name="Property sling.servlet.selectors", description="Description for sling.servlet.selectors")
	String sling_servlet_selectors() default {"dynadata_locations-results"};
	
	@AttributeDefination(name="Property sling.servlet.extentions", description="Description for sling.servlet.extentions")
	String sling_servlet_selectors() default "json";
	
	@AttributeDefination(name="Property sling.servlet.methods", description="Description for sling.servlet.methods")
	String sling_servlet_selectors() default "GET";
	
}