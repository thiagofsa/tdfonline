package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Paciente;


public interface PacienteDAOI {
		 
	    void addPaciente(Paciente paciente);
	     
	    List<Paciente> findAllPacientes();
	     
	    void deletePacienteByID(Integer id);
	     
	    Paciente findByID(Integer id);
	     
	    void updatePaciente(Paciente paciente);
	
	
}
