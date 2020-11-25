package main;


import vista.vista;
import controlador.Controlador;

public class main 
{

    public static void main(String[] args) 
    {
        vista vista = new vista();
        Controlador controlador = new Controlador(vista);
        
        vista.setVisible(true);
    }
}
