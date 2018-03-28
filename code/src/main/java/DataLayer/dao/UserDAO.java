package DataLayer.dao;

import DataLayer.model.Student;
import DataLayer.model.Teacher;
import DataLayer.model.User;
import DataLayer.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Boros on 3/27/2018.
 */
public class UserDAO {

    private static final String getAllStatementString = "SELECT * FROM utcn.`users`";
    private static final String getLoginStatementString = "SELECT * FROM utcn.`users` WHERE username = ? and userpassword =?";
    private static final String getSpecificStatementString = "SELECT * FROM utcn.`users` WHERE userid = ?";
    private static final String updateStatementString = "UPDATE utcn.`users` SET username=?,userpassword=?,usertype=?";
    private static final String deleteStatementString = "DELETE FROM utcn.`users` WHERE userid=?";
    private static final String insertStatementString = "INSERT INTO utcn.`users` (username,userpassword,usertype)" + "VALUES (?,?,?)";
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    public int insertUser(User user){

        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedid = -1;
        try{
            insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1,user.getName());
            insertStatement.setString(2,user.getPassword());
            insertStatement.setInt(3,user.getUsertype());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if(rs.next()){
                insertedid = rs.getInt(1);
            }

        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"UserDAO:insert " + e.getMessage());
        }finally{
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedid;
    }

    public void deletUser(User user){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, user.getUserid());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:delete " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public User getSpecificUser(int userid){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement getSpecificStatement = null;
        ResultSet resultSet = null;
        User founduser = null;
        try{
            getSpecificStatement = dbConnection.prepareStatement(getSpecificStatementString);
            getSpecificStatement.setInt(1,userid);
            resultSet = getSpecificStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getInt("usertype")==0)
                {
                    founduser = new Teacher();
                    founduser.setUsertype(resultSet.getInt("usertype"));
                }
                else if(resultSet.getInt("usertype")!=0)
                {
                    founduser = new Student();
                    founduser.setUsertype(resultSet.getInt("usertype"));
                }
                founduser.setUserid(userid);
                founduser.setUsername(resultSet.getString("username"));
                founduser.setPassword(resultSet.getString("password"));
            }
            else
            {
                founduser = null;
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"CourseDAO:getSpecific" + e.getMessage());
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(getSpecificStatement);
            ConnectionFactory.close(dbConnection);
        }
        return founduser;
    }

    public User getLoginUser(User user){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement getSpecificStatement = null;
        ResultSet resultSet = null;
        User loginuser = null;
        try{
            getSpecificStatement = dbConnection.prepareStatement(getLoginStatementString);
            getSpecificStatement.setString(1,user.getUsername());
            getSpecificStatement.setString(1,user.getPassword());
            resultSet = getSpecificStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getInt("usertype")==0)
                {
                    loginuser = new Teacher();
                }
                else if(resultSet.getInt("usertype")!=0)
                {
                    loginuser = new Student();
                }
                loginuser.setUserid(resultSet.getInt("userid"));
                loginuser.setUsertype(resultSet.getInt("usertype"));
                loginuser.setUsername(user.getUsername());
                loginuser.setPassword(user.getPassword());
            }
            else
            {
                loginuser = null;
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"CourseDAO:getLogin" + e.getMessage());
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(getSpecificStatement);
            ConnectionFactory.close(dbConnection);
        }
        return loginuser;
    }

    /*public ArrayList<Student> getAllStudents(){

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
                student.setAddress(resultSet.getString("identificationnumber"));
                student.setAddress(resultSet.getString("address"));
                student.setAddress(resultSet.getString("group"));

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

    public void updateUser(User user){

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try{
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setString(1,user.getUsername());
            updateStatement.setString(2,user.getPassword());
            updateStatement.setString(3,user.getUsertype());
            updateStatement.setInt(4,user.getUserid());
            updateStatement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING,"UserDAO:updateStudent " + e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    */
}
