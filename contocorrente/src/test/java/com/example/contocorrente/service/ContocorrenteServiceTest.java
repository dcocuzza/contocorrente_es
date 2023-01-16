package com.example.contocorrente.service;

import com.example.contocorrente.model.Anagrafica;
import com.example.contocorrente.model.Contocorrente;
import com.example.contocorrente.model.Movimento;
import com.example.contocorrente.repository.ContocorrenteRepository;
import com.example.contocorrente.repository.MovimentoRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class ContocorrenteServiceTest {

    @Autowired
    private ContocorrenteService contocorrenteService;

    @MockBean
    private ContocorrenteRepository contocorrenteRepository;

   /* @Test
    @DisplayName("Test getById success")
    void testGetById(){
        Anagrafica anagrafica = new Anagrafica("CCZ", "Daniele", "Cocuzza", new Date());
        Contocorrente contocorrente = new Contocorrente("IT002354", "IT", "002", "354", new Date(), null, 500,anagrafica, null);

        doReturn(Optional.of(contocorrente)).when(contocorrenteRepository).findById(contocorrente.getId());

        Optional<Contocorrente> ritornato = contocorrenteService.getContocorrenteById(contocorrente.getId());


        Assertions.assertTrue(ritornato.isPresent(), "Contocorrente non trovato");

    }*/

   /* @Test
    @DisplayName("Test getById Not Found")
    void testGetByIdNotFound() {
        doReturn(Optional.empty()).when(contocorrenteRepository).findById(1l);

        Optional<Contocorrente> ritornato = contocorrenteService.getContocorrenteById(1l);

        Assertions.assertFalse(ritornato.isPresent(), "Il contocorrente non dovrebbe essere trovato");

    }
*/
    @Test
    @DisplayName("Test getAll")
    void testGetAll() {
        Anagrafica anagrafica = new Anagrafica("CCZ", "Daniele", "Cocuzza", new Date());
        Contocorrente contocorrente1 = new Contocorrente("IT002354", "IT", "002", "354", new Date(), null, 500, anagrafica, null);
        Contocorrente contocorrente2 = new Contocorrente("FR003355", "FR", "003", "355", new Date(), null, 500, anagrafica, null);

        doReturn(Arrays.asList(contocorrente1, contocorrente2)).when(contocorrenteRepository).findAll();


        List<Contocorrente> conti = contocorrenteService.getAllConticorrenti();

        Assertions.assertEquals(2, conti.size(), "Dovrebbero esserci 2 conti correnti");
    }

   @Test
    @DisplayName("Test save Contocorrente")
    void testSave() {
       Anagrafica anagrafica = new Anagrafica("CCZ", "Daniele", "Cocuzza", new Date());
       Contocorrente contocorrente = new Contocorrente("IT002354", "IT", "002", "354", new Date(), null, 500, anagrafica, null);

       //doReturn(contocorrente).when(contocorrenteRepository).save(contocorrente);

       contocorrenteService.addContocorrente(contocorrente);

       verify(contocorrenteRepository, times(1)).save(contocorrente);


    }

    @Test
    @DisplayName("Test getSaldo Contocorrente")
    void testGetSaldo(){
        Anagrafica anagrafica = new Anagrafica("CCZ", "Daniele", "Cocuzza", new Date());
        Contocorrente contocorrente1 = new Contocorrente("IT002354", "IT", "002", "354", new Date(), null, 500, anagrafica, null);
        Contocorrente contocorrente2 = new Contocorrente("FR003355", "FR", "003", "355", new Date(), null, 500, anagrafica, null);


        doReturn(Optional.of(contocorrente1.getSaldo())).when(contocorrenteRepository).getSaldoById(contocorrente1.getId());

        Optional<Double> saldo = contocorrenteService.getSaldoById(contocorrente1.getId());


        Assertions.assertTrue(saldo.isPresent(), "Saldo non trovato");
        Assertions.assertEquals(contocorrente1.getSaldo(), saldo.get(), "Saldo diverso");

    }


}
