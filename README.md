# numberinwords

Este código fornece uma implementação da interface `NumberInWords` que permite escrever números Cardinais e Ordinais inteiros por extenso em português.

Em breve implementações para outros idiomas serão incluídas.

## Exemplos de uso

Exemplo de como criar uma instância da classe `CardinalInPortugueseWords` e usá-la para escrever por extenso:

```java
CardinalInPortugueseWords converter = new CardinalInPortugueseWords.Builder()
        .withMaleGender()
        .withCommaSeparator()
        .build();

// "um bilhão, duzentos e trinta e quatro milhões, quinhentos e sessenta e sete mil, oitocentos e noventa"
System.out.println(converter.inWords(1234567890L)); 
```

Exemplo de como criar uma instância da classe `OrdinalInPortugueseWords` e usá-la para escrever por extenso:

```java
OrdinalInPortugueseWords converter = new OrdinalInPortugueseWords.Builder()
        .withFemaleGender()
        .withCommaSeparator()
        .build();

System.out.println(converter.inWords(15L)); // "décima quinta"
```