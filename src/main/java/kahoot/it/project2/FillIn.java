package kahoot.it.project2;



public class FillIn extends Question {
    String description;
    String correctAnswer;
    @Override
    public void setAnswer(String correctAnswer) {
        this.correctAnswer=correctAnswer;
    }
    @Override
    public void setDescription(String description){
        this.description=description;
    }
    @Override
    public String getDescription(){
        return description;
    }
    @Override
    public String getAnswer(){return correctAnswer;}
    @Override
    public String toString() {
        return getDescription().replace("?","");
    }
}
