package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		List<GameListDTO> dto = result.stream().map(x -> new GameListDTO(x)).toList();
		return dto;
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		//Pegamos a lista
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		//Vamos remover o elemento da sua posição usando uma lista em memória, usando o remove, que retorna o obj removido
		GameMinProjection obj = list.remove(sourceIndex); //Agora a lista está sem este game, e os elementos reposicionados
		
		//Agora vamos inserir o obj na posição que queremos
		list.add(destinationIndex, obj);
		
		//Agora vamos salvar a alteração - só precisamos salvar o que está no range entre o indice de origem e de destino, pois o resto se mantém
		
		//Vamos pegar o valor mínimo e máximo entre o indice de origem e destino
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}

}
