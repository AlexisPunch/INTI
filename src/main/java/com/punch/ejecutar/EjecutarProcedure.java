/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.punch.ejecutar;

import com.proyecto1.inti.SQLConexion;
import java.awt.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author metal
 *
 */
public class EjecutarProcedure {

    private CallableStatement CALL_PROCD;
    private Connection CONEXION;
    private String PROCEDURE;

    private ArrayList<String> LISTA_PARAMETROS;
    private ArrayList<Object> LISTA_VALORES = new ArrayList<Object>();
    private ArrayList<String> LISTA_OUTPUTS = new ArrayList<String>();

    public EjecutarProcedure(String procedure) {
        this.PROCEDURE = procedure;
        this.LISTA_PARAMETROS = new ArrayList<String>();
        this.LISTA_VALORES = new ArrayList<Object>();
    }

    public EjecutarProcedure() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addParametro(String nombre, Object valor) {
        //AQUI VERIFICAR QUE NO ESTÁ EN LA LISTA 
        //if (LISTA_PARAMETROS.getItems())
        if (!LISTA_PARAMETROS.contains(nombre)) {
            LISTA_PARAMETROS.add(nombre);
            LISTA_VALORES.add(valor);
        }
    }

    public void addParametroOutput(String nombre, Object valor) {
        //AQUI VERIFICAR QUE NO ESTÁ EN LA LISTA 
        //if (LISTA_PARAMETROS.getItems())
        if (!LISTA_PARAMETROS.contains(nombre)) {
            LISTA_PARAMETROS.add(nombre);
            LISTA_VALORES.add(valor);
            LISTA_OUTPUTS.add(nombre);
        }
    }

    public Object obtenerOutPut(String nombreParametro) throws SQLException {
        return this.CALL_PROCD.getObject(nombreParametro);
    }

    public void ejecutar() throws Exception {

        try {

            SQLConexion conector = new SQLConexion();
            CONEXION = conector.conectar();

            String ini = "{call ";
            String fin = ")};";
            String abre = "(";
            String coma = "";

//"{call pLogin (?,?,?)};";
            String sql = ini + this.PROCEDURE + " ";
            for (int i = 0; i <= LISTA_PARAMETROS.size() - 1; i++) {
                sql += abre + coma + "?";
                coma = ",";
                abre = "";
                if (i == LISTA_PARAMETROS.size() - 1) {
                    sql += fin;
                }
            }
            this.CALL_PROCD = this.CONEXION.prepareCall(sql); // en este caso el preparecall es para ejecutar procedures. el statement es para ejecutar solo texto

            for (int i = 0; i <= LISTA_PARAMETROS.size() - 1; i++) {
                CALL_PROCD.setObject(LISTA_PARAMETROS.get(i), LISTA_VALORES.get(i));//,java.sql.Types.VARCHAR); //checar los boleanos etc
            }

            for (int i = 0; i <= LISTA_OUTPUTS.size() - 1; i++) {
                CALL_PROCD.registerOutParameter(LISTA_OUTPUTS.get(i), java.sql.Types.VARCHAR);
            }
            //hay que dejar por default un varchar pero habría que agregar que le puedas mandar que tipo de dato es en los metodos de addParametro

            CALL_PROCD.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            if (!Objects.isNull(CONEXION)) {
                if (!CONEXION.isClosed()) {
                    //CONEXION.close();
                }
            }
        }

//ya quedo we
    }
}
