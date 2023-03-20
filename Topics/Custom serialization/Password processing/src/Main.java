import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class UserProfile implements Serializable {
    private static final long serialVersionUID = 26292552485L;

    private String login;
    private String email;
    private transient String password;

    public UserProfile(String login, String email, String password) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    private void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();
        String encryptPassword = encrypt(password);
        oos.writeObject(encryptPassword);
    }

    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
        password = decrypt((String) ois.readObject());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    private String encrypt(String password) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            str.append((char) ((int) c + 1));
        }
        return str.toString();
    }

    private String decrypt(String password) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            str.append((char) ((int) c - 1));
        }
        return str.toString();
    }
}