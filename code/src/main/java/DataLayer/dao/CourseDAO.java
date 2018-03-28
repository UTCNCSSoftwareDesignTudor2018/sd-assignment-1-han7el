package DataLayer.dao;

import DataLayer.model.Course;
import DataLayer.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Boros on 3/26/2018.
 */
public class CourseDAO {

    private static final String getAllStatementString = "SELECT * FROM utcn.`courses`";
    private static final String getSpecificStatementString = "SELECT * FROM utcn.`courses` WHERE courseid = ?";
    private static final Logger LOGGER = Logger.getLogger(CourseDAO.class.getName());

    public ArrayList<Course> getAllCourses(){

        ArrayList<Course> courses = new ArrayList<Course>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement getAllStatement = null;
        ResultSet resultSet = null;

        try{
            getAllStatement = dbConnection.prepareStatement(getAllStatementString);
            resultSet = getAllStatement.executeQuery();
            while(resultSet.next()){
                Course course = new Course(resultSet.getInt("courseid"));
                course.setName(resultSet.getString("name"));

                courses.add(course);
            }
        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"CourseDAO:getAll" + e.getMessage());
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(getAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return courses;
    }

    public Course getSpecificCourse(int courseid){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement getSpecificStatement = null;
        ResultSet resultSet = null;
        Course foundcourse;
        try{
            getSpecificStatement = dbConnection.prepareStatement(getSpecificStatementString);
            getSpecificStatement.setInt(1,courseid);
            resultSet = getSpecificStatement.executeQuery();
            if(resultSet.next()){
                foundcourse = new Course(resultSet.getInt("courseid"));
                foundcourse.setName(resultSet.getString("name"));
            }
            else
            {
                foundcourse = null;
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"CourseDAO:getSpecific" + e.getMessage());
            foundcourse = null;
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(getSpecificStatement);
            ConnectionFactory.close(dbConnection);
        }
        return foundcourse;
    }
}
