package com.example.contocorrente.repository;


import com.example.contocorrente.model.Movimento;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class MovimentoRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MovimentoRepository movimentoRepository;

    public ConnectionHolder getConnectionHolder() {

        try {
            return (ConnectionHolder) dataSource.getConnection();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Test
    void testFindAll(){
        List<Movimento> movimenti = Lists.newArrayList(movimentoRepository.findAll());
        Assertions.assertEquals(2, movimenti.size(), "Expected 2 movimenti in the database");
    }


    @Test
    void testFindByIdSuccess(){
        Optional<Movimento> movimento = movimentoRepository.findById(1L);
        Assertions.assertTrue(movimento.isPresent(), "We should find a widget with ID 1");

        Movimento m = movimento.get();
        Assertions.assertEquals(1, m.getId(), "The movimento ID should be 1");
        Assertions.assertEquals("Prelievo", m.getDescrizione(), "Incorrect descrizione");
        Assertions.assertEquals(500.0, m.getImporto(), "Incorrept importo");

    }

    @Test
    void testFindByIdNotFound(){
        Optional<Movimento> widget = movimentoRepository.findById(3L);
        Assertions.assertFalse(widget.isPresent(), "A widget with ID 3 should not be found");
    }



}













