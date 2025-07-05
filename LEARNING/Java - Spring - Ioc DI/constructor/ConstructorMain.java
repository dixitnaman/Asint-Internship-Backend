class Breathe {
    String start() {
        return "is breathing";
    }
}
class Human {
    Breathe breathe;
    Human(Breathe breathe) {
        this.breathe = breathe;
    }
    void live() {
        System.out.println("Human " + breathe.start());
    }
}
public class ConstructorMain {
    public static void main(String[] args) {
        Human human = new Human(new Breathe());
        human.live();
    }
}
