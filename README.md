# exercicio1-rss
Exercício 1 da disciplina Programação 3

Passos
  1. Teste a aplicação e certifique-se de que está tudo funcionando.
  2. Altere a aplicação RSS de forma que passe a processar o arquivo XML usando a classe `ParserRSS`.
  3. Uma vez processado o XML por meio do parser, é retornado um objeto do tipo `List<ItemRSS>`. 
  4. Use este objeto para popular um `ListView` por meio de um `Adapter` --- o widget deve manter o mesmo id do TextView (`conteudoRSS`).
  5. Inicialmente, use um `ArrayAdapter<ItemRSS>` para confirmar que está funcionando. 
  6. Altere o `ListView`, para mostrar título e data para cada item RSS, por meio de um Adapter personalizado, usando o layout em `res/layout/itemlista.xml` como base. Este layout não deve ser alterado.
  7. Faça com que, ao clicar em um título, o usuário seja direcionado para o navegador. Opcionalmente, pode abrir em uma nova activity com `WebView`.
  8. Modifique a aplicação para que passe a carregar o endereço do feed a partir de uma `SharedPreferences` com a chave `rssfeed`. O endereço padrão para o feed está disponível em res/values/strings.xml.
  9. Inclua a possibilidade de alterar a `SharedPreference` (`rssfeed`) incluindo um botão na `ActionBar` da aplicação. Ao clicar no botão, abra `PreferenciasActivity`, que deve exibir uma `PreferenceScreen` gerada automaticamente por meio de um `Fragment` que estende a classe `PreferenceFragment`, como visto em sala. Use o arquivo em `res/xml/preferencias.xml` para definir a tela.


# Status

| Passo | Completou? |
| ------ | ------ |
| 1 | **sim** |
| 2 | **sim** |
| 3 | **sim** |
| 4 | **sim** |
| 5 | **sim** |
| 6 | **sim** |
| 7 | **sim** |
| 8 | **sim** |
| 9 | **sim** |
