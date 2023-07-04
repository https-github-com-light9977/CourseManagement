import java.sql.ResultSet;

public class T_Course {
    ResultSet courseRes = null;
    public void setCourseRes( ResultSet resultSet){
        this.courseRes = courseRes;
    }
    public ResultSet getCourseRes(){
        return courseRes;
    }
}
