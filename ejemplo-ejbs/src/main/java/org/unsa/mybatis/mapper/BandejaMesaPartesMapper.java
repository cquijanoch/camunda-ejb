package org.unsa.mybatis.mapper;

import java.util.List;

import org.unsa.mybatis.bean.BandejaDiga;
import org.unsa.mybatis.bean.BandejaMesaPartes;
import org.unsa.mybatis.bean.BandejaMesaPartesRequerimiento;

public interface BandejaMesaPartesMapper {

		int save(BandejaMesaPartes bandejaMesaPartes);
		int saveReqDiga(BandejaDiga requerimiento);
		List<BandejaMesaPartesRequerimiento> getAll();
		int updateEstado(BandejaMesaPartes registro);
		int updateDetalle(BandejaMesaPartes registro);
		
}
