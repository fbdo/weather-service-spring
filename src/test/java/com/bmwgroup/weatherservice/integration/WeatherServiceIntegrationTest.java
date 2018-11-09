package com.bmwgroup.weatherservice.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
		"openweathermap.url=http://localhost:8080/find?appid=111222333&q=%s&units=metric"
})
public class WeatherServiceIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().port(8080).notifier(new ConsoleNotifier(true)));


	@Test
	public void testGetCurrent() throws Exception {

		stubFor(WireMock.get(urlEqualTo("/find?appid=111222333&q=Munich&units=metric"))
				.withHeader("Accept", WireMock.equalTo("application/json, application/*+json"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withBodyFile("wiremock/openweathermap_by_city_response.json")));


		mvc.perform(get("/current"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.temperature", is(8.0)))
				.andExpect(jsonPath("$.description", is("mist")));
	}

}
