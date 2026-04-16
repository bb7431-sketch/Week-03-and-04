import java.util.Arrays;

class Client {
    String id;
    int riskScore;
    double accountBalance;

    public Client(String id, int riskScore, double accountBalance) {
        this.id = id;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return id + ":" + riskScore;
    }
}

public class ClientRiskScoreRanking {

    public static void bubbleSort(Client[] clients) {
        int n = clients.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.println("Bubble (asc): " + Arrays.toString(clients) + " // Swaps: " + swaps);
    }

    public static void insertionSort(Client[] clients) {
        int n = clients.length;
        for (int i = 1; i < n; ++i) {
            Client key = clients[i];
            int j = i - 1;
            
            // Sort by riskScore DESC, then accountBalance DESC
            while (j >= 0 && compareDesc(clients[j], key) > 0) {
                clients[j + 1] = clients[j];
                j = j - 1;
            }
            clients[j + 1] = key;
        }
        System.out.println("Insertion (desc): " + Arrays.toString(clients));
    }
    
    private static int compareDesc(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c2.riskScore, c1.riskScore); // Descending
        }
        return Double.compare(c2.accountBalance, c1.accountBalance); // Descending
    }

    public static void printTopRisks(Client[] clients, int topK) {
        System.out.print("Top " + topK + " risks: ");
        int limit = Math.min(topK, clients.length);
        for(int i=0; i<limit; i++) {
            System.out.print(clients[i].id + "(" + clients[i].riskScore + ")" + (i == limit - 1 ? "" : ", "));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Client[] clients = {
            new Client("clientC", 80, 10000),
            new Client("clientA", 20, 15000),
            new Client("clientB", 50, 5000)
        };
        
        Client[] bubbleClients = clients.clone();
        bubbleSort(bubbleClients);
        
        Client[] insertionClients = clients.clone();
        insertionSort(insertionClients);
        
        printTopRisks(insertionClients, 3);
    }
}
