import java.util.*;

class BullyProcess {
    int id;
    boolean isActive;

    BullyProcess(int id) {
        this.id = id;
        this.isActive = true;
    }
}

public class BullyAlgorithm {
    static Scanner sc = new Scanner(System.in);
    static BullyProcess[] processes;
    static int n;

    static void startElection(int initiatorId) {
        System.out.println("\nElection initiated by process " + initiatorId);
        boolean coordinatorElected = false;

        for (int i = initiatorId + 1; i < n; i++) {
            if (processes[i].isActive) {
                System.out.println("Process " + processes[i].id + " responded OK.");
                startElection(i); // Recursively let higher process continue the election
                return;
            }
        }

        // If no higher process is active, this one becomes coordinator
        System.out.println("Process " + processes[initiatorId].id + " becomes the Coordinator.");
        coordinatorElected = true;
    }

    public static void main(String[] args) {
        System.out.print("Enter number of processes: ");
        n = sc.nextInt();
        processes = new BullyProcess[n];

        System.out.println("Enter process IDs:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + i + " ID: ");
            int id = sc.nextInt();
            processes[i] = new BullyProcess(id);
        }

        Arrays.sort(processes, Comparator.comparingInt(p -> p.id));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Start Election");
            System.out.println("2. Crash Process");
            System.out.println("3. Recover Process");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter initiator index (0 to " + (n - 1) + "): ");
                    int initiator = sc.nextInt();
                    if (processes[initiator].isActive)
                        startElection(initiator);
                    else
                        System.out.println("Process is inactive!");
                    break;

                case 2:
                    System.out.print("Enter process index to crash: ");
                    int crash = sc.nextInt();
                    processes[crash].isActive = false;
                    System.out.println("Process " + processes[crash].id + " crashed.");
                    break;

                case 3:
                    System.out.print("Enter process index to recover: ");
                    int recover = sc.nextInt();
                    processes[recover].isActive = true;
                    System.out.println("Process " + processes[recover].id + " recovered.");
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}
