package com.example.json;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.is;
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
                .andExpect(jsonPath("$.Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Some name")));
    }

    @Test
    public void testGetAFlightMatchesLearn() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flights/flight").accept(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                        "  \"Departs\": \"2017-04-21 14:34\",\n" +
                        "  \"Tickets\": [\n" +
                        "    {\n" +
                        "      \"Passenger\": {\n" +
                        "        \"FirstName\": \"Some name\",\n" +
                        "        \"LastName\": \"Some other name\"\n" +
                        "      },\n" +
                        "      \"Price\": 200\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"));
    }

//    @Test
//    public void testGetFlights() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.get("/flights").accept(MediaType.APPLICATION_JSON);
//        this.mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.[0].departs", is("2017-04-21 14:34")))
//                .andExpect(jsonPath("$.[1].tickets[0].price", is(400)));
//    }

    @Test
    public void testGetFlightsUpperCase() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flights").accept(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.[1].Tickets[0].Price", is(400)));
    }

}