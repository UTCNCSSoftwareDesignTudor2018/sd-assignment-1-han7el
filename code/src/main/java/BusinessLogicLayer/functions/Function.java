package BusinessLogicLayer.functions;

/**
 * Created by Boros on 3/27/2018.
 */
public class Function {

    //comparison types
    public static final String STRONG = "STRONG";
    public static final String WEAK = "WEAK";

    //for user
    public static final int ICN_LENGTH = 6;
    public static final int CNP_LENGTH = 13;
    public static final int ADDRESS_NAME_LENGTH = 50;

    //for student
    public static final int IN_LENGTH = 8;
    public static final int GN_LENGTH = 5;

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static boolean isWellFormed(String str,int length,String type)
    {
        if(type.equals(STRONG))
            return length==str.length();
        if(type.equals(WEAK))
            return length>=str.length();

        else
            return false;
    }
}
