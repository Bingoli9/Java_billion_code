public class HttpResponse {
	// 响应状态码
	private Integer code;
	// 响应体
	private String body;
	public HttpResponse() {
	}
	public HttpResponse(Integer code, String body) {
		this.code = code;
		this.body = body;
	}
	//getter、setter
}