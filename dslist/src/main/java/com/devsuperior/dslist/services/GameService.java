package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;

@Service //Ou @Component
public class GameService {
	
	@Autowired //Para injetar uma instancia do game repository
	private GameRepository gameRepository;
	
	public List<GameMinDTO> findAll() {
		//Pegamos todos os games
		List<Game> result = gameRepository.findAll(); //Recebe tudo do bd
		
		//Tratando nosso resultado, resumindo as informações para nosso dto
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
		return dto;
	}
	
}
