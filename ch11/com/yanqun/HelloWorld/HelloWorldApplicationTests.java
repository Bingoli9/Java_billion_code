import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTests {
	Logger logger = LoggerFactory.getLogger(HelloWorldApplicationTests.class);
	@Test
	public void testLog() {// 日志级别
		logger.trace("trace********");
		logger.debug("debug********");
		logger.info("info*******");
		logger.warn("warn******");
		logger.error("error****");
	}
}