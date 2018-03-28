package DataLayer.dao;

import DataLayer.model.Student;
import DataLayer.connection.ConnectionFactory;
import DataLayer.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Boros on 3/26/2018.
 */
public class StudentDAO {

    private static final String getAllStatementString = "SELECT * FROM utcn.`students`";
    private static final String getSpecificStatementString = "SELECT * FROM utcn.`students` WHERE userid = ?";
    private static final String updateStatementString = "UPDATE utcn.`students` SET name=?,icnumber=?,cnp=?,address=?,identificationnumber=?,`group`=? WHERE userid=?";
    private static final String deleteStatementString = "DELETE FROM utcn.`students` WHERE userid=?";
    private static final String insertStatementString = "INSERT INTO utcn.`students` (userid,name,icnumber,cnp,address,identificationnumber,`group`)VALUES (?,?,?,?,?,?,?)";
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    public void insertStudent(Student student){

        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try{
            insertStatement = dbConnection.prepareStatement(insertStatementString);
            insertStatement.setInt(1,student.getUserid());
            insertStatement.setString(2,student.getName());
            insertStatement.setString(3,student.getIcnumber());
            insertStatement.setString(4,student.getCnp());
            insertStatement.setString(5,student.getAddress());
            insertStatement.setString(6,student.getIdentificationnumber());
            insertStatement.setString(7,student.getGroup());
            insertStatement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"StudentDAO:insert " + e.getMessage());
        }finally{
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void deleteStudent(Student student){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, student.getUserid());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StudentDAO:deleteStudent " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void updateStudent(Student student){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try{
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setString(1,student.getName());
            updateStatement.setString(2,student.getIcnumber());
            updateStatement.setString(3,student.getCnp());
            updateStatement.setString(4,student.getAddress());
            updateStatement.setString(5,student.getIdentificationnumber());
            updateStatement.setString(6,student.getGroup());
            updateStatement.setInt(7,student.getUserid());
            updateStatement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"StudentDAO:updateStudent " + e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public ArrayList<Student> getAllStudents(){

        ArrayList<Student> students = new ArrayList<Student>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement getAllStatement = null;
        ResultSet resultSet = null;

        try{
            getAllStatement = dbConnection.prepareStatement(getAllStatementString);
            resultSet = getAllStatement.executeQuery();
            while(resultSet.next()){
                Student student = new Student(resultSet.getInt("userid"));
                student.setName(resultSet.getString("name"));
                student.setIcnumber(resultSet.getString("icnumber"));
                student.setCnp(resultSet.getString("cnp"));
                student.setIdentificationnumber(resultSet.getString("identificationnumber"));
                student.setAddress(resultSet.getString("address"));
                student.setGroup(resultSet.getString("group"));

                students.add(student);
            }
        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"StudentDAO:getAll" + e.getMessage());
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(getAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return students;
    }

    public Student getSpecificStudent(User user){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement getSpecificStatement = null;
        ResultSet resultSet = null;
        Student foundstudent;
        try {
            getSpecificStatement = dbConnection.prepareStatement(getSpecificStatementString);
            getSpecificStatement.setInt(1, user.getUserid());
            resultSet = getSpecificStatement.executeQuery();

            if (resultSet.next()) {
                foundstudent = new Student();
                foundstudent.setUserid(user.getUserid());
                foundstudent.setUsername(user.getUsername());
                foundstudent.setPassword(user.getPassword());
                foundstudent.setUsertype(user.getUsertype());
                foundstudent.setName(resultSet.getString("name"));
                foundstudent.setIcnumber(resultSet.getString("icnumber"));
                foundstudent.setCnp(resultSet.getString("cnp"));
                foundstudent.setAddress(resultSet.getString("address"));
                foundstudent.setIdentificationnumber(resultSet.getString("identificationnumber"));
                foundstudent.setGroup(resultSet.getString("group"));
            }
            else
                foundstudent = null;
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"StudentDAO:getSpecific " + e.getMessage());
            foundstudent = null;
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(getSpecificStatement);
            ConnectionFactory.close(dbConnection);
        }
        return foundstudent;
    }
}
