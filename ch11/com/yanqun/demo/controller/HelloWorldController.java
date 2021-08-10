@RestController
public class HelloWorldController {
	@Resource
	private HttpClientService httpAPIService;
    //测试doGet()
	@ResponseBody
	@GetMapping("doGetHttpClient")
	public String doGetHttpClient() throws Exception {
		String response = httpAPIService.doGet("http://www.baidu.com") ;
		System.out.println( response);
		return response;
	}
//测试doPost()
	@ResponseBody
	@GetMapping("doPostHttpClient")
	public String doPostHttpClient() throws Exception {
		String url = "某网站域名";
		Map<String, Object> params = new HashMap<>();
		params.put("author", "YanQun") ;
		String response = httpAPIService.doPost(url, params);
		System.out.println( response);
		return response;
	}

} 