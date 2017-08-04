package org.unsa.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.unsa.mybatis.bean.Requerimiento;

public interface RequerimientoMapper {
	int save(Requerimiento requerimiento);
	List<Requerimiento> getAll();
	List<Requerimiento> getById(@Param("Requerimiento_requerimientoId") Integer id);
}
