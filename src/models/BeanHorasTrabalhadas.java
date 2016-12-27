package models;

public class BeanHorasTrabalhadas {

  private String dia;
  private String nome;
  private String entrada;
  private String saidaalmoco;
  private String retornoalmoco;
  private String saida;
  private String horastrabalhadas;


  /** Getters */
  public String getDia() {
    return this.dia;
  }


  public String getNome() {
    return this.nome;
  }


  public String getEntrada() {
    return this.entrada;
  }


  public String getSaidaalmoco() {
    return this.saidaalmoco;
  }


  public String getRetornoalmoco() {
    return this.retornoalmoco;
  }


  public String getSaida() {
    return this.saida;
  }


  public String getHorastrabalhadas() {
    return this.horastrabalhadas;
  }


  /** Setters */
  public void setDia(String dia) {
    this.dia = dia;
  }


  public void setNome(String nome) {
    this.nome = nome;
  }


  public void setEntrada(String entrada) {
    this.entrada = entrada;
  }


  public void setSaidaalmoco(String saidaalmoco) {
    this.saidaalmoco = saidaalmoco;
  }


  public void setRetornoalmoco(String retornoalmoco) {
    this.retornoalmoco = retornoalmoco;
  }


  public void setSaida(String saida) {
    this.saida = saida;
  }


  public void setHorastrabalhadas(String horastrabalhadas) {
    this.horastrabalhadas = horastrabalhadas;
  }


  public boolean checkNull() {
    boolean nullcheck = getEntrada() == null || getSaidaalmoco() == null || getRetornoalmoco() == null || getSaida() == null;
    return nullcheck;
  }


  @Override
  public String toString() {
    return this.getDia() + " " + this.getNome() + " " + this.getEntrada() + " " + this.getSaidaalmoco() + " " + this.getRetornoalmoco() + " " + this.getSaida();
  }

}