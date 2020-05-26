package com.uca.capas.service;

import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.uca.capas.dao.CategoriaDAO;
import com.uca.capas.dao.LibroDAO;
import com.uca.capas.domain.Libro;

@Service
public class LibroServiceImpl implements LibroService {
	
	@Autowired
	CategoriaDAO categoriaDAO;
	
	@Autowired
	LibroDAO libroDAO;

	@Override
	public List<Libro> findAll() throws DataAccessException {
		return libroDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Libro l) throws DataAccessException {

		l.setCategoria(categoriaDAO.findOne(l.getCcategoria()));
		l.setFingreso(new Date());
		libroDAO.save(l);

	}

}
