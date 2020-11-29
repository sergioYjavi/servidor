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
        this.setSize(800, 400);
        
        np = new norte();
        cp = new centro();
        sp = new sur();
        
        
        this.add(np,BorderLayout.NORTH);
        this.add(cp,BorderLayout.CENTER);
        this.add(sp,BorderLayout.SOUTH);
        
        
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
    
    /**
     * Limpia los campos de texto de la ventana
     */
    public void Limpiar()
    {
        cp.limpiar();
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
    
    
    public static void main(String[] args) {
        
        vista view = new vista();
        
        view.setVisible(true);
    }
    
    
}
