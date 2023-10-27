package com.backend.parcial.service;

import com.backend.parcial.dao.impl.OdontologoDaoH2;
import com.backend.parcial.dao.iDao;
import com.backend.parcial.model.Odontologo;
import java.util.List;

public class OdontologoService {

    private iDao<Odontologo> odontologoiDao;

    public OdontologoService(iDao<Odontologo> odontologoiDao){
        this.odontologoiDao=odontologoiDao;

    }
    public Odontologo guardarOdontologo (Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }
    public List<Odontologo> listarTodos(){
        return odontologoiDao.listarTodos();
    }


}
