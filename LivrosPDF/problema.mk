O processamento de macros deve ser realizado antes da montagem, sendo ativado a partir do módulo principal integrador do macro-montador. 
Deve permitir a definição de macros dentro de macros (macros aninhadas), 
bem como a chamada de macros dentro de macros (chamadas aninhadas), 
sendo, portanto, implementado em uma só passagem. 
O programa receberá como entrada um arquivo fonte informado para montagem e gerará como saída outro arquivo fonte .


Qual utiliza a tabela de uso, definição e de simbolos.
A tabela de uso, tabela de definição e tabela de símbolos são conceitos associados ao processo de ligação (linking) e ao sistema de ligação (linkage system). Vamos entender cada um deles:
Tabela de Uso (Use Table):
Definição: A tabela de uso é usada pelo ligador para rastrear referências externas feitas por diferentes módulos de um programa.
Função: Ela lista os símbolos que são referenciados, mas não definidos no módulo atual. Cada entrada na tabela de uso geralmente inclui o nome do símbolo e o endereço ou posição onde ele é usado.
Exemplo: Se um módulo A referencia uma função chamada funcaoX que é definida em outro módulo B, a tabela de uso de A teria uma entrada indicando que funcaoX é usado.
Tabela de Definição (Definition Table):

Definição: A tabela de definição é usada para rastrear os símbolos definidos em um módulo.
Função: Cada entrada na tabela de definição inclui o nome do símbolo, seu tipo e seu endereço ou posição no módulo. Isso ajuda o ligador a saber onde os símbolos são definidos.
Exemplo: Se um módulo B define uma função chamada funcaoX, a tabela de definição de B teria uma entrada indicando que funcaoX é definida e onde.
Tabela de Símbolos (Symbol Table):

Definição: A tabela de símbolos é uma tabela geral que contém informações sobre todos os símbolos usados e definidos no programa.
Função: Ela combina informações da tabela de uso e da tabela de definição, fornecendo uma visão geral de todos os símbolos no programa e seus respectivos tipos e localizações.
Exemplo: Uma entrada na tabela de símbolos pode incluir informações sobre um símbolo, como seu nome, tipo (usado ou definido), e endereço ou posição.
Estes conceitos são frequentemente utilizados em sistemas de ligação para garantir que todas as referências e definições de símbolos em diferentes módulos sejam resolvidas 
corretamente durante o processo de ligação, resultando em um programa executável coeso e funcional.