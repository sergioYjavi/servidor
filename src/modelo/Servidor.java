package modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newCachedThreadPool;


public class Servidor 
{
    //grupos acabados e iteraciones
    private static int cont, contIteracion;
    //tiempo total de cada grupo de taxi en todas las iteraciones. 
     private long [] tiempoEnIteracion;
    //Taxis conectados
    private static ArrayList<Taxi> list;
    //tiempos de cada taxi
    private static long[] tiempos;
    //taxis que han acabado
    private static int[] contador;
    //tiempo simulacion
    private long tiempoTotal;
    private static int PORT;
   //Nº taxis conectados
    private static int MAX_TAXI;
    //Nº taxis grupo
    private static int MAX_TAXI_GRUPO;
    private static int ITERACIONES;
    
    //Constructor
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
    
    //Inicia hilos
    public void iniciar(ArrayList<Taxi> list, ExecutorService pool)
    {
        for (int i = 0; i < list.size(); ++i) 
        {
            pool.execute(new ServidorThread(list.get(i), this, i));
        }
    }

    
    //Peticiones servidor
    private static ArrayList<Taxi> aceptacionConexion(ServerSocket 
            servidor)
    {
        System.out.println("Conectando");

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
            System.out.println("Excepcion al crear taxi: " + e);
        }
            
        return list;
    }

    //Coordenadas de taxis vecinos
 
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

    //Envía al taxi ubicado en 'indiceString' el mensaje msg.
    protected synchronized void enviar(String msg, String indiceString) throws IOException 
    {
        int indice = Integer.parseInt(indiceString);
        list.get(indice).getEscritura().writeUTF(msg);
        list.get(indice).getEscritura().flush();
    }
    
    //Guarda tiempo y posicIón del taxi
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
    
    /**
     * Devuelve el tiempo total medio de la simulación.
     * Tiempo total medio = Tiempo Total / ITERACIONES.
     * 
     * @return Tiempo total medio.
     */
    public String getTiempoMedioTotal()
    {
        return "" + (tiempoTotal/ITERACIONES);
    }
    
    /**
     * Devuelve los tiempos medios de cada grupo de trabajadores. 
     * Tiempo medio = Tiempo total / ITERACIONES
     * 
     * @return String con el texto relleno con los tiempos de cada grupo de 
     * trabajadores.
     */
    public String getTiemposMediosGrupos()
    {
        String ret = "";
        
        for(int i = 0; i < tiempoEnIteracion.length; i++)
        {
            ret += "Grupo " + i + " : " + (tiempoEnIteracion[i]/ITERACIONES) + " milisegundos.\n";
        }

        return ret;
    }

    /**
     * Devuelve el tiempo total medio de todos los grupos.
     * Tiempo total medio = (suma de todos los (Tiempo Total / ITERACIONES)) 
     * / Número de grupos.
     * 
     * @return Tiempo total medio de todos los grupos.
     */
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
