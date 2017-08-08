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
import org.unsa.mybatis.mapper.BandejaMesaPartesMapper;
import org.unsa.mybatis.mapper.RequerimientoMapper;
import org.unsa.mybatis.mapper.UsuarioMapper;

public class MesaPartesDao extends GeneralDao {
	private static final Logger LOGGER = Logger.getLogger(MesaPartesDao.class.getName());

	public List<UsuarioDto> getRequerimientos()
	{
		List<UsuarioDto> response=new ArrayList<UsuarioDto>();
		SqlSession sqlSession=getSessionFactory().openSession();
		try{
			RequerimientoMapper mapper = sqlSession.getMapper(RequerimientoMapper.class);
			List<Requerimiento> list=new ArrayList<Requerimiento>();
			list=  mapper.getAll();
			
			for(Requerimiento req:list)
			{
				UsuarioDto obj=new UsuarioDto();
				obj.setAsunto(req.getAsunto());
				obj.setDni(req.getUsuarioDni());
				obj.setNombre(req.getUsuarioNombre());
				obj.setId(req.getUsuarioId());
				response.add(obj);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return response;
		
	}
	
	public List<ReqMesaPartesDto> getReqMesaPartesByEstado(String estado)
	{
		List<ReqMesaPartesDto> response=new ArrayList<ReqMesaPartesDto>();
		SqlSession sqlSession=getSessionFactory().openSession();
		try{
			RequerimientoMapper mapper = sqlSession.getMapper(RequerimientoMapper.class);
			List<BandejaMesaPartes> list=new ArrayList<BandejaMesaPartes>();
			list=  mapper.listarBandejaByEstado(estado);
			
			for(BandejaMesaPartes req:list)
			{
				ReqMesaPartesDto obj=new ReqMesaPartesDto();
				obj.setId(req.getId());
				obj.setEstado(req.getEstado());
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
	
	public List<ReqMesaPartesDto> getReqMesaPartes()
	{
		List<ReqMesaPartesDto> response=new ArrayList<ReqMesaPartesDto>();
		SqlSession sqlSession=getSessionFactory().openSession();
		try{
			RequerimientoMapper mapper = sqlSession.getMapper(RequerimientoMapper.class);
			List<BandejaMesaPartes> list=new ArrayList<BandejaMesaPartes>();
			list=  mapper.listarBandeja();
			
			for(BandejaMesaPartes req:list)
			{
				ReqMesaPartesDto obj=new ReqMesaPartesDto();
				obj.setId(req.getId());
				obj.setEstado(req.getEstado());
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
			
			BandejaMesaPartesMapper mapper = sqlSession.getMapper(BandejaMesaPartesMapper.class);
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
	
	public boolean updateDetalleRequerimiento(ReqMesaPartesDto registro) {
		 SqlSession sqlSession=getSessionFactory().openSession();
		try {
			
			BandejaMesaPartesMapper mapper = sqlSession.getMapper(BandejaMesaPartesMapper.class);
			BandejaMesaPartes bandejaBean = new BandejaMesaPartes();

			bandejaBean.setId(registro.getId());
			bandejaBean.setDetalle(registro.getDetalle());	
			mapper.updateDetalle(bandejaBean);
			sqlSession.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	public ReqMesaPartesDto guardarBandejaDiga(ReqMesaPartesDto reqMesaPartes) {
		 SqlSession sqlSession=getSessionFactory().openSession();
		try {
			
			BandejaMesaPartesMapper mapper = sqlSession.getMapper(BandejaMesaPartesMapper.class);
			BandejaDiga bandejaDigaBean = new BandejaDiga();

			bandejaDigaBean.setId(reqMesaPartes.getId());
			bandejaDigaBean.setEstadoDiga(reqMesaPartes.getEstadoDiga());

			mapper.saveReqDiga(bandejaDigaBean);
			sqlSession.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return reqMesaPartes;
	}
	
}
