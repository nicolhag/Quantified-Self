public class Measurement implements Measurable {

  Date date = null;
  Double value = null;

  public Measurement(Date date, Double value) {
    this.date = date;
    this.value = value;
  }

  public Double getMeasurement() {
    return value;
  }

  public Date getDate() {
    return date;
  }
  
}
