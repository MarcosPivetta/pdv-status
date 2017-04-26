package br.com.centauro.loja.pdvstatus.type;

public enum CodigoEeeEnum {
	SUCESSO(10000), 
	CORIDO_ATIVACAO_INVALIDO(10001), 
	SAT_EM_PROCESSAMENTO(10098), 
	ERRO_DESCONHECIDO(10099);

	private int codigoEee;

	private CodigoEeeEnum(int codigoEee) {
		this.codigoEee = codigoEee;
	}

	public int getStatus() {
		return codigoEee;
	}

	public static CodigoEeeEnum fromInt(int codigoEee) {
		switch (codigoEee) {
		case 10000:
			return SUCESSO;
		case 10001:
			return CORIDO_ATIVACAO_INVALIDO;
		case 10098:
			return SAT_EM_PROCESSAMENTO;
		case 10099:
			return ERRO_DESCONHECIDO;
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case SUCESSO:
			return "Resposta com sucesso";
		case CORIDO_ATIVACAO_INVALIDO:
			return "Código de ativação inválido";
		case SAT_EM_PROCESSAMENTO:
			return "SAT em processamento";
		case ERRO_DESCONHECIDO:
			return "Erro desconhecido";
		default:
			return "";
		}
	}
}
