package com.amplifyframework.datastore.generated.model;

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

/** This is an auto generated class representing the Patient type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Patients")
public final class Patient implements Model {
  public static final QueryField ID = field("Patient", "id");
  public static final QueryField FULL_NAME = field("Patient", "fullName");
  public static final QueryField AGE = field("Patient", "age");
  public static final QueryField ADDRESS = field("Patient", "address");
  public static final QueryField PHONE = field("Patient", "phone");
  public static final QueryField HEIGHT = field("Patient", "height");
  public static final QueryField WEIGHT = field("Patient", "weight");
  public static final QueryField GENDER = field("Patient", "gender");
  public static final QueryField FILE_NAME = field("Patient", "fileName");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String fullName;
  private final @ModelField(targetType="String") String age;
  private final @ModelField(targetType="String") String address;
  private final @ModelField(targetType="String") String phone;
  private final @ModelField(targetType="String") String height;
  private final @ModelField(targetType="String") String weight;
  private final @ModelField(targetType="String") String gender;
  private final @ModelField(targetType="String") String fileName;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getFullName() {
      return fullName;
  }
  
  public String getAge() {
      return age;
  }
  
  public String getAddress() {
      return address;
  }
  
  public String getPhone() {
      return phone;
  }
  
  public String getHeight() {
      return height;
  }
  
  public String getWeight() {
      return weight;
  }
  
  public String getGender() {
      return gender;
  }
  
  public String getFileName() {
      return fileName;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Patient(String id, String fullName, String age, String address, String phone, String height, String weight, String gender, String fileName) {
    this.id = id;
    this.fullName = fullName;
    this.age = age;
    this.address = address;
    this.phone = phone;
    this.height = height;
    this.weight = weight;
    this.gender = gender;
    this.fileName = fileName;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Patient patient = (Patient) obj;
      return ObjectsCompat.equals(getId(), patient.getId()) &&
              ObjectsCompat.equals(getFullName(), patient.getFullName()) &&
              ObjectsCompat.equals(getAge(), patient.getAge()) &&
              ObjectsCompat.equals(getAddress(), patient.getAddress()) &&
              ObjectsCompat.equals(getPhone(), patient.getPhone()) &&
              ObjectsCompat.equals(getHeight(), patient.getHeight()) &&
              ObjectsCompat.equals(getWeight(), patient.getWeight()) &&
              ObjectsCompat.equals(getGender(), patient.getGender()) &&
              ObjectsCompat.equals(getFileName(), patient.getFileName()) &&
              ObjectsCompat.equals(getCreatedAt(), patient.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), patient.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFullName())
      .append(getAge())
      .append(getAddress())
      .append(getPhone())
      .append(getHeight())
      .append(getWeight())
      .append(getGender())
      .append(getFileName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Patient {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("fullName=" + String.valueOf(getFullName()) + ", ")
      .append("age=" + String.valueOf(getAge()) + ", ")
      .append("address=" + String.valueOf(getAddress()) + ", ")
      .append("phone=" + String.valueOf(getPhone()) + ", ")
      .append("height=" + String.valueOf(getHeight()) + ", ")
      .append("weight=" + String.valueOf(getWeight()) + ", ")
      .append("gender=" + String.valueOf(getGender()) + ", ")
      .append("fileName=" + String.valueOf(getFileName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static FullNameStep builder() {
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
  public static Patient justId(String id) {
    return new Patient(
      id,
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
      fullName,
      age,
      address,
      phone,
      height,
      weight,
      gender,
      fileName);
  }
  public interface FullNameStep {
    BuildStep fullName(String fullName);
  }
  

  public interface BuildStep {
    Patient build();
    BuildStep id(String id);
    BuildStep age(String age);
    BuildStep address(String address);
    BuildStep phone(String phone);
    BuildStep height(String height);
    BuildStep weight(String weight);
    BuildStep gender(String gender);
    BuildStep fileName(String fileName);
  }
  

  public static class Builder implements FullNameStep, BuildStep {
    private String id;
    private String fullName;
    private String age;
    private String address;
    private String phone;
    private String height;
    private String weight;
    private String gender;
    private String fileName;
    @Override
     public Patient build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Patient(
          id,
          fullName,
          age,
          address,
          phone,
          height,
          weight,
          gender,
          fileName);
    }
    
    @Override
     public BuildStep fullName(String fullName) {
        Objects.requireNonNull(fullName);
        this.fullName = fullName;
        return this;
    }
    
    @Override
     public BuildStep age(String age) {
        this.age = age;
        return this;
    }
    
    @Override
     public BuildStep address(String address) {
        this.address = address;
        return this;
    }
    
    @Override
     public BuildStep phone(String phone) {
        this.phone = phone;
        return this;
    }
    
    @Override
     public BuildStep height(String height) {
        this.height = height;
        return this;
    }
    
    @Override
     public BuildStep weight(String weight) {
        this.weight = weight;
        return this;
    }
    
    @Override
     public BuildStep gender(String gender) {
        this.gender = gender;
        return this;
    }
    
    @Override
     public BuildStep fileName(String fileName) {
        this.fileName = fileName;
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
    private CopyOfBuilder(String id, String fullName, String age, String address, String phone, String height, String weight, String gender, String fileName) {
      super.id(id);
      super.fullName(fullName)
        .age(age)
        .address(address)
        .phone(phone)
        .height(height)
        .weight(weight)
        .gender(gender)
        .fileName(fileName);
    }
    
    @Override
     public CopyOfBuilder fullName(String fullName) {
      return (CopyOfBuilder) super.fullName(fullName);
    }
    
    @Override
     public CopyOfBuilder age(String age) {
      return (CopyOfBuilder) super.age(age);
    }
    
    @Override
     public CopyOfBuilder address(String address) {
      return (CopyOfBuilder) super.address(address);
    }
    
    @Override
     public CopyOfBuilder phone(String phone) {
      return (CopyOfBuilder) super.phone(phone);
    }
    
    @Override
     public CopyOfBuilder height(String height) {
      return (CopyOfBuilder) super.height(height);
    }
    
    @Override
     public CopyOfBuilder weight(String weight) {
      return (CopyOfBuilder) super.weight(weight);
    }
    
    @Override
     public CopyOfBuilder gender(String gender) {
      return (CopyOfBuilder) super.gender(gender);
    }
    
    @Override
     public CopyOfBuilder fileName(String fileName) {
      return (CopyOfBuilder) super.fileName(fileName);
    }
  }
  
}
