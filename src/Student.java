import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;

    private List<String> answers;

    /**
     *
     * @param id
     */
    public Student(String id) {
        this.id = id;
        this.answers = new ArrayList<>();
    }

    /**
     *
     * @return Student id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return List of answers
     */
    public List<String> getAnswers() {
        return answers;
    }

    /**
     * Used to record a Students answer for the current question
     * @param answers Students answers
     */
    public void recordAnswer(List<String> answers) {
            this.answers = answers;
    }
}
