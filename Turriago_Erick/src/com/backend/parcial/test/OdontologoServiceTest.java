package com.backend.parcial.test;

import com.backend.parcial.dao.impl.OdontologoDaoH2;
import com.backend.parcial.dao.impl.OdontologoDaoMemoria;
import com.backend.parcial.model.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.backend.parcial.service.OdontologoService;
import com.backend.parcial.dao.H2Connection;
import com.backend.parcial.dao.iDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoServiceTest {

    private OdontologoService odontologoServiceDaoH2 = new OdontologoService(new OdontologoDaoH2());
    private OdontologoService odontologoServiceDaoMemoria = new OdontologoService(new OdontologoDaoMemoria());


    @BeforeAll
    static void doBefore() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/c1odontologo;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
            //System.out.println("Conexion establecida correctameten");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    void deberiaAgregarUnOdontologoPorDaoH2(){
        Odontologo odontologo = new Odontologo(20, "Juan", "Doc");
        Odontologo odontologoRegistrado = odontologoServiceDaoH2.guardarOdontologo(odontologo);
        Assertions.assertTrue(odontologoRegistrado.getId() != 0);
    }

    @Test
    void deberiaAgregarUnOdontologoPorDaoMemoria(){
        Odontologo odontologo = new Odontologo(20, "Juan", "Doc");
        Odontologo odontologoRegistrado = odontologoServiceDaoMemoria.guardarOdontologo(odontologo);
        Assertions.assertTrue(odontologoRegistrado.getId() != 0);

    }

    @Test
    void deberiaRetornarUnaListaNoVaciaEnDaoH2(){

        assertFalse(odontologoServiceDaoH2.listarTodos().isEmpty());
    }

    void deberiaRetornarUnaListaNoVaciaEnDaoMemoria(){
        assertFalse(odontologoServiceDaoMemoria.listarTodos().isEmpty());
    }

}
