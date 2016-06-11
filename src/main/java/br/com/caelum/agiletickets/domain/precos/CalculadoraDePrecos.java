package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;

		if (isCinema(sessao) || isShow(sessao)) {
			// quando estiver acabando os ingressos...
			if (getPercentualDisponivel(sessao) <= 0.05) {
				preco = sessao.getPrecoComAumento();
			} else {
				preco = sessao.getPreco();
			}
		} else if ((isBallet(sessao)) || (isOrquestra(sessao))) {
			if (getPercentualDisponivel(sessao) <= 0.50) {
				preco = sessao.getPrecoComAumento();
			} else {
				preco = sessao.getPreco();
			}

			if (sessao.getDuracaoEmMinutos() > 60) {
				preco = preco.add(aumentaPreco(sessao, 10));
			}
		} else {
			// nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal aumentaPreco(Sessao sessao, double taxa) {
		taxa = taxa / 100;
		return sessao.getPreco().multiply(BigDecimal.valueOf(taxa));
	}

	private static double getPercentualDisponivel(Sessao sessao) {
		return qtdeIngressosDisponiveis(sessao)
				/ sessao.getTotalIngressos().doubleValue();
	}

	private static int qtdeIngressosDisponiveis(Sessao sessao) {
		return sessao.getTotalIngressos() - sessao.getIngressosReservados();
	}

	private static boolean isOrquestra(Sessao sessao) {
		return sessao.getEspetaculo().getTipo()
				.equals(TipoDeEspetaculo.ORQUESTRA);
	}

	private static boolean isBallet(Sessao sessao) {
		return sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET);
	}

	private static boolean isShow(Sessao sessao) {
		return sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW);
	}

	private static boolean isCinema(Sessao sessao) {
		return sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA);
	}

}