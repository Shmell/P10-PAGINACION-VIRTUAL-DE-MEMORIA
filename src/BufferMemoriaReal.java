

import java.awt.Color;
import java.awt.Font;





public class BufferMemoriaReal
{
  static int espaciosOcupados=0;
  static int espaciosDisponibles=20;
  boolean[]ocupado= new boolean[espaciosDisponibles];
  
  
  
  public void llenaEspacios(int numero, int tamaño)
  {
    int j=0;
    for (int i=0;i<20;i++)
    {
        if (this.ocupado[i]==false)
        {
           Ventana.botonesMemoriaReal[i].setBackground(Color.black);
           Ventana.botonesMemoriaReal[i].setFont(new Font("Times New Roman", 0, 10));
           Ventana.botonesMemoriaReal[i].setFont(new Font("Times New Roman", 0, 10));
           Ventana.botonesMemoriaReal[i].setText(Integer.toString(numero+1));

          this.ocupado[i]=true;
          j++;
        }

        if(j==tamaño) 
           break;
     
    }
  }
  
  public void limpiaEspacios(int numero, int tamaño)
  {
    int j=0;
    
    for (int i=0;i<20;i++)
    {
        if ((this.ocupado[i]!=false) && (Integer.parseInt(Ventana.botonesMemoriaReal[i].getText())==(numero+1)))
            {
              
              Ventana.botonesMemoriaReal[i].setBackground(Color.white);
              
              this.ocupado[i] = false;
              j++;
            }
        
        if(j==tamaño) 
            break;   
    }
  }
  
  public synchronized void entrar(int numero, int tamaño)
  {
    if (tamaño>espaciosDisponibles) 
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    llenaEspacios(numero,tamaño);
    
    espaciosOcupados+=tamaño;
    espaciosDisponibles-=tamaño;
    
    
     if(espaciosDisponibles <0)
        espaciosDisponibles=0;
    
    if(espaciosOcupados >20)
        espaciosOcupados=20;
    
    
    Ventana.NumDisponiblesReal.setText(Integer.toString(espaciosDisponibles));
    Ventana.numUtilizadosReal.setText(Integer.toString(espaciosOcupados));
  }
  
  public synchronized void salir(int numero, int tamaño)
  {
    limpiaEspacios(numero,tamaño);
    
    espaciosOcupados-=tamaño;
    espaciosDisponibles+=tamaño;
    
    
     if(espaciosDisponibles <0)
        espaciosDisponibles=0;
    
    if(espaciosOcupados >20)
        espaciosOcupados=20;

     Ventana.NumDisponiblesReal.setText(Integer.toString(espaciosDisponibles));
    Ventana.numUtilizadosReal.setText(Integer.toString(espaciosOcupados));
    
    notify();
  }
}
