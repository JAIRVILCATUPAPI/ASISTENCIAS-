package Controladores;
import java.sql.PreparedStatement;
import Modelo.dmatricula;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class nmatricula{
  private conexion mysql = new conexion();
  private Connection cn = mysql.Connectar();
  private String sql;

public DefaultTableModel mostrar (String buscar){
  DefaultTableModel modelo;
  String [] titulos = {"Matricula","Codigo","Dni","Nombres","Apellidos","Grado","Nivel","Periodo","Estado","N"};
  String [] registros = new String [10];
  modelo = new DefaultTableModel(null, titulos);

  sql = "SELECT matricula.idmatricula, estudiante.idEstudiante, estudiante.identificacion, estudiante.Nombres,
    estudainte.Apellidos, grado.nombre AS nombre, grado.nivel, matricula.periodo, matricula.estado, grado.idgrado FROM
    estudiante INNER JOIN matricula ON estudiante.idEstudiante = matricula.idestudiante INNER JOIN grado ON matricula.idgrado = 
    grado.idgrado WHERE estudiante.apellidos LIKE ?";

  try {
    PreparedStatement 
