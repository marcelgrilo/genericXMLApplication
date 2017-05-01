# genericXMLApplication

## Antes de começar:
Antes de rodar a aplicação, deve-se primeiro criar um banco de dados mysql com o nome de genericxmldb. As tabelas são geradas pelo hibernate e não precisam ser criadas na mão.

## Overview da aplicação
Ao executar o arquivo genericXML.jar, uma janela swing deve aparecer.

A primeira tela que aparece é a tela principal, nela observa-se dois botões Start e Stop, para inicializar e parar a execução da navegação pela lista de servidores cadastrados e consequente download(do ftp server), descompactação, conversão (XML -> objeto JAVA) e persistencia dos dados no banco.
ainda é possível observar em cinza, os dados da Runtime atual, sendo possivel observar quantos núcleos tem a máquina, quantas threads a aplicação está usando, a quantidade de memória total e livre. E por fim observa-se uma área de texto par a logs do sistema.

Ao acionar o menu View > Config, navega-se para a tela de gestão de servidores, onde pode-se cadastrar servidores ftp para que os arquivos de dados possam ser baixados e persistidos.

## O formato dos arquivos:
Os arquivos XML devem ter a seguinte identidade.
(neste caso, o modelo a ser mapeado é apenas o Info)
´´´<infoList>
	<info>
		<ip>242.157.159.46</ip>
		<port>2581</port>
		<data>lorem ipsum</data>
	</info>
	...
	...
</infoList>
´´´

A aplicação está preparada para novos modelos de xml, bantando apenas criar novas classes de mapeamento para tais modelos (Foi utilizado JAXB para o mapeamento de XML para objeto JAVA).

## Desenvolcimento
Durante o desenvolvimento foi criado um servidor FTP localmente com o filezilla server e inserido o arquivo xml contendo o conteúdo logo acima compactado como zip.
A aplicação então foi configurada com os dados do servidor local e entao executou a tarefa persistindo os dados de Infos na base de dados.

A lista de servidores também é armazenada na base de dados.

O padrão de desenvolvimento fugiu um pouco do mvc, criando uma camada extra de serviços, que era responsável por realizar as tarefas de download, descompactação e conversão do modelo para xml. Porém, a parte da interface gráfica foi mais voltada ao MVC. Ainda, o modelo MVC fora um pouco alterado para separar melhor os componentes do sistema, como por exemplo, existem as classes de controle, controle.dao e controle.ui, cada um destes pacotes separando as responsabilidades de cada "setor" de controllers.

## testes unitários
Existem apenas dois testes unitários, onde o primeiro verifica a persistência de objetos no banco com hibernate e o segundo testa a conversão dos arquivos XML em objetos java(Info).(acabei não aplicando TDD com medo do tempo não dar).

## Interfaces
As interfaces foram utilizadas afim de aumentar a separação das camadas do sistema, de modo que as camadas geralmente se comunicam entre si através dos métodos expostos pelas interfaces.

A interface IUnpack, na camada de serviço, foi criada para que se possa inserir mais metodos de descompactação, no caso da aplicação atual foi implementado o unzip, mas qualquer outro método pode ser implementado e abstraido por esta interface.

A interface IModelFactory, por sua vez é utilizada para a inserção de novos modelos XML, sendo assim, a aplicação fica genérica para a contrução de modelos xml.

## Threads
ao iniciar a aplicação, o controle responsável pela main view cria uma thread de monitoramento do sistema, que atualiza os dados cada 3 segundos. Quando se clica em Start, uma nova thread é iniciada e a cada 24 horas executa a varredura pelos servidores, fazendo os downloads, descompactando, convertendo e persistindo os dados provindos do servidor FTP.

O número de threads que executam as operações de prodessamento dos arquivos é limitada para o número de núcleos presentes na cpu. Note que pode-se criar mais thread do que isto, porém pode haver muitas trocas de contrxto. Normalmente uso a regra de Número de cores + 1 para fazer uma threadpool nas minhas aplicações, mas esta pode utilizar um pouco mais. 

## Persistência
Foi utilizado MySql junto com o hybernate para a persistência dos dados. Assim, foram criadas daos para cada modelo que se deseja persistir. A utilização de uma classe GenericDao facilita o desenvolvimento, mantendo ja implementados os metodos CRUDE, sendo que dada 'ModeloDAO' que a extenda tenha apenas que implementar métodos específicos, caso existam.

## UI, Views e Controller
O sistema de UI foi criado para seguir um sistema hierárquico da forma:
MainFrameController
--MenuBarrController
--CenterControlelr
----ConfigController
----MainController
------RuntimeDataController
------Outros controllers(que não são da camada de ui)

As Views foram criadas da forma a evitar qualquer lógica de negócios, sendo que existe um sistema de comunicação com seus controllers correspondentes através das interfaces IChangeView e IView

## Outros
 Esta aplicação foi feita no eclipse.
 O repositório git mantem a workspace do eclipse junto com o projeto.
 Como foi muito rápido, não pensei tanto em manter organizado, e isto me incomoda muito. O correto seria isolar melhor as camadas da aplicação, domínio, serviços e infra estrutura(base de dados), e aplicar DDD para se desenvolver uma aplicação mais adaptável possível, e é claro Utilizar TDD corretamente.








