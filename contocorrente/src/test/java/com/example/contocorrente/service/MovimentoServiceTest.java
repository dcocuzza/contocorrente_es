package com.example.contocorrente.service;

import com.example.contocorrente.model.Movimento;
import com.example.contocorrente.repository.ContocorrenteRepository;
import com.example.contocorrente.repository.MovimentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class MovimentoServiceTest {

    @Autowired
    private MovimentoService movimentoService;

    @MockBean
    private MovimentoRepository movimentoRepository;

    @Test
    @DisplayName("Test findById success")
    void testFindById(){
        Movimento movimento = new Movimento(1l);

        doReturn(Optional.of(movimento)).when(movimentoRepository).findById(1l);

        // Execute the service call
        Optional<Movimento> returnedMovimento = movimentoService.getMovimentoById(1l);

        // Assert the response
        Assertions.assertTrue(returnedMovimento.isPresent(), "Movimento was not found");
        Assertions.assertSame(returnedMovimento.get(), movimento, "The movimento returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not FOund")
    void testFindByIdNotFound() {
        // Setup our mock repository
        doReturn(Optional.empty()).when(movimentoRepository).findById(1l);

        // Execute the service call
        Optional<Movimento> returnedMovimento = movimentoService.getMovimentoById(1l);

        // Assert the response
        Assertions.assertFalse(returnedMovimento.isPresent(), "Widget should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        // Setup our mock repository
        Movimento movimento = new Movimento(1l);
        Movimento movimento1 = new Movimento(2l);
        doReturn(Arrays.asList(movimento, movimento1)).when(movimentoRepository).findAll();

        // Execute the service call
        List<Movimento> movimenti = movimentoService.getAllMovimenti();

        // Assert the response
        Assertions.assertEquals(2, movimenti.size(), "findAll should return 2 widgets");
    }

    @Test
    @DisplayName("Test save widget")
    void testSave() {
        // Setup our mock repository
        Movimento movimento = new Movimento(1l);
        doReturn(movimento).when(movimentoRepository).save(any());

        // Execute the service call
        movimentoService.addMovimento(movimento);

        Movimento returnedMovimento = movimentoService.getMovimentoById(1L).get();


        // Assert the response
        Assertions.assertNotNull(returnedMovimento, "The saved widget should not be null");
    }


}
