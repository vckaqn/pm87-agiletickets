package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
	public void deveCriarSomenteUmaSessaoCasoAsDatasSejamIguais() {
		Espetaculo e = new Espetaculo();
		
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate();
		LocalTime horario = new LocalTime(); 
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		List<Sessao> sessoes = e.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertTrue(sessoes.size() == 1);
	}
	
	@Test
	public void deveCriarSessoesDiariasEntreAsDatasDeInicioEFim() {
		Espetaculo e = new Espetaculo();
		
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().plusDays(10);
		LocalTime horario = new LocalTime(); 
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		List<Sessao> sessoes = e.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertTrue(sessoes.size() == 11);
	}
	
	@Test
	public void deveCriarSessoesSemanaisEntreAsDatasDeInicioEFim() {
		Espetaculo e = new Espetaculo();
		
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().plusDays(15);
		LocalTime horario = new LocalTime(); 
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		List<Sessao> sessoes = e.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertTrue(sessoes.size() == 3);
	}

	@Test
	public void naoDeveCriarSessaoCasoADataInicioMaiorQueDataFim() {
		Espetaculo e = new Espetaculo();
		
		LocalDate inicio = new LocalDate().plusDays(2);
		LocalDate fim = new LocalDate();
		LocalTime horario = new LocalTime(); 
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		List<Sessao> sessoes = e.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertTrue(sessoes.size() == 0);
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
