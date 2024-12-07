import java.util.ArrayList;
import java.util.Scanner;

class Sorting {
    public void bubbleSort(String[] tickets, String[] names) {
        int n = tickets.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareTicket(tickets[j], tickets[j + 1]) > 0) {
                    // Menukar tiket
                    String tempTicket = tickets[j];
                    tickets[j] = tickets[j + 1];
                    tickets[j + 1] = tempTicket;

                    // Menukar nama penonton
                    String tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                }
            }
        }
    }

    public void selectionSort(String[] tickets, String[] names) {
        int n = tickets.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (compareTicket(tickets[j], tickets[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Menukar tiket
            String tempTicket = tickets[minIndex];
            tickets[minIndex] = tickets[i];
            tickets[i] = tempTicket;

            // Menukar nama penonton
            String tempName = names[minIndex];
            names[minIndex] = names[i];
            names[i] = tempName;
        }
    }

    private int compareTicket(String ticket1, String ticket2) {
        String[] categories = {"CAT1", "CAT2", "CAT3", "CAT4", "CAT5"};
        int index1 = -1, index2 = -1;

        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(ticket1)) index1 = i;
            if (categories[i].equals(ticket2)) index2 = i;
        }

        return Integer.compare(index1, index2);
    }
}

public class Konser {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Sorting sorting = new Sorting();

        System.out.println("==================================================");
        System.out.println("       Visualisasi Tempat Duduk Konser NIKI       ");
        System.out.println("==================================================");

        System.out.print("Masukkan jumlah penonton: ");
        int n = input.nextInt();
        input.nextLine(); 

        String[] names = new String[n];
        String[] tickets = new String[n];

        System.out.println("Masukkan data penonton (Nama dan Jenis Tiket):");
        for (int i = 0; i < n; i++) {
            System.out.print("Nama Penonton ke-" + (i + 1) + ": ");
            names[i] = input.nextLine();

            System.out.print("Jenis Tiket (CAT1 - CAT5): ");
            tickets[i] = input.nextLine().toUpperCase();

            if (!tickets[i].matches("CAT[1-5]")) {
                System.out.println("Tiket tidak valid. Masukkan ulang data penonton.");
                i--;
            }
        }

        System.out.println("\nPilih metode sorting:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.print("Masukkan pilihan: ");
        int choice = input.nextInt();
        System.out.println();

        switch (choice) {
            case 1:
                sorting.bubbleSort(tickets, names);
                break;
            case 2:
                sorting.selectionSort(tickets, names);
                break;
            default:
                input.close();
                return;
        }

        printSeating(names, tickets);
        input.close();
    }

    private static void printSeating(String[] names, String[] tickets) {
        String[] categories = {"CAT1", "CAT2", "CAT3", "CAT4", "CAT5"};
    
        System.out.printf("%-10s %-20s\n", "Kategori", "Nama Penonton");
        System.out.println("----------------------------------------------------");
    
        for (String category : categories) {
            ArrayList<String> row = new ArrayList<>();
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i].equals(category)) {
                    row.add(names[i]);
                }
            }
            if (!row.isEmpty()) {
                System.out.printf("%-10s ", category);
                for (String name : row) {
                    System.out.printf("%-15s", name); // Lebar tetap untuk nama
                }
                System.out.println();
            }
        }
    }    
}
