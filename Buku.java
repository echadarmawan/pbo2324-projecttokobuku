/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
        System.out.println("ID Buku\t\t: " + idBuku);
        System.out.println("Judul\t\t: " + judul);
        System.out.println("Pengarang\t: " + pengarang);
        System.out.println("Tahun Terbit\t: " + tahunTerbit);
        System.out.printf("Harga\t\t: Rp%,.0f\n", harga);
    }
}
