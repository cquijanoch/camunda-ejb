package org.unsa.dto;

public class ContohDto {
	private String nama;
    private String alamat;
 
    public ContohDto(){
    	
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
