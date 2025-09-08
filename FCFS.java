import java.util.Scanner;

class Process {
    int id; // Process ID
    int arrivalTime; // Arrival time of the process
    int burstTime; // Burst time of the process
    int waitingTime; // Waiting time of the process
    int turnaroundTime; // Turnaround time of the process

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class FCFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();

        Process[] processes = new Process[n];

        // Input process details
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time of process " + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Enter burst time of process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        // Calculate waiting time and turnaround time
        int currentTime = 0;
        for (Process process : processes) {
            if (currentTime < process.arrivalTime) {
                currentTime = process.arrivalTime; // Wait for the process to arrive
            }
            process.waitingTime = currentTime - process.arrivalTime;
            currentTime += process.burstTime;
            process.turnaroundTime = process.waitingTime + process.burstTime;
        }

        // Print results
        System.out.println("\nProcess ID\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Process process : processes) {
            System.out.printf("%-12d\t%-12d\t%-12d\t%-12d\t%-12d\n",
                    process.id,
                    process.arrivalTime,
                    process.burstTime,
                    process.waitingTime,
                    process.turnaroundTime);
        }

        scanner.close();
    }
}
