//Ahmed Tamer No.4 4TI
class Pupil extends Thread{
    int id;
    public Pupil (int id) {
        this.id = id;
    }

    public void run () {
        for (int i = 0 ; i < 7; i++){
            Main.mutex.acquire();
            if (Main.stock > 0) {
                Main.stock--;
                Main.served++;}
            
            else {Main.rejected++;}
            Main.mutex.release();

        }
    }
}    

public class Main {
    static int stock = 18;
    static int served = 0;
    static int rejected = 0;
    static  MySemaphore mutex = new MySemaphore (1);

    public static void main(String[] args) {
        Pupil p1 = new Pupil(1);
        Pupil p2 = new Pupil(2);
        Pupil p3 = new Pupil(3);

        p1.start();
        p2.start();
        p3.start();

        try {
            p1.join();
            p2.join();
            p3.join();}
            catch (InterruptedException e) {}
        System.out.println("Shots given:" + " " +served);
        System.out.println("Visits refused:" + " " +rejected);
        System.out.println("Shots left:" + " " +stock);
    }
}
