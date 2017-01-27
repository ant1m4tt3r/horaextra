package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Reader {

	File fileIn;

	// REGEX
	// \d{4}/\d{2}/\d{2} data
	// \d{2}:\d{2}:\d{2} hora

	public Reader(String fileIn) {
		if (fileIn == null || fileIn.isEmpty())
			throw new NullPointerException("Arquivo de entrada não pode ser nulo ou vazio");
		this.fileIn = new File(fileIn);
	}

	public List<FileBean> read() {
		List<FileBean> listBean = new ArrayList<FileBean>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileIn));
			String nome = "";
			String data = "";
			String hora = "";
			String line = "";
			FileBean bean;
			while (reader.ready()) {
				line = reader.readLine();
				if (line.matches("\\d.*")) {
					bean = new FileBean();
					nome = line.substring(19, 31).trim();
					data = line.substring(38, 48);
					hora = line.substring(50, 58);

					bean.setNome(nome);
					bean.setData(data);
					bean.setHora(hora);

					listBean.add(bean);
				}
			}
			reader.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Arquivo inválido. Por favor, utilize um arquivo com o formato suportado ou contate o administrador do sistema.");
			e.printStackTrace();
		}
		return listBean;
	}

}
