
//DS Assignment 4: Implement Berkeley algorithm for clock synchronization.

import java.text.*;
import java.util.*;

public class BerkelyAlgorithm {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of clients in your network: ");
        int clientCount = sc.nextInt();
        sc.nextLine();

        String[] timeString = new String[1 + clientCount];  // 1 server + clientCount clients

        for (int i = 0; i < timeString.length; i++) {
            if (i == 0) {
                System.out.print("Enter time displayed in Server (HH:mm): ");
            } else {
                System.out.print("Enter time displayed in Client " + i + " (HH:mm): ");
            }

            String time = sc.nextLine();
            timeString[i] = appendCurrentDateToTime(time);
        }

        System.out.println("\nBefore Synchronization");
        displayTime(timeString, "");

        berkeleyAlgorithm(timeString);

        System.out.println("\nAfter Synchronization");
        displayTime(timeString, "Synchronized ");

        sc.close();
    }

    public static void berkeleyAlgorithm(String[] timeString) throws ParseException {
        int n = timeString.length;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm | yyyy-MM-dd");

        // Converting time to milliseconds
        long[] timeInMilliseconds = new long[n];
        for (int i = 0; i < n; i++) {
            timeInMilliseconds[i] = simpleDateFormat.parse(timeString[i]).getTime();
        }

        // Calculating time difference w.r.t. server
        long serverTime = timeInMilliseconds[0];
        long[] differenceInTimeWithServer = new long[n];
        for (int i = 0; i < n; i++) {
            differenceInTimeWithServer[i] = timeInMilliseconds[i] - serverTime;
        }

        // Calculating Fault tolerant average
        long avg = 0;
        for (int i = 0; i < n; i++) {
            avg += differenceInTimeWithServer[i];
        }
        avg /= n;
        System.out.println("Fault tolerant average = " + avg / (1000 * 60));    // Displaying fault-tolerant average in minutes

        // Adjusting the time in Server and Clients
        for (int i = 0; i < n; i++) {
            long offset = avg - differenceInTimeWithServer[i];
            timeInMilliseconds[i] += offset;
            if (i == 0) {
                continue;
            }
            System.out.println("Clock " + i + " adjustment = " + offset / (1000 * 60)); // Displaying adjustment value in minutes
        }

        // Converting milliseconds to actual time
        for (int i = 0; i < n; i++) {
            timeString[i] = simpleDateFormat.format(new Date(timeInMilliseconds[i]));
        }
    }

    private static void displayTime(String[] time, String prefix) {
        System.out.println(prefix + "Server Clock:\t" + time[0]);
        for (int i = 1; i < time.length; i++) {
            System.out.println(prefix + "Client " + i + " Clock:\t" + time[i]);
        }
        System.out.println();
    }

    private static String appendCurrentDateToTime(String time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return time + " | " + year + "-" + month + "-" + day;
    }
}


/* Commands:
denzil@denzil-VirtualBox:~/Documents/assignments$ javac BerkeleyAlgorithm.java

denzil@denzil-VirtualBox:~/Documents/assignments$ java BerkeleyAlgorithm
*/

