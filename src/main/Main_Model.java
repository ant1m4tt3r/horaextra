package main;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.BeanHorasTrabalhadas;
import models.FileBean;

public class Main_Model {

	Main_Controller controller;

	private static final String REGEX_ENTRADA = "0[4-9]:.*";
	private static final String REGEX_SAIDA_ALMOCO = "((10:[0-5][0-9]:)|(11:[0-5][0-9]:)|(12:([0-5][0-9]|):)).*";
	private static final String REGEX_RETORNO_ALMOCO = "((1[1-4]:[0-5][0-9]:)).*";
	private static final String REGEX_SAIDA = "((1[6-9]:[0-5][0-9]:)).*";

	Main_Model(Main_Controller controller) {
		this.controller = controller;
	}

	void getFileName(File file) {
		if (file == null)
			return;

		String saidaText = file.getParent() + "\\" + file.getName().substring(0, file.getName().indexOf("."))
				+ "saida.xlsx";

		controller.setPesquisarText(file.getAbsolutePath());
		controller.setSaidarText(saidaText);
	}

	boolean checkFile(String path) {
		if (path == null || path.isEmpty())
			return false;
		return true;
	}

	void order(List<FileBean> listBean) {
		listBean.sort(new Orderator());
	}

	public List<BeanHorasTrabalhadas> separate(List<FileBean> listBean) {
		BeanHorasTrabalhadas beanHorasTrabalhadas = null;
		Map<String, BeanHorasTrabalhadas> map = new LinkedHashMap<String, BeanHorasTrabalhadas>();
		List<BeanHorasTrabalhadas> listHorasTrabalhadas = new ArrayList<>();

		for (int i = 0; i < listBean.size(); i++) {
			FileBean bean = listBean.get(i);
			if (map.get(bean.getNome() + bean.getData()) == null) {
				beanHorasTrabalhadas = new BeanHorasTrabalhadas();
				beanHorasTrabalhadas.setNome(bean.getNome());
				beanHorasTrabalhadas.setDia(bean.getData());
				map.put(bean.getNome() + bean.getData(), beanHorasTrabalhadas);
			} else {
				beanHorasTrabalhadas = map.get(bean.getNome() + bean.getData());
			}

			decideHour(bean, beanHorasTrabalhadas);

		}

		for (Entry<String, BeanHorasTrabalhadas> entry : map.entrySet()) {
			setWorkedHours(entry.getValue());
			listHorasTrabalhadas.add(entry.getValue());
		}

		return listHorasTrabalhadas;

	}

	private void decideHour(FileBean bean, BeanHorasTrabalhadas beanHorasTrabalhadas) {
		// entrada
		if (bean.getHora().matches(REGEX_ENTRADA)) {
			if (beanHorasTrabalhadas.getEntrada() == null) {
				beanHorasTrabalhadas.setEntrada(bean.getHora());
				return;
			}
		}

		// saidaalmoço
		if (bean.getHora().matches(REGEX_SAIDA_ALMOCO)) {
			if (beanHorasTrabalhadas.getSaidaalmoco() == null) {
				beanHorasTrabalhadas.setSaidaalmoco(bean.getHora());
				return;
			}
		}

		// retorno almoço
		if (bean.getHora().matches(REGEX_RETORNO_ALMOCO)) {
			if (beanHorasTrabalhadas.getRetornoalmoco() == null) {
				beanHorasTrabalhadas.setRetornoalmoco(bean.getHora());
				return;
			}
		}

		// saida
		if (bean.getHora().matches(REGEX_SAIDA)) {
			if (beanHorasTrabalhadas.getSaida() == null) {
				beanHorasTrabalhadas.setSaida(bean.getHora());
				return;
			}
		}
	}

	private void setWorkedHours(BeanHorasTrabalhadas beanHorasTrabalhadas) {
		if (beanHorasTrabalhadas.checkNull()) {
			beanHorasTrabalhadas.setHorastrabalhadas("");
		} else {
			beanHorasTrabalhadas.setHorastrabalhadas(getHorasTrabalhadas(beanHorasTrabalhadas));
		}
	}

	private String getHorasTrabalhadas(BeanHorasTrabalhadas beanHorasTrabalhadas) {
		LocalTime entrada = LocalTime.parse(beanHorasTrabalhadas.getEntrada());
		LocalTime saidaAlmoco = LocalTime.parse(beanHorasTrabalhadas.getSaidaalmoco());

		LocalTime retornoAlmoco = LocalTime.parse(beanHorasTrabalhadas.getRetornoalmoco());
		LocalTime saida = LocalTime.parse(beanHorasTrabalhadas.getSaida());

		LocalTime manha = saidaAlmoco.minusHours(entrada.getHour()).minusMinutes(entrada.getMinute());
		LocalTime tarde = saida.minusHours(retornoAlmoco.getHour()).minusMinutes(retornoAlmoco.getMinute());

		LocalTime total = tarde.plusHours(manha.getHour()).plusMinutes(manha.getMinute());

		return total.toString();
	}

	class Orderator implements Comparator<FileBean> {

		@Override
		public int compare(FileBean o1, FileBean o2) {

			DateFormat formatterDate = new SimpleDateFormat("yyyy/MM/dd");
			DateFormat formatterTime = new SimpleDateFormat("HH:mm");
			Date d1 = null;
			Date d2 = null;
			Date t1 = null;
			Date t2 = null;
			try {
				d1 = formatterDate.parse(o1.getData());
				d2 = formatterDate.parse(o2.getData());
				t1 = formatterTime.parse(o1.getHora());
				t2 = formatterTime.parse(o2.getHora());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			int data = d1.compareTo(d2);
			int nome = o1.getNome().compareTo(o2.getNome());
			int hora = t1.compareTo(t2);

			return data == 0 ? (nome == 0 ? hora : nome) : data;

		}

	}

}
