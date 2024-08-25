package tokobuku;

/*
 * Subclass Fiksi
 */
public class Fiksi extends Buku {
    private String subJenis;
    private String genre;
    
    // Constructor
    public Fiksi(String id, String judul, String pengarang, int tahunTerbit, double harga, String subJenis, String genre) {
        super(id, judul, pengarang, tahunTerbit, harga);
        this.subJenis = subJenis;
        this.genre = genre;
    }
    
    // Getter dan setter subJenis
    public void setSubJenis(String subJenis) {
        this.subJenis = subJenis;
    }
    
    public String getSubJenis() {
        return subJenis;
    }
    
    // Getter dan setter genre
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getGenre() {
        return genre;
    }
    
    // Override method tampilInfo untuk menampilkan informasi buku Fiksi
    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.printf("%s: %s", subJenis, genre);
    }
}

