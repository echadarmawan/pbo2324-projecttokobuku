package tokobuku;

/*
 * Implementasi CRUD ke Database pada Toko Buku
 */
import java.sql.*;
import java.util.Scanner;

public class TokoBuku {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/toko_buku";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static Connection conn;
    private static Scanner keyboard = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            // Menghubungkan ke database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Terhubung ke database.");

            while (true) {
                System.out.println("\n===== MENU =====");
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
                        conn.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Menu tidak valid!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void tambahBuku() {
        System.out.println("Jenis Buku:");
        System.out.println("1. Non Fiksi");
        System.out.println("2. Fiksi");
        System.out.print("Pilihan\t\t: ");
        int jenis = keyboard.nextInt();
        keyboard.nextLine(); // membersihkan newline di buffer
        
        String id, judul, pengarang, subJenis = "", genre = "", subjek = "";
        int tahunTerbit;
        double harga;
        
        System.out.print("ID Buku\t\t: ");
        id = keyboard.nextLine();
        System.out.print("Judul\t\t: ");
        judul = keyboard.nextLine();
        System.out.print("Pengarang\t: ");
        pengarang = keyboard.nextLine();
        System.out.printf("Tahun Terbit\t: ");
        tahunTerbit = keyboard.nextInt();
        System.out.print("Harga\t\t: Rp");
        harga = keyboard.nextDouble();
        keyboard.nextLine(); // membersihkan newline di buffer
        
        String sql = "INSERT INTO buku (id_buku, judul, pengarang, tahun_terbit, harga, jenis, sub_jenis, genre, subjek) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (jenis == 1) {
                System.out.print("Subjek\t\t: ");
                subjek = keyboard.nextLine();
                stmt.setString(6, "Non Fiksi");
            } else if (jenis == 2) {
                System.out.print("Sub jenis\t: ");
                subJenis = keyboard.nextLine();
                System.out.print("Genre\t\t: ");
                genre = keyboard.nextLine();
                stmt.setString(6, "Fiksi");
            } else {
                System.out.println("Pilihan tidak valid!");
                return;
            }
            
            stmt.setString(1, id);
            stmt.setString(2, judul);
            stmt.setString(3, pengarang);
            stmt.setInt(4, tahunTerbit);
            stmt.setDouble(5, harga);
            stmt.setString(7, subJenis);
            stmt.setString(8, genre);
            stmt.setString(9, subjek);
            
            stmt.executeUpdate();
            System.out.println("Buku berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void tampilkanDaftarBuku() {
        String sql = "SELECT * FROM buku";
        
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (!rs.isBeforeFirst()) {
                System.out.println("Daftar buku kosong.");
                return;
            }
            
            System.out.println("\n===== DAFTAR BUKU NON-FIKSI DAN FIKSI =====");
            System.out.println("====================================================================================================================================================");
            System.out.println("  ID BUKU  |                   JUDUL                  |       PENGARANG      | TAHUN TERBIT |         HARGA        |    SUBJEK / SUBJENIS: GENRE    ");
            System.out.println("====================================================================================================================================================");
            while (rs.next()) {
                String id = rs.getString("id_buku");
                String judul = rs.getString("judul");
                String pengarang = rs.getString("pengarang");
                int tahunTerbit = rs.getInt("tahun_terbit");
                double harga = rs.getDouble("harga");
                String jenis = rs.getString("jenis");
                String subJenis = rs.getString("sub_jenis");
                String genre = rs.getString("genre");
                String subjek = rs.getString("subjek");
                
                if ("Non Fiksi".equals(jenis)) {
                    System.out.printf("%10s | %40s | %30s | %12d | Rp%,18.0f | %s\n", id, judul, pengarang, tahunTerbit, harga, subjek);
                } else if ("Fiksi".equals(jenis)) {
                    System.out.printf("%10s | %40s | %30s | %12d | Rp%,18.0f | %s: %s\n", id, judul, pengarang, tahunTerbit, harga, subJenis, genre);
                }
            }
            System.out.println("====================================================================================================================================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void ubahBuku() {
        System.out.print("Masukkan ID buku yang ingin diubah: ");
        String id = keyboard.nextLine();
        
        String sql = "SELECT * FROM buku WHERE id_buku = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Data buku ditemukan. Silakan masukkan data baru.");
                    System.out.print("ID Buku\t\t: ");
                    String newId = keyboard.nextLine();
                    System.out.print("Judul\t\t: ");
                    String judul = keyboard.nextLine();
                    System.out.print("Pengarang\t: ");
                    String pengarang = keyboard.nextLine();
                    System.out.print("Tahun Terbit\t: ");
                    int tahunTerbit = keyboard.nextInt();
                    System.out.print("Harga\t\t: Rp");
                    double harga = keyboard.nextDouble();
                    keyboard.nextLine(); // membersihkan newline di buffer
                    
                    String jenis = rs.getString("jenis");
                    String subJenis = "", genre = "", subjek = "";
                    
                    if ("Non Fiksi".equals(jenis)) {
                        System.out.print("Subjek\t\t: ");
                        subjek = keyboard.nextLine();
                    } else if ("Fiksi".equals(jenis)) {
                        System.out.print("Sub jenis\t: ");
                        subJenis = keyboard.nextLine();
                        System.out.print("Genre\t\t: ");
                        genre = keyboard.nextLine();
                    }
                    
                    String updateSql = "UPDATE buku SET id_buku = ?, judul = ?, pengarang = ?, tahun_terbit = ?, harga = ?, sub_jenis = ?, genre = ?, subjek = ? WHERE id_buku = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setString(1, newId);
                        updateStmt.setString(2, judul);
                        updateStmt.setString(3, pengarang);
                        updateStmt.setInt(4, tahunTerbit);
                        updateStmt.setDouble(5, harga);
                        updateStmt.setString(6, subJenis);
                        updateStmt.setString(7, genre);
                        updateStmt.setString(8, subjek);
                        updateStmt.setString(9, id);
                        
                        updateStmt.executeUpdate();
                        System.out.println("Data buku berhasil diubah!");
                    }
                } else {
                    System.out.println("Buku tidak ditemukan.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void hapusBuku() {
        System.out.print("Masukkan ID buku yang ingin dihapus: ");
        String id = keyboard.nextLine();
        
        String sql = "DELETE FROM buku WHERE id_buku = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Buku berhasil dihapus.");
            } else {
                System.out.println("Buku tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}