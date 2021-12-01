package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Appointment type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Appointments")
@Index(name = "ByDoctor", fields = {"doctorId"})
public final class Appointment implements Model {
  public static final QueryField ID = field("Appointment", "id");
  public static final QueryField DOCTOR_ID = field("Appointment", "doctorId");
  public static final QueryField USER = field("Appointment", "user");
  public static final QueryField DOCTOR = field("Appointment", "doctor");
  public static final QueryField LAT = field("Appointment", "lat");
  public static final QueryField LON = field("Appointment", "lon");
  public static final QueryField SYMPTOMS = field("Appointment", "symptoms");
  public static final QueryField DATE = field("Appointment", "date");
  public static final QueryField TIME = field("Appointment", "time");
  public static final QueryField STATUS = field("Appointment", "status");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String doctorId;
  private final @ModelField(targetType="String", isRequired = true) String user;
  private final @ModelField(targetType="String") String doctor;
  private final @ModelField(targetType="Float") Double lat;
  private final @ModelField(targetType="Float") Double lon;
  private final @ModelField(targetType="String") String symptoms;
  private final @ModelField(targetType="String") String date;
  private final @ModelField(targetType="String") String time;
  private final @ModelField(targetType="String") String status;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getDoctorId() {
      return doctorId;
  }
  
  public String getUser() {
      return user;
  }
  
  public String getDoctor() {
      return doctor;
  }
  
  public Double getLat() {
      return lat;
  }
  
  public Double getLon() {
      return lon;
  }
  
  public String getSymptoms() {
      return symptoms;
  }
  
  public String getDate() {
      return date;
  }
  
  public String getTime() {
      return time;
  }
  
  public String getStatus() {
      return status;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Appointment(String id, String doctorId, String user, String doctor, Double lat, Double lon, String symptoms, String date, String time, String status) {
    this.id = id;
    this.doctorId = doctorId;
    this.user = user;
    this.doctor = doctor;
    this.lat = lat;
    this.lon = lon;
    this.symptoms = symptoms;
    this.date = date;
    this.time = time;
    this.status = status;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Appointment appointment = (Appointment) obj;
      return ObjectsCompat.equals(getId(), appointment.getId()) &&
              ObjectsCompat.equals(getDoctorId(), appointment.getDoctorId()) &&
              ObjectsCompat.equals(getUser(), appointment.getUser()) &&
              ObjectsCompat.equals(getDoctor(), appointment.getDoctor()) &&
              ObjectsCompat.equals(getLat(), appointment.getLat()) &&
              ObjectsCompat.equals(getLon(), appointment.getLon()) &&
              ObjectsCompat.equals(getSymptoms(), appointment.getSymptoms()) &&
              ObjectsCompat.equals(getDate(), appointment.getDate()) &&
              ObjectsCompat.equals(getTime(), appointment.getTime()) &&
              ObjectsCompat.equals(getStatus(), appointment.getStatus()) &&
              ObjectsCompat.equals(getCreatedAt(), appointment.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), appointment.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDoctorId())
      .append(getUser())
      .append(getDoctor())
      .append(getLat())
      .append(getLon())
      .append(getSymptoms())
      .append(getDate())
      .append(getTime())
      .append(getStatus())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Appointment {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("doctorId=" + String.valueOf(getDoctorId()) + ", ")
      .append("user=" + String.valueOf(getUser()) + ", ")
      .append("doctor=" + String.valueOf(getDoctor()) + ", ")
      .append("lat=" + String.valueOf(getLat()) + ", ")
      .append("lon=" + String.valueOf(getLon()) + ", ")
      .append("symptoms=" + String.valueOf(getSymptoms()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("time=" + String.valueOf(getTime()) + ", ")
      .append("status=" + String.valueOf(getStatus()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static DoctorIdStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Appointment justId(String id) {
    return new Appointment(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      doctorId,
      user,
      doctor,
      lat,
      lon,
      symptoms,
      date,
      time,
      status);
  }
  public interface DoctorIdStep {
    UserStep doctorId(String doctorId);
  }
  

  public interface UserStep {
    BuildStep user(String user);
  }
  

  public interface BuildStep {
    Appointment build();
    BuildStep id(String id);
    BuildStep doctor(String doctor);
    BuildStep lat(Double lat);
    BuildStep lon(Double lon);
    BuildStep symptoms(String symptoms);
    BuildStep date(String date);
    BuildStep time(String time);
    BuildStep status(String status);
  }
  

  public static class Builder implements DoctorIdStep, UserStep, BuildStep {
    private String id;
    private String doctorId;
    private String user;
    private String doctor;
    private Double lat;
    private Double lon;
    private String symptoms;
    private String date;
    private String time;
    private String status;
    @Override
     public Appointment build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Appointment(
          id,
          doctorId,
          user,
          doctor,
          lat,
          lon,
          symptoms,
          date,
          time,
          status);
    }
    
    @Override
     public UserStep doctorId(String doctorId) {
        Objects.requireNonNull(doctorId);
        this.doctorId = doctorId;
        return this;
    }
    
    @Override
     public BuildStep user(String user) {
        Objects.requireNonNull(user);
        this.user = user;
        return this;
    }
    
    @Override
     public BuildStep doctor(String doctor) {
        this.doctor = doctor;
        return this;
    }
    
    @Override
     public BuildStep lat(Double lat) {
        this.lat = lat;
        return this;
    }
    
    @Override
     public BuildStep lon(Double lon) {
        this.lon = lon;
        return this;
    }
    
    @Override
     public BuildStep symptoms(String symptoms) {
        this.symptoms = symptoms;
        return this;
    }
    
    @Override
     public BuildStep date(String date) {
        this.date = date;
        return this;
    }
    
    @Override
     public BuildStep time(String time) {
        this.time = time;
        return this;
    }
    
    @Override
     public BuildStep status(String status) {
        this.status = status;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String doctorId, String user, String doctor, Double lat, Double lon, String symptoms, String date, String time, String status) {
      super.id(id);
      super.doctorId(doctorId)
        .user(user)
        .doctor(doctor)
        .lat(lat)
        .lon(lon)
        .symptoms(symptoms)
        .date(date)
        .time(time)
        .status(status);
    }
    
    @Override
     public CopyOfBuilder doctorId(String doctorId) {
      return (CopyOfBuilder) super.doctorId(doctorId);
    }
    
    @Override
     public CopyOfBuilder user(String user) {
      return (CopyOfBuilder) super.user(user);
    }
    
    @Override
     public CopyOfBuilder doctor(String doctor) {
      return (CopyOfBuilder) super.doctor(doctor);
    }
    
    @Override
     public CopyOfBuilder lat(Double lat) {
      return (CopyOfBuilder) super.lat(lat);
    }
    
    @Override
     public CopyOfBuilder lon(Double lon) {
      return (CopyOfBuilder) super.lon(lon);
    }
    
    @Override
     public CopyOfBuilder symptoms(String symptoms) {
      return (CopyOfBuilder) super.symptoms(symptoms);
    }
    
    @Override
     public CopyOfBuilder date(String date) {
      return (CopyOfBuilder) super.date(date);
    }
    
    @Override
     public CopyOfBuilder time(String time) {
      return (CopyOfBuilder) super.time(time);
    }
    
    @Override
     public CopyOfBuilder status(String status) {
      return (CopyOfBuilder) super.status(status);
    }
  }

    public static final Comparator<Appointment> By_DATE_ASCENDING = new Comparator<Appointment>() {
        @Override
        public int compare(Appointment o1, Appointment o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };

    public static final Comparator<Appointment> By_DATE_DESCENDING = new Comparator<Appointment>() {
        @Override
        public int compare(Appointment o1, Appointment o2) {
            return o2.getDate().compareTo(o1.getDate());
        }
    };
}
