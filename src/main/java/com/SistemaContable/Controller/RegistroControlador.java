package com.SistemaContable.Controller;

import com.SistemaContable.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio servicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "index";
	}

	@GetMapping("/Catalogo")
	public String catalogo(Model model) {
		model.addAttribute("dato", "Hola Mundo");
		return "catalogo";
	}

	@GetMapping("/LibroDiario")
	public String LibroDiario(Model model) {
		model.addAttribute("dato", "Hola Mundo");
		return "LibroDiario";
	}

	@GetMapping("/LibroMayor")
	public String LibroMayor(Model model) {
		model.addAttribute("dato", "Hola Mundo");
		return "LibroMayor";
	}

	@GetMapping("/BalanceDeComprobacion")
	public String BalanceDeComprobacion(Model model) {
		model.addAttribute("dato", "Hola Mundo");
		return "BalanceDeComprobacion";
	}

	@GetMapping("/EstadoDeResultado")
	public String EstadoDeResultado(Model model) {
		model.addAttribute("dato", "Hola Mundo");
		return "EstadoDeResultado";
	}

	@GetMapping("/BalanceGeneral")
	public String BalanceGeneral(Model model) {
		model.addAttribute("dato", "Hola Mundo");
		return "BalanceGeneral";
	}



}
