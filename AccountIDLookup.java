import java.util.Arrays;

public class AccountIDLookup {

    public static void linearSearch(String[] logs, String target) {
        int first = -1;
        int last = -1;
        int comps = 0;

        for (int i = 0; i < logs.length; i++) {
            comps++;
            if (logs[i].equals(target)) {
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }
        
        System.out.println("Linear first " + target + ": index " + first + " (" + comps + " comparisons)");
    }

    public static void binarySearch(String[] sortedLogs, String target) {
        int low = 0;
        int high = sortedLogs.length - 1;
        int comps = 0;
        int index = -1;

        while (low <= high) {
            comps++;
            int mid = low + (high - low) / 2;
            int cmp = sortedLogs[mid].compareTo(target);
            
            if (cmp == 0) {
                index = mid;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (index != -1) {
            // Count occurrences around the found index
            int count = 1;
            int i = index - 1;
            while (i >= 0 && sortedLogs[i].equals(target)) {
                count++;
                i--;
            }
            i = index + 1;
            while (i < sortedLogs.length && sortedLogs[i].equals(target)) {
                count++;
                i++;
            }
            System.out.println("Binary " + target + ": index " + index + " (" + comps + " comparisons), count=" + count);
        } else {
            System.out.println("Binary " + target + ": not found (" + comps + " comparisons)");
        }
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};
        
        // Linear search expects original layout or a specific one, sample output implies finding first accB at index 0.
        linearSearch(logs, "accB");

        // Binary search requires sorted array
        String[] sortedLogs = logs.clone();
        Arrays.sort(sortedLogs); // [accA, accB, accB, accC]
        // In this sorted array:
        // Index 0: accA
        // Index 1: accB
        // Index 2: accB
        // Index 3: accC
        binarySearch(sortedLogs, "accB");
    }
}
