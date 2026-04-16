public class RiskThresholdBinaryLookup {

    public static void linearSearch(int[] risks, int target) {
        int comps = 0;
        boolean found = false;
        for (int i = 0; i < risks.length; i++) {
            comps++;
            if (risks[i] == target) {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Linear: threshold=" + target + " -> not found (" + comps + " comps)");
        } else {
            System.out.println("Linear: threshold=" + target + " -> found (" + comps + " comps)");
        }
    }

    public static void binarySearchFloorCeiling(int[] risks, int target) {
        int low = 0;
        int high = risks.length - 1;
        int comps = 0;
        
        int floor = -1;
        int ceiling = -1;

        while (low <= high) {
            comps++;
            int mid = low + (high - low) / 2;
            
            if (risks[mid] == target) {
                floor = risks[mid];
                ceiling = risks[mid];
                break;
            } else if (risks[mid] < target) {
                floor = risks[mid];
                low = mid + 1;
            } else {
                ceiling = risks[mid];
                high = mid - 1;
            }
        }

        System.out.println("Binary floor(" + target + "): " + (floor == -1 ? "none" : floor) + 
                           ", ceiling: " + (ceiling == -1 ? "none" : ceiling) + 
                           " (" + comps + " comps)");
    }

    public static void main(String[] args) {
        int[] sortedRisks = {10, 25, 50, 100};
        
        linearSearch(sortedRisks, 30);
        binarySearchFloorCeiling(sortedRisks, 30);
    }
}
