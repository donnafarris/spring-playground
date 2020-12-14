package com.example.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(JSONResponseController.class)
public class JSONResponseControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetAFlight() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flights/flight").accept(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Some name")));
    }

    @Test
    public void testGetAFlightMatchesLearn() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flights/flight").accept(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                        "  \"departs\": \"2017-04-21 14:34\",\n" +
                        "  \"tickets\": [\n" +
                        "    {\n" +
                        "      \"passenger\": {\n" +
                        "        \"firstName\": \"Some name\",\n" +
                        "        \"lastName\": \"Some other name\"\n" +
                        "      },\n" +
                        "      \"price\": 200\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"));
    }


    @Test
    public void testGetFlightsUpperCase() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flights").accept(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.[1].tickets[0].price", is(400)));
    }

    @Test
    public void testPostTicketsStringLiteral() throws Exception {
        String response = "{\n  \"result\": 350\n}";
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n    \"tickets\": [\n      {\n        \"passenger\": {\n          \"firstName\": \"Some name\",\n          \"lastName\": \"Some other name\"\n        },\n        \"price\": 200\n      },\n      {\n        \"passenger\": {\n          \"firstName\": \"Name B\",\n          \"lastName\": \"Name C\"\n        },\n        \"price\": 150\n      }\n    ]\n  }");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(response));

    }

    @Test
    public void testPostTicketsObjectMapper() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // Spring / JSON Endpoints / Testing JSON Requests
        HashMap<String, Object> data = new HashMap<String, Object>(){
            {
                put("tickets", );
            }
        }
        String response = "{\n  \"result\": 350\n}";
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n    \"tickets\": [\n      {\n        \"passenger\": {\n          \"firstName\": \"Some name\",\n          \"lastName\": \"Some other name\"\n        },\n        \"price\": 200\n      },\n      {\n        \"passenger\": {\n          \"firstName\": \"Name B\",\n          \"lastName\": \"Name C\"\n        },\n        \"price\": 150\n      }\n    ]\n  }");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(response));

    }


}