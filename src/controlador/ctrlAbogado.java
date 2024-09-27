package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.mdlAbogado;
import vistas.jfrAbogado; 

//////////////////////////1- Implementar herencia: ActionListener
public class ctrlAbogado implements MouseListener, KeyListener {

    //////////////////////////2- Parametros
    private mdlAbogado modelo;
    private jfrAbogado vista;

    //////////////////////////3- Constructor de la clase
    public ctrlAbogado(mdlAbogado modelo, jfrAbogado vista) {
        this.modelo = modelo;
        this.vista = vista;

        //Siempre poner todos los botones que vamos a detectar
        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.txtBuscar.addKeyListener(this);
        vista.tbAbogado.addMouseListener(this);

        modelo.Mostrar(vista.tbAbogado);
    }

    /////////////////////////////////////////Eventos
    @Override
    public void mouseClicked(MouseEvent e) {
        //////////////////////////4- Detección de clicks en la vista
        if (e.getSource() == vista.btnGuardar) {
            if (vista.txtCorreo.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty()|| vista.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setNombre_Abogado(vista.txtNombre.getText());
                    modelo.setEdad_Abogado(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso_Abogado(Double.parseDouble(vista.txtPeso.getText()));
                    modelo.setCorreo_Abogado(vista.txtCorreo.getText());
                    //Ejecutar el metodo 
                    modelo.Guardar();
                    modelo.Mostrar(vista.tbAbogado);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtCorreo.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.tbAbogado);
                modelo.Mostrar(vista.tbAbogado);
                modelo.limpiar(vista);
            }
        }

        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtCorreo.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setNombre_Abogado(vista.txtNombre.getText());
                    modelo.setEdad_Abogado(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso_Abogado(Double.parseDouble(vista.txtPeso.getText()));
                    modelo.setCorreo_Abogado(vista.txtCorreo.getText());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.tbAbogado);
                    modelo.Mostrar(vista.tbAbogado);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }

        if (e.getSource() == vista.tbAbogado) {
            modelo.cargarDatosTabla(vista);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscar) {
            modelo.Buscar(vista.tbAbogado, vista.txtBuscar);
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
