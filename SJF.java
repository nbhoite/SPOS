import java.util.Arrays;
import java.util.Comparator;
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

public class SJF {
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

        // Sort processes by arrival time
        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

        // Calculate waiting time and turnaround time
        int currentTime = 0;
        int completed = 0;
        boolean[] isCompleted = new boolean[n];

        while (completed < n) {
            int idx = -1;
            int minBurstTime = Integer.MAX_VALUE;

            // Find the process with the smallest burst time that has arrived
            for (int i = 0; i < n; i++) {
                if (processes[i].arrivalTime <= currentTime && !isCompleted[i] && processes[i].burstTime < minBurstTime) {
                    minBurstTime = processes[i].burstTime;
                    idx = i;
                }
            }

            // If no process is available, increment current time
            if (idx == -1) {
                currentTime++;
            } else {
                // Update waiting and turnaround time for the selected process
                processes[idx].waitingTime = currentTime - processes[idx].arrivalTime;
                processes[idx].turnaroundTime = processes[idx].waitingTime + processes[idx].burstTime;
                currentTime += processes[idx].burstTime;
                isCompleted[idx] = true;
                completed++;
            }
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
