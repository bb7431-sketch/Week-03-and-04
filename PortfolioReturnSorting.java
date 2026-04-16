import java.util.Arrays;

class Asset {
    String id;
    double returnRate;
    double volatility;

    public Asset(String id, double returnRate, double volatility) {
        this.id = id;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return id + ":" + (int)(returnRate * 100) + "%";
    }
}

public class PortfolioReturnSorting {

    // Merge Sort - by returnRate ASC
    public static void mergeSort(Asset[] assets, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(assets, left, mid);
            mergeSort(assets, mid + 1, right);
            merge(assets, left, mid, right);
        }
    }

    private static void merge(Asset[] assets, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; ++i) L[i] = assets[left + i];
        for (int j = 0; j < n2; ++j) R[j] = assets[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) { // Stable
                assets[k] = L[i];
                i++;
            } else {
                assets[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) assets[k++] = L[i++];
        while (j < n2) assets[k++] = R[j++];
    }

    // Quick Sort - by returnRate DESC + volatility ASC
    public static void quickSort(Asset[] assets, int low, int high) {
        if (low < high) {
            int pi = partition(assets, low, high);
            quickSort(assets, low, pi - 1);
            quickSort(assets, pi + 1, high);
        }
    }

    private static int partition(Asset[] assets, int low, int high) {
        Asset pivot = assets[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (compareDescAsc(assets[j], pivot) <= 0) {
                i++;
                Asset temp = assets[i];
                assets[i] = assets[j];
                assets[j] = temp;
            }
        }
        Asset temp = assets[i + 1];
        assets[i + 1] = assets[high];
        assets[high] = temp;
        return i + 1;
    }

    private static int compareDescAsc(Asset a1, Asset a2) {
        int returnCmp = Double.compare(a2.returnRate, a1.returnRate); // Descending
        if (returnCmp != 0) return returnCmp;
        return Double.compare(a1.volatility, a2.volatility); // Ascending
    }

    public static void main(String[] args) {
        Asset[] assets = {
            new Asset("AAPL", 0.12, 0.05),
            new Asset("TSLA", 0.08, 0.09),
            new Asset("GOOG", 0.15, 0.04)
        };

        Asset[] mergeAssets = assets.clone();
        mergeSort(mergeAssets, 0, mergeAssets.length - 1);
        System.out.println("Merge: " + Arrays.toString(mergeAssets));

        Asset[] quickAssets = assets.clone();
        quickSort(quickAssets, 0, quickAssets.length - 1);
        System.out.println("Quick (desc): " + Arrays.toString(quickAssets));
    }
}
