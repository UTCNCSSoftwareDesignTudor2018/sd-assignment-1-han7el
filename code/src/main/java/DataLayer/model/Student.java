package DataLayer.model;

/**
 * Created by Boros on 3/26/2018.
 */
public class Student extends User{

    private String identificationnumber;
    private String group;

    public Student(){

    }

    public Student(int userid){
        super(userid);
    }

    public String getIdentificationnumber() {
        return identificationnumber;
    }

    public void setIdentificationnumber(String identificationnumber) {
        this.identificationnumber = identificationnumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String toString(){
        return this.getName() + "; " + "Identification number: " +
                this.getIdentificationnumber() + "; " + "group: " +
                this.getGroup() + "; " + "identity card number: " +
                this.getIcnumber() + "; " + "CNP: " +
                this.getCnp() + "; " + "address " +
                this.getAddress() + "\n";
    }
}
