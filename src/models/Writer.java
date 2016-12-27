package models;

import java.awt.Desktop;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import handler.SheetHandler;
import main.Main_Controller;

public class Writer {

//  private final String[] titles = { "Dia", "Nome", "Entrada", "Saída_almoço", "Retorno_Almoço", "Saída", "Horas Trabalhadas" };

  private File fileOut;


  public Writer(String fileOut) {
    this.fileOut = new File(fileOut);
  }


  public void write(List<BeanHorasTrabalhadas> listHorasTrabalhadas) {
    int line = 2;
    SheetHandler handler = new SheetHandler();
    try {
      handler.setFile(new File("./pattern/Horas_trabalhadas.xlsx"));
      handler.setSheet(0);
      handler.fillTitles(1);
      // writeTitles(handler);

      handler.setLine(line);

      for (BeanHorasTrabalhadas beanHorasTrabalhadas : listHorasTrabalhadas) {
        handler.setCellValueString(0, line, formattDate(beanHorasTrabalhadas.getDia()));
        handler.setCellValueString(1, line, beanHorasTrabalhadas.getNome());
        handler.setCellValueString(2, line, beanHorasTrabalhadas.getEntrada());
        handler.setCellValueString(3, line, beanHorasTrabalhadas.getSaidaalmoco());
        handler.setCellValueString(4, line, beanHorasTrabalhadas.getRetornoalmoco());
        handler.setCellValueString(5, line, beanHorasTrabalhadas.getSaida());
        handler.setCellValueString(6, line, beanHorasTrabalhadas.getHorastrabalhadas());
        if (beanHorasTrabalhadas.getHorastrabalhadas().isEmpty())
          handler.setCellErrorColor(6, line);

        line++;
        handler.setLine(line);
      }

      handler.save(fileOut);
      handler.close();
      Main_Controller.finished();
      Desktop.getDesktop().open(fileOut);

    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }


  private String formattDate(String oldDate) {
    final String OLD_FORMAT = "yyyy/MM/dd";
    final String NEW_FORMAT = "dd/MM/yyyy";
    String data = "";
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
      Date d = sdf.parse(oldDate);
      sdf.applyPattern(NEW_FORMAT);
      data = sdf.format(d);
    }
    catch (ParseException e) {
      e.printStackTrace();
    }
    return data;

  }


//  private void writeTitles(SheetHandler handler) {
//    for (int i = 0; i < titles.length; i++) {
//      handler.setCellValueString(i, 1, titles[i]);
//    }
//  }

}
