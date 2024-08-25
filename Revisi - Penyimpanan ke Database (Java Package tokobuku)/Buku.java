package tokobuku;

/*
 * Superclass Buku
 */
public class Buku {
    private String idBuku;
    private String judul;
    private String pengarang;
    private int tahunTerbit;
    private double harga;
    
    // Constructor
    public Buku(String id, String judul, String pengarang, int tahunTerbit, double harga) {
        idBuku = id;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.harga = harga;
    }
    
    // Getter dan setter idBuku
    public void setIdBuku(String id) {
        idBuku = id;
    }
    
    public String getIdBuku() {
        return idBuku;
    }
    
    // Getter dan setter judul
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public String getJudul() {
        return judul;
    }
    
    // Getter dan setter pengarang
    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    
    public String getPengarang() {
        return pengarang;
    }
    
    // Getter dan setter tahunTerbit
    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }
    
    public int getTahunTerbit() {
        return tahunTerbit;
    }
    
    // Getter dan setter harga
    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    public double getHarga() {
        return harga;
    }
    
    // Method untuk menampilkan informasi buku
    public void tampilInfo() {
        System.out.printf("%10s | %40s | %30s | %12d | Rp%,18.0f | ", idBuku, judul, pengarang, tahunTerbit, harga);
    }
}
