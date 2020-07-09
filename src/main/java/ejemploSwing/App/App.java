package ejemploSwing.App;

import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import ejemploSwing.JPanel.PanelAudios;


public class App extends JFrame implements ActionListener{
	
	FileInputStream fileInputStream;
	BufferedInputStream bufferedInputStream;
	Player player;
	
	String fis;
	
	/*  Hilos  */
	Thread reproducirThread;
	Thread playThread;
	
	
	JButton audio1 = new JButton("Clau");
	JButton audio2 = new JButton("Me sale caca");
	JButton audio3 = new JButton("Pedito");
	JButton audio4 = new JButton("ISABELAA");
	JButton play = new JButton("Play");
	JButton pause = new JButton("Pause");
	

	
	/*  Punto en el centro de la pantalla  */
	Point centro  = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	
	/*  Pause = valor que contiene donde se pauso el audio  */
	long pausa;
	long totalLength;
	
	/*  Bandera que indica si debemos pausar o continuar el audio donde fue pausado  */
	int pausa0play1=0;
	
	private void App() {
	}
	
	private void crearVentana(){
		audio1.setBounds(35,50,150,55);
		this.add(audio1);
		audio2.setBounds(35, 150,150,55);
		audio3.setBounds(35,250,150,55);
		audio4.setBounds(35,350,150,55);
		play.setBounds(400,100,150,55);
		pause.setBounds(400, 300, 150, 55);
		this.add(audio1);
		this.add(audio2);
		this.add(audio3);
		this.add(audio4);
		this.add(play);
		this.add(pause);
		audio1.addActionListener(this);
		audio2.addActionListener(this);
		audio3.addActionListener(this);
		audio4.addActionListener(this);
		play.addActionListener(this);
		pause.addActionListener(this);
		
		playThread = new Thread(runnableResume);
		reproducirThread = new Thread(runnablePlay);
	}
	

	public static void main(String[] args) {
		App app = new App();
		app.setTitle("Audios");
		app.getContentPane().setLayout(null);
		app.getContentPane().setBackground(Color.pink);
		app.setSize(720, 500);
		/*  Pongo la app en el medio de la pantalla  */
		app.setLocation(app.centro.x - (int)app.getSize().getWidth()/2, app.centro.y - (int)app.getSize().getHeight()/2);
		app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	
		app.setVisible(true);
		
		
		app.crearVentana();

		
		System.out.println("app creada");
}
	public void reproducirAudio() {
			try {
				fileInputStream = new FileInputStream(fis);
				bufferedInputStream = new BufferedInputStream(fileInputStream);
				player=new Player(bufferedInputStream);
				totalLength=fileInputStream.available();
				player.play();
				
			 } catch (FileNotFoundException e1) {
	              e1.printStackTrace();
	          } catch (JavaLayerException e1) {
	              e1.printStackTrace();
	          } catch (IOException e1) {
	              e1.printStackTrace();
	          }
	}
	public void pausaAudio() {
		/*debemos pausar el audio*/
		 try {
			pausa=fileInputStream.available();
			player.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void playAudio() {
		/*debemos continuar el audios*/
		  try {
            //code for resume button
			fileInputStream=new FileInputStream(fis);
            bufferedInputStream=new BufferedInputStream(fileInputStream);
            player=new Player(bufferedInputStream);
            fileInputStream.skip(totalLength-pausa);
            player.play();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (JavaLayerException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == audio1) {
			System.out.println("Soy clau");
			fis = "D:\\ArchivosDePrograma\\eclipse\\eclips-workspace\\CTM\\src\\main\\java\\res\\clau.mp3";
			reproducirThread.start();
			
		}
		if(e.getSource() == audio2) {
			System.out.println("Me sale caca");
			fis = "D:\\ArchivosDePrograma\\eclipse\\eclips-workspace\\CTM\\src\\main\\java\\res\\Me sale caca.mp3";
			reproducirThread.start();
		}
		if(e.getSource() == audio3) {
			System.out.println("Pedito");
			fis = "D:\\ArchivosDePrograma\\eclipse\\eclips-workspace\\CTM\\src\\main\\java\\res\\Pedito.mp3";
			reproducirThread.start();;
		}
		if(e.getSource() == audio4) {
			System.out.println("Soy ISABELAA");
			fis = "D:\\ArchivosDePrograma\\eclipse\\eclips-workspace\\CTM\\src\\main\\java\\res\\ISABELAA.mp3";
			reproducirThread.start();;
		}
		if(e.getSource() == play) {
			System.out.println("Play");
			playThread.start();
		}
		if(e.getSource() == pause) {
			System.out.println("Pause");
			pausaAudio();
		}
	
	

	
	}
	 Runnable runnablePlay=new Runnable() {
		 public void run() {
	    	  reproducirAudio();	          
	      }
	  };
	 Runnable runnableResume=new Runnable() {
		 public void run() {
	    	  playAudio();	          
		  }
	  };
}
