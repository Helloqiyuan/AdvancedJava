package voteSystem;

import java.io.Serial;
import java.io.Serializable;
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String ID;
    private String password;
    private int voteNumbers = 2;

    public User(String name, String ID, String password) {
        this.name = name;
        this.ID = ID;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVoteNumbers() {
        return voteNumbers;
    }

    public void setVoteNumbers(int voteNumbers) {
        this.voteNumbers = voteNumbers;
    }

    @Override
    public String toString(){
        return "{用户:" + name + ",学号:" + ID + ",voteNum:" + voteNumbers +  "}";
    }
    @Override
    public boolean equals(Object x){
        if(x == null){
            return false;
        }
        if(this.getClass() != x.getClass()){
            return false;
        }
        if(this == x) {
            return true;
        }
        User u = (User) x;
        return name.equals(u.name) && ID.equals(u.ID) && password.equals(u.password);
    }

    @Override
    public int hashCode() {
        return (name + ID + password).hashCode();
    }
}
