package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class vista extends JFrame 
{
    private norte np;
    private sur sp;
    private centro cp;
    public vista()
    {
        this.setLayout(new BorderLayout());
        np = new norte();
        sp = new sur();
        cp = new centro();
        
        this.add(np,BorderLayout.NORTH);
        this.add(sp,BorderLayout.SOUTH);
        this.add(cp,BorderLayout.CENTER);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

        this.setVisible(true);
    }
    
    public int getPuerto()
    {
        return np.getPuerto();
    }
    
    //Tiempo medio total
    public void setTiempoMedio(String tiempoMedioTotal)
    {
       cp.setTiempoMedio(tiempoMedioTotal);
    }
    //Tiempo grupos
    public void setTiemposMediosGrupos(String tiemposMediosGrupos)
    {
       cp.setTiemposMediosGrupos(tiemposMediosGrupos);
    }
   //Tiempo medio grupos
    public void setTiempoGruposMedio(String tiempoMedioGrupos)
    {
        cp.setTiempoGruposMedio(tiempoMedioGrupos);
    }
    
    
    public int getMax_Taxi()
    {
        return np.getMax_Taxi();
    }
    
    public int getMax_Taxi_Grupo()
    {
        return np.getMax_Taxi_Grupo();
    }
    
    public int getIteraciones()
    {
        return np.getIteraciones();
    }
    
    public void setActionListener(ActionListener actionListener)
    {
        sp.setActionListener(actionListener);
    }
    
}
