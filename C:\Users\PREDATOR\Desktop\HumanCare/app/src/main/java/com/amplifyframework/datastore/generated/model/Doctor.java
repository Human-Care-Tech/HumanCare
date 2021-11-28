package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
import com.amplifyframework.core.model.temporal.Temporal;

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

/** This is an auto generated class representing the Doctor type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Doctors")
public final class Doctor implements Model {
  public static final QueryField ID = field("Doctor", "id");
  public static final QueryField NAME = field("Doctor", "name");
  public static final QueryField SPECIALTY = field("Doctor", "specialty");
  public static final QueryField LOCATION = field("Doctor", "location");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String", isRequired = true) String specialty;
  private final @ModelField(targetType="String", isRequired = true) String location;
  private final @ModelField(targetType="Appointment") @HasMany(associatedWith = "doctorId", type = Appointment.class) List<Appointment> appointments = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getSpecialty() {
      return specialty;
  }
  
  public String getLocation() {
      return location;
  }
  
  public List<Appointment> getAppointments() {
      return appointments;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Doctor(String id, String name, String specialty, String location) {
    this.id = id;
    this.name = name;
    this.specialty = specialty;
    this.location = location;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Doctor doctor = (Doctor) obj;
      return ObjectsCompat.equals(getId(), doctor.getId()) &&
              ObjectsCompat.equals(getName(), doctor.getName()) &&
              ObjectsCompat.equals(getSpecialty(), doctor.getSpecialty()) &&
              ObjectsCompat.equals(getLocation(), doctor.getLocation()) &&
              ObjectsCompat.equals(getCreatedAt(), doctor.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), doctor.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getSpecialty())
      .append(getLocation())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Doctor {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("specialty=" + String.valueOf(getSpecialty()) + ", ")
      .append("location=" + String.valueOf(getLocation()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
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
  public static Doctor justId(String id) {
    return new Doctor(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      specialty,
      location);
  }
  public interface NameStep {
    SpecialtyStep name(String name);
  }
  

  public interface SpecialtyStep {
    LocationStep specialty(String specialty);
  }
  

  public interface LocationStep {
    BuildStep location(String location);
  }
  

  public interface BuildStep {
    Doctor build();
    BuildStep id(String id);
  }
  

  public static class Builder implements NameStep, SpecialtyStep, LocationStep, BuildStep {
    private String id;
    private String name;
    private String specialty;
    private String location;
    @Override
     public Doctor build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Doctor(
          id,
          name,
          specialty,
          location);
    }
    
    @Override
     public SpecialtyStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public LocationStep specialty(String specialty) {
        Objects.requireNonNull(specialty);
        this.specialty = specialty;
        return this;
    }
    
    @Override
     public BuildStep location(String location) {
        Objects.requireNonNull(location);
        this.location = location;
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
    private CopyOfBuilder(String id, String name, String specialty, String location) {
      super.id(id);
      super.name(name)
        .specialty(specialty)
        .location(location);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder specialty(String specialty) {
      return (CopyOfBuilder) super.specialty(specialty);
    }
    
    @Override
     public CopyOfBuilder location(String location) {
      return (CopyOfBuilder) super.location(location);
    }
  }
  
}
