# number in words

Biblioteca java para escrever números por extenso para os seguintes tipos de números:

- Números Cardinais
- Números Ordinais
- Números Decimais
- Números Decimais com unidades
- Números Fracionários
- Números Romanos
- Moedas
- Datas

Atualmente estão disponíveis funcionalidades para os seguintes idiomas:

- Português (Cardinais, Ordinais, Decimais, Fracionários, Moedas e Datas)
- Inglês (Cardinais, Ordinais e Datas)
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
- Definir apenas dia e mês ou mês e ano para Datas

## Exemplo Cardinal em portugês

```java
        var inPortuguese = NumberInWordsFactory.createCardinalBuilderChooser()
            .forPortugueseLanguage()
            .build();

        //dois mil duzentos e cinquenta e dois
        System.out.println(inPortuguese.inWords(2252L)); 
```

### Usando gênero femenino

```java
        var inPortuguese = NumberInWordsFactory.createCardinalBuilderChooser()
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
        var inEnglish = NumberInWordsFactory.createCardinalBuilderChooser()
            .forEnglishLanguage()
            .build();

        //two thousand two hundred fifty-two
        System.out.println(inEnglish.inWords(2252L));
        
        //two million two hundred fifty-two thousand
        System.out.println(inEnglish.inWords(2252000L));
```

### Usando 'and' para separar valores nas centenas e vírgula para milhares

```java
        var inEnglish = NumberInWordsFactory.createCardinalBuilderChooser()
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
        var inPortuguese = NumberInWordsFactory.createOrdinalBuilderChooser()
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
        var inPortuguese = NumberInWordsFactory.createOrdinalBuilderChooser()
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

## Exemplo Ordinal em Inglês

````java
        var ordinalInEnglish = NumberInWordsFactory.createOrdinalBuilderChooser()
            .forEnglishLanguage()
            .build();

        //first
        System.out.println(ordinalInEnglish.inWords(1L));

        //tenth
        System.out.println(ordinalInEnglish.inWords(10L));

        //twenty-fifth
        System.out.println(ordinalInEnglish.inWords(25L));

        //eighty-eighth
        System.out.println(ordinalInEnglish.inWords(88L));

        //one hundredth
        System.out.println(ordinalInEnglish.inWords(100L));

        //one hundred first
        System.out.println(ordinalInEnglish.inWords(101L));

        //one thousandth
        System.out.println(ordinalInEnglish.inWords(1000L));

        //one thousand and second
        System.out.println(ordinalInEnglish.inWords(1002L));
````

### Usando Representação numérica (1st, 2nd, etc)

````java
        var ordinalInEnglish = NumberInWordsFactory.createOrdinalBuilderChooser()
            .forEnglishLanguage()
            .withNumberRepresentation()
            .build();

        //1st
        System.out.println(ordinalInEnglish.inWords(1L));

        //2st
        System.out.println(ordinalInEnglish.inWords(2L));

        //3rd
        System.out.println(ordinalInEnglish.inWords(3L));

        //10th
        System.out.println(ordinalInEnglish.inWords(10L));

        //25th
        System.out.println(ordinalInEnglish.inWords(25L));

        //1000th
        System.out.println(ordinalInEnglish.inWords(1000L));

        //1002nd
        System.out.println(ordinalInEnglish.inWords(1002L));
````

## Exemplo Decimal em português

````java
        var inPortuguese = NumberInWordsFactory.createDecimalBuilderChooser()
            .forPortugueseLanguage()
            .withCommaSeparator()
            .build();

        //um inteiro e dois décimos
        System.out.println(inPortuguese.inWords(new BigDecimal("1.2")));
        
        //mil, oitocentos e oitenta e oito inteiros e dois mil, duzentos e vinte e três décimos de milésimo
        System.out.println(inPortuguese.inWords(new BigDecimal("1888.2223")));
````

### Usando pronúncia com ponto

````java
        DecimalInWords decimalNumber = NumberInWordsFactory.createDecimalBuilderChooser()
            .forPortugueseLanguage()
            .withFloatPointPronuntiation()
            .build();

        //dez ponto um
        System.out.println(decimalNumber.inWords(new BigDecimal("10.1")));

        //zero ponto cinquenta e cinco
        System.out.println(decimalNumber.inWords(new BigDecimal("0.55")));

        //vinte e dois ponto zero zero dezoito
        System.out.println(decimalNumber.inWords(new BigDecimal("22.0018")));
````

## Exemplo Decimal com Unidade em português

````java
        var inPortuguese = NumberInWordsFactory.createDecimalUnitBuilderChooser()
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
        var inPortuguese = NumberInWordsFactory.createDecimalUnitBuilderChooser()
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
        var real = NumberInWordsFactory.createMoneyBuilderChooser()
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
        var bitcoin = NumberInWordsFactory.createMoneyBuilderChooser()
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

## Exemplo Moeda Customizada em português

````java
        var lira = NumberInWordsFactory.createMoneyBuilderChooser()
            .forPortugueseLanguage()
            .withCurrencyName("lira turca", "liras turca")
            .withCentsName("kuruş", "kuruş")
            .withSubdivisionDecimalPlaces(2)
            .withGenderForIntegerPart(Gender.FEMALE)
            .withGenderForCentsPart(Gender.MALE)
            .build();

        //uma lira turca
        System.out.println(lira.inWords(new BigDecimal("1.00")));
        
        //duas liras turca
        System.out.println(lira.inWords(new BigDecimal("2.00")));
        
        //uma lira turca e vinte e cinco kuruş
        System.out.println(lira.inWords(new BigDecimal("1.25")));
        
        //um kuruş
        System.out.println(lira.inWords(new BigDecimal("0.01")));
```` 

## Exemplo Data em português

````java
        var date = NumberInWordsFactory.createDateBuilderChooser()
            .forPortugueseLanguage()
            .build();

        //trinta e um de dezembro de dois mil e vinte e três
        System.out.println(date.inWords(LocalDate.of(2023, 12, 31)));

        //primeiro de janeiro de dois mil e vinte e três
        System.out.println(date.inWords(LocalDate.of(2023, 1, 1)));
````

### Usando apenas dia e mês

````java
        var date = NumberInWordsFactory.createDateBuilderChooser()
            .forPortugueseLanguage()
            .usingDayAndMonth()
            .build();

        //trinta e um de dezembro
        System.out.println(date.inWords(LocalDate.of(2023, 12, 31)));

        //primeiro de janeiro
        System.out.println(date.inWords(LocalDate.of(2023, 1, 1)));
````

### Usando apenas mês e ano

````java
        var date = NumberInWordsFactory.createDateBuilderChooser()
            .forPortugueseLanguage()
            .usingMonthAndYear()
            .build();

        //dezembro de dois mil e vinte e três
        System.out.println(date.inWords(LocalDate.of(2023, 12, 31)));

        //janeiro de dois mil e vinte e três
        System.out.println(date.inWords(LocalDate.of(2023, 1, 1)));
````

## Exemplo Data em inglês

````java
        var dateInEnglish = NumberInWordsFactory.createDateBuilderChooser()
            .forEnglishLanguage()
            .build();

        //thirty-first December two thousand and twenty-three
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 12, 31)));
        
        //first January two thousand and twenty-three
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 1, 1)));
````

### Usando 'the' antes do dia

````java
        var dateInEnglish = NumberInWordsFactory.createDateBuilderChooser()
        .forEnglishLanguage()
        .withTheBeforeDay()
        .build();

        //the thirty-first December two thousand and twenty-three
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 12, 31)));

        //the first January two thousand and twenty-three
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 1, 1)));
````

### Usando 'the' antes e 'of' depois do dia

````java
        var dateInEnglish = NumberInWordsFactory.createDateBuilderChooser()
            .forEnglishLanguage()
            .withTheBeforeDay()
            .withOfAfterDay()
            .build();

        //the thirty-first of December two thousand and twenty-three
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 12, 31)));
        
        //the first of January two thousand and twenty-three
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 1, 1)));
````

### Usando mês antes do dia

````java
        var dateInEnglish = NumberInWordsFactory.createDateBuilderChooser()
        .forEnglishLanguage()
        .withMonthFirst()
        .build();

        //December thirty-first two thousand and twenty-three
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 12, 31)));

        //January first two thousand and twenty-three
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 1, 1)));
````

### Usando dia no formato cardinal

````java
        var dateInEnglish = NumberInWordsFactory.createDateBuilderChooser()
            .forEnglishLanguage()
            .withCardinalForDay()
            .build();

        //thirty-one December two thousand and twenty-three
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 12, 31)));
        
        //one January two thousand and twenty-three
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 1, 1)));
````

## Exemplo Número Fracionário em Português

````java
        var fractional = NumberInWordsFactory.createFractionalBuilderChooser()
            .forPortugueseLanguage()
            .build();

        //um meio (1/2)
        System.out.println(fractional.inWords(Fractional.of(2)));

        //dois terços (2/3)
        System.out.println(fractional.inWords(Fractional.of(2, 3)));

        //um cento e um avos (1/101)
        System.out.println(fractional.inWords(Fractional.of(101)));

        //um centésimo (1/100)
        System.out.println(fractional.inWords(Fractional.of(100)));

        //vinte e um e dois terços (21 + 2/3)
        System.out.println(fractional.inWords(Fractional.of(21, 2, 3)));
````

### Usando gênero femenino

````java
        var fractional = NumberInWordsFactory.createFractionalBuilderChooser()
            .forPortugueseLanguage()
            .withFemaleGender()
            .build();

        //uma metade (1/2)
        System.out.println(fractional.inWords(Fractional.of(2)));

        //duas terças partes (2/3)
        System.out.println(fractional.inWords(Fractional.of(2, 3)));

        //uma cento e uma parte (1/101)
        System.out.println(fractional.inWords(Fractional.of(101)));

        //uma centésima parte (1/100)
        System.out.println(fractional.inWords(Fractional.of(100)));

        //vinte e uma e duas terças partes (21 + 2/3)
        System.out.println(fractional.inWords(Fractional.of(21, 2, 3)));
````

### Usando pronúncia 'sobre' (1/2 = 'um sobre dois')

````java
        var fractional = NumberInWordsFactory.createFractionalBuilderChooser()
            .forPortugueseLanguage()
            .withOverPronuntiation()
            .build();

        //um sobre dois (1/2)
        System.out.println(fractional.inWords(Fractional.of(2)));

        //vinte e nove e dois sobre três (29 + 2/3)
        System.out.println(fractional.inWords(Fractional.of(29, 2, 3)));

        //um sobre cinquenta (1/50)
        System.out.println(fractional.inWords(Fractional.of(50)));

        //trinta e três sobre cem (33/100)
        System.out.println(fractional.inWords(Fractional.of(33,100)));
````

### Usando pronúncia Decimal
````java
        var fractional = NumberInWordsFactory.createFractionalBuilderChooser()
            .forPortugueseLanguage()
            .withDecimalResult() //default 2 casas decimas. 
            //.withDecimalResult(3) //para diferentes casas decimais passar parametro
            .build();

        //cinco décimos (1/2 = 0.5)
        System.out.println(fractional.inWords(Fractional.of(2)));

        //dezenove inteiros e trinta e três centésimos (29 * 2/3 --> 19.33)
        System.out.println(fractional.inWords(Fractional.of(29,2, 3)));

        //dois centésimos (1/50 --> 0.02)
        System.out.println(fractional.inWords(Fractional.of(50)));

        //um centésimo (1/100 --> 0.01)
        System.out.println(fractional.inWords(Fractional.of(100)));
````

## Exemplo hora em português

````java
        var timeInPortuguese = NumberInWordsFactory.createTimeBuilderChooser()
            .forPortugueseLanguage()
            .build();

        //dez horas e quarenta minutos
        System.out.println(timeInPortuguese.inWords(LocalTime.of(10, 40)));
        //zero hora e um minuto
        System.out.println(timeInPortuguese.inWords(LocalTime.of(0, 1)));
        //doze horas e quinze minutos
        System.out.println(timeInPortuguese.inWords(LocalTime.of(12, 15)));
        //onze horas e cinquenta minutos
        System.out.println(timeInPortuguese.inWords(LocalTime.of(11, 50)));
        //vinte e três horas e cinquenta e oito minutos
        System.out.println(timeInPortuguese.inWords(LocalTime.of(23, 58)));
````

### Incluíndo opções para pronúncia dos 30 minutos, meia-noite, meio-dia e minutos para horas

````java
        var timeInPortuguese = NumberInWordsFactory.createTimeBuilderChooser()
                .forPortugueseLanguage()
                .witHalfTo30Minutes()
                .withMiddayAndMidnightPronuntiation()
                .withMinuteToHourPronuntiation()
                .build();

        //vinte minutos para às onze horas
        System.out.println(timeInPortuguese.inWords(LocalTime.of(10, 40)));
        //meia-noite e meia
        System.out.println(timeInPortuguese.inWords(LocalTime.of(0, 30)));
        //meio-dia e meia
        System.out.println(timeInPortuguese.inWords(LocalTime.of(12, 30)));
        //dez minutos para o meio-dia
        System.out.println(timeInPortuguese.inWords(LocalTime.of(11, 50)));
        //dois minutos para meia-noite
        System.out.println(timeInPortuguese.inWords(LocalTime.of(23, 58)));
````

### incluindo formato de 12 horas e pronúncia informal (sem horas e minutos)

````java
        var timeInPortuguese = NumberInWordsFactory.createTimeBuilderChooser()
                .forPortugueseLanguage()
                .witHalfTo30Minutes()
                .withMiddayAndMidnightPronuntiation()
                .withMinuteToHourPronuntiation()
                .with12HoursFormat()
                .withInformalPronuntiation()
                .build();

        //vinte para às onze
        System.out.println(timeInPortuguese.inWords(LocalTime.of(10, 40)));
        //oito e meia
        System.out.println(timeInPortuguese.inWords(LocalTime.of(20, 30)));
        //dez e meia
        System.out.println(timeInPortuguese.inWords(LocalTime.of(22, 30)));
        //dez para o meio-dia
        System.out.println(timeInPortuguese.inWords(LocalTime.of(11, 50)));
        //dois para às dez
        System.out.println(timeInPortuguese.inWords(LocalTime.of(21, 58)));
````

### Incluíndo o período do dia na pronúncia

````java
        var timeInPortuguese = NumberInWordsFactory.createTimeBuilderChooser()
                .forPortugueseLanguage()
                .withHalfFor30Minutes()
                .withMiddayAndMidnightPronuntiation()
                .withMinuteToHourPronuntiation()
                .with12HoursFormat()
                .withInformalPronuntiation()
                .withPeriodPronuntiation()
                .build();

        //vinte para meia-noite
        System.out.println(timeInPortuguese.inWords(LocalTime.of(23, 40)));
        //nove e meia da manhã
        System.out.println(timeInPortuguese.inWords(LocalTime.of(9, 30)));
        //dez e meia da noite
        System.out.println(timeInPortuguese.inWords(LocalTime.of(22, 30)));
        //dez para o meio-dia
        System.out.println(timeInPortuguese.inWords(LocalTime.of(11, 50)));
        //dois para às dez da noite
        System.out.println(timeInPortuguese.inWords(LocalTime.of(21, 58)));
````