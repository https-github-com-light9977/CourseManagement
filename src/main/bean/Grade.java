package main.bean;

import javax.servlet.http.Part;

public class Grade {
    String stuid;
    String stuname;
    String hwid;
    String grade;
    String text;
    Part part;

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setStuid(String stuid){
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
    public void setHwid( String hwid){
        this.hwid = hwid;
    }
    public String getHwid(){
        return hwid;
    }
    public void setGrade( String grade){
        this.grade = grade;
    }
    public String getGrade(){
        return grade;
    }

}
