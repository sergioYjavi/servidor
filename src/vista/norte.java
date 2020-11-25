package vista;

import modelo.Valores;
import javax.swing.*;

class norte extends JPanel 
{
    private JLabel jltaxis, jltaxisgrupo, jliteraciones, jlpuerto;
    private JTextField jtftaxi, jtftaxigrupo, jtfiteracions, jtfpuerto;
    
    public norte()
    {
        jltaxis = new JLabel("Nº de taxis: ");
        jltaxisgrupo = new JLabel("Nº de taxis por grupo: ");
        jliteraciones = new JLabel("Nº de iteraciones: ");
        jlpuerto = new JLabel("Puerto: ");
        
        jtftaxi = new JTextField(5);
        jtftaxi.setText("100");
        jtftaxigrupo = new JTextField(5);
        jtftaxigrupo.setText("10");
        jtfiteracions = new JTextField(5);
        jtfiteracions.setText("10");
        jtfpuerto = new JTextField(5);
        jtfpuerto.setText("12345");
        
        add(jltaxis);
        add(jtftaxi);
        add(jltaxisgrupo);
        add(jtftaxigrupo);
        add(jliteraciones);
        add(jtfiteracions);
        add(jlpuerto);
        add(jtfpuerto);
                
        this.setVisible(true);
    }

        public int getIteraciones()
    {
        try
        {
            return Integer.parseInt(jtfiteracions.getText());
        }
        catch(NumberFormatException e)
        {
            return Valores.ITERACIONES;
        }
    }
    public int getPuerto()
    {
        try
        {
            return Integer.parseInt(jtfpuerto.getText());
        }
        catch(NumberFormatException e)
        {
            return Valores.PORT;
        }
    }

    public int getMax_Taxi()
    {
        try
        {
            return Integer.parseInt(jtftaxi.getText());
        }
        catch(NumberFormatException e)
        {
            return Valores.MAX_TAXI;
        }
    }

    public int getMax_Taxi_Grupo()
    {
        try
        {
            return Integer.parseInt(jtftaxigrupo.getText());
        }
        catch(NumberFormatException e)
        {
            return Valores.MAX_TAXI_GRUPO;
        }
    }


}
