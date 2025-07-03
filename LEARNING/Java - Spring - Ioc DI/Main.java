//Engine class - represents a car's engine

class Engine {

    // Engine start karne ka method
    public void start() {
        System.out.println("Engine started...");  // Jab ye method chalega to engine start ka message print hoga
    }
}

//MusicSystem class - represents the car's music system
class MusicSystem {

    // Music play karne ka method
    public void play() {
        System.out.println("Music system playing");  // Jab ye method chalega to music playing ka message aayega
    }
}

//Car class - Car ke andar Engine aur MusicSystem dono chahiye hote hain
class Car {

    // Car ke andar ke 2 components (dependencies)
    private Engine engine;             // Engine object
    private MusicSystem musicSystem;   // MusicSystem object

    //Constructor - yahan hum manually Engine aur MusicSystem inject kar rahe hain
    Car(Engine engine, MusicSystem musicSystem) {
        this.engine = engine;               // Injected engine assign kar diya class ke variable me
        this.musicSystem = musicSystem;     // Injected musicSystem assign kar diya
    }

    //drive() method - car chalane ka process yahan define hai
    public void drive() {
        engine.start();           // Pehle engine start hoga
        musicSystem.play();       // Fir music system play karega
        System.out.println("Car is driving");  // Aur finally car chal rahi hai ye message aayega
    }
}

//Main class - Java ka entry point (yahin se program start hota hai)
public class Main {

    //main method - program ka starting point
    public static void main(String[] args) {
        // Step 1: Engine ka object banaya
        Engine engine = new Engine();

        // Step 2: MusicSystem ka object banaya
        MusicSystem musicSystem = new MusicSystem();

        // Step 3: Car ka object banaya aur engine + musicSystem inject kiya constructor me
        Car car = new Car(engine, musicSystem);

        //Step 4: Car ka drive method call kiya
        car.drive();  // Output me: Engine started → Music system playing → Car is driving
    }
}
