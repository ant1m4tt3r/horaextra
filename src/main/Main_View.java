package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import utilities.Utilities;
import java.awt.Toolkit;

public class Main_View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1986645205903841703L;

	@SuppressWarnings("unused")
	private Main_Controller controller;

	private JTextField textFieldPesquisar;
	private JTextField textFieldSaida;

	/**
	 * Create the frame.
	 */
	public Main_View(Main_Controller controller) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main_View.class.getResource("/img/technician32.png")));
		setTitle("Calculadora de Horas Trabalhadas");
		this.controller = controller;
		setSize(new Dimension(616, 224));
		Utilities.centralizarComponente(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 45, 50, 167, 207, 104, 0 };
		gridBagLayout.rowHeights = new int[] { 24, 17, 23, 17, 31, 33, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.btnPesquisarControl();
			}
		});

		JLabel lblNewLabel = new JLabel("Arquivo");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		textFieldPesquisar = new JTextField();
		GridBagConstraints gbc_textFieldPesquisar = new GridBagConstraints();
		gbc_textFieldPesquisar.fill = GridBagConstraints.BOTH;
		gbc_textFieldPesquisar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPesquisar.gridwidth = 3;
		gbc_textFieldPesquisar.gridx = 1;
		gbc_textFieldPesquisar.gridy = 2;
		getContentPane().add(textFieldPesquisar, gbc_textFieldPesquisar);
		textFieldPesquisar.setColumns(10);

		GridBagConstraints gbc_btnPesquisar = new GridBagConstraints();
		gbc_btnPesquisar.anchor = GridBagConstraints.NORTH;
		gbc_btnPesquisar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPesquisar.insets = new Insets(0, 0, 5, 0);
		gbc_btnPesquisar.gridx = 4;
		gbc_btnPesquisar.gridy = 2;
		getContentPane().add(btnPesquisar, gbc_btnPesquisar);

		JLabel lblSaida = new JLabel("Saída");
		lblSaida.setFont(new Font("Arial", Font.PLAIN, 13));
		GridBagConstraints gbc_lblSaida = new GridBagConstraints();
		gbc_lblSaida.anchor = GridBagConstraints.NORTH;
		gbc_lblSaida.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSaida.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaida.gridx = 1;
		gbc_lblSaida.gridy = 3;
		getContentPane().add(lblSaida, gbc_lblSaida);

		JButton btnOrdenar = new JButton("Iniciar");
		btnOrdenar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.btnIniciarControl();
			}
		});

		textFieldSaida = new JTextField();
		textFieldSaida.setColumns(10);
		GridBagConstraints gbc_textFieldSaida = new GridBagConstraints();
		gbc_textFieldSaida.fill = GridBagConstraints.BOTH;
		gbc_textFieldSaida.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSaida.gridwidth = 3;
		gbc_textFieldSaida.gridx = 1;
		gbc_textFieldSaida.gridy = 4;
		getContentPane().add(textFieldSaida, gbc_textFieldSaida);
		GridBagConstraints gbc_btnOrdenar = new GridBagConstraints();
		gbc_btnOrdenar.anchor = GridBagConstraints.WEST;
		gbc_btnOrdenar.insets = new Insets(0, 0, 0, 5);
		gbc_btnOrdenar.gridx = 3;
		gbc_btnOrdenar.gridy = 5;
		getContentPane().add(btnOrdenar, gbc_btnOrdenar);
		setVisible(true);
	}

	File showFileChooser() {
		JFileChooser chooser = new JFileChooser();
		int option = chooser.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}

	void showAlertMessage() {
		JOptionPane.showMessageDialog(this, "Selecione um arquivo válido.");
	}

	static void finish() {
		JOptionPane.showMessageDialog(null, "Concluido com sucesso!");
	}

	void setTextPesquisar(String path) {
		this.textFieldPesquisar.setText(path);
	}

	String getTextPesquisar() {
		return this.textFieldPesquisar.getText();
	}

	void setTextSaida(String textSaida) {
		this.textFieldSaida.setText(textSaida);
	}

	String getTextSaida() {
		return this.textFieldSaida.getText();
	}

}
