# number in words

Biblioteca java para escrever números por extenso para os seguintes tipos de números:

- Números Cardinais
- Números Ordinais
- Números Decimais
- Números Decimais com unidades
- Números Romanos
- Moeda

Atualmente estão disponíveis funcionalidades para os seguintes idiomas:

- Português (Cardinais, Ordinais, Decimais, Moeda)
- Inglês (Cardinais)
- Espanhol (Cardinais)

A biblioteca oferece configurações especiais para cada tipo de conversão, como por exemplo:

- Definir o Gênero para números ordinais e cardinais;
- Nos números decimais definir o Gênero da parte inteira e da parte decimal separadamente;
- Usar ou não vírgula para separar valores dos blocos mil, milhão, bilhão, etc.;
- Descrição especial para quantidade zero;
- Descrição espeacial para valores negativos (e positivos);
- Uso de apócope (espanhol);
- Uso de 'and' para separar valores em centenas (inglês);
- Definir o nome da moeda e das subdivisões (centavo);
- Definir a quantidade de casas decimais das subdivisões de moedas (ex. Real 2. Bitcoin 8);

## Exemplo Cardinal em portugês

```java
        var inPortuguese = NumberInWordsFactory
            .createCardinalInWordsBuilder()
            .forPortugueseLanguage()
            .build();

        //dois mil duzentos e cinquenta e dois
        System.out.println(inPortuguese.inWords(2252L)); 
```

### Usando gênero femenino

```java
        var inPortuguese = NumberInWordsFactory
            .createCardinalInWordsBuilder()
            .forPortugueseLanguage()
            .withFemaleGender()
            .build();

        //duas mil duzentas e cinquenta e duas
        System.out.println(inPortuguese.inWords(2252L));

        //dois milhões duzentas e cinquenta e duas mil
        System.out.println(inPortuguese.inWords(2252000L));
```

## Exemplo Cardinal em inglês

```java
        var inEnglish = NumberInWordsFactory.createCardinalInWordsBuilder()
            .forEnglishLanguage()
            .build();

        //two thousand two hundred fifty-two
        System.out.println(inEnglish.inWords(2252L));
        
        //two million two hundred fifty-two thousand
        System.out.println(inEnglish.inWords(2252000L));
```

### Usando 'and' para separar valores nas centenas e vírgula para milhares

```java
        var inEnglish = NumberInWordsFactory.createCardinalInWordsBuilder()
            .forEnglishLanguage()
            .withAndInHundred()
            .withCommaSeparator()
            .build();

        //two thousand, two hundred and fifty-two
        System.out.println(inEnglish.inWords(2252L));
        
        //two million, two hundred and fifty-two thousand
        System.out.println(inEnglish.inWords(2252000L));
```

## Exemplo Ordinal em português

````java
        var inPortuguese = NumberInWordsFactory.createOrdinalInWordsBuilder()
            .forPortugueseLanguage()
            .build();

        //décimo segundo
        System.out.println(inPortuguese.inWords(12L));

        //segundo milésimo ducentésimo quinquagésimo segundo
        System.out.println(inPortuguese.inWords(2252L));

        //segundo milionésimo ducentésimo quinquagésimo segundo milésimo
        System.out.println(inPortuguese.inWords(2252000L));
````

### Usando gênero femenino e vírgula

````java
        var inPortuguese = NumberInWordsFactory.createOrdinalInWordsBuilder()
            .forPortugueseLanguage()
            .withFemaleGender()
            .withCommaSeparator()
            .build();

        //décimo segundo
        System.out.println(inPortuguese.inWords(12L));
        
        //segunda milésima, ducentésima quinquagésima segunda
        System.out.println(inPortuguese.inWords(2252L));
        
        //segunda milionésima, ducentésima quinquagésima segunda milésima
        System.out.println(inPortuguese.inWords(2252000L));
````

## Exemplo Decimal em português

````java
        var inPortuguese = NumberInWordsFactory.createDecimalInWordsBuilder()
            .forPortugueseLanguage()
            .withCommaSeparator()
            .build();

        //um inteiro e dois décimos
        System.out.println(inPortuguese.inWords(new BigDecimal("1.2")));
        
        //mil, oitocentos e oitenta e oito inteiros e dois mil, duzentos e vinte e três décimos de milésimo
        System.out.println(inPortuguese.inWords(new BigDecimal("1888.2223")));
````

## Exemplo Decimal com Unidade em português

````java
        var inPortuguese = NumberInWordsFactory.createDecimalUnitInWordsBuilder()
            .forPortugueseLanguage()
            .build();

        //uma unidade e dois décimos
        System.out.println(inPortuguese.inWords(new BigDecimal("1.2")));
        
        //uma unidade
        System.out.println(inPortuguese.inWords(new BigDecimal("1")));
        
        //mil oitocentas e oitenta e oito unidades e dois mil duzentos e vinte e três décimos de milésimo
        System.out.println(inPortuguese.inWords(new BigDecimal("1888.2223")));
````

### Especificando a descrição da unidade
````java
        var inPortuguese = NumberInWordsFactory.createDecimalUnitInWordsBuilder()
            .forPortugueseLanguage()
            .withUnitDescription("metro")
            .withGender(Gender.MALE) //defaul is FEMALE
            .build();

        //um metro e dois décimos
        System.out.println(inPortuguese.inWords(new BigDecimal("1.2")));
        
        //um metro
        System.out.println(inPortuguese.inWords(new BigDecimal("1")));
        
        //mil oitocentos e oitenta e oito metros e dois mil duzentos e vinte e três décimos de milésimo
        System.out.println(inPortuguese.inWords(new BigDecimal("1888.2223")));
````

## Exemplo Moeda (Real) em português

````java
        var real = NumberInWordsFactory.createMoneyInWordsBuilder()
            .forRealInPortuguese()
            .withCommaSeparator()
            .build();

        //um real
        System.out.println(real.inWords(new BigDecimal("1")));
        
        //um real e vinte centavos
        System.out.println(real.inWords(new BigDecimal("1.2")));
        
        //mil, oitocentos e oitenta e oito reais e dois mil, duzentos e vinte e três décimos de milésimo
        System.out.println(real.inWords(new BigDecimal("1888.2223")));
        
        //vinte centavos de real
        System.out.println(real.inWords(new BigDecimal("0.2")));
        
        //dois milésimos de real
        System.out.println(real.inWords(new BigDecimal("0.002")));
````

## Exemplo Moeda (Bitcoin) em português

````java
var bitcoin = NumberInWordsFactory.createMoneyInWordsBuilder()
                .forBitcoinInPortuguese()
                .build();

        //um bitcoin
        System.out.println(bitcoin.inWords(new BigDecimal("1")));

        //um bitcoin e vinte milhões de satoshis
        System.out.println(bitcoin.inWords(new BigDecimal("1.2")));

        //mil, oitocentos e oitenta e oito bitcoins e vinte e dois milhões, duzentos e trinta mil satoshis
        System.out.println(bitcoin.inWords(new BigDecimal("1888.2223")));

        //vinte milhões de satoshis
        System.out.println(bitcoin.inWords(new BigDecimal("0.2")));

        //duzentos mil satoshis
        System.out.println(bitcoin.inWords(new BigDecimal("0.002")));

        //dois bitcoins e um satoshi
        System.out.println(bitcoin.inWords(new BigDecimal("2.00000001")));

        //um satoshi
        System.out.println(bitcoin.inWords(new BigDecimal("0.00000001")));

        //dois satoshis
        System.out.println(bitcoin.inWords(new BigDecimal("0.00000002")));

        //um bilionésimo de bitcoin
        System.out.println(bitcoin.inWords(new BigDecimal("0.000000001")));
        
        //um milhão de satoshis
        System.out.println(bitcoin.inWords(new BigDecimal("0.01")));

        //mil satoshis
        System.out.println(bitcoin.inWords(new BigDecimal("0.00001")));
````