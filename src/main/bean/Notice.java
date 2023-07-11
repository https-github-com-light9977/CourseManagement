package main.bean;

public class Notice {
    String noticeid;
    String content;
    String time;
    String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setNoticeid(String noticeid){
        this.noticeid = noticeid;
    }
    public String getNoticeid() {
        return noticeid;
    }
    public void setContent( String content){
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setTime( String time){
        this.time = time;
    }
    public String getTime() {
        return time;
    }
}
