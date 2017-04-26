package br.com.centauro.loja.pdvstatus.type;

public enum SatStatusEnum {
    DESBLOQUEADO(0),
    BLOQUEIO_SEFAZ(1),
    BLOQUEIO_CONTRIBUINTE(2),
    BLOQUEIO_AUTONOMO(3),
    BLOQUEIO_PARA_DESATIVACAO(4);

    private int status;
    
    private SatStatusEnum(int status) {
    	this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static SatStatusEnum fromInt(int status) {
	switch (status) {
	case 0:
	    return DESBLOQUEADO;
	case 1:
	    return BLOQUEIO_SEFAZ;
	case 2:
	    return BLOQUEIO_CONTRIBUINTE;
	case 3:
	    return BLOQUEIO_AUTONOMO;
	case 4:
	    return BLOQUEIO_PARA_DESATIVACAO;
	default:
	    return null;
	}
    }
    
    @Override
    public String toString() {
	switch (this) {
	case DESBLOQUEADO:
	    return "Desbloqueado";
	case BLOQUEIO_SEFAZ:
	    return "Bloqueado SEFAZ";
	case BLOQUEIO_CONTRIBUINTE:
	    return "Bloqueado Contribuinte";
	case BLOQUEIO_AUTONOMO:
	    return "Bloqueio Autônomo";
	case BLOQUEIO_PARA_DESATIVACAO:
	    return "Bloqueio Para Desativação";
	default:
	    return "";
	}
    }
}
