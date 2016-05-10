
import javax.swing.table.DefaultTableModel;

public class Proceso extends Thread
{
  BufferMemoriaReal memoriaReal;
  BufferMemoriaVirtual memoriaVirtual;
  
  String nombre;
  int tiempo=0;
  float progreso;
  
  int tamañoReal;
  int tamañoVirtual;
  
  int numero;
  int tiempoVida=50;
 
  
  public Proceso(BufferMemoriaReal memoriaReal, BufferMemoriaVirtual memoriaVirtual, String nom, int numero, int tamañoReal, int tamañoVirtual, int tiempoProceso)
  {
    this.memoriaReal=memoriaReal;
    this.memoriaVirtual=memoriaVirtual;
    
    this.nombre=nom;
    this.numero=numero;
    
    this.tamañoReal=tamañoReal;
     this.tamañoVirtual=tamañoVirtual;
    
   
    this.progreso=tiempoVida/tiempoProceso;
    
    Ventana.modelProcesos = (DefaultTableModel)Ventana.tablaProcesos.getModel();
    String[] nuevaFila = { "", "", "", ""};
    Ventana.modelProcesos.addRow(nuevaFila);
    
    Ventana.tablaProcesos.setValueAt(nombre,numero,0);
    Ventana.tablaProcesos.setValueAt(tamañoReal+tamañoVirtual,numero,1);
    Ventana.tablaProcesos.setValueAt("Esperando",numero,2);
    
  }
  
  public void ejecutar()
  {
   String status="";   
      
    while (tiempo < tiempoVida)
    {
        try
        {

          if(getState().toString().equals("RUNNABLE"))
              status="Ejecutando";

          Ventana.tablaProcesos.setValueAt(status,numero,2);

          sleep(1000);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
        
        
        tiempo+=progreso;
        
        if(tiempo>=tiempoVida)
        {
            
          Ventana.tablaProcesos.setValueAt("Finalizado",numero, 2);
        }
    }
    
    memoriaReal.salir(numero,tamañoReal);
    memoriaVirtual.salir(numero,tamañoVirtual);
    
  }
  
  public void run()
  {
    while(true)
    {
      if ((tamañoReal<BufferMemoriaReal.espaciosDisponibles)&&(tamañoVirtual<BufferMemoriaVirtual.espaciosDisponibles))
      {
        memoriaReal.entrar(numero,tamañoReal);
        memoriaVirtual.entrar(numero,tamañoVirtual);
        
        
        ejecutar();
        break;
      }
      
      if ((tamañoReal + tamañoVirtual) <= (BufferMemoriaReal.espaciosDisponibles + BufferMemoriaVirtual.espaciosDisponibles))
      {
        int aux = this.tamañoReal + this.tamañoVirtual;
        this.tamañoReal = memoriaReal.espaciosDisponibles;
        this.tamañoVirtual = (aux - this.tamañoReal);
        
        this.memoriaReal.entrar(numero, this.tamañoReal);
        this.memoriaVirtual.entrar(numero, this.tamañoVirtual);
        
        ejecutar();
        break;
      }
   
      
      
      try
      {
        sleep(1000);
      }
      catch (InterruptedException e1)
      {
        e1.printStackTrace();
      }
    }
    
  }
  
  
}

