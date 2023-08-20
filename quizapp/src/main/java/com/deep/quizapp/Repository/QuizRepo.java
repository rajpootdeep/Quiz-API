package com.deep.quizapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep.quizapp.Model.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Long> {

}
