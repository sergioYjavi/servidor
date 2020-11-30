package modelo;

import java.io.DataInputStream;

public class ServidorThread extends Thread
{
    private Taxi taxi = null;
    private Servidor servidor = null;
    private int posicion = 0;
    //Constructor
    public ServidorThread(Taxi t, Servidor s, int p) 
    {
        super();
        taxi = t;
        servidor = s;
        posicion = p;
    }

    public void run() 
    {
        try 
        {
            boolean terminar = true;
            String msg = "";
            String[] s;
            
            DataInputStream in = taxi.getLectura();
            
            while (terminar) 
            {
                msg = in.readUTF();
                while(!msg.substring(0,3).equals("ACK"))
                {
                    msg = in.readUTF();
                }
                
                s = msg.split(";");
                switch(s[0])
                {
                    case "ACK1":
                        taxi.setCoordenada(s[1]);
                        
                        servidor.enviarATodos(posicion);
                    break;
                     case "ACK2":
                        if(!s[0].equals("ACK1"))
                        {
                            servidor.enviar(msg, s[3]);
                        }
                     break;
                     default:
                        long num = Long.parseLong(s[1]);
                         servidor.sumarTiempo(num, taxi.getIdGrupo());
                }
            }
            
            taxi.setLiberarRecurso();
        }
        catch (Exception e) 
        {
            System.out.println(" Excepcion: " + e);
        }
        catch (Throwable ex)
        {
            System.out.println(" Excepcion: " + ex);
        }
    }
}
