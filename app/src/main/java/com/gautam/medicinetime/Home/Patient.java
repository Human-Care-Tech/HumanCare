package com.gautam.medicinetime.Home;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Patient {

    @NonNull
    @ColumnInfo (name = "patient_id")
    @PrimaryKey
    String id;



    @ColumnInfo(name ="patient_full_name")
    String fullName;

    @ColumnInfo(name ="patient_age")
    String age;

    @ColumnInfo(name ="patient_address")
    String address;

    @ColumnInfo(name ="patient_phone")
    String phone;

    @ColumnInfo(name ="patient_height")
    String height;

    @ColumnInfo(name ="patient_weight")
    String weight;

    @ColumnInfo(name ="patient_gender")
    String gender;

    public Patient() {
    }

    public Patient(String fullName, String age, String address, String phone, String height, String weight, String gender) {
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
        this.gender = gender;

    }

    public Patient(String id, String fullName, String age, String address, String phone, String height, String weight, String gender) {
        this.id= id;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
