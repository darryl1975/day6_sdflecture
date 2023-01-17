package sg.edu.nus.iss;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        // Thread thread1 = new Thread(new Runnable() {

        // @Override
        // public void run() {
        // for (int i = 0; i < 5; i++) {
        // System.out.println(Thread.currentThread().getName() +
        // "\tRunnable ..." + i);
        // }

        // }

        // });
        // thread1.start();

        MyRunnableImplementation mRI = new MyRunnableImplementation("Task 1");
        MyRunnableImplementation mRI2 = new MyRunnableImplementation("Task 2");
        MyRunnableImplementation mRI3 = new MyRunnableImplementation("Task 3");
        MyRunnableImplementation mRI4 = new MyRunnableImplementation("Task 4");
        MyRunnableImplementation mRI5 = new MyRunnableImplementation("Task 5");
        // Thread thread2 = new Thread(mRI);
        // thread2.start();

        // Thread thread3 = new Thread(mRI);
        // thread3.start();

        // ExecutorService executorService = Executors.newSingleThreadExecutor();
        // executorService.execute(mRI);
        // executorService.execute(mRI2);
        // executorService.shutdown();

        // ExecutorService executorService = Executors.newFixedThreadPool(3);
        // executorService.execute(mRI);
        // executorService.execute(mRI2);
        // executorService.execute(mRI3);
        // executorService.execute(mRI4);
        // executorService.execute(mRI5);
        // executorService.shutdown();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(mRI);
        executorService.execute(mRI2);
        executorService.execute(mRI3);
        executorService.execute(mRI4);
        executorService.execute(mRI5);
        executorService.shutdown();

        MyRunnableInterface<Integer> addOperation = (a, b) -> {
            return a + b;
        };
        MyRunnableInterface<Integer> multiplyOperation = (a, b) -> {
            return a * b;
        };
        MyRunnableInterface<Integer> minusOperation = (a, b) -> {
            return a - b;
        };
        MyRunnableInterface<String> concatenateString = (a, b) -> {
            return a + b;
        };
        MyMessageInterface printString = (a) -> {
            System.out.println(a);
        };

        System.out.println("addOperation: " + addOperation.process(1, 1));
        System.out.println("multiplyOperation: " + multiplyOperation.process(2, 5));
        System.out.println("minusOperation: " + minusOperation.process(10, 2));
        System.out.println(
                "concatenateString: " + concatenateString.process("The quick brown fox ", "jumps over the wall."));
        printString.printMessage("Let's take a break at 12pm.");

        // list of employees
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(1, "Adam", "Cheng", 50000));
        employees.add(new Employee(2, "Bertram", "Chong", 7000));
        employees.add(new Employee(3, "Kelvin", "Khoo", 8000));
        employees.add(new Employee(4, "Bernard", "Tan", 30000));
        employees.add(new Employee(5, "Donnie", "Yen", 50000));
        employees.add(new Employee(6, "Adam", "Cheng", 50000));
        // employees.forEach(emp -> {
        // System.out.println(emp);
        // });

        List<Employee> filteredEmployees = employees.stream().filter(emp -> emp.getLastName().contains("Ch"))
                .collect(Collectors.toList());
        // filteredEmployees.forEach(emp -> System.out.println(emp));

        // employees.sort(Comparator.comparing(e -> e.getFirstName()));
        // employees.sort(Comparator.comparing(Employee::getFirstName));
        // employees.sort(Comparator.comparing(Employee::getFirstName).reversed());

        Comparator<Employee> compare = Comparator.comparing(e -> e.getFirstName());
        employees.sort(compare.reversed());

        // employees.forEach(emp -> {
        // System.out.println(emp);
        // });

        Comparator<Employee> groupByComparator = Comparator.comparing(Employee::getFirstName)
                .thenComparing(Employee::getLastName);
        employees.sort(groupByComparator);
        employees.forEach(emp -> {
            System.out.println(emp);
        });
    }
}
