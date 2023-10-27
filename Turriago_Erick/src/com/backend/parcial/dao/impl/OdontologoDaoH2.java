package com.backend.parcial.dao.impl;
import org.apache.log4j.Logger;
import com.backend.parcial.model.Odontologo;
import com.backend.parcial.dao.H2Connection;
import com.backend.parcial.dao.iDao;
import org.apache.log4j.Logger;
import java.util.ArrayList;

import java.sql.*;
import java.util.List;


public class OdontologoDaoH2 implements iDao<Odontologo>{

    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);
    private static final String SQL_INSERT = "INSERT INTO ODONTOLOGO VALUES(?,?,?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGO";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Inicio de guardado odontologo");
        Connection connection = null;
        Odontologo odontologoPersistido = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            logger.info("DAo base");
            //PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psInsert = connection.prepareStatement("INSERT INTO ODONTOLOGO (NUM_MATRICULA,NOMBRE, APELLIDO) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            logger.info(odontologo);
            psInsert.setInt(1, odontologo.getNumMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute();

            connection.commit();
            ResultSet resultSet = psInsert.getGeneratedKeys();
            while (resultSet.next()) {
                odontologoPersistido = new Odontologo(resultSet.getInt(1), odontologo.getNumMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }

            logger.info("Odontologo guardado: " + odontologoPersistido);


        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    logger.info("Tuvimos un problema");
                    logger.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    logger.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                logger.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return odontologoPersistido;
    }

    @Override
    public List<Odontologo> listarTodos() {
        logger.info("Inicio de operación listar");
        Connection connection= null;
        List<Odontologo> odontologos = new ArrayList<Odontologo>();
        try{
            connection= H2Connection.getConnection();
            PreparedStatement psListar= connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = psListar.executeQuery();

            while (resultSet.next()){
                Odontologo odontologo = new Odontologo(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                odontologos.add(odontologo);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return odontologos;
    }
}
