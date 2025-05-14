package homework.work4_Json;

public class Space {
    private int id;
    private String name;
    public Space(){
    }
    public Space(int id,String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Space{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
