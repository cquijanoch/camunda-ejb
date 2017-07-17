package org.unsa.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.unsa.mybatis.bean.Contoh;

public interface ContohMapper {
	int save(Contoh contoh);
	 
    @Delete("DELETE FROM contoh WHERE nama = #{nama}")
    int delete(String nama);
 
    @Select("SELECT * FROM contoh WHERE nama = #{nama}")
    Contoh select(String nama);
 
    @Select("SELECT * FROM contoh")
    List<Contoh> selectAll();
}
