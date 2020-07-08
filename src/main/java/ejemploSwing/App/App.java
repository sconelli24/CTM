package ejemploSwing.App;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	
	JButton audio1 = new JButton("Clau");
	JButton audio2 = new JButton("Me hice caca");
	JButton audio3 = new JButton("Risa Braca");
	JButton audio4 = new JButton("ISABELAA");
	
	private void App() {
	}
	
	private void crearVentana(){
		audio1.setBounds(35,50,150,55);
		this.add(audio1);
		audio2.setBounds(35, 150,150,55);
		audio3.setBounds(35,250,150,55);
		audio4.setBounds(35,350,150,55);
		this.add(audio1);
		this.add(audio2);
		this.add(audio3);
		this.add(audio4);
		audio1.addActionListener(this);
		audio2.addActionListener(this);
		audio3.addActionListener(this);
		audio4.addActionListener(this);
	}
	

	public static void main(String[] args) {
		App app = new App();
		app.setTitle("Audios");
		app.getContentPane().setLayout(null);
		app.getContentPane().setBackground(Color.pink);
		
		app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		app.setSize(720, 500);
		app.setVisible(true);
		
		
		app.crearVentana();

		
		System.out.println("app creada");
}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == audio1) {
			System.out.println("Soy clau");
			try {
				fileInputStream = new FileInputStream("D:\\ArchivosDePrograma\\eclipse\\eclips-workspace\\CTM\\src\\main\\java\\res\\clau.mp3");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
				player=new Player(bufferedInputStream);
			} catch (JavaLayerException e1) {
				e1.printStackTrace();
			}
            try {
				player.play();
			} catch (JavaLayerException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == audio2) {
			System.out.println("Soy a2");
		}
		if(e.getSource() == audio3) {
			System.out.println("Soy no me acue");
		}
		if(e.getSource() == audio4) {
			System.out.println("Soy ISABELAA");
		}

	}
	
	
}