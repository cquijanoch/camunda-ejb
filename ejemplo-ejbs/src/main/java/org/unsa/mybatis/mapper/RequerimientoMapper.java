package org.unsa.mybatis.mapper;

import java.util.List;

import org.unsa.mybatis.bean.BandejaMesaPartes;
import org.unsa.mybatis.bean.Requerimiento;

public interface RequerimientoMapper {
	public void saveReq(Requerimiento requerimiento);
	public void saveBandejaMesaPartes(BandejaMesaPartes requerimiento);
	 
	public List<Requerimiento> getAll();
	public List<BandejaMesaPartes> listarBandeja();
	public List<BandejaMesaPartes> listarBandejaByEstado(String estado);
}
