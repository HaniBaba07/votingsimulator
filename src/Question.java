import java.util.List;

public class Question {
    private boolean isMultiple;
    private List<String> options;

    /**
     *
     * @param options
     * @param isMultiple
     */
    public Question(List<String> options, boolean isMultiple) {
        this.options = options;
        this.isMultiple = isMultiple;
    }

    /**
     *
     * @return the questions options
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     *
     * @return if the question should accept multiple answers
     */
    public boolean isMultiple() {
        return isMultiple;
    }
}
