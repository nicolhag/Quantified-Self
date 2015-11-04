import java.util.Date;

public class Measurement implements Measurable {

  private String measurementType, comment;
  private Date date;
  private Double value;
  private Person person;
  private int measurementNr;
  private double stressLvl;

  public Measurement(String measurementType, Date date, Double value) {
    this.measurementType = measurementType;
    this.date = date;
    this.value = value;
  }

  public void setMeasurementNumber(int nr){
      measurementNr = nr;
  }

  public int getMeasurementNumber(){
      return measurementNr;
  }

  public void setComment(String comment){
      this.comment = comment;
  }

  public String getComment(){
      return comment;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Double getValue() {
    return value;
  }

  public void setStressLevel(double stressLvl){
    this.stressLvl = stressLvl;
  }

  public double getStressLevel(){
      return stressLvl;
  }

  public String getMeasurementType() {
    return measurementType;
  }

  public Date getDate() {
    return date;
  }

  public boolean hasDateRegistered(Date date) {
    return false;
  }

}
