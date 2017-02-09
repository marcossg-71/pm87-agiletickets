package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	@Test
	public void criarUmaUnicaSessaoQuandoInicioIgualAFim() {
		//arrange
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2017, 2, 9);
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime(21, 0);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		//act
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		//assert
		Assert.assertNotNull("Lista de sessões não deve ser nula.",sessoes);
		Assert.assertEquals(1, sessoes.size());
		Sessao unica = sessoes.get(0);
		Assert.assertEquals("O espetáculo é diferente da esperada",espetaculo, unica.getEspetaculo());
		Assert.assertEquals("09/02/17",unica.getDia());
		Assert.assertEquals("21:00",unica.getHora());
	}
	@Test
	public void criarSessaoQuandoPeriocidadeDiariaMaisDeUmDia() {
		//arrange
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2017, 2, 9);
		LocalDate fim = new LocalDate(2017, 2, 11);
		LocalTime horario = new LocalTime(21, 0);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		//act
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		//assert
		Assert.assertEquals("Deve criar 3 sessões para 3 dias de espetáculo.", 3, sessoes.size());
	}
	
	@Test
	public void criarSessaoQuandoPeriocidadeSemanalMaisDeUmaSemana() {
		//arrange
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2017, 2, 9);
		LocalDate fim = new LocalDate(2017, 3, 10);
		LocalTime horario = new LocalTime(21, 0);
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		//act
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		//assert
		Assert.assertEquals(4, sessoes.size());
	}
	
	@Test(expected=RuntimeException.class)
	public void naoCriarSessaoQuandoInicioMaiorQueFim() {
		//arrange
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2017, 3, 10);
		LocalDate fim = new LocalDate(2017, 2, 9);
		LocalTime horario = new LocalTime(21, 0);
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		//act
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		//assert
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveCriarSessaoQuandoCamposObrigatoriosNaoPrenchidos() {
		//arrange
		Espetaculo espetaculo = null;
		LocalDate inicio = null;
		LocalDate fim = null;
		LocalTime horario = null;
		Periodicidade periodicidade = null;
		//act
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		//assert
	}
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
