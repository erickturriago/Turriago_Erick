package com.backend.parcial.dao.impl;

import com.backend.parcial.dao.iDao;
import com.backend.parcial.model.Odontologo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class OdontologoDaoMemoria implements iDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologoRepository = new ArrayList<>();

    public OdontologoDaoMemoria() {

    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        int id = odontologoRepository.size() + 1;
        odontologoRepository.add(odontologo);
        Odontologo odontologoGuardado = new Odontologo(id,  odontologo.getNumMatricula(), odontologo.getNombre(),odontologo.getApellido());
        return odontologoGuardado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository;
    }

}
