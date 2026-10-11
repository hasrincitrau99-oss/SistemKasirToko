import java.util.Scanner;

// Class Node untuk menyimpan data pelanggan (nomor antrian, nama, total belanja)
class Node {
    String nomorAntrian;
    String namaPelanggan;
    double totalBelanja;
    Node next;

    // Konstruktor Node
    public Node(String nomorAntrian, String namaPelanggan, double totalBelanja) {
        this.nomorAntrian = nomorAntrian;
        this.namaPelanggan = namaPelanggan;
        this.totalBelanja = totalBelanja;
        this.next = null;
    }
}

// Class Queue untuk menangani Antrian Pelanggan (FIFO: First In, First Out)
class Queue {
    private Node head, tail;
    private int size;

    public Queue() {
        this.head = this.tail = null;
        this.size = 0;
    }

    // Menambahkan pelanggan ke antrian (enqueue)
    public void enqueue(String nomorAntrian, String namaPelanggan, double totalBelanja) {
        Node newNode = new Node(nomorAntrian, namaPelanggan, totalBelanja);
        if (this.tail == null) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        size++;
        System.out.println("Data pelanggan ditambahkan ke antrian!");
    }

    // Mengeluarkan pelanggan terdepan yang selesai dilayani (dequeue)
    public Node dequeue() {
        if (this.head == null) {
            return null;
        }
        Node temp = this.head;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;
        }
        size--;
        return temp;
    }

    // Menampilkan daftar antrian saat ini
    public void displayQueue() {
        if (this.head == null) {
            System.out.println("Antrian saat ini kosong.");
            return;
        }
        System.out.println("\n--- DAFTAR ANTRIAN PELANGGAN ---");
        Node current = this.head;
        int no = 1;
        while (current != null) {
            System.out.println(no + ". No Antrian: " + current.nomorAntrian + 
                               " | Nama: " + current.namaPelanggan + 
                               " | Total Belanja: Rp " + (int)current.totalBelanja);
            current = current.next;
            no++;
        }
    }

    public int getSize() {
        return size;
    }
}

// Class Stack untuk menangani Riwayat Transaksi (LIFO: Last In, First Out)
class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    // Menyimpan transaksi yang selesai ke stack (push)
    public void push(String nomorAntrian, String namaPelanggan, double totalBelanja) {
        Node newNode = new Node(nomorAntrian, namaPelanggan, totalBelanja);
        newNode.next = this.top;
        this.top = newNode;
    }

    // Menampilkan riwayat transaksi dari yang terbaru ke yang lama (display stack)
    public void displayStack() {
        if (this.top == null) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        System.out.println("\n--- RIWAYAT TRANSAKSI (TERBARU KE LAMA) ---");
        Node current = this.top;
        int no = 1;
        while (current != null) {
            System.out.println(no + ". No Antrian: " + current.nomorAntrian + 
                               " | Nama: " + current.namaPelanggan + 
                               " | Total Belanja: Rp " + (int)current.totalBelanja);
            current = current.next;
            no++;
        }
    }
}

// Class Utama Program
public class SistemKasirToko {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue antrian = new Queue();
        Stack riwayat = new Stack();
        int pilihan;

        do {
            System.out.println("\n=== SISTEM KASIR TOKO ===");
            System.out.println("1. Tambah Antrian");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrian");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline buffer

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nomor Antrian: ");
                    String noAntri = scanner.nextLine();
                    System.out.print("Masukkan Nama Pelanggan: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Total Belanja: ");
                    double total = scanner.nextDouble();
                    
                    antrian.enqueue(noAntri, nama, total);
                    break;

                case 2:
                    Node dilayani = antrian.dequeue();
                    if (dilayani != null) {
                        System.out.println("Melayani pelanggan " + dilayani.nomorAntrian + " (" + dilayani.namaPelanggan + ")");
                        // Data transaksi dipindah/disimpan ke stack riwayat
                        riwayat.push(dilayani.nomorAntrian, dilayani.namaPelanggan, dilayani.totalBelanja);
                        System.out.println("Transaksi disimpan ke riwayat.");
                    } else {
                        System.out.println("Antrian kosong, tidak ada pelanggan untuk dilayani.");
                    }
                    break;

                case 3:
                    antrian.displayQueue();
                    break;

                case 4:
                    riwayat.displayStack();
                    break;

                case 5:
                    System.out.println("Keluar dari program. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 5);

        scanner.close();
    }
}