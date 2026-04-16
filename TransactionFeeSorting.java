import java.util.ArrayList;
import java.util.List;

class Transaction {
    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionFeeSorting {

    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0;
        int swaps = 0;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // Swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break;
        }
        System.out.println("BubbleSort (fees): " + list + " // " + passes + " passes, " + swaps + " swaps");
    }

    public static void insertionSort(List<Transaction> list) {
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            Transaction key = list.get(i);
            int j = i - 1;
            
            // Sort by timestamp ascending, then fee ascending
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        System.out.println("InsertionSort (timestamp+fee): " + list);
    }
    
    private static int compare(Transaction t1, Transaction t2) {
        int timeCmp = t1.timestamp.compareTo(t2.timestamp);
        if (timeCmp != 0) return timeCmp;
        return Double.compare(t1.fee, t2.fee);
    }

    public static void findOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50.0) {
                outliers.add(t);
            }
        }
        if (outliers.isEmpty()) {
            System.out.println("High-fee outliers: none");
        } else {
            System.out.println("High-fee outliers: " + outliers);
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));
        
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        bubbleSort(bubbleList);
        
        List<Transaction> insertionList = new ArrayList<>(transactions);
        insertionSort(insertionList);
        
        findOutliers(transactions);
    }
}
