package DataLayer.dao;

import DataLayer.model.EnrolledCourse;
import DataLayer.model.Course;
import DataLayer.model.Student;
import DataLayer.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Boros on 3/26/2018.
 */
public class EnrolmentDAO {

    private static final String getSpecificStatementString = "SELECT * FROM utcn.`enrolments` WHERE userid = ?";
    private static final String insertStatementString = "INSERT INTO utcn.`enrolments` (userid,courseid,grade,date)" + "VALUES (?,?,?,?)";
    private static final Logger LOGGER = Logger.getLogger(EnrolmentDAO.class.getName());

    public void insertEnrolment(EnrolledCourse enrolledCourse,Student student){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try{
            insertStatement = dbConnection.prepareStatement(insertStatementString);
            insertStatement.setInt(1,student.getUserid());
            insertStatement.setInt(2,enrolledCourse.getCourse().getCourseid());
            insertStatement.setInt(3,enrolledCourse.getGrade());
            insertStatement.setDate(4,enrolledCourse.getDate());
            insertStatement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"EnrolmentDAO:insert " + e.getMessage());
        }finally{
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public ArrayList<EnrolledCourse> getEnrolledCoursesForStudent(Student student){

        ArrayList<EnrolledCourse> enrolledCourses = new ArrayList<EnrolledCourse>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement getAllEnrolledStatement = null;
        ResultSet resultSet = null;

        try{
            getAllEnrolledStatement = dbConnection.prepareStatement(getSpecificStatementString);
            getAllEnrolledStatement.setInt(1,student.getUserid());
            resultSet = getAllEnrolledStatement.executeQuery();
            while(resultSet.next()){
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.getSpecificCourse(resultSet.getInt("courseid"));
                EnrolledCourse enrolledCourse = new EnrolledCourse(course);
                enrolledCourse.setGrade(resultSet.getInt("grade"));
                enrolledCourse.setDate(resultSet.getDate("date"));

                enrolledCourses.add(enrolledCourse);
            }
        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"TeacherDAO:getAll" + e.getMessage());
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(getAllEnrolledStatement);
            ConnectionFactory.close(dbConnection);
        }
        return enrolledCourses;
    }

}
