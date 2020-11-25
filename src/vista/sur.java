package vista;

import java.awt.event.ActionListener;
import javax.swing.*;

class sur extends JPanel 
{
    private JButton inicio, salir, act;
    
    public sur()
    {
        inicio = new JButton("Empezar");
        inicio.setActionCommand("inicio");
        salir = new JButton("Cerrar");
        salir.setActionCommand("salir");
        act = new JButton("Actualizar");
        act.setActionCommand("act");
        
        add(inicio);
        add(act);
        add(salir);
        this.setVisible(true);
    }

    public void setActionListener(ActionListener actionListener) 
    {
        inicio.addActionListener(actionListener);
        act.addActionListener(actionListener);
        salir.addActionListener(actionListener);
    }
}
