package datastructure;

class Node {
    public int id;
    public String data;
    public Node leftChild;
    public Node rightChild;
}

class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    // ==============================================================
    // TUGAS PRAKTIKUM 2: Modifikasi insert untuk mencegah duplicate key
    // ==============================================================
    public void insert(int id, String data) {
        Node newNode = new Node();
        newNode.id = id; 
        newNode.data = data;
        
        if (root == null) { 
            root = newNode; 
            return; 
        }
        
        Node current = root;
        Node parent;
        
        while (true) {
            parent = current;
            
            // Pengecekan Duplicate Key
            if (id == current.id) {
                System.out.println("--> GAGAL INSERT: Node dengan ID " + id + " sudah terdaftar!"); 
                return; // Proses dihentikan agar duplikat tidak masuk
            }
            
            if (id < current.id) {
                current = current.leftChild;
                if (current == null) { 
                    parent.leftChild = newNode; 
                    return; 
                }
            } else {
                current = current.rightChild;
                if (current == null) { 
                    parent.rightChild = newNode; 
                    return; 
                }
            }
        }
    }

    // ==============================================================
    // TUGAS PRAKTIKUM 1: Method mencari nilai Minimum
    // ==============================================================
    public Node findMin() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
    }

    // ==============================================================
    // TUGAS PRAKTIKUM 1: Method mencari nilai Maksimum
    // ==============================================================
    public Node findMax() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current;
    }

    // Method pembantu untuk menampilkan isi tree
    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print("{" + localRoot.id + ", " + localRoot.data + "} ");
            inOrder(localRoot.rightChild);
        }
    }
    
    public Node getRoot() { 
        return root; 
    }
}

public class TreeApp {
    public static void main(String[] args) {
        Tree theTree = new Tree();
        
        // Memasukkan data awal
        theTree.insert(50, "Ahmad");
        theTree.insert(25, "Rosa");
        theTree.insert(75, "Raisa");
        theTree.insert(12, "Naya");
        theTree.insert(37, "Gagas");

        // Menampilkan output Soal 1 terlebih dahulu
        System.out.println("=== UJI SOAL 1: FIND MIN & MAX ===");
        Node min = theTree.findMin();
        Node max = theTree.findMax();
        
        if (min != null) {
            System.out.println("Nilai Minimum  : " + min.id + " (" + min.data + ")");
        }
        if (max != null) {
            System.out.println("Nilai Maksimum : " + max.id + " (" + max.data + ")");
        }

        // Menampilkan output Soal 2 setelahnya
        System.out.println("\n=== UJI SOAL 2: DUPLICATE KEY ===");
        theTree.insert(25, "Duplikat Rosa");  // Sistem akan mencetak pesan GAGAL
        theTree.insert(75, "Duplikat Raisa"); // Sistem akan mencetak pesan GAGAL

        // Menampilkan hasil akhir tree
        System.out.println("\n=== ISI TREE AKHIR (TERURUT NAIK) ===");
        theTree.inOrder(theTree.getRoot());
        System.out.println();
    }
}
