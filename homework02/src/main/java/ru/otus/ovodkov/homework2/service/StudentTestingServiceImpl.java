package ru.otus.ovodkov.homework2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.ovodkov.homework2.dao.QuestionsDao;
import ru.otus.ovodkov.homework2.dao.StudentDao;
import ru.otus.ovodkov.homework2.domain.Question;
import ru.otus.ovodkov.homework2.domain.Student;
import ru.otus.ovodkov.homework2.domain.TestResult;

import java.util.List;

/**
 * Сервис тестирования студентов
 *
 * @author Sergey Ovodkov
 * @project homework2
 * @created 2020-03-05
 * @see StudentTestingService
 */
@Service
public class StudentTestingServiceImpl implements StudentTestingService {

    @Value("${answer.success}")
    private int minCorrectAnswers;

    private final QuestionsDao questionsDao;
    private final StudentDao studentDao;
    private final RenderQuestion renderQuestion;
    private final AnswerProcessing answerProcessing;
    private final RenderTestResult renderTestResult;

    @Autowired
    public StudentTestingServiceImpl(QuestionsDao questionsDao,
                                     StudentDao studentDao,
                                     RenderQuestion renderQuestion,
                                     AnswerProcessing answerProcessing,
                                     RenderTestResult renderTestResult) {
        this.questionsDao = questionsDao;
        this.studentDao = studentDao;
        this.renderQuestion = renderQuestion;
        this.answerProcessing = answerProcessing;
        this.renderTestResult = renderTestResult;
    }

    /**
     * @see StudentTestingService#studentTesting()
     */
    public void studentTesting() {
        List<Question> questions = questionsDao.getQuestions();
        Student student = studentDao.getStudentPersonalData();

        int countCorrectAnswer = 0;

        for (Question question : questions) {
            renderQuestion.showQuestion(question);
            countCorrectAnswer += answerProcessing.getAnswer() == question.getCorrectAnswer() ? 1 : 0;
        }

        var isPassedTest = countCorrectAnswer >= minCorrectAnswers;

        renderTestResult.showTestResult(new TestResult(student, countCorrectAnswer), isPassedTest);
    }
}
