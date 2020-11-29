package modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newCachedThreadPool;

public class Servidor 
{
    private static ArrayList<Taxi> list;
    
    private static long[] tiempos;
    
    private static int[] contador;
    
    private static int cont, contIteracion;
    private long [] tiempoEnIteracion;
    
    private long tiempoTotal;
   
    private static int PORT;
    
    private static int MAX_TAXI;
    
    private static int MAX_TAXI_GRUPO;
    private static int ITERACIONES;
        public Servidor(int PORT, int MAX_TAXI, int MAX_TAXI_GRUPO
            , int ITERACIONES)
    {
        this.PORT = PORT;
        this.MAX_TAXI = MAX_TAXI;
        this.MAX_TAXI_GRUPO = MAX_TAXI_GRUPO;
        this.ITERACIONES = ITERACIONES;
        
        tiempoTotal = 0;
        tiempoEnIteracion = new long[MAX_TAXI/MAX_TAXI_GRUPO];
        contIteracion = 0;
        cont = 0;
        tiempos = new long[MAX_TAXI/MAX_TAXI_GRUPO];
        contador = new int[MAX_TAXI/MAX_TAXI_GRUPO];
        
        for(int i = 0; i < MAX_TAXI/MAX_TAXI_GRUPO; i++)
        {
            tiempos[i] = 0;
            contador[i] = 0;
            tiempoEnIteracion[i] = 0;
        }
        try
        {
            list = new ArrayList<Taxi>();
            list.clear();
            ExecutorService pool = newCachedThreadPool();
            ServerSocket servidor = new ServerSocket(PORT);
   
            list = aceptacionConexion(servidor); 
            iniciar(list, pool);
        }
        catch(IOException ie)
        {
            System.out.println("Error: " + ie.getMessage());
        }
    }
    
    public void iniciar(ArrayList<Taxi> list, ExecutorService pool)
    {
        for (int i = 0; i < list.size(); ++i) 
        {
            pool.execute(new ServidorThread(list.get(i), this, i));
        }
    }

    
    private static ArrayList<Taxi> aceptacionConexion(ServerSocket 
            servidor)
    {
        int i = 0;
        ArrayList<Taxi> list = new ArrayList<Taxi>();
        try
        {
            while (i < MAX_TAXI) 
            {
                Socket socket = servidor.accept();
                Taxi t = new Taxi(socket, MAX_TAXI_GRUPO);
                list.add(t);
                i++;
            }
        }
        catch(Exception e)
        {
            System.out.println("Excepcion al crear trabajadores: " + e);
        }
            
        return list;
    }

 
    protected synchronized void enviarATodos(int indice) throws IOException 
    {
        String msg = "";
        for (int i = 0; i < list.size(); ++i)
        {     
            if (list.get(i).getIdTaxi() != list.get(indice).getIdTaxi() && list.get(i).getIdGrupo() == list.get(indice).getIdGrupo()) 
            {
                msg = "ACK1;" + list.get(indice).getCoordenada() + ";" + list.get(indice).getIdTaxi() + ";" + list.get(i).getIdTaxi();
                list.get(i).getEscritura().writeUTF(msg);
                list.get(i).getEscritura().flush();
            }
        }
    }
    protected synchronized void enviar(String msg, String indiceString) throws IOException 
    {
        int indice = Integer.parseInt(indiceString);
        list.get(indice).getEscritura().writeUTF(msg);
        list.get(indice).getEscritura().flush();
    }
    protected synchronized void sumarTiempo(long num, int pos) throws Throwable
    {
        tiempos[pos] += num;
        tiempoTotal += num;
        contador[pos]++;
        if(contador[pos] == (MAX_TAXI/MAX_TAXI_GRUPO))
        {
            contador[pos] = 0;
            tiempoEnIteracion[pos] += tiempos[pos];
            tiempos[pos] = 0;
            cont++;
            if(cont == (MAX_TAXI/MAX_TAXI_GRUPO))
            {
                tiempoEnIteracion[pos] = 0; //Tiempo de cada grupo
                contIteracion ++;
                if(contIteracion == ITERACIONES)
                {
                    finalize();
                }
            }    
        }
    }
    
    public String getTiempoMedioTotal()
    {
        return "" + (tiempoTotal/ITERACIONES);
    }
    
    public String getTiemposMediosGrupos()
    {
        String ret = "";
        
        for(int i = 0; i < tiempoEnIteracion.length; i++)
        {
            ret += "Grupo " + i + " : " + (tiempoEnIteracion[i]/ITERACIONES) + " milisegundos.\n";
        }

        return ret;
    }

    public String getTiempoMedioGrupos()
    {
        int suma = 0;
        for(int i = 0; i < tiempoEnIteracion.length; i++)
        {
            suma += (tiempoEnIteracion[i]/ITERACIONES);
        }

        return "" + (suma/tiempoEnIteracion.length);
    }
}
