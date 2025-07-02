
class Pen {

    String color;
    String type;

    public void write(String color) {
        color = "black";
        System.out.println("writing with " + this.color + " " + this.type);
        System.out.println("writing with " + color + " " + type);
    }
}

class Student {

    String name;
    int age;

    // public void printname() {
    //     System.out.println(name);
    //     System.out.println(rollno);
    // }
    // //default contructor
    // // Student() {
    // //     System.out.println("contructor");
    // // }
    // Student(String name, int rollno) {
    //     this.name=name;
    //     this.rollno=rollno;
    //     System.out.println("meow");
    // }
    //POLYMORPHISM
    // public void StringInfo(String name, int age) {
    //     System.out.println(name + " " + age);
    //     System.out.println("method 1");
    // }
    // public void StringInfo(int age, String name) {
    //     System.out.println(age + " " + name);
    //     System.out.println("method 2");
    // }
}

// -------------------------------------------------------------------------------------
// //INHERITANCE
// class Shape {
//     public void area() {
//         System.out.println("Area is");
//     }
// }
// class Triangle extends Shape {
//     public void area(int l, int h) {
//         System.out.println("Area is " + (int) (0.5 * l * h));
//     }
// }
// class EquiTri extends Triangle {
//     public void area(int side) {
//         System.out.println("Area is " + (int) ((Math.sqrt(3) / 4) * side * side));
//     }
// }       
// -------------------------------------------------------------------------------------
//ABSTRACTION
abstract class Animals {

    abstract void walk();    
    // public void walk() {
    //     System.out.println("Walks on N legs");
    // }
}

class Horse extends Animals {

    public void walk() {
        System.out.println("Walks on 4 legs");
    }

}

class Chicken extends Animals {

    public void walk() {
        System.out.println("Walks on 2 legs");
    }

}

public class OOPS {

    public static void main(String[] args) {
        // Pen pen1 = new Pen(); //new lagane se mem heap ke andr ek jgh allocate ho jayegi, where the object gets stored
        // pen1.color = "red"; //jb class ke method ki value set ki jati hai to usse this. krke bulate hai, taaki agar function ke andr same name se koi aur variable ho to uski val call naho, jaise niche kra hai, write function me ek to color hai pen ke character me and ek hai jo func paramter se pass kr diya, dono different hai
        // pen1.type = "ball";
        // Pen pen2 = new Pen();
        // pen2.color = "blue";
        // pen2.type = "gell";

        // pen1.write("black");
        // pen2.write();
        // Student s1 = new Student("Naman", 123 ); //paramterized constructor
        // s1.name = "Naman";
        // -------------------------------------------------------------------------------------
        //POLYMORPHISM
        // Student s1 = new Student();
        // s1.name = "Naman";
        // s1.age = 21;
        // s1.StringInfo(s1.name, s1.age);
        // s1.StringInfo(s1.age, s1.name);
        // -------------------------------------------------------------------------------------
        //INHERITANCE
        // EquiTri tri1 = new EquiTri();
        // Triangle tri2 = new EquiTri();
        // tri1.area(4);
        // tri1.area(4, 5);
        // tri2.area(4, 3);
        // ((EquiTri) tri2).area(4);
        // -------------------------------------------------------------------------------------
        //ABSTRACTION
        Horse horse = new Horse();
        horse.walk();
    }
}
