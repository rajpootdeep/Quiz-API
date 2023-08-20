package com.deep.quizapp.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deep.quizapp.Model.Question;
import com.deep.quizapp.Model.QuestionWrapper;
import com.deep.quizapp.Model.Quiz;
import com.deep.quizapp.Model.Response;
import com.deep.quizapp.Repository.QuestionRepo;
import com.deep.quizapp.Repository.QuizRepo;

@Service
public class QuizService {

    @Autowired
    QuizRepo repo;

    @Autowired
    QuestionRepo qRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
List<Question> questions=qRepo.findRandomQuestionsByCategory(category, numQ);


        Quiz quiz=new Quiz();
        quiz.setTitle(title);
       
        quiz.setQuestions(questions);

        repo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Long id) {
		
		
		try {
	Optional<Quiz> quiz =repo.findById(id);
	List<Question> qFromDb=quiz.get().getQuestions();
	
	List<QuestionWrapper> questionForUser=new ArrayList<>();
	
	for(Question q : qFromDb) {
		QuestionWrapper qp=new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
		questionForUser.add(qp);
	}
	
	return new ResponseEntity<>(questionForUser,HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Spme thing wrong");
		}
		
		return new ResponseEntity<>(new ArrayList<QuestionWrapper>(),HttpStatus.BAD_REQUEST); 
	}

	public ResponseEntity<Integer> calculatedResult(Long id, List<Response> responses) {
		Quiz quiz=repo.findById(id).get();
		List<Question> questions=quiz.getQuestions();
		int right =0;
		int i=0;
		for(Response response : responses)
		{
			if(response.getResOption().equals(questions.get(i).getRightAnswer()))
					right++;
			
			i++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
