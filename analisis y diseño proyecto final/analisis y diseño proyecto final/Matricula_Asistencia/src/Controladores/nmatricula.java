
package Controladores;

import java.sql.PreparedStatement;
import Modelo.dmatricula;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class nmatricula {
  
    private conexion mysql=new conexion();
    
    private  Connection  cn= mysql.Connectar(); 
    
    private  String  sql;
    
     public DefaultTableModel mostrar(String buscar) {
    DefaultTableModel modelo;
    String[] titulos = {"Matricula", "Codigo", "Dni", "Nombres", "Apellidos", "Grado", "Nivel", "Periodo", "Estado", "N"};
    String[] registros = new String[10]; 
    modelo = new DefaultTableModel(null, titulos);

    sql = "SELECT matricula.idmatricula, estudiante.idEstudiante, estudiante.identificacion, estudiante.Nombres, estudiante.Apellidos, grado.nombre AS nombre, grado.nivel, matricula.periodo, matricula.estado, grado.idgrado FROM estudiante INNER JOIN matricula ON estudiante.idEstudiante = matricula.idestudiante INNER JOIN grado ON matricula.idgrado = grado.idgrado WHERE estudiante.apellidos LIKE ?";

    try {
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, "%" + buscar + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            registros[0] = rs.getString("idmatricula");
            registros[1] = rs.getString("idEstudiante");
            registros[2] = rs.getString("identificacion");
            registros[3] = rs.getString("Nombres");
            registros[4] = rs.getString("Apellidos");
            registros[5] = rs.getString("nombre");
            registros[6] = rs.getString("nivel");
            registros[7] = rs.getString("periodo");
            registros[8] = rs.getString("estado");
            registros[9] = rs.getString("idgrado");

            modelo.addRow(registros);
        }

        return modelo;

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        return null;
    }
}
    
    
    public boolean insertar (dmatricula v) {
    
    sql= " insert into matricula (idestudiante,idgrado,periodo,estado) values (?,?,?,?)";
    
    try {
        
        PreparedStatement pst = cn.prepareStatement(sql);
         
        pst.setString (1, v.getEstudiante());
          
          pst.setString (2, v.getGrado());
              pst.setString (3, v.getPeriodo());
              
                  pst.setString (4, v.getEstado());
                  
                  
  
          
        
        int n = pst.executeUpdate ();        
        if (n != 0) {       
        return  true ;        
         } else {   
        return  false ;      
      }
  } catch (Exception e)  {

JOptionPane.showMessageDialog(null,e);

      return false;

          } 
       
        
      }   
         

    

    public boolean editar (dmatricula  v) {
    
    sql="update matricula set idestudiante=?,idgrado=?,periodo=?,estado=?  where idmatricula=?";
    
    try {

        
        PreparedStatement pst= cn.prepareStatement(sql);
        
        
        //------ empieza  0  es codigo    1 el siguiente campo   sucesidamente
        
           pst.setString (1, v.getEstudiante());
          
          pst.setString (2, v.getGrado());
              pst.setString (3, v.getPeriodo());
              
                  pst.setString (4, v.getEstado());
          
           
        pst.setInt (5, v.getId());
        
         int n= pst.executeUpdate ();
        
        if (n != 0) {
        
        return  true ;
        
         } else {
    
        return  false ;
    
    
}
         
         } catch (Exception e)  {

JOptionPane.showMessageDialog(null,e);

return false;



 
    } 
       
        
      }     
    
    
    
    
    
    public boolean eliminar (dmatricula v) {
    
    sql="delete from  matricula where idmatricula=?";
    
    try {

        
        PreparedStatement pst= cn.prepareStatement(sql);
        
          pst.setInt (1, v.getId());
        
   
        
         int n= pst.executeUpdate ();
        
        if (n != 0) {
        
        return  true ;
        
         } else {
    
        return  false ;
    
    
}
         
         } catch (Exception e)  {

JOptionPane.showMessageDialog(null,e);

return false;



 
    } 
       
        
      }     
    
    
    
    
    
    

}

