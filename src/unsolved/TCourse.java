package unsolved;

import java.sql.ResultSet;

public class TCourse {
    ResultSet courseRes = null;
    public void setCourseRes( ResultSet resultSet){
        this.courseRes = courseRes;
    }
    public ResultSet getCourseRes(){
        return courseRes;
    }
}
