package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Taxi 
{
    private String coordenada;
    
    private Socket socket;
    
    private static int id_taxi_prox;
    private final int id_taxi;

    private  int id_grupo;
    
    private DataInputStream lee;
    
    private DataOutputStream esc;
   
    public Taxi(Socket soc, int MAX_TAXI_GRUPO) throws IOException
    {
        
        socket = soc;
        id_taxi = id_taxi_prox;
        id_grupo = id_taxi / MAX_TAXI_GRUPO;
        id_taxi_prox += 1;
        
        lee = new DataInputStream(socket.getInputStream());  
	esc = new DataOutputStream(socket.getOutputStream());
        
    }

    public void setLiberarRecurso() throws IOException{
        socket.close();
        lee.close();
        esc.close();
    }

    public int getIdGrupo()
    {
        return id_grupo;
    }
    
    public DataInputStream getLectura()
    {
        return lee;
    }

    public void setLectura(DataInputStream br)
    {
        lee = br;
    }

    public int getIdTaxi()
    {
        return id_taxi;
    }
    public Socket getSocket()
    {
        return socket;
    }

    public void setSocket(Socket so)
    {
        socket = so;
    }

    public void setEscritura(DataOutputStream pw)
    {
        esc = pw;
    }

    public DataOutputStream getEscritura()
    {
        return esc;
    }
    public void setCoordenada(String c)
    {
        coordenada = c;
    }

    public String getCoordenada()
    {
        return coordenada;
    }

    @Override
    public String toString()
    {
        String s = "Taxib [id " + id_taxi + ", coordenada "
                + coordenada + ", socket: " + socket + "]";
        try {
            socket.setSoTimeout(10000);
        } catch (SocketException ex) {
            Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }

        return s;
        
    }
}
