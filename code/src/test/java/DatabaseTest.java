import BusinessLogicLayer.bll.StudentBLL;
import BusinessLogicLayer.functions.Function;
import DataLayer.model.Student;

/**
 * Created by Boros on 3/28/2018.
 */
public class DatabaseTest {

    public static void main(String[] args) {

        testStudentInsert();
    }

    public static void testStudentInsert(){
        Student student = new Student();
        student.setUsername("test");
        student.setPassword("123");
        student.setName("Test students");
        student.setUsertype(1);
        student.setGroup("30432");
        student.setIdentificationnumber("12345678");
        student.setAddress("beius");
        student.setCnp("1234567891111");
        student.setIcnumber("123456");

        StudentBLL test = new StudentBLL();
        test.insertStudent(student);
        try{
            Student verifystudent = test.getSpecificStudent(student);
            assert verifystudent.getUserid()==student.getUserid();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }


    }
}
