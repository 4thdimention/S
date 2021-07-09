

@Component(Service = {OpportunityDetailService.class})
public class OpportunityDetailsService{
	
	@Reference
	@Self
	private QueryBuilder builder;
	
	public JSONObject getDetails(SlingHttpDervletRequest request){
		String[] selectors = request.getRequestPathInfo().getSelectors();
		String[] suffix = request.getRequestPathInfo().getSuffix().split("/");
		log.info("suffix{}{}",request.getRequestPatInfo().getSuffix());
		
		JSONObject mainMapObject = new JSONObject();
		
		try {
			Session session = request.getResourceResolver().adaptTo(Session.class);
			if(builder != null && session != null){
				Map<String , String> map = new HashMap<>();
				map.put("path","/content/msdotcom/en/people-opportunities/students/students-graduates/program-details-cities");
				map.put("type","cq:page");
				map.put("group.property","jcr:content/jcr:title");
				map.put("property.OR","true");
				map.put("p.limit","-1");
				
				if(selectors != null && "dynadata_locations-results".equalsIgnoreCase(selectors[0])){
					for(int i=0; i<suffix.length; i++){
						log.info(" suffix {}{}",wsuffix[i]);
						map.put("group.property."+i+"_value",suffix[i]);
					}
				}
				log.info("=========>{}",map);
					Query query = builder.createQuery(PredicateGroup.create(map), session);
					query.setStart(0);
					SearchResult result = query.getResult();
					log.info("result{}",result);
					JSONArray propertyJsonArray = new JSONArray();
					JSONObject categoryOP = new JSONObject();
					Number categoryTimeStamp = 0;
					Ffor (Hit hit : result.getHits()){
						Resource opNode = request.getResourceResolver().getResource(hit.getPath()+"/jc:content/program-city-details");
						
						if(opNode != null){
							ValueMap properties = opNode.getValueMap();
							log.info("value"+properties);
							if(properties != null){
								JSONObject obj = new JSONObject();
								obj.put("citytext",(String) properties.getOrDefault("citytext",""));
								obj.put("imagepath",(String) properties.getOrDefault("imagepath",""));
								mainMapObject.put(hit.getTitle(),obj);
							}
						}
					}
			}else
			{
				log.info("Invalid Session");
			}
			
		}catch (Exception e){
			log.error("Service Excepton :: {0}",e);
			
		}return mainMapObject;
		
	}

}