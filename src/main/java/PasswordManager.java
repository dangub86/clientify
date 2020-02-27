
public class PasswordManager
{
    private String password;

    public PasswordManager()
    {

    }

    public PasswordManager(String password)
    {
        this.password= AESAlgorithm.decrypt(password);
        //this.password=password;
    }

    public String getPassword()
    {
        return password;
    }


}
