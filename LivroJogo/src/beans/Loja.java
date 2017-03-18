package beans;

import programa.dados.Bolsa;

public class Loja {

	private Bolsa artigos;

	public Loja(Bolsa artigos) {
		super();
		this.setArtigos(artigos);
	}

	public Bolsa getArtigos() {
		return artigos;
	}

	public void setArtigos(Bolsa artigos) {
		this.artigos = artigos;
	}
	
	
}
