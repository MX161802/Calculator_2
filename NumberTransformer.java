import java.util.List;

class NumberTransformer {

    //метод для преобразования из римского числа в арабское
    public int romanToArabic(String input) {

        //преобразуем число к верхнему регистру
        String romanNumeral = input.toUpperCase();
        int result = 0;

        //получаем список римских цифр в порядке убывания
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        //проходимся по списку римских цифр и сверяем строку на наличие элементов enum
        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {

            //получаем элемент enum по индексу
            RomanNumeral symbol = romanNumerals.get(i);

            //если строка начинается с i-ого элемента enum, то

            if (romanNumeral.startsWith(symbol.name())) {

                //прибавляем к итоговому числу значение элемента enum
                result += symbol.getValue();

                //удаляем из строки диапазон, совпадающий с элементом enum
                romanNumeral = romanNumeral.substring(symbol.name().length());
            }

            //иначе увеличиваем индекс
            else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " нельзя конвертировать в римское число");
        }

        return result;
    }

    //метод для преобразования из арабского числа в римское
    public String arabicToRoman(int number) {

        //если число не попадает в диапазон от 1 до 100, то кидаем исключение
        if ((number < 1) || (number > 100)) {
            throw new IllegalArgumentException(number + " вне диапазона (0,100)");
        }

        //получаем список римских цифр в порядке убывания
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        //объявлеям итоговую строку-результат
        StringBuilder sb = new StringBuilder();

        //проходимся по списку римских цифр и сверяем строку на наличие элементов enum
        while ((number > 0) && (i < romanNumerals.size())) {

            //получаем элемент enum по индексу
            RomanNumeral currentSymbol = romanNumerals.get(i);

            //если значение элемента enum меньше либо равно числа из параметра (с учетом последующих операций)
            if (currentSymbol.getValue() <= number) {

                //добавляем к итоговой строке элемент enum
                sb.append(currentSymbol.name());

                //уменьшаем число из параметра метода на значение элемента enum
                number -= currentSymbol.getValue();
            } else {

                //иначе увеличиваем индекс
                i++;
            }
        }
        return sb.toString();
    }
}
