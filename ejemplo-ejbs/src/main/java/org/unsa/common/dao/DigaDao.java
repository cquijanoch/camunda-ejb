package org.unsa.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.UsuarioDto;
import org.unsa.mybatis.bean.BandejaDiga;
import org.unsa.mybatis.bean.BandejaMesaPartes;
import org.unsa.mybatis.bean.Requerimiento;
import org.unsa.mybatis.bean.Usuario;
import org.unsa.mybatis.mapper.BandejaDigaMapper;
import org.unsa.mybatis.mapper.BandejaMesaPartesMapper;
import org.unsa.mybatis.mapper.RequerimientoMapper;
import org.unsa.mybatis.mapper.UsuarioMapper;

public class DigaDao extends GeneralDao {
	private static final Logger LOGGER = Logger.getLogger(DigaDao.class.getName());


	public List<ReqMesaPartesDto> getReqDigaByEstado(String estado)
	{
		List<ReqMesaPartesDto> response=new ArrayList<ReqMesaPartesDto>();
		SqlSession sqlSession=getSessionFactory().openSession();
		try{
			BandejaDigaMapper mapper = sqlSession.getMapper(BandejaDigaMapper.class);
			List<BandejaDiga> list=new ArrayList<BandejaDiga>();
			list=  mapper.listarBandejaByEstado(estado);
			
			for(BandejaDiga req:list)
			{
				ReqMesaPartesDto obj=new ReqMesaPartesDto();
				obj.setId(req.getId());
				obj.setEstadoDiga(req.getEstadoDiga());
				obj.setDetalle(req.getDetalle());
				obj.setRequerimientoId(req.getRequerimientoId());
				obj.setAsunto(req.getAsunto());
				obj.setUsuarioId(req.getUsuarioId());
				obj.setNombre(req.getNombre());
				obj.setDni(req.getDni());
				response.add(obj);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return response;
		
	}
	
	public List<ReqMesaPartesDto> getReqDiga()
	{
		List<ReqMesaPartesDto> response=new ArrayList<ReqMesaPartesDto>();
		SqlSession sqlSession=getSessionFactory().openSession();
		try{
			BandejaDigaMapper mapper = sqlSession.getMapper(BandejaDigaMapper.class);
			List<BandejaDiga> list=new ArrayList<BandejaDiga>();
			list=  mapper.listarBandeja();
			
			for(BandejaDiga req:list)
			{
				ReqMesaPartesDto obj=new ReqMesaPartesDto();
				obj.setId(req.getId());
				obj.setEstadoDiga(req.getEstadoDiga());
				obj.setDetalle(req.getDetalle());
				obj.setRequerimientoId(req.getRequerimientoId());
				obj.setAsunto(req.getAsunto());
				obj.setUsuarioId(req.getUsuarioId());
				obj.setNombre(req.getNombre());
				obj.setDni(req.getDni());
				response.add(obj);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return response;
		
	}
	public boolean updateEstadoRequerimiento(ReqMesaPartesDto registro,String estado) {
		 SqlSession sqlSession=getSessionFactory().openSession();
		try {
			
			BandejaDigaMapper mapper = sqlSession.getMapper(BandejaDigaMapper.class);
			BandejaMesaPartes bandejaBean = new BandejaMesaPartes();

			bandejaBean.setId(registro.getId());
			bandejaBean.setEstado(estado);
			
			mapper.updateEstado(bandejaBean);
			sqlSession.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}

	public ReqMesaPartesDto getReqDigaById(Integer id)
	{
		ReqMesaPartesDto response=new ReqMesaPartesDto();
		SqlSession sqlSession=getSessionFactory().openSession();
		try{
			BandejaDigaMapper mapper = sqlSession.getMapper(BandejaDigaMapper.class);
			BandejaDiga bandeja = new BandejaDiga();
			bandeja = mapper.getBandejaById(id);

			response.setId(bandeja.getId());
			response.setEstadoDiga(bandeja.getEstadoDiga());
			response.setDetalle(bandeja.getDetalle());
			response.setRequerimientoId(bandeja.getRequerimientoId());
			response.setAsunto(bandeja.getAsunto());
			response.setUsuarioId(bandeja.getUsuarioId());
			response.setNombre(bandeja.getNombre());
			response.setDni(bandeja.getDni());
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return response;
		
	}
}
