package org.unsa.mybatis.mapper;

import java.util.List;

import org.unsa.mybatis.bean.Requerimiento;

public interface RequerimientoMapper {
	int save(Requerimiento requerimiento);
	List<Requerimiento> getAll();
}
