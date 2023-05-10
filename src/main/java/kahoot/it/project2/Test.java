package kahoot.it.project2;

import java.util.ArrayList;
import java.util.Collections;

public class Test extends Question{
    String description;
    String correctAnswer;
    int idx;

    ArrayList<String> labels=new ArrayList<>();
    @Override
    public void setAnswer(String answer) {correctAnswer=answer;}
    @Override
    public void setDescription(String description) {this.description=description;}
    @Override
    public String getDescription() {return description;}
    public void setOptions(ArrayList<String> options){
        Collections.shuffle(options);
        this.labels=options;
    }
    public String getAnswer() {
        if (correctAnswer.equals(labels.get(0))) {
            return labels.get(0);
        } else if (correctAnswer.equals(labels.get(1))) {
            return labels.get(1);
        } else if (correctAnswer.equals(labels.get(2))) {
            return labels.get(2);
        } else {
            return labels.get(3);}
    }

    @Override
    public String toString(){return getDescription();}
}
