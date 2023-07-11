package user.teacher.bean;

public class ManageStu {
    String stuid;
    String stuname;
    String checkin;
    String checkinGrade;
    String hwGrade;

    public void setStuid( String stuid){
        this.stuid = stuid;
    }
    public String getStuid() {
        return stuid;
    }
    public void setStudent_name( String stuname){
        this.stuname = stuname;
    }
    public String getStuname() {
        return stuname;
    }

    public void setCheckin( String checkin){
        this.checkin = checkin;
    }
    public String getCheckin() {
        return checkin;
    }

    public void setCheckinGrade( String checkinGrade){
        this.checkinGrade = checkinGrade;
    }
    public String getCheckinGrade() {
        return checkinGrade;
    }

    public void setHwGrade( String hwGrade){
        this.hwGrade = hwGrade;
    }
    public String getHwGrade() {
        return hwGrade;
    }
}
