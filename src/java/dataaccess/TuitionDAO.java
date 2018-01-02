/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;

import transferobjects.Tuition;

/**
 *
 * @author fatema
 */
public interface TuitionDAO {
    List<Tuition> getAllTuitions();
    void addTuition(Tuition tuition);
    Tuition getTuitionByStudentNumber(Integer studentnumber);
    void updateTuition(Integer studentnumber, double paid, double remainder);
    void deleteTuition(Integer studentNumber);
    
  }
    

