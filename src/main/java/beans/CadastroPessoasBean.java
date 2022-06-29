package beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Pessoa;
import modelo.PessoaFisica;
import modelo.PessoaJuridica;
import modelo.Sexo;

@ManagedBean
@SessionScoped
public class CadastroPessoasBean {
	
	private Pessoa pessoaSelecionada;
	private Collection<Pessoa> lista;
	private String tipoNovaPessoa;
	
	public CadastroPessoasBean() {
		pessoaSelecionada = null;
		lista = new ArrayList<Pessoa>();
		
		for(int x=0; x<10; x++) {
			Pessoa p = (x%2==0) ? new PessoaFisica() : new PessoaJuridica();
			p.setNome(String.format("Fulano %02d",x));
			p.setEmail(String.format("Ffulano%02d@teste.com",x));
			p.setTelefone(String.format("9999999%02d",x));
			
			lista.add(p);
		}
	}
	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}
	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
	public Collection<Pessoa> getLista() {
		return lista;
	}
	public void setLista(Collection<Pessoa> lista) {
		this.lista = lista;
	}
	public void criar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if(tipoNovaPessoa == null) {
			contexto.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Você deve especificar o tipo! ",""));
			return;
		}
		if(tipoNovaPessoa.equals("PF")) {
			pessoaSelecionada = new PessoaFisica();
		}else if(tipoNovaPessoa.equals("PJ")) {
			pessoaSelecionada = new PessoaJuridica();
		}
	}
	public void salvar() {
		if(!lista.contains(pessoaSelecionada)) {
			lista.add(pessoaSelecionada);
		}
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso! ",""));    
	}
	public String cancelar() {
		pessoaSelecionada = null;
		tipoNovaPessoa = null;
		return "cadastropessoas.jsf";
	}
	public void excluir() {
		lista.remove(pessoaSelecionada);
		pessoaSelecionada = null;
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Pessoa Excluída com Sucesso!", "Detalhes"));
	}
	public Sexo[] getSexos() {
		return modelo.Sexo.values();
	}
	public String getTipoNovaPessoa() {
		return tipoNovaPessoa;
	}
	public void setTipoNovaPessoa(String tipoNovaPessoa) {
		this.tipoNovaPessoa = tipoNovaPessoa;
	}
	public boolean isPessoaFisicaSelecionada() {
		return pessoaSelecionada instanceof PessoaFisica;
	}
	public boolean isPessoaJuridicaSelecionada() {
		return pessoaSelecionada instanceof PessoaJuridica;
	}
	
	
	
	
	
	
	
	
}
