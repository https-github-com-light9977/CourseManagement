package main.bean;

import java.sql.ResultSet;

public class User {
    String logid="";
    String password = "";
    String name ="";
    String backNews="";

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    String role;
    ResultSet courseRes = null;

    public User(){}
    public void setLogid(String logid){
        this.logid = logid;
    }
    public String getLogid(){
        return logid;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setBackNews(String s) {
        backNews = s;
    }
    public String getBackNews(){
        return backNews;
    }

    public void setPassword(String password) { this.password = password;}
    public String getPassword(){
        return password;
    }
    public void setCourseRes( ResultSet resultSet){
        this.courseRes = courseRes;
    }
    public ResultSet getCourseRes(){
        return courseRes;
    }


}

