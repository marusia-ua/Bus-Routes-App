package com.marusia.bus.routes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marusia.bus.routes.domain.RouteResponse;
import com.marusia.bus.routes.service.RoutesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RoutesController.class)
public class RoutesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoutesService service;

    @Test
    public void testCheckConnection() throws Exception {
        when(service.checkConnection(any(Integer.class), any(Integer.class))).thenReturn(true);
        RouteResponse response = new RouteResponse(100, 200, true);
        mockMvc.perform(get("/api/direct")
                .param("dep_sid", "100")
                .param("arr_sid", "200")
        ).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void testCheckConnectionMissingArgument() throws Exception {
        mockMvc.perform(get("/api/direct")
                .param("dep_sid", "100")
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testCheckConnectionInvalidArgument() throws Exception {
        mockMvc.perform(get("/api/direct")
                .param("dep_sid", "AAA")
                .param("arr_sid", "200")
        ).andExpect(status().isBadRequest());
    }
}