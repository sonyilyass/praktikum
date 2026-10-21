class Warga {
    String nama;
    int jumlah;
    String alamat;

    Warga(String nama, int jumlah, String alamat) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.alamat = alamat;
    }
}

class Node {
    Warga data;
    Node prev, next;

    Node(Warga data) {
        this.data = data;
    }
}

class DLL {
    Node head, tail;
    int size;

    // true jika nama kepala keluarga sudah ada
    boolean cek(String nama) {
        Node t = head;
        while (t != null) {
            if (t.data.nama.equals(nama)) return true;
            t = t.next;
        }
        return false;
    }

    // tambah di belakang, tolak jika nama sudah ada
    void tambah(String nama, int jumlah, String alamat) {
        if (cek(nama)) {
            System.out.println(nama + " sudah ada");
            return;
        }
        Node baru = new Node(new Warga(nama, jumlah, alamat));
        if (head == null) {
            head = tail = baru;
        } else {
            tail.next = baru;
            baru.prev = tail;
            tail = baru;
        }
        size++;
    }

    void tampil() {
        System.out.println("data warga RT:");
        for (Node t = head; t != null; t = t.next) {
            System.out.println("  kepala keluarga: " + t.data.nama
                    + ", jumlah anggota: " + t.data.jumlah
                    + ", alamat: " + t.data.alamat);
        }
        System.out.println();
    }

    // pencarian dari head dan tail sekaligus, berhenti saat pointer bertemu
    boolean cari(String nama) {
        Node dep = head, bel = tail;
        while (dep != null && bel != null) {
            if (dep.data.nama.equals(nama)) return true;
            if (bel.data.nama.equals(nama)) return true;
            if (dep == bel || dep.next == bel) break;
            dep = dep.next;
            bel = bel.prev;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        DLL l = new DLL();
        l.tambah("Ali", 4, "Jl. D Belayan no 12");
        l.tambah("Ahmad", 2, "Jl. D Belayan no 9");
        l.tambah("Abdurrohman", 3, "Jl. D Belayan no 7");
        l.tambah("Burhan", 4, "Jl. D Belayan no 6");
        l.tambah("Bachtiar", 5, "Jl. D Belayan no 10");
        l.tambah("Sofyan", 1, "Jl. D Belayan no 11");
        l.tampil();

        System.out.println("--tambah nama yang sudah ada--");
        l.tambah("Ali", 4, "Jl. D Belayan no 12");
        System.out.println();

        System.out.println("--tambah--");
        l.tambah("Choirul", 6, "Jl. D Sentani no 13");
        l.tambah("Sujatmoko", 3, "Jl. D Sentani no 15");
        l.tambah("Galuh", 2, "Jl. D Sentani no 14");
        l.tambah("Hari", 4, "Jl. D Belayan no 8");
        l.tampil();

        System.out.println("--pencarian--");
        String[] daftar = {"Aris", "Burhan"};
        for (String x : daftar) {
            System.out.println("cari " + x);
            System.out.println(" " + x
                    + (l.cari(x) ? " telah terdaftar" : " belum telah terdaftar"));
        }
    }
}
