package com.example.contocorrente.api;


import com.example.contocorrente.model.Anagrafica;
import com.example.contocorrente.service.AnagraficaService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class AnagraficaControllerTest {

   @MockBean
    private AnagraficaService anagraficaService;

   @Autowired
    private MockMvc mockMvc;


   @Test
    @DisplayName("GET /api/v1/anagrafica success")
    void testGetAllAnagrafiche() throws Exception {
       Anagrafica anagrafica1 = new Anagrafica(1L, "CCZ", "Daniele", "Cocuzza", new Date());
       Anagrafica anagrafica2 = new Anagrafica(2L, "PCR", "Pierpaolo", "Pecoraio", new Date());
       doReturn(Lists.newArrayList(anagrafica1, anagrafica2)).when(anagraficaService).getAllAnagrafiche();

        mockMvc.perform(get("/api/v1/anagrafica"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", is("Daniele")))
                .andExpect(jsonPath("$[0].cognome", is("Cocuzza")))
                .andExpect(jsonPath("$[1].nome", is("Pierpaolo")))
                .andExpect(jsonPath("$[1].cognome", is("Pecoraio")));

   }

  /* @Test
    @DisplayName("GET /api/v1/anagrafica/1")
    void testGetAnagraficaById() throws Exception{
       Anagrafica anagrafica1 = new Anagrafica(1L, "CCZ", "Daniele", "Cocuzza", new Date());
       doReturn(Optional.of(anagrafica1)).when(anagraficaService).getAnagraficaById(1l);

       mockMvc.perform(get("/api/v1/anagrafica/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.nome", is("Daniele")))
               .andExpect(jsonPath("$.cognome", is("Cocuzza")));

   }*/

   /* @Test
    @DisplayName("GET /api/v1/anagrafica/1 - Not Found")
    void testGetAnagraficaByIdNotFound() throws Exception {
        // Setup our mocked service
        doReturn(Optional.empty()).when(anagraficaService).getAnagraficaById(1l);

        // Execute the GET request
        mockMvc.perform(get("/api/v1/anagrafica/{id}", 1L))
                // Validate the response code
                .andExpect(status().isNotFound());
    }
*/
    @Test
    @DisplayName("POST /api/v1/anagrafica")
    void testAddAnagrafica(){
        Anagrafica anagrafica = new Anagrafica(1L, "CCZ", "Daniele", "Cocuzza", new Date());
        anagraficaService.addAnagrafica(anagrafica);
        verify(anagraficaService, times(1)).addAnagrafica(anagrafica);
    }

    @Test
    @DisplayName("PUT /api/v1/anagrafica")
    void testUpdateAnagrafica(){
        Anagrafica anagrafica1 = new Anagrafica(1L, "CCZ", "Daniele", "Cocuzza", new Date());
        anagraficaService.addAnagrafica(anagrafica1);
        anagrafica1.setCf("CCZDNL");
        anagraficaService.updateAnagrafica(anagrafica1.getId(), anagrafica1);
        verify(anagraficaService, times(1)).updateAnagrafica(anagrafica1.getId(), anagrafica1);
    }





}
