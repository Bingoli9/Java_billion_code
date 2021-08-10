@RestController
public class Boot {
    @RequestMapping(value = "/helloGroovy")
    public String sayHello() {
        return "Hello Spring Boot,hello Groovy";
    }
}