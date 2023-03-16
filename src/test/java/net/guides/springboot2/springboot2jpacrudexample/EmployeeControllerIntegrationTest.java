package net.guides.springboot2.springboot2jpacrudexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.springboot2.jpacrudexample.Application;
import com.springboot2.jpacrudexample.model.SonarJobInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employees",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetEmployeeById() {
		SonarJobInfo employee = restTemplate.getForObject(getRootUrl() + "/employees/1", SonarJobInfo.class);
		System.out.println(employee.getJobName());
		assertNotNull(employee);
	}

	@Test
	public void testCreateEmployee() {
		SonarJobInfo employee = new SonarJobInfo();
		employee.setEmailId("admin@gmail.com");
		employee.setJobName("admin");
		employee.setJobType("admin");

		ResponseEntity<SonarJobInfo> postResponse = restTemplate.postForEntity(getRootUrl() + "/employees", employee, SonarJobInfo.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		int id = 1;
		SonarJobInfo employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, SonarJobInfo.class);
		employee.setJobName("admin1");
		employee.setJobType("admin2");

		restTemplate.put(getRootUrl() + "/employees/" + id, employee);

		SonarJobInfo updatedEmployee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, SonarJobInfo.class);
		assertNotNull(updatedEmployee);
	}

	@Test
	public void testDeleteEmployee() {
		int id = 2;
		SonarJobInfo employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, SonarJobInfo.class);
		assertNotNull(employee);

		restTemplate.delete(getRootUrl() + "/employees/" + id);

		try {
			employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, SonarJobInfo.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
