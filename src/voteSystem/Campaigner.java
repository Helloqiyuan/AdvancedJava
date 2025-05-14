package voteSystem;

import java.io.Serial;
import java.io.Serializable;

public class Campaigner implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    //姓名
    private String name;
    //学分
    private double xf;
    //竞选宣言
    private String jxxf;
    //得票数
    private int VoteCount;

    public Campaigner(String name, double xf, String jxxf) {
        this.name = name;
        this.xf = xf;
        this.jxxf = jxxf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getVoteCount() {
        return VoteCount;
    }

    public void setVoteCount(int voteCount) {
        this.VoteCount = voteCount;
    }

    @Override
    public String toString() {
        return  name + "\t\t" + xf + "\t\t" + jxxf;
    }

    public void incrementVote(){
        VoteCount ++;
    }
}
