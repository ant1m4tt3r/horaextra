/**
 * @author Hugo Azevedo Soares
 */

package main;

import java.awt.EventQueue;
import java.util.List;

import models.BeanHorasTrabalhadas;
import models.FileBean;
import models.Reader;
import models.Writer;

public class Main_Controller {

	private Main_View view;
	private Main_Model model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Main_Controller();
	}

	private Main_Controller() {
		createView();
		createModel();
	}

	private void createView() {
		Main_Controller main_Controller = this;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					view = new Main_View(main_Controller);
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void createModel() {
		model = new Main_Model(this);
	}

	void btnPesquisarControl() {
		model.getFileName(view.showFileChooser());
	}

	void btnIniciarControl() {
		if (model.checkFile(view.getTextPesquisar())) {
			Reader reader = new Reader(view.getTextPesquisar());
			List<FileBean> listBean = reader.read();
			model.order(listBean);
			List<BeanHorasTrabalhadas> listHorasTrabalhadas = model.separate(listBean);
			Writer writer = new Writer(view.getTextSaida());
			writer.write(listHorasTrabalhadas);
		} else {
			view.showAlertMessage();
		}
	}

	void setPesquisarText(String text) {
		view.setTextPesquisar(text);
	}

	void setSaidarText(String text) {
		view.setTextSaida(text);
	}

	public static void finished() {
		Main_View.finish();
	}

}
