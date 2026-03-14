import java.util.Random;

// single-runway airport simulation
public class Sim {

    public static void main(String[] args) {

        if (args.length < 5) {
            System.out.println("Usage: java Sim landingRate takeoffRate landingTime takeoffTime simTime");
            return;
        }

        int landRate = Integer.parseInt(args[0]); // percent chance of landing arrival per tick
        int takeRate = Integer.parseInt(args[1]); // percent chance of takeoff arrival per tick
        int landTime = Integer.parseInt(args[2]); // ticks to complete a landing
        int takeTime = Integer.parseInt(args[3]); // ticks to complete a takeoff
        int simTime  = Integer.parseInt(args[4]); // total ticks to simulate

        LinkedQueue<Airplane> landQ = new LinkedQueue<>();
        LinkedQueue<Airplane> takeQ = new LinkedQueue<>();

        Random rand = new Random();

        int runway = 0; // ticks remaining on current runway operation

        int landWait = 0, takeWait = 0;
        int landed = 0, tookOff = 0;
        long landQLen = 0, takeQLen = 0;

        // each iteration is one 5-minute tick
        for (int clock = 0; clock < simTime; clock++) {

            // generate random arrivals
            if (rand.nextInt(100) < landRate) landQ.enqueue(new Airplane(clock));
            if (rand.nextInt(100) < takeRate) takeQ.enqueue(new Airplane(clock));

            // accumulate queue lengths for averaging
            landQLen += landQ.size();
            takeQLen += takeQ.size();

            // tick down runway, then serve next plane if free
            if (runway > 0) runway--;

            if (runway == 0) {
                if (!landQ.isEmpty()) {               // landing has priority
                    Airplane p = landQ.dequeue();
                    landWait += clock - p.getTime();
                    landed++;
                    runway = landTime - 1;
                } else if (!takeQ.isEmpty()) {
                    Airplane p = takeQ.dequeue();
                    takeWait += clock - p.getTime();
                    tookOff++;
                    runway = takeTime - 1;
                }
            }
        }

        System.out.println("Simulation Finished");
        System.out.println("Total landings: " + landed);
        System.out.println("Total takeoffs: " + tookOff);
        if (landed  > 0) System.out.println("Average landing wait: " + (double) landWait / landed);
        if (tookOff > 0) System.out.println("Average takeoff wait: " + (double) takeWait / tookOff);
        System.out.println("Average landing queue length: " + (double) landQLen / simTime);
        System.out.println("Average takeoff queue length: " + (double) takeQLen / simTime);
    }
}