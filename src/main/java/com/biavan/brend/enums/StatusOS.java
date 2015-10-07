package com.biavan.brend.enums;

public enum StatusOS {
	
	PENDENTE("Pendente"),
	EM_ANDAMENTO("Em Andamento"),
	FINALIZADA("Finalizada"),
	REABERTA("Reaberta"),
	CANCELADA("Cancelada");

    private final String text;

    private StatusOS(final String text) {
        this.text = text;
    }

    public String getText() {
    	return text;
    }
    
    @Override
    public String toString() {
        return text;
    }
    
    public static StatusOS getStatusByText(String text) {
		
		if ("Pendente".equals(text))
			return StatusOS.PENDENTE;

		if ("Em Andamento".equals(text))
			return StatusOS.EM_ANDAMENTO;
		
		if ("Finalizada".equals(text))
			return StatusOS.FINALIZADA;
		
		if ("Reaberta".equals(text))
			return StatusOS.REABERTA;
		
		if ("Cancelada".equals(text))
			return StatusOS.CANCELADA;
		
		return null;
	}
	
}
