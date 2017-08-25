import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

public class ventana1 extends JFrame {

	private JPanel contentPane;
	private final JEditorPane editorPane = new JEditorPane();


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

}
