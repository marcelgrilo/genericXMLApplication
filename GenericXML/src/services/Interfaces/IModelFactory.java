package services.Interfaces;

import java.io.File;

/**
 * IModelFactory, interface que generaliza a cria��o dos modelos Dao. As
 * implementacoes desta interface devem prover metodos para converter os
 * arquivos xml em modelos
 */
public interface IModelFactory<TModel, TXMLModel> {
	public TModel CreateModels(File XMLFile);

	public TModel Convert(TXMLModel xmlModel);

}