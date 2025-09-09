import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Process {
    int id;
    int burstTime;
    int priority;

    Process(int id, int burstTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numberOfProcesses = scanner.nextInt();
        
        Process[] processes = new Process[numberOfProcesses];

        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.print("Enter burst time for Process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();
            System.out.print("Enter priority for Process " + (i + 1) + ": ");
            int priority = scanner.nextInt();
            processes[i] = new Process(i + 1, burstTime, priority);
        }

        // Sort processes by priority (lower number means higher priority)
        Arrays.sort(processes, Comparator.comparingInt(p -> p.priority));

        System.out.println("\nProcess Execution Order:");
        int time = 0;

        for (Process process : processes) {
            System.out.println("Process " + process.id + " (Burst Time: " + process.burstTime + ") executed at time " + time);
            time += process.burstTime;
            System.out.println("Process " + process.id + " completed at time " + time);
        }

        // Calculate and display waiting and turnaround times
        for (Process process : processes) {
            int turnaroundTime = time; // As all processes complete execution
            int waitingTime = turnaroundTime - process.burstTime;

            System.out.println("Process " + process.id + " - Waiting Time: " + waitingTime + ", Turnaround Time: " + turnaroundTime);
        }

        scanner.close();
    }
}