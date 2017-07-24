package org.unsa.mybatis.mapper;

import java.util.List;

import org.unsa.mybatis.bean.BandejaDiga;

public interface BandejaDigaMapper {
	int save(BandejaDiga bandejaDiga);
	List<BandejaDiga> getAll();
}
