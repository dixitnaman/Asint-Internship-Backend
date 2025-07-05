class Breathe {
    String start() {
        return "is breathing";
    }
}
class Human {
    Breathe breathe;
    void live() {
        System.out.println("Human " + breathe.start());
    }
}
public class FieldMain {
    public static void main(String[] args) {
        Human human = new Human();
        human.breathe = new Breathe();
        human.live();
    }
}
