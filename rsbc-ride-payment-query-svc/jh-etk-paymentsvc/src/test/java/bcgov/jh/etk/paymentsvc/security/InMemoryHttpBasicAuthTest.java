package bcgov.jh.etk.paymentsvc.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@MockBean(JpaMetamodelMappingContext.class)
//@TestPropertySource(locations = "classpath:application.properties")
public class InMemoryHttpBasicAuthTest {

	@Autowired
	private MockMvc mockMvc;

	@Value("${svc.security.user.name}")
	private String apiUserName;

	@Value("${svc.security.user.password}")
	private String apiUserPassword;

	//	@Test
	public void accessProtected() throws Exception {
		this.mockMvc.perform(get("/"))
				.andExpect(unauthenticated())
				.andExpect(status().isUnauthorized());
	}

//	@Test
	public void loginUser() throws Exception {
		this.mockMvc.perform(get("/")
				.with(httpBasic(apiUserName, apiUserPassword)))
				.andExpect(status().isOk());
	}

//	@Test
	public void loginInvalidPass() throws Exception {
		this.mockMvc.perform(get("/")
				.with(httpBasic(apiUserName, apiUserPassword+"123")))
				.andExpect(status().isUnauthorized());
	}

//	@Test
	public void loginInvalidUser() throws Exception {
		this.mockMvc.perform(get("/")
				.with(httpBasic(apiUserName+"123", apiUserPassword)))
				.andExpect(status().isUnauthorized());
	}


//	@Test
	public void loginInvalidUserPass() throws Exception {
		this.mockMvc.perform(formLogin().user("invalid").password("invalid"))
				.andExpect(unauthenticated());
	}

}
