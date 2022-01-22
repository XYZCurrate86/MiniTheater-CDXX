package tubesplrs.pasien;

public class pasien {

    private int id;
    private String nama;
    private String alamat;
    private String tanggal;
    private String kelas;
    private int ruangan;
    private String status;



    public pasien(int id, String nama, String alamat, String tanggal, String kelas, int ruangan, String status) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.tanggal = tanggal;
        this.kelas = kelas;
        this.ruangan = ruangan;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public int getRuangan() {
        return ruangan;
    }

    public void setRuangan(int ruangan) {
        this.ruangan = ruangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusPembayaran(String status) {
        this.status = status;
    }


}
