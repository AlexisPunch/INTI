/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto1.inti;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author metal
 *///esta es la que necesitas we
public class SQLConexion {
     Connection conectar = null;
   public  Statement st = null;
    boolean entrar;
    String usuario = "sa";
    String contrasenia = "party509";
    String bd = "proyecto2";
    String ip = "localhost";
    String puerto = "1433";
  
    String cadena = "jdbc:sqlserver://" + ip +":"+puerto+"/"+ bd;
    //metodos con minuscula :()
    public Connection conectar ()
    {
        try {
            
            String cadena ="jdbc:sqlserver://localhost:"+puerto+";"+"databaseName="+bd;
            conectar=DriverManager.getConnection(cadena,usuario,contrasenia);
                 //st = conectar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            //que diantre haces aqui we?
            
            
          
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null,"No se pudo conectar a la base de datos" + e);
            
        }
        return  conectar;
    }          

   
   
}
