package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
import com.uca.capas.service.CategoriaService;
import com.uca.capas.service.LibroService;

@Controller
public class MainController {
	
	@Autowired
	LibroService libroService;
	
	@Autowired
	CategoriaService categoriaService;	
	
	@RequestMapping("/index")
	public ModelAndView index() {
		
		ModelAndView mav = new ModelAndView();

		mav.setViewName("index");
		
		return mav;
		
	}
	
	@RequestMapping("/categoria")
	public ModelAndView cat() {
		
		ModelAndView mav = new ModelAndView();
		Categoria categoria = new Categoria();
		
		mav.addObject("categoria", categoria);
		mav.setViewName("categoria");
		
		return mav;
		
	}
	
	@RequestMapping("/guardarCat")
	public ModelAndView formCategoria(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName("categoria");
		}else {
			categoriaService.save(categoria);
			mav.addObject("message", "Categoría guardada con éxito");
			mav.setViewName("index");
		}
		
		return mav;
		
	}
	
	@RequestMapping("/libro")
	public ModelAndView libro() {
		
		ModelAndView mav = new ModelAndView();
		List<Categoria> categorias = categoriaService.findAll();
		Libro libro = new Libro();
		
		mav.addObject("categorias", categorias);
		mav.addObject("libro", libro);
		mav.setViewName("libro");
		
		return mav;
		
	}
	
	@RequestMapping("/guardarLibro")
	public ModelAndView formLibro(@Valid @ModelAttribute Libro libro, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			List<Categoria> categorias = categoriaService.findAll();
			mav.addObject("categorias", categorias);
			mav.setViewName("libro");
		}else {
			libroService.save(libro);
			mav.addObject("message", "Libro guardado con éxito");
			mav.setViewName("index");
		}
		
		return mav;
		
	}
	
	@RequestMapping("/listado")
	public ModelAndView verLibros() {
		
		ModelAndView mav = new ModelAndView();
		List<Libro> libros = libroService.findAll();
		Libro libro = new Libro();
		
		mav.addObject("libros", libros);
		mav.setViewName("listado");
		
		return mav;
		
	}

}
