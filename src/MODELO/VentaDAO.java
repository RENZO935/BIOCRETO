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


/**
 *
 * @author USER
 */
public class VentaDAO {
     Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    public int RgistrarVenta(Venta v){
    String sql="INSERT INTO ventas (cliente,vendedor,total) VALUES (?,?,?)";
        try {
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        ps.setString(1, v.getCliente());
        ps.setString(2, v.getVendedor());
        ps.setDouble(3, v.getTotal());
        ps.execute();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
         try {
             con.close();
         } catch (SQLException e) {
             System.out.println(e.toString());
         }
     }
        return r;
            }
    
    public int RegistrarDetalle(Detalle dv){
    String sql="INSERT INTO detalles (cod_producto,cantidad,precio,id_venta) VALUES (?,?,?,?)";
     try {
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        ps.setString(1, dv.getCod_pro());
        ps.setInt(2, dv.getCantidad());
        ps.setDouble(3, dv.getPrecio());
        ps.setInt(4, dv.getId());
        ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
         try {
             con.close();
         } catch (SQLException e) {
             System.out.println(e.toString());
         }
     }
     return r;
    }
    
    public int IdVenta(){
    int id=0;
    String sql="SELECT MAX(id) FROM ventas";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
            id=rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }
    public boolean ActualizarStock(int cant,String cod){
    String sql="UPDATE productos SET stock = ? WHERE codigo = ?";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setString(2, cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    
    
    }
    
         public List ListarVentas() {
        List<Venta> listaVentas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta vent = new Venta();
                vent.setId(rs.getInt("id"));
                vent.setCliente(rs.getString("codigo"));
                vent.setVendedor(rs.getString("vendedor"));
                vent.setTotal(rs.getDouble("total"));
                listaVentas.add(vent);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaVentas;
    }
     
}
