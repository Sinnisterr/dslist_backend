package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;


@Service
public class GameListService {

	// O Spring vai injetar automaticamente a depencia GameRepository.
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	
	// Obedecendo principio ACID Atomic, Consistente, Isolada e Durável
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		// map vai transformar todo objeto original que era game para meu objeto GameMinDTO
		// depois voce converte de novo apra uma lista normal com tolist.
		List<GameListDTO> dto = result.stream().map(x -> new GameListDTO(x)).toList();
		return dto;

	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for ( int i = min; i <= max; i++ ) {
			Long gameId = list.get(i).getId();
			gameListRepository.updateBelongingPosition(listId, gameId, i);
		}
		
			
	}
	

}
