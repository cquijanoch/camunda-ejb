package org.unsa.mybatis.mapper;

import java.util.List;

import org.unsa.mybatis.bean.BandejaMesaPartes;
import org.unsa.mybatis.bean.BandejaMesaPartesRequerimiento;

public interface BandejaMesaPartesMapper {

		int save(BandejaMesaPartes bandejaMesaPartes);
		List<BandejaMesaPartesRequerimiento> getAll();
}
