package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;


class centro extends JPanel 
{
    private JLabel jltiempomedio, jlmilisegundos, jltiempogrupo, jltotalmediagrupos,jltitulografica,jltitulo;
    private JTextField jtftiempomedio, jtfmediagrupos;
    private JTextArea textar;
    private JScrollPane jsp;

    public centro()
    {
        jltiempomedio = new JLabel("Tiempo total medio: ");
        jlmilisegundos = new JLabel("milisegundos");
        jltiempogrupo = new JLabel("Tiempo por grupo: ");
        jltotalmediagrupos = new JLabel("Tiempo medio de los grupos: ");
        
        jtftiempomedio = new JTextField(10);
        jtftiempomedio.setEnabled(false);
        
        jtfmediagrupos = new JTextField(10);
        jtfmediagrupos.setEnabled(false);
        
        textar = new JTextArea(5, 50);
        textar.setEnabled(false);
        jsp = new JScrollPane(textar);
        
        
        jltitulo = new JLabel("SERVIDOR");
        jltitulo.setFont(new Font("Serif", Font.BOLD, 30)); jltitulo.setForeground(Color.RED); 

        jltitulografica = new JLabel("GRÁFICA");
        jltitulo.setFont(new Font("Serif", Font.BOLD, 30)); jltitulo.setForeground(Color.BLUE); 

        
        add(jltiempomedio);
        add(jtftiempomedio);
        add(jlmilisegundos);
        add(jltiempogrupo);
        add(jsp);
        add(jltotalmediagrupos);
        add(jtfmediagrupos);
        add(jlmilisegundos);
        add(jltitulo);
        add(jltitulografica);
        
                
        this.setVisible(true);
    }
    
    /**
     * Limpia todos los campos antes de efectuar una nueva prueba
     * 
     */
    public void limpiar()
    {
        textar.setText("");
        jtftiempomedio.setText("");
        jtfmediagrupos.setText("");
    }
    
    /**
     * Asignar el tiempo medio del total 
     * @param tiempoMedioTotal tiempo 
     */
    public void setTiempoMedio(String tiempoMedioTotal) 
    {
        jtftiempomedio.setText(tiempoMedioTotal);
    }

    /**
     * Asignar tiempo medio del grupo 
     * @param tiemposMediosGrupos  tiempo 
     */
    public void setTiemposMediosGrupos(String tiemposMediosGrupos)
    {
        textar.setText(tiemposMediosGrupos);
    }

    /**
     * Asignar tiempo medio del grupo
     * @param tiemposMediosGrupos  tiempo
     */
    public void setTiempoGruposMedio(String tiemposMediosGrupos)
    {
        jtfmediagrupos.setText(tiemposMediosGrupos);
    }
}
