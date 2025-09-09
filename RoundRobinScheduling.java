import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Process {
    int id;
    int burstTime;
    int remainingTime;

    // Constructor
    Process(int id, int burstTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of processes: ");
        int numberOfProcesses = scanner.nextInt();
        
        Process[] processes = new Process[numberOfProcesses];
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.print("Enter burst time for Process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();
            processes[i] = new Process(i + 1, burstTime);
        }
        
        System.out.print("Enter time quantum: ");
        int timeQuantum = scanner.nextInt();
        
        roundRobinScheduling(processes, timeQuantum);
        scanner.close();
    }

    public static void roundRobinScheduling(Process[] processes, int timeQuantum) {
        int n = processes.length;
        Queue<Process> queue = new LinkedList<>();
        
        int time = 0;
        boolean[] isCompleted = new boolean[n];
        int completedProcesses = 0;
        int[] waitingTime = new int[n]; 
        int[] turnaroundTime = new int[n];

        while (completedProcesses < n) {
            for (Process process : processes) {
                if (process.remainingTime > 0) {
                    queue.add(process);
                }
            }

            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();
                if (currentProcess.remainingTime > timeQuantum) {
                    time += timeQuantum;
                    currentProcess.remainingTime -= timeQuantum;
                    // Add waiting time for all other processes in the queue
                    for (Process p : queue) {
                        waitingTime[p.id - 1] += timeQuantum;
                    }
                    queue.add(currentProcess); // Re-add to the end of the queue
                } else {
                    time += currentProcess.remainingTime; // Finish the process
                    waitingTime[currentProcess.id - 1] += (time - currentProcess.burstTime - waitingTime[currentProcess.id - 1]);
                    currentProcess.remainingTime = 0;
                    System.out.println("Process " + currentProcess.id + " completed at time " + time);
                    turnaroundTime[currentProcess.id - 1] = time; // Turnaround time is total time
                    completedProcesses++;
                }
            }
        }

        // Display Waiting and Turnaround Times
        System.out.println("\nProcess ID | Waiting Time | Turnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println("    " + (i + 1) + "       |      " + waitingTime[i] + "       |       " + turnaroundTime[i]);
        }
    }
}