package Controladores;

import java.sql.PreparedStatement;
import Modelo.dusuario;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class nusuario {

    private conexion mysql = new conexion();

    private Connection cn = mysql.Connectar();

    private String sql;

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;

        String[] titulos = {"Codigo", "Usuario", "Contraseña", "Cargo", "Persona", "Direccion", "Telefono", "Estado"};

        String[] registros = new String[8];

        modelo = new DefaultTableModel(null, titulos);

        sql = "select  * from usuario  where usuario like '" + buscar + "%'";

        try {

            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                registros[0] = rs.getString("cod_usuario");

                registros[1] = rs.getString("usuario");

                registros[2] = rs.getString("contraseña");

                registros[3] = rs.getString("cargo");

                registros[4] = rs.getString("persona");

                registros[5] = rs.getString("direccion");

                registros[6] = rs.getString("telefono");

                registros[7] = rs.getString("estado");

                modelo.addRow(registros);

            }

            return modelo;

        } catch (Exception e) {

            JOptionPane.showConfirmDialog(null, e);

            return null;

        }

    }

    public boolean insertar(dusuario v) {

        sql = " insert into usuario (usuario,contraseña,cargo,persona,direccion,telefono,estado) values (?,?,?,?,?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, v.getUsuario());

            pst.setString(2, v.getContraseña());
            pst.setString(3, v.getCargos());

            pst.setString(4, v.getPersonal());

            pst.setString(5, v.getDireccion());

            pst.setString(6, v.getTelefono());

            pst.setString(7, v.getEstado());

            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

            return false;

        }

    }

    public boolean editar(dusuario v) {

        sql = "update usuario set usuario=? ,contraseña=? ,cargo=? ,persona=? ,direccion=? ,telefono=? ,estado=?   where cod_usuario=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sql);

            //------ empieza  0  es codigo    1 el siguiente campo   sucesidamente
            pst.setString(1, v.getUsuario());

            pst.setString(2, v.getContraseña());
            pst.setString(3, v.getCargos());

            pst.setString(4, v.getPersonal());

            pst.setString(5, v.getDireccion());

            pst.setString(6, v.getTelefono());

            pst.setString(7, v.getEstado());

            pst.setInt(8, v.getIdusuario());

            int n = pst.executeUpdate();

            if (n != 0) {

                return true;

            } else {

                return false;

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

            return false;

        }

    }

    public boolean login(dusuario user) {
        PreparedStatement ps = null;

        ResultSet rs = null;

        try {
            sql = "SELECT  usuario,contraseña,cargo  FROM usuario    where  usuario =?";

            ps = cn.prepareStatement(sql);

            ps.setString(1, user.getUsuario());

            rs = ps.executeQuery();

            if (rs.next()) {
                if (user.getContraseña().equals(rs.getString(2))) {

                    user.setCargos(rs.getString(3));

                    return true;

                } else {

                    return false;
                }

            }

            return false;

        } catch (SQLException ex) {

            System.err.println(ex.toString());

            return false;

        }

    }

    public boolean eliminar(dusuario v) {

        sql = "delete from  usuario where cod_usuario=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setInt(1, v.getIdusuario());

            int n = pst.executeUpdate();

            if (n != 0) {

                return true;

            } else {

                return false;

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

            return false;

        }

    }

}
