package bean;

public class HwSubmitList {
    String Student_id;
    String Student_name;
    String grade;
    String submitTime;
    String Homework_id;

    public void setStudent_id( String Student_id){
        this.Student_id = Student_id;
    }
    public String getStudent_id() {
        return Student_id;
    }
    public void setStudent_name( String Student_name){
        this.Student_name = Student_name;
    }
    public String getStudent_name() {
        return Student_name;
    }

    public void setGrade( String grade){
        this.grade = grade;
    }
    public String getGrade() {
        return grade;
    }

    public void setHomework_id( String Homework_id){
        this.Homework_id = Homework_id;
    }
    public String getHomework_id() {
        return Homework_id;
    }

    public void setSubmitTime( String submitTime){
        this.submitTime = submitTime;
    }
    public String getSubmitTime() {
        return submitTime;
    }
}
