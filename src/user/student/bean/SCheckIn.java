package user.student.bean;

public class SCheckIn {
    String stuid;
    String stuname;
    String checkin_id;
    String checked;
    String checkin_deadline;

    public String getCheckin_deadline() {
        return checkin_deadline;
    }

    public void setCheckin_deadline(String checkin_deadline) {
        this.checkin_deadline = checkin_deadline;
    }
    public void setStuid( String stuid){
        this.stuid = stuid;
    }
    public String getStuid(){
        return stuid;
    }
    public void setStuname( String stuname){
        this.stuname = stuname;
    }
    public String getStuname(){
        return stuname;
    }
    public void setCheckin_id( String checkin_id){
        this.checkin_id = checkin_id;
    }
    public String getCheckin_id(){
        return checkin_id;
    }
    public void setChecked( String checked){
        this.checked = checked;
    }
    public String getChecked(){
        return checked;
    }

}
