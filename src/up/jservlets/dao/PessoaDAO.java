package up.jservlets.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import up.jservlets.bean.Pessoa;

public class PessoaDAO {
	
	private String archive;
	private String path;

	public void setPath(String path){
		this.path = path;
	}
	
	public void setArchive(String archive){
		this.archive = archive;
	}
	
	public List<Pessoa> getLista() throws Exception{
		List<Pessoa> pessoas = new ArrayList<>();
		Pessoa pessoa;
		//this.setPath("C:\\temp\\");
		
		File archpessoa = new File(path);
		
		String[] lista = archpessoa.list();
		
		for(String l : lista){
			if (l.contains("@")){
				this.setArchive(l);
				pessoa = (Pessoa)deserializaObjeto();
				pessoas.add(pessoa);
			}
		}
		
		return pessoas;
	}
	
	public void salvaPessoa(Pessoa pessoa) throws Exception{
		serializaObjeto(pessoa);
	}
	
	public boolean removePessoa() throws Exception{
		File archpessoa = new File(path + File.separator + archive);
		
		return archpessoa.delete();
	}
	
	public Pessoa obtemPessoa() throws Exception{
		this.setPath("C:\\temp\\");
		return (Pessoa)deserializaObjeto();
	}
	
	private void serializaObjeto(Object obj) throws Exception{
		FileOutputStream fos = new FileOutputStream(path + File.separator + archive);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.close();
	}
	
	private Object deserializaObjeto() throws Exception{
		FileInputStream fis = new FileInputStream(path + File.separator+ archive);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object result = ois.readObject();
		ois.close();

		return result;
	}	
}