# Projeto Teste da Saraiva [books]

O projeto foi criado com base em tecnologias da especificação Java EE 6, portanto, dispõe de EJB 3.1, JPA 2 (Hibernate), JAX-RS 2.0 (Resteasy / Jackson).

Utilizei o Container Java EE Wildfly versão 10.1.0.Final, por toda sua facilidade de configuração e integração rápida com o Eclipse, cujo qual, utilizei o Oxygen.2.

O primeiro passo é efetuar o download do mesmo através do git pelo endereço: https://github.com/magneon/books.git

Assim que o download estiver concluído, abrir em sua IDE favorita.

Para isso, vá em File > Import... > Existing Maven Project

Na janela que se abre, navegue até o diretório raiz escolhido para download do código do Git.

Marque a caixa 'books', pois assim importará o projeto pai e seus respectivos módulos automaticamente e clique em Finish.

Caso você não tenha um Container Java EE instalado na máquina é possível obter um através do link: http://wildfly.org/downloads/

Depois de instalar o Container Java EE Wildfly 10.1.0.Final, navegue até a pasta do mesmo e vá até o diretório "configuration".
%WILDFLY_HOME%/standalone/configuration

Abra o arquivo: standalone.xml.

Navegue até a tag \<datasources\> e cole o seguinte conteúdo entre as tags datasources:

	<datasource jndi-name="java:jboss/datasources/SaraivaDS" pool-name="SaraivaDS" enabled="true" use-java-context="true">
		<connection-url>jdbc:mysql://IP_DO_BANCO_AQUI:3306/saraiva?useSSL=false</connection-url>
		<driver>mysql</driver>
		<pool>
			<min-pool-size>0</min-pool-size>
			<max-pool-size>100</max-pool-size>
			<prefill>true</prefill>
			<flush-strategy>FailingConnectionOnly</flush-strategy>
			<allow-multiple-users>true</allow-multiple-users>
		</pool>
		<security>
			<user-name>USUARIO</user-name>
			<password>SENHA</password>
		</security>
		<validation>
			<valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
			<check-valid-connection-sql>SELECT 1</check-valid-connection-sql>
			<background-validation>true</background-validation>
			<background-validation-millis>120000</background-validation-millis>
			<exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
		</validation>
	</datasource>

Atente-se aos detalhes:

1. Deve se trocar o ip de conexão com o banco de dados.
2. Deve se trocar o usuário do banco de dados.
3. Deve se trocar a senha do usuário do banco de dados.

Na sequência, cole também o seguinte conteúdo, logo após a última tag:

	<driver name="mysql" module="com.mysql">
		<xa-datasource-class>com.mysql.jdbc.DataSource</xa-datasource-class>
	</driver>

Além disso, faz-se necessário incluir o driver de conexão do banco de dados (MySQL), nos módulos padrões do Wildfly. Para isso, faça download do MySQL Driver Conector no http://mvnrepository.com/, buscando pelo nome: mysql-connector-java, na versão 5.1.38.

Depois de baixar o conector do MySQL, vá até a pasta do Wildfly 10.1.0.Final novamente no seguinte diretório:
%WILDFLY_HOME%\modules\system\layers\base\

Crie a pasta "com", caso não tenha.

Dentro dela, crie a pasta "mysql", caso não tenha.

Por fim, dentro da pasta "mysql" crie a pasta "main", caso não tenha.

Dentro da pasta "main", cole o jar do conector do MySQL e crie um arquivo xml, chamado module.xml, com o seguinte conteúdo:

	<?xml version="1.0" encoding="UTF-8"?>
	<module xmlns="urn:jboss:module:1.3" name="com.mysql">
	<resources>
		<resource-root path="mysql-connector-java-5.1.38.jar"/>
	</resources>
	<dependencies>
		<module name="javax.api"/>
		<module name="javax.transaction.api"/>
		<module name="javax.servlet.api" optional="true"/>
	</dependencies>
	</module>

Depois dessa configuração, agora é hora de criar o banco de dados no MySQL.

Crie um database chamado "saraiva", com o seguinte comando:

	CREATE DATABASE saraiva;

Na sequência, acesse o banco recém criado através do comando:

	USE saraiva;

Por fim, crie a tabela do projeto, através do comando:

	CREATE TABLE book (
			sku INT PRIMARY KEY NOT NULL DEFAULT 0,
			name VARCHAR(255),
			brand VARCHAR(255),
			price DECIMAL(16,2)
	);

Agora, com tudo isso feito, basta ir na IDE favorita, iniciar o Wildfly 10.1.0.Final, fazer o deploy da aplicação e acessar através do endereco: "http://localhost:8080/saraiva/"

Criei as operações baseadas no contexto "/saraiva" para melhor identificar o projeto.

As operações disponíveis são:

	Inclusão de um novo Livro
	Tipo de Operação: POST 
	Headers: Content-Type [application/json]
	URI: http://localhost:8080/books/rest/saraiva
	Corpo da Mensagem:
	{
		"sku": "{sku}"
	}


	Consulta de um Livro
	Tipo de Operação: GET
	URI: http://localhost:8080/books/rest/saraiva/{sku}
	Corpo da Mensagem: Não se aplica


	Listagem de Livros
	Tipo de Operação: GET
	URI: http://localhost:8080/books/rest/saraiva/
	Corpo da Mensagem: Não se aplica


	Listagem de Livros com Filtro
	Tipo de Operação: GET
	URI: http://localhost:8080/books/rest/saraiva/?price=X&limit=Y
	Corpo da Mensagem: Não se aplica
	Observação: 'X' e 'Y' são opcionais, ambos podem ser passados, assim como ambos podem ser excluídos, no caso de exclusão, a URI se tornara a mesma da operação acima.


	Exclusão de um Livro
	Tipo de Operação: DELETE
	URI: http://localhost:8080/books/rest/saraiva/{sku}
