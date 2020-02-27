package ru.otus.ovodkov.homework.domain;

/**
 * Вопрос тестирования
 *
 * @author ovodkov.s
 */
public class Question {

    private String question;
    private String[] answerOptions;
    private int correctAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(String[] answerOptions) {
        this.answerOptions = answerOptions;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * .ctor
     *
     * @param question      Тело вопроса
     * @param answerOptions Список вариантов ответов.
     * @param correctAnswer Номер правильного варианта ответа.
     */
    public Question(String question, String[] answerOptions, int correctAnswer) {
        this.question = question;
        this.answerOptions = answerOptions;
        this.correctAnswer = correctAnswer;
    }
}
