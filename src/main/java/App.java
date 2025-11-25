import java.util.List;
import java.util.Scanner;

import models.Shift;
import models.Task;
import models.enums.TaskStatus;
import repository.ShiftRepository;
import repository.TaskRepository;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShiftRepository cobaShift = new ShiftRepository();
       
        List<Shift> dataShift =  cobaShift.showAllShift();
        // for (Shift s : dataShift) {
        //     System.out.println();
        //     System.out.println(s.getShiftId() + " - " + s.getStartTime() + " - " + s.getEndTime() + " - " + s.getDate() + " - " + s.isPresent());
        // }

        // System.out.println("Masukan employee id: ");
        // String employeeIdInput = sc.nextLine();
        // dataShift = cobaShift.findShiftsByEmployeeId(employeeIdInput);
        // for (Shift sh : dataShift) {
        //    System.out.println(sh.getShiftId() + " - " + sh.getStartTime() + " - " + sh.getEndTime() + " - " + sh.getDate() + " - " + sh.isPresent());
        // }

        // System.out.println("Masukkan shift id: ");
        // String shiftIdInput = sc.nextLine();
        TaskRepository cobaTask = new TaskRepository();

        // List<Task> listTask = cobaTask.findTasksByShiftId(shiftIdInput);
        // for (Task t : listTask) {
        //     System.out.println(t.getTask_id() + " - " + t.getTitle() + " - " + t.getDescription() + " - " + t.isStatus() + " - " + t.getDeadline() + " - " + t.getCompletedAt());
        // }

        // System.out.println("Masukkan shift id: ");
        // String employeeIdForTask = sc.nextLine();

        // List<Task> listTaskPerStaff = cobaTask.findTasksByShiftId(employeeIdForTask);
        // for (Task t : listTaskPerStaff) {
        //     System.out.println(t.getTask_id() + " - " + t.getTitle() + " - " + t.getDescription() + " - " + t.isStatus() + " - " + t.getDeadline() + " - " + t.getCompletedAt());
        // }

        // System.out.println("Masukkan employee id: ");
        // String absenEmployee = sc.nextLine();
        // System.out.println("Masukkan shift id: ");
        // String absenShiftId = sc.nextLine();
        // System.out.println("Apakah hadir (true = hadir or false = tidak hadir) : ");
        // boolean absenKehadiran = sc.nextBoolean();
        // cobaShift.submitAttendance(absenEmployee, absenShiftId, absenKehadiran);


        System.out.println("masukkan task id: ");
        String taskIdInput = sc.nextLine();
        System.out.println("Update status (completed, cancelled, on progress): ");
        String inputStatus = sc.nextLine();
        TaskStatus taskStatus = null;
        switch (inputStatus) {
            case "completed":
                taskStatus = TaskStatus.COMPLETED;
                break;
            case "cancelled":
                taskStatus = TaskStatus.CANCELLED;
                break;
            case "on progress":
                taskStatus = TaskStatus.ON_PROGRESS;
                break;
            default:
                break;
        }
        if (cobaTask.updateTaskStatus(taskIdInput, taskStatus)) {
            System.out.println("update berhasil");
        } else {
            System.out.println("update gagal");
        }
    }
}
