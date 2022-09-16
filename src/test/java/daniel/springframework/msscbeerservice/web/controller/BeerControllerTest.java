package daniel.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import daniel.springframework.msscbeerservice.service.BeerService;
import daniel.springframework.msscbeerservice.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BeerService mockBeerService;

    @Test
    void handleGetById() throws Exception {
        mockMvc.perform(get("/api/v1/beers/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void handlePost() throws Exception {
        BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        when(mockBeerService.createBeer(any(BeerDto.class))).thenReturn(beerDto);
        mockMvc.perform(post("/api/v1/beers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void handlePut() throws Exception {
        BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beers/" + beerDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void handleDelete() {
    }
}