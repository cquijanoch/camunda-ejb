package org.unsa.mybatis.bean;

public class Contoh {
	private String nama;
    private String alamat;
 
    public Contoh(){
    	
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


	@Override
    public String toString(){
        return nama+" : "+alamat;
    }
}
