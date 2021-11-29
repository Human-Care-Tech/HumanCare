package com.gautam.medicinetime.Home;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientDao {
    @Query("SELECT * FROM Patient" )
    List<Patient> getAll();

    @Query("SELECT * FROM Patient WHERE patient_id = :patientId")
    Patient getPatientByPatientId (String patientId);

    @Query("SELECT * FROM Patient WHERE patient_id = :id")
    Patient getPatientById (String id);




    @Insert
    Long insertUser (Patient patient);

}
