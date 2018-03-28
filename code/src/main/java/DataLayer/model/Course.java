package DataLayer.model;

/**
 * Created by Boros on 3/26/2018.
 */
public class Course {

    int courseid;
    String name;

    public Course(int courseid){
        this.courseid = courseid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public boolean equals(Object object){
        if(courseid == ((Course)object).getCourseid())
            return true;
        return false;
    }
}
