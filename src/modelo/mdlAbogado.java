package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import vistas.jfrAbogado;

/**
 *
 * @author exequ
 */
public class mdlAbogado {

    ////////////////////////1- Parametros
    private String Nombre_Abogado;
    private int Edad_Abogado;
    private Double Peso_Abogado;
    private String Correo_Abogado;

        ////////////////////////2- Metodos get y set
    public String getNombre_Abogado() {
        return Nombre_Abogado;
    }

    public void setNombre_Abogado(String Nombre_Abogado) {
        this.Nombre_Abogado = Nombre_Abogado;
    }

    public int getEdad_Abogado() {
        return Edad_Abogado;
    }

    public void setEdad_Abogado(int Edad_Abogado) {
        this.Edad_Abogado = Edad_Abogado;
    }

    public Double getPeso_Abogado() {
        return Peso_Abogado;
    }

    public void setPeso_Abogado(Double Peso_Abogado) {
        this.Peso_Abogado = Peso_Abogado;
    }

    public String getCorreo_Abogado() {
        return Correo_Abogado;
    }

    public void setCorreo_Abogado(String Correo_Abogado) {
        this.Correo_Abogado = Correo_Abogado;
    }
    

    ////////////////////////3- Métodos 
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO tbAbogado(UUID_Abogado, Nombre_Abogado, Edad_Abogado, Peso_Abogado, Correo_Abogado) VALUES (?, ?, ?, ?,?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getNombre_Abogado());
            pstmt.setInt(3, getEdad_Abogado());
            pstmt.setDouble(4, getPeso_Abogado());
            pstmt.setString(5, getCorreo_Abogado());


            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }

    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{ "Nombre", "Edad", "Peso" , "Correo"});
        try {
            //Consulta a ejecutar
            String query = "SELECT * FROM tbAbogado";
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(query);
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{
                    rs.getString("Nombre_Abogado"), 
                    rs.getInt("Edad_Abogado"), 
                    rs.getDouble("Peso_Abogado"),
                    rs.getString("Correo_Abogado")
                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }

    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from tbAbogado where UUID_Abogado = ?";
            PreparedStatement deleteAbogado = conexion.prepareStatement(sql);
            deleteAbogado.setString(1, miId);
            deleteAbogado.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }

    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update tbAbogado set Nombre_Abogado= ?, Edad_Abogado = ?, Peso_Abogado = ? , Correo_Abogado = ? where UUID_Abogado = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre_Abogado());
                updateUser.setInt(2, getEdad_Abogado());
                updateUser.setDouble(3, getPeso_Abogado());
                updateUser.setString(4, getCorreo_Abogado());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }

    public void Buscar(JTable tabla, JTextField miTextField) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Nombre", "Edad", "Peso", "Correo"});
        try {
            String sql = "SELECT * FROM tbAbogado WHERE nombre LIKE ? || '%'";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miTextField.getText());
            ResultSet rs = deleteEstudiante.executeQuery();

            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("Nombre"), rs.getInt("Edad"), rs.getDouble("Peso"), rs.getString("Correo")});
            }

            
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }

    public void limpiar(jfrAbogado vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }

    public void cargarDatosTabla(jfrAbogado vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.tbAbogado.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String NombreDeTB = vista.tbAbogado.getValueAt(filaSeleccionada, 0).toString();
            String EdadDeTb = vista.tbAbogado.getValueAt(filaSeleccionada, 1).toString();
            String PesoDeTb = vista.tbAbogado.getValueAt(filaSeleccionada, 2).toString();
            String CorreoDeTB = vista.tbAbogado.getValueAt(filaSeleccionada, 3).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTb);
            vista.txtCorreo.setText(CorreoDeTB);
        }
    }

}
