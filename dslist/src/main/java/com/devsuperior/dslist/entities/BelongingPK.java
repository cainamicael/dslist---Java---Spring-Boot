package com.devsuperior.dslist.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable /*Para dizer no belonging que estes dois campos serão 1 atributo*/
public class BelongingPK {

	@ManyToOne //Tipo do relacionamento
	@JoinColumn(name = "game_id") //Chave estrangeira 01
	private Game game;
	
	@ManyToOne
	@JoinColumn(name = "list_id") //Chave estrangeira 02
	private GameList list;
	
	public BelongingPK() {
	}

	public BelongingPK(Game game, GameList list) {
		this.game = game;
		this.list = list;
	}

	public Game getGame() {
		return game;
	}

	public GameList getList() {
		return list;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setList(GameList list) {
		this.list = list;
	}

	@Override
	public int hashCode() {
		return Objects.hash(game, list);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BelongingPK other = (BelongingPK) obj;
		return Objects.equals(game, other.game) && Objects.equals(list, other.list);
	}
}
