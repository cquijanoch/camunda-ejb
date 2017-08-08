package org.unsa.common.dao;

import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.UsuarioDto;
import org.unsa.mybatis.bean.BandejaMesaPartes;
import org.unsa.mybatis.bean.Requerimiento;
import org.unsa.mybatis.bean.Usuario;
import org.unsa.mybatis.mapper.RequerimientoMapper;
import org.unsa.mybatis.mapper.UsuarioMapper;
import org.unsa.util.Constantes;

public class UsuarioDao extends GeneralDao {
	private static final Logger LOGGER = Logger.getLogger(UsuarioDao.class.getName());

	public UsuarioDto saveUser(UsuarioDto usuarioDto) {
		 SqlSession sqlSession=getSessionFactory().openSession();
		try {
			
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			Usuario usuarioBean = new Usuario();

			usuarioBean.setDni(usuarioDto.getDni());
			usuarioBean.setNombre(usuarioDto.getNombre());

			mapper.saveUsuario(usuarioBean);
			sqlSession.commit();
			usuarioDto.setId(usuarioBean.getUsuarioId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return usuarioDto;
	}
	
	public ReqMesaPartesDto saveRequerimiento(UsuarioDto usuarioDto) {
		 SqlSession sqlSession=getSessionFactory().openSession();
		 ReqMesaPartesDto requerimiento=new ReqMesaPartesDto();
		try {
			
			RequerimientoMapper mapper = sqlSession.getMapper(RequerimientoMapper.class);
			Requerimiento requerimientoBean = new Requerimiento();

			requerimientoBean.setUsuarioId(usuarioDto.getId());
			requerimientoBean.setAsunto(usuarioDto.getAsunto());
			mapper.saveReq(requerimientoBean);
			requerimiento.setUsuarioId(requerimientoBean.getUsuarioId());
			requerimiento.setDni(requerimientoBean.getUsuarioDni());
			requerimiento.setAsunto(requerimientoBean.getAsunto());
			requerimiento.setRequerimientoId(requerimientoBean.getRequerimientoId());
			
			sqlSession.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return requerimiento;
	}
	
	public UsuarioDto searchUserByDni(String dni) {
		 SqlSession sqlSession=getSessionFactory().openSession();
		 UsuarioDto response=new UsuarioDto();
		try {
			
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);

			Usuario usuarioBean=mapper.getUserByDni(dni);
			if(usuarioBean==null)
				return null;
			
			response.setId(usuarioBean.getUsuarioId());
			response.setNombre(usuarioBean.getNombre());
			response.setDni(usuarioBean.getDni());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return response;
	}
	
	public ReqMesaPartesDto saveReqMesaPartes(ReqMesaPartesDto registroMesaPartes) {
		 SqlSession sqlSession=getSessionFactory().openSession();
		 
		try {
			
			RequerimientoMapper mapper = sqlSession.getMapper(RequerimientoMapper.class);
			BandejaMesaPartes requerimientoBean = new BandejaMesaPartes();
			requerimientoBean.setRequerimientoId(registroMesaPartes.getRequerimientoId());
			requerimientoBean.setEstado(Constantes.ESTADO_REQ_SIN_CALIFICACION);
			mapper.saveBandejaMesaPartes(requerimientoBean);
			sqlSession.commit();
			registroMesaPartes.setEstado(requerimientoBean.getEstado());
			registroMesaPartes.setId(requerimientoBean.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return registroMesaPartes;
	}
	
}
