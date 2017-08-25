import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import analizador.*;

public class ventana1 extends JFrame {

	private JPanel contentPane;
	private final JEditorPane editorPane = new JEditorPane();
	analizadorL1 parser =null;

	/**
	 * Create the frame.
	 */
	public ventana1() {
		setTitle("Servidor BD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("click en boton");
				llamadaParser();
			}
		});
		panel.add(btnNewButton);
		editorPane.setMargin(new Insets(3, 3, 1, 3));
		editorPane.setPreferredSize(new Dimension(606, 321));
		panel.add(editorPane);
		
		JLabel lblNewLabel = new JLabel("Consola Servidor");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("Estatus: ");
		contentPane.add(lblNewLabel_1, BorderLayout.SOUTH);
		
		
	}
	
	private void llamadaParser() {
		///llamada a las ventanas*/
		String sentences=editorPane.getText();
		InputStream is= new ByteArrayInputStream(sentences.getBytes());
		
		if (parser==null) {
			parser= new analizadorL1(is);
		}else {
			analizadorL1.ReInit(is);			
		}
		
		try
	      {
	        switch (analizadorL1.one_line())
	        {
	          case 0 : 
	          System.out.print("OK. ");
	          System.out.println("se ha aceptador "+sentences);
	          break;
	          case 1 : 
	          System.out.println("Goodbye.");
	          break;
	          default : 
	          break;
	        }
	      }
	      catch (Exception e)
	      {
	        System.out.println("NOK.");
	        System.out.println(e.getMessage());
	        analizadorL1.ReInit(System.in);
	      }
	      catch (Error e)
	      {
	        System.out.println("Oops.");
	        System.out.println(e.getMessage());
	        //break;
	      }
		
	}

}
