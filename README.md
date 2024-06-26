# API - Controle de pedidos

## Descrição

O projeto consiste em um CRUD para a cadastramento de produtos (products) e pedidos (orders), com as seguintes funcionalidades:

- CRUD de Produtos (Product): Permite a criação, leitura, atualização e exclusão de produtos.
- CRUD de Pedidos: (Order): Permite a criação, leitura, atualização e exclusão de pedidos, incluindo a associação de itens de pedido aos produtos correspondentes.

Na raiz do projeto, você encontra o arquivo Curso-Java-backend-spring-outras-ferramentas.pdf
com conteúdo teórico sobre o Spring e outras ferramentas que utilizamos.

## Estrutura do Projeto

O projeto está estruturado utilizando arquitetura hexagonal (Ports and Adapters) da seguinte forma:

- **Adapters:**
  - **in:**
    - **controller:** Classes com os endpoins de POST, PUT, DELETE E GET
    - **request:** Classes utilizadas nas requisições de product e order.
  - **out:**
      - **mapper:** Conversão de request para entity e entity para response.
      - **order:** Adapters que implementam as interfaces definidas em application/ports/out/order
      - **product:** Adapters que implementam as interfaces definidas em application/ports/out/product
      - **response:** Classes de resposta para as operações CRUD de product e order.
      - **repository:** Interfaces que implementam JpaRepository ou CrudRepository.
          - **entity:** Classes de entidade mapeadas.

- **Application:**
    - **core:**
      - **domain:** Classes de domínio.
      - **usecase:** Classes com as regras de negócio.
  - **ports:**
  - - **in:**
      - **order:** Interfaces de entrada de order (implementadas pelos usecases de order)
      - **product:** Interfaces de entrada de product (implementadas pelos usecases de product)
    - **out:**
      - **order:** Interfaces de saída de orders (implementadas pelos adpters de order para operações CRUD)
      - **product:** Interfaces de saída de pruducts (implementadas pelos adpters de product para operações CRUD)
- **config:** Configuração dos beans para cada use case e outras
- **exception:** Exceções customizadas e globais do projeto que respondem a possíveis falhas nas operações CRUD.
 
## Tecnologias Utilizadas

- **Spring Boot:** Framework principal para a construção do aplicativo.
- **Spring Web:** Para construção de aplicações web.
- **Spring Data JPA:** Para a persistência de dados.
- **Spring Doc - OpenAPI** Para documentação da API. 
- **Lombok:** Para reduzir o código boilerplate.
- **H2 Database:** Banco de dados em memória utilizado para desenvolvimento e testes.
- **Maven:** Gerenciador de dependências e ferramenta de construção do projeto.

### Pré-requisitos

- Java 14 ou superior
- Maven
- IDE de sua preferência

## Utilizado no desenvolvimento

- Java JDK 17
- Maven
- IntelliJ

## Configuração do ambiente de desenvolvimento

### Instalação do Java JDK 17

#### Passo 1: Baixar o JDK 17
Acesse o site oficial da Oracle para download do JDK: https://www.oracle.com/java/technologies/downloads/#jdk17-windows
- Selecione a versão adequada para o seu sistema operacional (Windows) e baixe o instalador (.exe).

#### Passo 2: Instalar o JDK 17
- Após baixar o instalador, execute o arquivo .exe.
- Siga as instruções do assistente de instalação.
- Anote o caminho de instalação (por exemplo, C:\Program Files\Java\jdk-17).

### Passo 3: Configurar Variáveis de Ambiente

Adicionar JAVA_HOME:

- Abra o Menu Iniciar e digite "variáveis de ambiente".
- Clique em "Editar as variáveis de ambiente do sistema".
- Na janela "Propriedades do Sistema", clique em "Variáveis de Ambiente".
- Na seção "Variáveis do sistema", clique em "Novo".
- Nome da variável: JAVA_HOME
- Valor da variável: C:\Program Files\Java\jdk-17 (ou o caminho onde você instalou o JDK).

Adicionar JAVA_HOME ao PATH:

- Ainda na janela "Variáveis de Ambiente", localize a variável Path na seção "Variáveis do sistema" e clique em "Editar".
- Clique em "Novo" e adicione o caminho %JAVA_HOME%\bin.

#### Passo 4: Verificar a Instalação
- Abra o Prompt de Comando (CMD).
- Digite o comando java -version e pressione Enter.
- Você deve ver a versão do Java instalada. Algo semelhante a:

```
java version "17.0.x"
Java(TM) SE Runtime Environment (build 17.0.x)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.x, mixed mode, sharing)
```
### Instalação do Maven

#### Passo 1: Baixar o Maven
- Acesse o site oficial do Maven: https://maven.apache.org/download.cgi.
- Na seção "Files", clique no link para baixar o arquivo ZIP da versão binária mais recente, por exemplo, apache-maven-3.x.y-bin.zip.

#### Passo 2: Extrair o Arquivo ZIP
- Depois de baixar o arquivo, extraia o conteúdo para uma pasta de sua escolha, por exemplo, C:\apache-maven.

#### Passo 3: Configurar Variáveis de Ambiente
- Adicionar MAVEN_HOME:
  - Abra o Menu Iniciar e digite "variáveis de ambiente".
  - Clique em "Editar as variáveis de ambiente do sistema".
  - Na janela "Propriedades do Sistema", clique em "Variáveis de Ambiente".
  - Na seção "Variáveis do sistema", clique em "Novo".
  - Nome da variável: MAVEN_HOME
  - Valor da variável: C:\apache-maven (ou o caminho onde você extraiu o Maven).
   
- Adicionar MAVEN_HOME ao PATH:

  - Ainda na janela "Variáveis de Ambiente", localize a variável Path na seção "Variáveis do sistema" e clique em "Editar".
  - Clique em "Novo" e adicione o caminho C:\apache-maven\bin (ou o caminho correspondente à pasta bin do Maven).

#### Passo 4: Verificar a Instalação
- Abra o Prompt de Comando (CMD).
- Digite o comando mvn -v e pressione Enter.
- Você deve ver a versão do Maven e detalhes sobre a sua instalação. Algo semelhante a:

```
Apache Maven 3.x.y (versão)
Maven home: C:\apache-maven
Java version: 1.8.0_xx, vendor: Oracle Corporation
```
### Instalação do IntelliJ IDEA

#### Passo 1: Baixar o IntelliJ IDEA
- Acesse o site oficial da JetBrains para download do IntelliJ IDEA: https://www.jetbrains.com/idea/download.
- Escolha a edição que você deseja instalar (Community Edition é gratuita e suficiente para a maioria dos projetos Java) e clique em "Download".

- #### Passo 2: Instalar o IntelliJ IDEA
- Após baixar o instalador, execute o arquivo .exe.
- Siga as instruções do assistente de instalação.
- Durante a instalação, você pode personalizar a instalação conforme necessário (por exemplo, adicionar atalhos ao menu Iniciar ou à área de trabalho,
  associar arquivos .java ao IntelliJ IDEA, etc.).
- Conclua a instalação clicando em "Finish".

#### Passo 3: Configurar o IntelliJ IDEA
- Abra o IntelliJ IDEA pela primeira vez.
- Você pode ser solicitado a importar configurações de uma instalação anterior. Se não tiver nenhuma, escolha "Do not import settings".
- Siga as instruções para configurar temas, plugins, etc.

#### Passo 4: Verificar a Configuração do JDK
- No IntelliJ IDEA, vá para File > Project Structure (ou pressione Ctrl+Alt+Shift+S).
- Na seção Platform Settings, selecione SDKs.
- Clique no ícone de + e selecione Add JDK.
- Navegue até o diretório onde você instalou o JDK 17 (por exemplo, C:\Program Files\Java\jdk-17) e selecione-o.
- Verifique se o JDK 17 está listado corretamente.

#### Passo 5: Abrir um Projeto Java Existente
- No IntelliJ IDEA, vá para File > Open.
- Navegue até o diretório do seu projeto Java existente.
- Selecione a pasta do projeto e clique em OK.
- O IntelliJ IDEA pode detectar automaticamente a estrutura do projeto e perguntar se você deseja abrir como um projeto Maven/Gradle,
  se aplicável. Selecione a opção apropriada.
- Se o projeto não tiver uma configuração de build (por exemplo, Maven ou Gradle), você pode precisar configurar manualmente o JDK. Vá para File > Project Structure e selecione
  o JDK 17 para o projeto.

#### Passo 6: Executar o Projeto
- Certifique-se de que todas as dependências do projeto estejam configuradas corretamente.
- Navegue até a classe principal do seu projeto (geralmente contém o método public static void main(String[] args)).
- Clique com o botão direito na classe principal e selecione Run 'NomeDaClassePrincipal'.
- O IntelliJ IDEA compilará e executará seu projeto. O console na parte inferior mostrará a saída do programa.

#### Dicas Adicionais
- Plugins: IntelliJ IDEA suporta muitos plugins que podem ajudar no desenvolvimento. Vá para File > Settings > Plugins para explorar e instalar plugins adicionais.
- Version Control: Se seu projeto estiver em um sistema de controle de versão (como Git), você pode clonar o repositório diretamente pelo IntelliJ IDEA. 
  Vá para File > New > Project from Version Control.

### Arquivo application.properties
```
spring.application.name=Orders
# ConfiguraÃ§Ã£o do banco de dados H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Springdoc OpenApi 3.1 & Swagger 3
springdoc.swagger-ui.path=/api-pedido.html
springdoc.api-docs.path=/api-pedido
springdoc.packagesToScan=br.com.campos.pedidos.adapters.in.controller
```

### Banco de Dados

O banco de dados H2 é inicializado automaticamente na memória quando a aplicação é executada. Para acessar a console do H2, 
acesse http://localhost:8080/h2-console e utilize as seguintes credenciais:

- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (deixe em branco eu informe a senha que você configurou na aplicação)

### Download do projeto

Você pode baixar o projeto para o seu computador de duas formas:

1. Fazer o download a partir do GitHUB
- Clique em Code
- Escolha Download ZIP
- Crie uma pasta no seu computador
- Copie o arquivo baixado para a pasta criada
- Descompacte o arquivo
2. Clone o repositório (Requer o Git instalado no seu computador)
```
   cd seu-repositorio
   git clone https://github.com/birapcampos/curso-java-backend-modulo3.git
```
2. Compilação e Execução: 
```
mvn clean install
mvn spring-boot:run  
```

### Após subir a aplicação, você pode acessar a API através do link abaixo:
http://localhost:8080/api-pedido.html

Este link irá abrir a interface (UI) da documentação da API, onde 
você pode fazer os testes seguindo os passos descritos no 
documento curso-java-backend-testando-api.pdf disponível na raiz do projeto.

### Você também pode utilizar o Postman ou outra ferramenta similar e utilizar os endpoint descritos abaixo.

### Exemplos de Endpoints

#### Product
- POST /api/v1/product: Cria um novo produto.
- GET /api/v1/product: Lista todos os produtos.
- GET /api/v1/product/{id}: Obtém um produto pelo ID.
- PUT /api/v1/product/{id}: Atualiza um produto pelo ID.
- DELETE /api/v1/product/{id}: Exclui um produto pelo ID.

#### Order
- POST api/v1/order: Cria um novo pedido.
- GET api/v1/order: Lista todos os pedidos.
- GET /api/order/{id}: Obtém um pedido pelo ID.
- PUT /api/order/{id}: Atualiza um pedido pelo ID.
- DELETE /api/order/{id}: Exclui um pedido pelo ID.

### Exemplos de JSON para criação e alteração de produtos:

#### Criar product: (não esqueça de definir o verbo HTTP como POST)
#### BODY:
```
{
  "name": "Geladeira",
  "price": 3250.90
}
```
#### Alterar product: (não esqueça de definir o verbo HTTP como PUT)
http://localhost:8080/api/v1/product/{id}
#### BODY:

```
{
  "name": "Geladeira",
  "price": 3500.00
}
```

#### Criar order: (não esqueça de definir o verbo HTTP como POST)
#### BODY:
```
{
  "customerName": "Cliente 001",
  "items": [
    {
      "product": {
        "id": 1
      },
      "quantity": 2
    },
    {
      "product": {
        "id": 2
      },
      "quantity": 1
    }

  ]
}
```

#### Alterar order: (não esqueça de definir o verbo HTTP como PUT)
http://localhost:8080/api/v1/order/1
#### BODY:
```
{
  "customerName": "Cliente XYZ",
  "items": [
    {
      "product": {
        "id": 1
      },
      "quantity": 5
    } 
  ]
}
```