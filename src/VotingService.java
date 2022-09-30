import java.util.*;

public class VotingService {
    private Question question;
    private List<Student> students;


    /**
     *
     * @param question current question
     * @param students List of students who will be answering the question
     */
    public VotingService(Question question, List<Student> students) {
        this.question = question;
        this.students = students;
    }

    /**
     *
     * @param studentId the student id who is answering the question
     * @param answer list of one answer if single, or multiple if isMultiple
     */
    public void submitAnswer(String studentId, List<String> answer) {
        for(Student student : students){
            if(student.getId().equals(studentId)){
                student.recordAnswer(answer);
            }
        }
    }

    /**
     *
     * @return frequency map of question options/answers
     */
    public Map<String, Integer> getStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        for(String option: question.getOptions()){
            stats.put(option, 0);
        }

        for(Student student: students) {
            for(String answer : student.getAnswers()){
                    stats.put(answer, stats.get(answer) + 1);
                }
            }

        return stats;
    }
}
