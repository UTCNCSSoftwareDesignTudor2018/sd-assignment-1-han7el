import BusinessLogicLayer.bll.StudentBLL;
import BusinessLogicLayer.functions.Function;
import DataLayer.model.Student;

/**
 * Created by Boros on 3/28/2018.
 */
public class DatabaseTest {

    public static void main(String[] args) {

        Student student = new Student();
        student.setUsername("hanielb");
        student.setPassword("123");
        student.setName("Hanniel Boros");
        student.setUsertype(1);
        student.setGroup("30432");
        student.setIdentificationnumber("12345678");
        student.setAddress("beius");
        student.setCnp("1234567891111");
        student.setIcnumber("123456");
        //System.out.print(student.getCnp().length());
        StudentBLL test = new StudentBLL();
        test.insertStudent(student);
    }
}
