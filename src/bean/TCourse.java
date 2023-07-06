package bean;

import java.sql.ResultSet;

public class TCourse {
    String courseName;
    String courseTime;
    String classId;

    //    ResultSet courseRes = null;
    public void setCourseName( String courseName){
        this.courseName = courseName;
    }
    public String getCourseName(){
        return courseName;
    }
    public void setCourseTime( String courseTime){
        this.courseTime = courseTime;
    }
    public String getCourseTime(){
        return courseTime;
    }
    public void setClassid( String classeId){
        this.classId = classeId;
    }
    public String getClasseId(){
        return classId;
    }
}

