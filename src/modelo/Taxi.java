package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Taxi 
{
    private static int id_taxi_prox;
    private final int id_taxi;
    private  int id_grupo;
    //Coordenada taxi
    private String coordenada;
    //Socket taxi
    private Socket socket;
    //Calculo taxi cercano
    private DataInputStream lee;
    /**
     * Flujo datos taxi.
     */
    private DataOutputStream esc;
   
    //Constructor
    public Taxi(Socket soc, int MAX_TAXI_GRUPO) throws IOException
    {
        
        socket = soc;
        id_taxi = id_taxi_prox;
        id_grupo = id_taxi / MAX_TAXI_GRUPO;
        id_taxi_prox += 1;
        
        lee = new DataInputStream(socket.getInputStream());  
	esc = new DataOutputStream(socket.getOutputStream());
        
        
    }
    
    
    //Flujo datos taxi
    public DataInputStream getLectura()
    {
        return lee;
    }
    
    
    //Cierro socket
    public void setLiberarRecurso() throws IOException{
        socket.close();
        lee.close();
        esc.close();
    }
    //Grupo del taxi
    public int getIdGrupo()
    {
        return id_grupo;
    }
    
    
    //Socket taxi
    public Socket getSocket()
    {
        return socket;
    }

    //insertar datos taxi
    public void setLectura(DataInputStream milectura)
    {
        lee = milectura;
    }

    //Idtaxi
    public int getIdTaxi()
    {
        return id_taxi;
    }
    
    //Coordenada
    public void setCoordenada(String cor)
    {
        coordenada = cor;
    }
    
    //devuelve coordenada
    public String getCoordenada()
    {
        return coordenada;
    }

    
    
    // inserta socket taxi
    public void setSocket(Socket misocket)
    {
        socket = misocket;
    }

    //flujo de insercción del taxi
    public void setEscritura(DataOutputStream miescr)
    {
        esc = miescr;
    }

    //salida flujo de datos
    public DataOutputStream getEscritura()
    {
        return esc;
    }
    
    //devuelve string datos taxi
    @Override
    public String toString()
    {
        String s = "Taxi [id " + id_taxi + ", coordenada "
                + coordenada + ", socket: " + socket + "]";
        return s;
    }
}
