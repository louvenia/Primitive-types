package ex05;

import java.util.Scanner;

public class Program {
    private static final Scanner input = new Scanner(System.in);
    private static final String[] studentName = new String[10];
    private static final int[][] classes = new int[7][5];
    private static final String[][][][] attendance = new String[10][30][10][1];
    private static final int[] countClasses = new int[7];

    private static void errorMessage(String message) {
        System.err.println(message);
        input.close();
        System.exit(-1);
    }

    private static void printSchedule() {
        for(int i = 0; i < 30;) {
            int j = 0;
            if (i == 0) {
                System.out.printf("%10s"," ");
            }
            int day = ++i % 7;
            while(j < 5 && classes[day][j] != 0) {
                if (day == 0) {
                    System.out.printf("%1d:00%3s%3d|", classes[day][j], "MO", i);
                } else if (day == 1) {
                    System.out.printf("%1d:00%3s%3d|", classes[day][j], "TU", i);
                } else if (day == 2) {
                    System.out.printf("%1d:00%3s%3d|", classes[day][j], "WE", i);
                } else if (day == 3) {
                    System.out.printf("%1d:00%3s%3d|", classes[day][j], "TH", i);
                } else if (day == 4) {
                    System.out.printf("%1d:00%3s%3d|", classes[day][j], "FR", i);
                } else if (day == 5) {
                    System.out.printf("%1d:00%3s%3d|", classes[day][j], "SA", i);
                } else {
                    System.out.printf("%1d:00%3s%3d|", classes[day][j], "SU", i);
                }
                j++;
            }
        }
        System.out.println();
        for (int i = 0; i < studentName.length && studentName[i] != null; i++) {
            System.out.printf("%10s", studentName[i]);
            for (int k = 1; k <= 30; k++) {
                for (int j = 0; j < 5 && classes[k % 7][j] != 0; j++) {
                    if(attendance[j][k-1][i][0] != null) {
                        if (attendance[j][k-1][i][0].equals("HERE")) {
                            System.out.printf("%10d|", 1);
                        } else if (attendance[j][k-1][i][0].equals("NOT_HERE")) {
                            System.out.printf("%10d|", -1);
                        }
                    } else {
                        System.out.printf("%10s|", " ");
                    }
                }
            }
            System.out.println();
        }
    }

    private static int findDayAndClassOfWeek(int d, String time) {
        int week = d % 7;
        int i = 0;
        while (i < 5 && classes[week][i] != Integer.parseInt(time)) {
            i++;
        }
        if (i < 5) {
            return i;
        }
        return 0;
    }

    private static void recordingAttendance() {
        String name, time, statusAttendance;
        int day, countStudent, timeInt;
        while(!(name = input.next()).equals(".")) {
            time = input.next();
            day = input.nextInt();
            statusAttendance = input.next();
            countStudent = 0;
            while (countStudent < studentName.length && !studentName[countStudent].equals(name)) {
                countStudent++;
            }
            timeInt = findDayAndClassOfWeek(day, time);
            attendance[timeInt][day-1][countStudent][0] = statusAttendance;
        }
    }

    private static void sortClasses() {
        for(int i = 0; i < 7; i++) {
            for(int k = 0; k < 4; k++) {
                for (int j = 0; j < 4 - k && classes[i][j] != 0 && classes[i][j+1] != 0; j++) {
                    if (classes[i][j + 1] < classes[i][j]) {
                        int swap = classes[i][j];
                        classes[i][j] = classes[i][j + 1];
                        classes[i][j + 1] = swap;
                    }
                }
            }
        }
    }

    private static boolean checkClasses() {
        boolean result = false;
        for (int countClass : countClasses) {
            if (countClass == 5) {
                result = true;
                break;
            }
        }
        return result;
    }

    private static void populatingTimetable() {
        String time, weekdays, allTime = "1 2 3 4 5";
        int timeInt, totalClasses = 0;
        while(!(time = input.next()).equals(".")) {
            if(totalClasses == 10) {
                errorMessage("Total classes per week cannot exceed 10");
            } else if(!allTime.contains(time)) {
                errorMessage("Classes can be held on any day of week between 1 pm and 6 pm");
            } else if(checkClasses()) {
                errorMessage("5 lessons per day");
            }
            weekdays = input.next();
            timeInt = Integer.parseInt(time);
            if (weekdays.equals("MO")) {
                classes[0][countClasses[0]++] = timeInt;
            } else if (weekdays.equals("TU")) {
                classes[1][countClasses[1]++] = timeInt;
            } else if (weekdays.equals("WE")) {
                classes[2][countClasses[2]++] = timeInt;
            } else if (weekdays.equals("TH")) {
                classes[3][countClasses[3]++] = timeInt;
            } else if (weekdays.equals("FR")) {
                classes[4][countClasses[4]++] = timeInt;
            } else if (weekdays.equals("SA")) {
                classes[5][countClasses[5]++] = timeInt;
            } else if (weekdays.equals("SU")) {
                classes[6][countClasses[6]++] = timeInt;
            } else {
                errorMessage("You need to enter the day of the week for the classes (Ex: MO, TU, WE, TH, FR, SA, SU)");
            }
            totalClasses++;
        }
    }

    private static void creatingListStudents() {
        String name;
        int countStudent = 0;
        while(!(name = input.nextLine()).equals(".")) {
            if(countStudent == 10) {
                errorMessage("Maximum number of students in the timetable is 10");
            } else if(name.length() > 10) {
                errorMessage("Maximum length of a student's name is 10 (no spaces)");
            }
            studentName[countStudent] = name;
            countStudent++;
        }
    }

    public static void main(String[] args) {
        creatingListStudents();
        populatingTimetable();
        sortClasses();
        recordingAttendance();
        printSchedule();
        input.close();
    }
}
