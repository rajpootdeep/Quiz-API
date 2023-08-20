package com.deep.quizapp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deep.quizapp.Model.Question;
import com.deep.quizapp.Repository.QuestionRepo;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repo;

    public ResponseEntity<List<Question>> getAllQuestions() {

        try{
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<Question>(), HttpStatus.BAD_REQUEST);
    }



    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

        try{
        return new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK) ;
        }catch (Exception e){
    e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<Question>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        repo.save(question);
        return new ResponseEntity<String>("Success", HttpStatus.CREATED);
    }

}
