package org.unsa.business;

import java.util.List;

import javax.ejb.Local;

import org.unsa.mybatis.bean.Contoh;

@Local
public interface Bar {

    String decirHola();
    
    List<Contoh> listarCon();

}
