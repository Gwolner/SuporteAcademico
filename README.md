# Sistema de Suporte Acadêmico - SSA

Projeto avaliativo desenvolvido para a disciplina de Desenvolvimento de Software Corporativo (DesCorp) do curso de Análise e Desenvolvimento de Sistemas.

## Descrição da avaliação

Foi solicitado pelo professor [Marcos André](https://github.com/marcosifpe) a elaboração de um projeto de sistema em Java EE que será avaliado durante quatro entregas.
Cada entrega visa explorar alguns aspectos do Java EE como JPA, JPQL e EJB. Além disso, foram utilizados a [dependência EclipseLink](https://mvnrepository.com/artifact/org.eclipse.persistence/eclipselink/2.5.0) como suporte ao mapeamento ORM do JPA e o framework [PrimeFAces](https://www.primefaces.org/showcase/) para a criação de páginas em JSF.

## Projeto proposto

Consiste em um Sistema de Suporte Acadêmico (SSA) que permite o aluno obter seu fardamento semestral, associar-se a uma Bolsa de auxilio aos estudos (monitoria, estágio, pesquisa ou apoio à baixa renda) além de obter emprestimos de livros didáticos para estudo.
O SSA tambem permite que professores obtenham o emprestimo de livros para que possam planejar suas aulas.

## Persistência

Para conexão ao banco de dados, foi usada uma [dependência MySQL](https://mvnrepository.com/artifact/mysql/mysql-connector-java) do Maven. A versão do MySQL Connector/J vai mudar conforme a versão do BD MySQL utilizado.

## Testes unitários

Cada entrega possui uma quantidade necessaria de testes unitários.
Para a realização destes testes foram utilizados as dependências [JUnit](https://mvnrepository.com/artifact/junit/junit/4.12) 
```
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```

e [DbUnit](https://mvnrepository.com/artifact/org.dbunit/dbunit/2.5.1), ambas do Maven.
```
<dependency>
    <groupId>org.dbunit</groupId>
    <artifactId>dbunit</artifactId>
    <version>2.5.3</version>
    <scope>test</scope>            
</dependency>
```

## Primeira entrega

Inicialmente o projeto consiste em realizar o CRUD para cada modelo/entidade (Entity), cujos testes unitários fazem uso de um arquivo dataset.xml para criar registros no banco e, posteriormente, atualizar, remover ou retornar estes registros.

## Segunda entrega

Além das funcionalidades ja mencionadas, agora apresenta JPQL para realizar vários tipos de consultas no banco, além de queryes de Update e Delete. Conta ainda com testes de validaçãos onde mensagens são exibidas caso os dados inseridos estarem incorretos.

## Terceira entrega

Adaptação da entrega anterior para uso do EJB.






