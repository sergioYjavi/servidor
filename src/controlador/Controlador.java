package controlador;


import modelo.Servidor;
import vista.vista;
import java.awt.event.*;

public class Controlador 
{
    private Servidor servidor;
    
    private vista vista;
    public Controlador(vista vista)
    {
        this.vista = vista;
        vista.addWindowListener(new ControladorWindowListener());
        vista.setActionListener(new ControladorActionListener());
    }
    
    class ControladorWindowListener extends WindowAdapter 
    {
        @Override
        public void windowClosing(WindowEvent e) 
        {
            System.exit(0);
        }
    }
    
    class ControladorActionListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            
            String command = ae.getActionCommand();
            switch (command) 
            {
                case "inicio":
                    vista.Limpiar();
                    servidor = new Servidor(
                            vista.getPuerto(),
                            vista.getMax_Taxi(), 
                            vista.getMax_Taxi_Grupo(),
                            vista.getIteraciones());
                    break;
                case "salir":
                    System.exit(0);
                    break;
                case "act":
                    vista.setTiempoMedio(servidor.getTiempoMedioTotal());
                    vista.setTiemposMediosGrupos(servidor.getTiemposMediosGrupos());
                    vista.setTiempoGruposMedio(servidor.getTiempoMedioGrupos());
                    break;
                default:
                    System.out.println("Comando ’" + 
                        command + "’ no reconocido.");
                    break;
            }
        }
    }
}