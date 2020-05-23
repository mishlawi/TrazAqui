package Model;
import java.io.Serializable;


public class LoginErradoException extends Exception
{

    public LoginErradoException(){
        super();
    }
    public LoginErradoException(String message){
        super(message);
    }


}
