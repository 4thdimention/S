

import services.OpportunityDetailService;


@Component(service={Servlet.class,ServletConfig.class,Serializeable.class})
@Designate(0cd=LocatonDetailsServletConfig.class)
public class LocationDetailServlet extends SlingSafeMethodsServlet{
	
	LocationDetailServletConfig config;
	
	@Reference
	OpportunityDetailService locationService;
	
	@Activate
	@Modified
	public void activate(LocationDetailServletConfig config)
	{
		this.config=config;
	}

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletReponse response)throws ServletException, IOException{
		response.setContentType("application/json");
		response.setStatus(200);
		response.getWritter().write(locationService.getDetails(request).toString());
	}
}