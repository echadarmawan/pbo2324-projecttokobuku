/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * Implementasi CRUD pada Toko Buku
 */
import java.util.ArrayList;
import java.util.Scanner;

public class TokoBuku {
    private static ArrayList<Buku> daftarBuku = new ArrayList<>();
    private static Scanner keyboard = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Daftar Buku");
            System.out.println("3. Ubah Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
            int menu = keyboard.nextInt();
            keyboard.nextLine(); // membersihkan newline di buffer
            
            switch (menu) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                    tampilkanDaftarBuku();
                    break;
                case 3:
                    ubahBuku();
                    break;
                case 4:
                    hapusBuku();
                    break;
                case 5:
                    System.out.println("Terima kasih!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Menu tidak valid!");
            }
        }
    }
    
    private static void tambahBuku() {
        System.out.print("ID Buku\t\t: ");
        String id = keyboard.nextLine();
        System.out.print("Judul\t\t: ");
        String judul = keyboard.nextLine();
        System.out.print("Pengarang\t: ");
        String pengarang = keyboard.nextLine();
        System.out.printf("Tahun Terbit\t: ");
        int tahunTerbit = keyboard.nextInt();
        System.out.print("Harga\t\t: Rp");
        double harga = keyboard.nextDouble();
        keyboard.nextLine(); // membersihkan newline di buffer
        
        System.out.println("Jenis Buku:");
        System.out.println("1. Non Fiksi");
        System.out.println("2. Fiksi");
        System.out.print("Pilihan\t\t: ");
        int jenis = keyboard.nextInt();
        keyboard.nextLine(); // membersihkan newline di buffer
        
        if (jenis == 1) {
            System.out.print("Subjek\t\t: ");
            String subjek = keyboard.nextLine();
            NonFiksi nonFiksi = new NonFiksi(id, judul, pengarang, tahunTerbit, harga, subjek);
            daftarBuku.add(nonFiksi);
        } else if (jenis == 2) {
            System.out.print("Sub jenis\t: ");
            String subJenis = keyboard.nextLine();
            System.out.print("Genre\t\t: ");
            String genre = keyboard.nextLine();
            Fiksi fiksi = new Fiksi(id, judul, pengarang, tahunTerbit, harga, subJenis, genre);
            daftarBuku.add(fiksi);
        } else {
            System.out.println("Pilihan tidak valid!");
            return;
        }
        
        System.out.println("Buku berhasil ditambahkan.");
    }
    
    private static void tampilkanDaftarBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Daftar buku kosong.");
        } else {
            System.out.println("\n=== DAFTAR BUKU ===");
            for (Buku buku : daftarBuku) {
                buku.tampilInfo();
                System.out.println(); // baris kosong antar buku
            }
        }
    }
    
    private static void ubahBuku() {
        if (daftarBuku.isEmpty()) {
            return;
        }
        
        System.out.print("Masukkan ID buku yang ingin diubah: ");
        String id = keyboard.nextLine();
        
        boolean ditemukan = false;
        for (Buku buku : daftarBuku) {
            if (buku.getIdBuku().equalsIgnoreCase(id)) {
                ditemukan = true;
                System.out.println("Data buku ditemukan. Silakan masukkan data baru.");
                System.out.print("ID Buku\t\t: ");
                buku.setIdBuku(keyboard.nextLine());
                System.out.print("Judul\t\t: ");
                buku.setJudul(keyboard.nextLine());
                System.out.print("Pengarang\t: ");
                buku.setPengarang(keyboard.nextLine());
                System.out.print("Tahun Terbit\t: ");
                buku.setTahunTerbit(keyboard.nextInt());
                System.out.print("Harga\t\t: Rp");
                buku.setHarga(keyboard.nextDouble());
                keyboard.nextLine(); // membersihkan newline di buffer
                if (buku instanceof NonFiksi) {
                    System.out.print("Subjek\t\t: ");
                    ((NonFiksi) buku).setSubjek(keyboard.nextLine());
                } else if (buku instanceof Fiksi) {
                    System.out.println("Sub jenis\t: ");
                    ((Fiksi) buku).setSubJenis(keyboard.nextLine());
                    System.out.print("Genre\t\t: ");
                    ((Fiksi) buku).setGenre(keyboard.nextLine());
                }
                System.out.println("Data buku berhasil diubah!");
                break;
            }
        }
        
        if(!ditemukan) {
            System.out.println("Buku tidak ditemukan.");
        }
    }
    
    private static void hapusBuku() {
        if (daftarBuku.isEmpty()) {
            return;
        }
        
        System.out.print("Masukkan ID buku yang ingin dihapus: ");
        String id = keyboard.nextLine();
        
        boolean ditemukan = false;
        for (int i = 0; i < daftarBuku.size(); i++) {
            if (daftarBuku.get(i).getIdBuku().equalsIgnoreCase(id)) {
                ditemukan = true;
                daftarBuku.remove(i);
                System.out.println("Buku berhasil dihapus.");
                break;
            }
        }
        
        if(!ditemukan) {
            System.out.println("Buku tidak ditemukan.");
        }
    }
}
