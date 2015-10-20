import java.util.Date;
public interface Measurable {

  public void setPerson(Person person);

  public Double getValue();

  public Date getDate();

  public boolean hasDateRegistered(Date date);

  public String getMeasurementType();

}
