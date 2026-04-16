import java.util.Arrays;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class HistoricalTradeVolume {

    // Merge Sort - Ascending
    public static void mergeSort(Trade[] trades, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(trades, left, mid);
            mergeSort(trades, mid + 1, right);
            merge(trades, left, mid, right);
        }
    }

    private static void merge(Trade[] trades, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; ++i) L[i] = trades[left + i];
        for (int j = 0; j < n2; ++j) R[j] = trades[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) { // Stable
                trades[k] = L[i];
                i++;
            } else {
                trades[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) trades[k++] = L[i++];
        while (j < n2) trades[k++] = R[j++];
    }

    // Quick Sort - Descending
    public static void quickSort(Trade[] trades, int low, int high) {
        if (low < high) {
            int pi = partition(trades, low, high);
            quickSort(trades, low, pi - 1);
            quickSort(trades, pi + 1, high);
        }
    }

    private static int partition(Trade[] trades, int low, int high) {
        int pivot = trades[high].volume;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (trades[j].volume >= pivot) { // Descending
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }
        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    public static Trade[] mergeLists(Trade[] list1, Trade[] list2) {
        Trade[] merged = new Trade[list1.length + list2.length];
        int i = 0, j = 0, k = 0;
        // Merge assuming both are sorted ascending
        while(i < list1.length && j < list2.length) {
            if(list1[i].volume <= list2[j].volume) {
                merged[k++] = list1[i++];
            } else {
                merged[k++] = list2[j++];
            }
        }
        while(i < list1.length) merged[k++] = list1[i++];
        while(j < list2.length) merged[k++] = list2[j++];
        return merged;
    }

    public static void main(String[] args) {
        Trade[] trades = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };

        Trade[] mergeTrades = trades.clone();
        mergeSort(mergeTrades, 0, mergeTrades.length - 1);
        System.out.println("MergeSort: " + Arrays.toString(mergeTrades));

        Trade[] quickTrades = trades.clone();
        quickSort(quickTrades, 0, quickTrades.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickTrades));

        // Sample merging morning and afternoon
        Trade[] morning = { new Trade("m1", 200), new Trade("m2", 400) };
        Trade[] afternoon = { new Trade("a1", 100), new Trade("a2", 300) };
        mergeSort(morning, 0, morning.length - 1);
        mergeSort(afternoon, 0, afternoon.length - 1);
        Trade[] merged = mergeLists(morning, afternoon);
        
        int totalVolume = 0;
        for (Trade t : merged) totalVolume += t.volume;
        System.out.println("Merged morning+afternoon total: " + totalVolume);
    }
}
