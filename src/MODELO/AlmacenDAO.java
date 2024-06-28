/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import com.mysql.cj.protocol.Resultset;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class AlmacenDAO {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    
    public boolean RegistrarProductos(Almacen pro){
    String sql="INSERT INTO productos (codigo,nombre,stock,precio) VALUES(?,?,?,?)";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setInt(3, pro.getStock());
            ps.setDouble(4, pro.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
        return false;
        }
    
    }
    
    
    
     public List ListarProductos() {
        List<Almacen> listapro = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Almacen pro = new Almacen();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
                listapro.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listapro;
    }
     
     
     public boolean EliminarProducto(int id){
String sql="DELETE FROM productos WHERE id = ?";
    try {
        ps=con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
    }finally{
        try {
             con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
   
    }
}
     
     
     
     public boolean ModificarProductos(Almacen pro){
    String sql="UPDATE productos SET codigo=?, nombre=?,stock=?,precio=? WHERE id=?";
    try {
        ps=con.prepareStatement(sql);
        ps.setString(1, pro.getCodigo());
        ps.setString(2, pro.getNombre());
        ps.setInt(3, pro.getStock());
        ps.setDouble(4, pro.getPrecio());
        ps.setInt(5, pro.getId());
        ps.execute();   
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
    }finally{
    
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
     
     public Almacen BuscarPro(String cod){
     Almacen producto=new Almacen();
     String sql="SELECT * FROM productos Where codigo= ?";
         try {
           con=cn.getConnection();
           ps=con.prepareStatement(sql);
           ps.setString(1, cod);
           rs=ps.executeQuery();
           if(rs.next()){
         producto.setNombre(rs.getString("nombre"));
         producto.setPrecio(rs.getDouble("precio"));
         producto.setStock(rs.getInt("stock"));
         
         
         }
         } catch (SQLException e) {
             System.out.println(e.toString());
         }
     return producto;
     }
     

     
}
