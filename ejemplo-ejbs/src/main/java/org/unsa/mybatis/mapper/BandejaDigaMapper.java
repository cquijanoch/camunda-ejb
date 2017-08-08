package org.unsa.mybatis.mapper;

import java.util.List;

import org.unsa.mybatis.bean.BandejaDiga;
import org.unsa.mybatis.bean.BandejaMesaPartes;

public interface BandejaDigaMapper {
	int save(BandejaDiga bandejaDiga);
	public List<BandejaDiga> listarBandeja();
	public List<BandejaDiga> listarBandejaByEstado(String estado);
	int updateEstado(BandejaMesaPartes registro);
	BandejaDiga getBandejaById(Integer id);
}
