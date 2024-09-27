package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.mdlEstudiantes;
import vistas.jfrEstudiantes; 

//////////////////////////1- Implementar herencia: ActionListener
public class ctrlEstudiantes implements MouseListener, KeyListener {

    //////////////////////////2- Parametros
    private mdlEstudiantes modelo;
    private jfrEstudiantes vista;

    //////////////////////////3- Constructor de la clase
    public ctrlEstudiantes(mdlEstudiantes modelo, jfrEstudiantes vista) {
        this.modelo = modelo;
        this.vista = vista;

        //Siempre poner todos los botones que vamos a detectar
        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.txtBuscar.addKeyListener(this);
        vista.tbEstudiantes.addMouseListener(this);

        modelo.Mostrar(vista.tbEstudiantes);
    }

    /////////////////////////////////////////Eventos
    @Override
    public void mouseClicked(MouseEvent e) {
        //////////////////////////4- Detección de clicks en la vista
        if (e.getSource() == vista.btnGuardar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtEspecialidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setNombre(vista.txtNombre.getText());
                    modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setEspecialidad(vista.txtEspecialidad.getText());
                    //Ejecutar el metodo 
                    modelo.Guardar();
                    modelo.Mostrar(vista.tbEstudiantes);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtEspecialidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.tbEstudiantes);
                modelo.Mostrar(vista.tbEstudiantes);
                modelo.limpiar(vista);
            }
        }

        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtEspecialidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setNombre(vista.txtNombre.getText());
                    modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setEspecialidad(vista.txtEspecialidad.getText());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.tbEstudiantes);
                    modelo.Mostrar(vista.tbEstudiantes);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }

        if (e.getSource() == vista.tbEstudiantes) {
            modelo.cargarDatosTabla(vista);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscar) {
            modelo.Buscar(vista.tbEstudiantes, vista.txtBuscar);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

}
