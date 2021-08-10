@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDataApplicationTests {
	@Autowired
	DataSource dataSource ;
	@Test
	public void testDB() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println(connection.getClass());
		connection.close();
	}
}	