class Breathe {
    String start() {
        return "is breathing";
    }
}
class Human {
    Breathe breathe;
    void setBreathe(Breathe breathe) {
        this.breathe = breathe;
    }
    void live() {
        System.out.println("Human "+breathe.start());
    }
}
public class SetterMain {
    public static void main(String[] args) {
        Human human = new Human();
        human.setBreathe(new Breathe());
        human.live();
    }
}