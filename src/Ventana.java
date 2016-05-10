
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;


import static java.lang.Thread.sleep;


public class Ventana extends JFrame
{
  int contadorProcesos=0;
  Random tamanoAleatorioReal=new Random();
  Random tamanoAleatorioVirtual=new Random();
  
  
  Random tiempoAleatorio=new Random();
  
  DefaultTableCellRenderer centrado=new DefaultTableCellRenderer();
  static DefaultTableModel modelProcesos=new DefaultTableModel();
  static JTable tablaProcesos=new JTable(modelProcesos);
  
  Thread auto;
  static JButton[]botonesMemoriaReal=new JButton[20];
  static JButton[]botonesMemoriaVirtual=new JButton[20];
  
  static JLabel NumDisponiblesReal;
  static JLabel numUtilizadosReal;
  
  static JLabel NumDisponiblesVirtual;
  static JLabel numUtilizadosVirtual;
  
  /////////////////////////////////////////////////////
  public Ventana()
  {
    setTitle("Paginacion Virtual de Memoria");
    setVisible(true);
    initialize();
    this.getContentPane().setBackground(Color.LIGHT_GRAY);
    
    auto.start();
    
  }
  
  private void initialize()
  {
    setBounds(10,10,540,620);
    setDefaultCloseOperation(3);
    getContentPane().setLayout(null);
   
    centrado = new DefaultTableCellRenderer();
    centrado.setHorizontalAlignment(0);
    
    JScrollPane laminaProcesos = new JScrollPane();
    
    laminaProcesos.setBounds(10, 20, 500, 316);
    this.getContentPane().add(laminaProcesos);
    
    tablaProcesos = new JTable();
    tablaProcesos.setModel(new DefaultTableModel(
      new Object[0][], 
      new String[] {
      "N Proceso", "Tamaño", "Status"}));
    
    laminaProcesos.setViewportView(tablaProcesos);
    
    tablaProcesos.getColumnModel().getColumn(0).setCellRenderer(this.centrado);
    tablaProcesos.getColumnModel().getColumn(1).setCellRenderer(this.centrado);
    tablaProcesos.getColumnModel().getColumn(2).setCellRenderer(this.centrado);
   
    JLabel lblMemoriaReal = new JLabel("Memoria Real");
    lblMemoriaReal.setFont(new Font("Calibri", 50, 18));
    lblMemoriaReal.setForeground(Color.red);
    lblMemoriaReal.setBounds(50, 381, 120, 14);
    this.add(lblMemoriaReal);
    
     JLabel lblMemoriaVirtual = new JLabel("Memoria Virtual");
    lblMemoriaVirtual.setFont(new Font("Calibri", 50, 18));
    lblMemoriaVirtual.setForeground(Color.red);
    lblMemoriaVirtual.setBounds(50, 480, 120, 14);
    this.add(lblMemoriaVirtual);
    
    JLabel lblDisponibleReal = new JLabel("Disponible");
    lblDisponibleReal.setBounds(188,381,73,14);
    this.add(lblDisponibleReal);
    
    JLabel lblDisponibleVirtual = new JLabel("Disponible");
    lblDisponibleVirtual.setBounds(188,480,73,14);
    this.add(lblDisponibleVirtual);
    
    JLabel lblOcupadoReal = new JLabel("Utilizada");
    lblOcupadoReal.setBounds(290,381,63,14);
    this.add(lblOcupadoReal);
    
    JLabel lblOcupadoVirtual = new JLabel("Utilizada");
    lblOcupadoVirtual.setBounds(290,480,63,14);
    this.add(lblOcupadoVirtual);
    
    NumDisponiblesReal = new JLabel("20");
    NumDisponiblesReal.setForeground(Color.blue);
    NumDisponiblesReal.setFont(new Font("Times New Roman",0,20));
    NumDisponiblesReal.setBounds(255, 379, 21, 14);
    this.add(NumDisponiblesReal);
    
    numUtilizadosReal = new JLabel("0");
    numUtilizadosReal.setFont(new Font("Times New Roman",0,20));
    numUtilizadosReal.setForeground(Color.blue);
    numUtilizadosReal.setBounds(350,379,21,14);
    this.add(numUtilizadosReal);
    
    NumDisponiblesVirtual = new JLabel("20");
    NumDisponiblesVirtual.setForeground(Color.blue);
    NumDisponiblesVirtual.setFont(new Font("Times New Roman",0,20));
    NumDisponiblesVirtual.setBounds(255, 480, 21, 14);
    this.add(NumDisponiblesVirtual);
    
    numUtilizadosVirtual = new JLabel("0");
    numUtilizadosVirtual.setFont(new Font("Times New Roman",0,20));
    numUtilizadosVirtual.setForeground(Color.blue);
    numUtilizadosVirtual.setBounds(350,480,21,14);
    this.add(numUtilizadosVirtual);
    
   
    
    JButton info = new JButton("INFO");
    info.setBackground(new java.awt.Color(102, 102, 102));
    info.setForeground(new java.awt.Color(255, 255, 255));
    info.setBounds(460,550,60,25);
    this.add(info);
    
    info.addActionListener(new ActionListener() {
       
        public void actionPerformed(ActionEvent e) 
        {
          JOptionPane.showMessageDialog(null, "Desarrollado por:\nSamuel Ramirez Torres\nCodigo:304454235\nBibliogafia:\nSistemas.Operativos\nWilliam Stallings\n2000 2da Edicion Prentice Hall ");
        }
    });
    
   
    
    int x=10,y=400;
    for(int i=0;i<botonesMemoriaReal.length;i++)
    {   
        if(i>=10)
            y=420;
        
        if(i==10)
            x=10;
        
        botonesMemoriaReal[i] = new JButton();
        botonesMemoriaReal[i].setBounds(x, y, 50, 20);
        botonesMemoriaReal[i].setBackground(Color.WHITE);
        botonesMemoriaReal[i].setForeground(Color.WHITE);
        botonesMemoriaReal[i].setFont(new Font("Times New Roman", 0, 14));

        this.getContentPane().add(botonesMemoriaReal[i]);

        x+=50;
    }

    x=10;
    y=500;
    for(int i=0;i<botonesMemoriaVirtual.length;i++)
    {   
        if(i>=10)
            y=520;
        
        if(i==10)
            x=10;
        
        botonesMemoriaVirtual[i] = new JButton();
        botonesMemoriaVirtual[i].setBounds(x, y, 50, 20);
        botonesMemoriaVirtual[i].setBackground(Color.WHITE);
        botonesMemoriaVirtual[i].setForeground(Color.WHITE);
        botonesMemoriaVirtual[i].setFont(new Font("Times New Roman", 0, 14));

        this.getContentPane().add(botonesMemoriaVirtual[i]);

        x+=50;
    }

    
     final BufferMemoriaReal memoriaReal = new BufferMemoriaReal();
     final BufferMemoriaVirtual memoriaVirtual = new BufferMemoriaVirtual();
    
    
    auto=new Thread(){
        public void run()
        {
            while(true)
            {

                new Proceso(memoriaReal,memoriaVirtual, "Proceso " + (contadorProcesos+1), contadorProcesos, tamanoAleatorioReal.nextInt(5) + 2,tamanoAleatorioVirtual.nextInt(5) + 2, tiempoAleatorio.nextInt(12) + 5).start();
                contadorProcesos += 1;

                try 
                {
                    sleep(4000);
                } 
                catch (InterruptedException ex) 
                {

                }           
            }
        }  
    };
    
    
    
 
  }
  
  
  
  
  //////////////////////////////////////////////////////////////////////////////////
  
  public static void main(String[] args)
  {
    Ventana ventana = new Ventana();       
  }
   
}
