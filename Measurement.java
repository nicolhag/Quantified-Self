import java.util.Date;

public class Measurement implements Measurable {

  Date date = null;
  Double value = null;
  Person person = null;

  public Measurement(Date date, Double value) {
    this.date = date;
    this.value = value;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Double getMeasurement() {
    return value;
  }

  public Date getDate() {
    return date;
  }

  public boolean hasDateRegistered(Date date) {
    return false;
  }

}
