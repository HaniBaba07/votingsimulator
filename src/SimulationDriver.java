import java.util.*;

public class SimulationDriver {
    public static void main(String[] args) {
        Random random = new Random();
        // Configure question type/choices here, currently random type with 5 options
        Question question = new Question(List.of("Right", "Wrong"), false);

        // gets a random number of students, can be configured by passing in max
        List<Student> students = generateRandomNumberOfStudents(50);

        System.out.println("There will be " + students.size() + " student(s) responding to this poll.");
        System.out.println();

        String type = question.isMultiple() ? "Multiple Choice" : "Single Choice";
        System.out.println("The students will be responding to a " + type + " Question");
        System.out.println();

        System.out.println("Students will have " +  question.getOptions().size() + " answers to select from");
        System.out.println();
        printOptions(question);

        System.out.println();
        VotingService service = new VotingService(question, students);
        System.out.println();
        System.out.println("(Collecting initial answers...)");

        submitAnswers(question, students, service);

        System.out.println();

        System.out.println("Final call for resubmissions...");
        System.out.println();
        List<Student> resubmissions = getRandomStudentsForResubmission(random, students);
        submitAnswers(question, resubmissions, service);

        System.out.println("(Answers were resubmitted by students " + resubmissions.size() + " time(s).)");
        System.out.println();

        System.out.println("Final Answer Totals");
        printStatistics(service);

    }

    /**
     * Prints the current statistics recorded in the voting service
     * @param service the voting service
     */
    private static void printStatistics(VotingService service) {
        Map<String, Integer> statistics = service.getStatistics();
        for(Map.Entry<String, Integer> entry : statistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     *
     * @param random - Random instnace for reuse
     * @param students List of students who already answered
     * @return random sublist of shuffled students
     */
    private static List<Student> getRandomStudentsForResubmission(Random random, List<Student> students) {
        Collections.shuffle(students);
        List<Student> resubmissions = students.subList(0, random.nextInt(students.size()));
        return resubmissions;
    }

    /**
     *
     * @param question - The current question being answered
     * @param students - The list of students who are answering the question
     * @param service - The current configured voting service
     */
    private static void submitAnswers(Question question, List<Student> students, VotingService service) {
        for(Student student : students){
            service.submitAnswer(student.getId(), getRandomAnswer(question));
        }
    }

    /**
     * Prints the current question options
     * @param question The current question being answered
     */
    private static void printOptions(Question question) {
        for(String option : question.getOptions()){
            System.out.print(option + " ");
        }
    }

    /**
     *
     * @param max - the maximum amount of students
     * @return List of random students
     */
    private static List<Student> generateRandomNumberOfStudents(int max) {
        Random random = new Random();
        Set<String> generatedIds = new HashSet<>();
        int numberOfStudents = random.nextInt(max) + 1;
        List<Student> students = new ArrayList<>();
        for(int i = 0; i < numberOfStudents; i++) {
            String id = getRandomString();
            while(generatedIds.contains(id)){
                id = getRandomString();
            }
            generatedIds.add(id);
            students.add(new Student(id));
        }
        return students;
    }

    /**
     * Used to generate a unique student ID
     * @return a unique 9 letter String
     */
    private static String getRandomString() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        String id = "";
        for(int i = 0; i < 9; i++) {
            id += letters.charAt(random.nextInt(letters.length()));
        }
        return id;
    }

    /**
     *
     * @param question to generate answers for
     * @return answers to the question, one answer if single, random multiple if isMultiple
     */
    private static List<String> getRandomAnswer(Question question){
        Random random = new Random();
        int numberOfOptions = question.getOptions().size();
        if(!question.isMultiple()){
            int option = random.nextInt(numberOfOptions);
            return List.of(question.getOptions().get(option));
        }
        int chosenOptions = random.nextInt(numberOfOptions) + 1;
        List<String> answers = new ArrayList<>();
        Set<Integer> pickedOptions = new HashSet<>();
        while(pickedOptions.size() < chosenOptions) {
            pickedOptions.add(random.nextInt(numberOfOptions));
        }

        for(int option : pickedOptions){
            answers.add(question.getOptions().get(option));
        }

        return answers;
    }

}
