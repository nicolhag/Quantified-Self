import java.util.Date;
public interface Measurable {

  public Double getMeasurement();

  public Date getDate();

  public boolean hasDateRegistered(Date date);

}
