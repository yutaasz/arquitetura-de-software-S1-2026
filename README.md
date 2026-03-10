# Refatoração de Sistema Legado

Bem-vindos, futuros Arquitetos de Software!

Vocês acabaram de ser contratados para assumir a manutenção de um sistema legado de Gestão de Produtos. O desenvolvedor anterior estava com pressa, o prazo era curto e ele aplicou a famosa metodologia **"Go-Horse"**.

O código atual (localizado em `ProdutoController.java`) "funciona", mas é uma bomba-relógio arquitetural. Ele mistura regras de preço, validações de marketing e acesso direto ao banco de dados em um único método. Qualquer mudança no banco quebra a API, e não há segurança alguma sobre o tipo de dado que está sendo trafegado.

Sua missão nesta Sprint é colocar ordem na casa aplicando Engenharia de Software de verdade.

## Sua História na Sprint

Você deve refatorar o `ProdutoController.java` aplicando rigorosamente:
1. **O Padrão Arquitetural MVC** (Model-View-Controller).
2. **Princípios SOLID** (com foco especial em *Single Responsibility Principle* e *Dependency Inversion Principle*).
3. **Modelos de Domínio Ricos** (A lógica de negócio deve pertencer aos objetos de negócio, e não ao Controller ou ao Service).

**⚠️ Regra de Ouro:** Não altere as versões do Java e do Spring Boot no `pom.xml`. O servidor de produção já está configurado com essas versões e não podemos atualizá-lo agora.

### O que será avaliado na sua refatoração?
* **Camada de View:** Os DTOs de Request e Response foram criados corretamente? O sistema parou de usar aquele `Map<String, Object>` perigoso?
* **Camada de Negócio:** As regras de "Produto Premium" e "Desconto de Atacado" estão nas classes corretas (Entities/Business Objects)?
* **Camada de Dados:** O acesso ao banco (Repository) foi isolado das regras de negócio?
* **Inversão de Dependências:** Foram criadas interfaces para a comunicação entre as camadas?

---

## Instruções de Entrega (LEIA COM ATENÇÃO)

No mercado de trabalho, seguir o fluxo de versionamento é tão importante quanto escrever um bom código. Para realizar a entrega desta atividade, siga **rigorosamente** os passos abaixo:

1. **Fork do Repositório:**
   Realize o *fork* deste repositório base para a sua conta pessoal no GitHub.

2. **Crie uma Branch de Feature:**
   No seu repositório "forkado", crie uma nova branch para trabalhar.
   A branch **deve** ter o nome exato: `feature/MVC`

3. **Visibilidade do Repositório:**
   Certifique-se de que o seu repositório no GitHub está configurado como **Público**. Se estiver privado, eu não conseguirei acessar e avaliar o seu código.

4. **Abertura do Pull Request (PR) e Envio:**
  * Após finalizar e "commitar" seu código na branch `feature/MVC`, abra um **Pull Request (PR)** apontando para a sua própria branch `main`.
  * **A entrega oficial:** Copie o link deste Pull Request aberto e anexe na atividade do Google Classroom.

---

## 🌟 Desafio Extra: Nível Arquiteto Cloud (Opcional)

Se você já terminou, separou as responsabilidades e acha que o código está lindo na sua máquina, que tal testar seus limites? No mercado real, o código só está "pronto" quando passa por uma esteira de automação.

Se quiser colocar um diferencial de peso no seu portfólio, tente implementar um (ou todos) os desafios abaixo:

* **1. CI com GitHub Actions:** Configure uma esteira que rode um `mvn clean test` a cada push na sua branch. Quero ver o "selo verde" no seu PR!
* **2. Qualidade com SonarCloud:** Integre o SonarCloud ao seu repositório para escanear *Code Smells* e falhas de segurança. (Anexe o print do *Quality Gate* na entrega).
* **3. Evolução para Spring Data JPA:** Remova o `JdbcTemplate` com SQL manual e implemente a persistência usando o ecossistema do Spring Data JPA.

*(Dica: A documentação do GitHub e do Spring são suas melhores amigas aqui. Fique à vontade para usar IAs como apoio ao estudo dessas ferramentas).*

---
**Boa sorte, e lembrem-se: Arquitetura consiste em manter as opções abertas!** 

*Prof. Martins*