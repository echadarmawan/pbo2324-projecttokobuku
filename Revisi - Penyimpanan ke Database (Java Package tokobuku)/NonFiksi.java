package tokobuku;

/*
 * Subclass NonFiksi
 */
public class NonFiksi extends Buku {
    private String subjek;
    
    // Constructor
    public NonFiksi(String id, String judul, String pengarang, int tahunTerbit, double harga, String subjek) {
        super(id, judul, pengarang, tahunTerbit, harga);
        this.subjek = subjek;
    }
    
    // Getter dan setter subjek
    public void setSubjek(String subjek) {
        this.subjek = subjek;
    }
    
    public String getSubjek() {
        return subjek;
    }
    
    // Override method tampilInfo untuk menampilkan informasi buku NonFiksi
    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.printf("%s", subjek);
    }
}
