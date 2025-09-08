import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Task {
    private String description;
    private boolean completed;
    private int priority; // 1 - High, 2 - Medium, 3 - Low

    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public int getPriority() {
        return priority;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return (completed ? "[x] " : "[ ] ") + description + " (Priority: " + priority + ")";
    }
}

public class TodoList {
    private ArrayList<Task> tasks;

    public TodoList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String description, int priority) {
        tasks.add(new Task(description, priority));
    }

    public void editTask(int index, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDescription(newDescription); // Using setter method
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void markTaskCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markCompleted();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void displayTasks() {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
        }
    }

    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the To-Do List Application!");

        do {
            System.out.println("\nCommands: add, edit, delete, complete, list, exit");
            System.out.print("Enter a command: ");
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "add":
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter priority (1-High, 2-Medium, 3-Low): ");
                    int priority = Integer.parseInt(scanner.nextLine());
                    todoList.addTask(description, priority);
                    break;

                case "edit":
                    todoList.displayTasks();
                    System.out.print("Enter task index to edit: ");
                    int editIndex = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    todoList.editTask(editIndex, newDescription);
                    break;

                case "delete":
                    todoList.displayTasks();
                    System.out.print("Enter task index to delete: ");
                    int deleteIndex = Integer.parseInt(scanner.nextLine());
                    todoList.deleteTask(deleteIndex);
                    break;

                case "complete":
                    todoList.displayTasks();
                    System.out.print("Enter task index to mark as completed: ");
                    int completeIndex = Integer.parseInt(scanner.nextLine());
                    todoList.markTaskCompleted(completeIndex);
                    break;

                case "list":
                    todoList.displayTasks();
                    break;

                case "exit":
                    System.out.println("Exiting application.");
                    break;

                default:
                    System.out.println("Invalid command. Try again.");
            }
        } while (!command.equalsIgnoreCase("exit"));

        scanner.close();
    }
}
