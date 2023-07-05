package bean;

public class Login {
    String logid="",
            name ="",
            backNews="登录失败";
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
}

