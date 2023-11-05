package com.ubl.studentweb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ubl.studentweb.domain.Student;

@RestController
public class StudentController {

    public static Map<String, Student> studentMap = new HashMap<>();
    
    
    /**
     * @return
     */
    @GetMapping("/students")
    public List<Student> getStudents(){
        return  studentMap.values().stream().collect(Collectors.toList());


}

    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentMap.put(student.getNim(), student);
        Student savedStudent = studentMap.get(student.getNim());
        return new ResponseEntity<>("Student with NIM : "+ savedStudent.getNim() + "has been created", HttpStatus.OK);
    }

    @GetMapping(value = "/students/{nim}")
    public ResponseEntity<Student> findStudent(@PathVariable("nim") String nim) {
        final Student student = studentMap.get(nim);
        return new ResponseEntity<>(student, HttpStatus.OK);

    }

        @PutMapping(value = "/students/{nim}")
        public ResponseEntity<String> updateStudent(@PathVariable("nim")String nim,
                @RequestBody Student student) {
            final Student studentToBeUpdated = studentMap.get(student.getNim());
            studentToBeUpdated.setAddress(student.getAddress());
            studentToBeUpdated.setDateOfBirth(student.getDateOfBirth());
            studentToBeUpdated.setFullName(student.getFullName());
            
            studentMap.put(student.getNim(), studentToBeUpdated);
            return new ResponseEntity<>("Student with NIM: " +studentToBeUpdated.getNim() +
                    "has been updated", HttpStatus.OK);
                } 
                
       @DeleteMapping(value = "/students/{nim}")
       public ResponseEntity<Void> deleteStudent(@PathVariable("nim") String nim) {
            studentMap.remove(nim);
            return new ResponseEntity<Void>(HttpStatus.OK);
       }         
    }