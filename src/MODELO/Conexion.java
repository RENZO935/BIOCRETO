/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;


public class Conexion {
   Connection con;
    public Connection getConnection(){
        try {
           //  String access="jdbc:ucanaccess://D:/BiocretoDataBase.accdb";
           String myBD = "jdbc:mysql://localhost:3306/sistemabiocreto?serverTimezone=UTC";
            con = DriverManager.getConnection(myBD,"root","");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return con;
    } 
    
   
}
