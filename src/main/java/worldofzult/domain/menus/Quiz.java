package worldofzult.domain.menus;

import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    private ArrayList<Question> questions;
    private int score = 0;

    public Quiz() {
        this.questions = new ArrayList<Question>();
    }

    public void run() {
        for (Question question : questions) {
            System.out.println(question.getQuestion());

            for (String answer : question.getAnswers()) {
                System.out.println(answer);
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Vælg svarmulighed: ");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase(question.getCorrectAnswer())) {
                score++;
                System.out.println("Korrekt svar\n");
            } else {
                System.out.println("Forkert svar, det rigtige svar er: " + question.getCorrectAnswer() + "\n");
            }
        }
        System.out.println("Quiz score: " + score + "/" + questions.size());
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void initQuiz() {
        Question q1 = new Question("Hvad er det primære formål med vækstlys i landbruget og indendørs havebrug?", new String[]{"A: Forhindre skadedyr", "B: Fremme fotosyntese og plantevækst", "C: Regulere temperatur i drivhuset"}, "B");
        Question q2 = new Question("Hvordan hjælper et vandingssystem med at forbedre landbrugsproduktionen?", new String[]{"A: Fremmer samarbejdet mellem bier og planter", "B: Minimerer væksten af ukrudt", "C: Opretholder den optimale jordfugtighed"}, "C");
        Question q3 = new Question("Hvilken funktion har biokul i landbrug?", new String[]{"A: Det skræmmer skadedyr væk", "B: Det forbedrer jordkvaliteten", "C: Det gør at planterne smager lidt af biokul"}, "B");
        Question q4 = new Question("Hvad er den primære fordel ved at bruge en såmaskine i landbruget?", new String[]{"A: Landmanden sparer tid og kræfter", "B: Lavere forbrug af diesel", "C: Mindre vandforbrug"}, "A");
        Question q5 = new Question("Hvor meget mad går til spilde hvert år efter høsten på verdensplan?", new String[]{"A: 15%", "B: 20%", "C: 33%"}, "C");

        this.addQuestion(q1);
        this.addQuestion(q2);
        this.addQuestion(q3);
        this.addQuestion(q4);
        this.addQuestion(q5);
    }

    public boolean checkAnswer(int index, String answer) {
        return this.questions.get(index).getCorrectAnswer().equals(answer);
    }
}
