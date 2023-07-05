package bean;

public class Login {
    String logid="",
            password = "",
            name ="",
            backNews="";
    public Login(){}
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

}

