package worldofzult.domain.menus;

public class Question{
    private String question; // The question
    private String[] answers; // Array of possible answers
    private String correctAnswer; // Correct answer

    // Constructor
    public Question(String question, String[] answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    // Get question
    public String getQuestion() {
        return question;
    }

    // Get answers
    public String[] getAnswers() {
        return answers;
    }

    // Get correct answer
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
