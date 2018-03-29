package DataLayer.dao;

import DataLayer.model.Teacher;
import DataLayer.connection.ConnectionFactory;
import DataLayer.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Boros on 3/26/2018.
 */
public class TeacherDAO {

    private static final String getAllStatementString = "SELECT * FROM utcn.`teachers`";
    private static final String getSpecificStatementString = "SELECT * FROM utcn.`teachers` WHERE userid = ?";
    private static final String updateStatementString = "UPDATE utcn.`teachers` SET name=?,icnumber=?,cnp=?,address=? WHERE userid=?";
    private static final String deleteStatementString = "DELETE FROM utcn.`teachers` WHERE userid=?";
    private static final String insertStatementString = "INSERT INTO utcn.`teachers` (userid,name,icnumber,cnp,address" + "VALUES (?,?,?,?,?)";
    private static final Logger LOGGER = Logger.getLogger(TeacherDAO.class.getName());

    public void insertTeacher(Teacher teacher){

        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try{
            insertStatement = dbConnection.prepareStatement(insertStatementString);
            insertStatement.setInt(1,teacher.getUserid());
            insertStatement.setString(2,teacher.getName());
            insertStatement.setString(3,teacher.getIcnumber());
            insertStatement.setString(4,teacher.getCnp());
            insertStatement.setString(5,teacher.getAddress());
            insertStatement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"TeacherDAO:insert " + e.getMessage());
        }finally{
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void deleteTeacher(Teacher teacher){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, teacher.getUserid());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "TeacherDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void updateTeacher(Teacher teacher){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try{
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setString(1,teacher.getName());
            updateStatement.setString(2,teacher.getIcnumber());
            updateStatement.setString(3,teacher.getCnp());
            updateStatement.setString(4,teacher.getAddress());
            updateStatement.setInt(5,teacher.getUserid());
            updateStatement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"TeacherDAO:updateTeacher " + e.getMessage());
            new IllegalArgumentException(e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public ArrayList<Teacher> getAllTeachers(){

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement getAllStatement = null;
        ResultSet resultSet = null;

        try{
            getAllStatement = dbConnection.prepareStatement(getAllStatementString);
            resultSet = getAllStatement.executeQuery();
            while(resultSet.next()){
                Teacher teacher = new Teacher(resultSet.getInt("userid"));
                teacher.setName(resultSet.getString("name"));
                teacher.setIcnumber(resultSet.getString("icnumber"));
                teacher.setCnp(resultSet.getString("cnp"));
                teacher.setAddress(resultSet.getString("address"));

                teachers.add(teacher);
            }
        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"TeacherDAO:getAll" + e.getMessage());
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(getAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return teachers;
    }

    public Teacher getSpecificTeacher(User user){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement getSpecificStatement = null;
        ResultSet resultSet = null;
        Teacher foundTeacher;
        try {
            getSpecificStatement = dbConnection.prepareStatement(getSpecificStatementString);
            getSpecificStatement.setInt(1, user.getUserid());
            resultSet = getSpecificStatement.executeQuery();

            if (resultSet.next()) {
                foundTeacher = new Teacher();
                foundTeacher.setUserid(user.getUserid());
                foundTeacher.setUsername(user.getUsername());
                foundTeacher.setPassword(user.getPassword());
                foundTeacher.setUsertype(user.getUsertype());
                foundTeacher.setName(resultSet.getString("name"));
                foundTeacher.setIcnumber(resultSet.getString("icnumber"));
                foundTeacher.setCnp(resultSet.getString("cnp"));
                foundTeacher.setAddress(resultSet.getString("address"));
            }
            else
                foundTeacher = null;
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"TeacherDAO:getSpecific " + e.getMessage());
            foundTeacher = null;
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(getSpecificStatement);
            ConnectionFactory.close(dbConnection);
        }
        return foundTeacher;
    }

}
