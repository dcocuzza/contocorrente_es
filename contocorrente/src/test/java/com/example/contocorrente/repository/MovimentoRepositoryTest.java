package com.example.contocorrente.repository;


import com.example.contocorrente.model.Anagrafica;
import com.example.contocorrente.model.Contocorrente;
import com.example.contocorrente.model.Movimento;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@ActiveProfiles("test")
@Sql({"/tabelle.sql", "/dati.sql"})
public class MovimentoRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private AnagraficaRepository anagraficaRepository;
    @Autowired
    private ContocorrenteRepository contocorrenteRepository;

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
    @Transactional
    void testFindAll(){

        List<Movimento> movimenti = Lists.newArrayList(movimentoRepository.findAll());
        Assertions.assertEquals(1, movimenti.size(), "Expected 1 movimenti in the database");

    }


    @Test
    @Transactional
    void testFindByIdSuccess(){


        Optional<Movimento> mov = movimentoRepository.findById(802L);
        Assertions.assertTrue(mov.isPresent(), "We should find a widget with ID 1");

        Movimento m = mov.get();
        Assertions.assertEquals("Prelievo", m.getDescrizione(), "Incorrect descrizione");
        Assertions.assertEquals(20, m.getImporto(), "Incorrept importo");


    }

    @Test
    @Transactional
    void testFindByIdNotFound(){

        Optional<Movimento> mov = movimentoRepository.findById(300L);
        Assertions.assertFalse(mov.isPresent(), "A movimento with ID 3 should not be found");

    }

    @Test
    @Transactional
    void testGetMovimentiByIdConto(){

        List<Movimento> movimenti = movimentoRepository.getMovimentiByIdConto(105L);
        Assertions.assertFalse(movimenti.isEmpty(), "Non dovrebbe essere vuota");
        Assertions.assertEquals("Prelievo", movimenti.get(0).getDescrizione(), "Descrizione incorretta");
        Assertions.assertEquals(20, movimenti.get(0).getImporto(), "Importo incorretto");

    }



}













