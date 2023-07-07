package bean;

import java.sql.ResultSet;

public class THomework {
    String hwid;
    String hw_requirement;
    String deadline;
    public void setHwid( String hwid){
        this.hwid = hwid;
    }
    public String getHwid() {
        return hwid;
    }
    public void setHw_requirement( String hw_requirement){
        this.hw_requirement = hw_requirement;
    }
    public String getHw_requirement() {
        return hw_requirement;
    }

    public void setDeadline( String deadline){
        this.deadline = deadline;
    }
    public String getDeadline() {
        return deadline;
    }
}
