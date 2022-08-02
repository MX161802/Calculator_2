import java.util.Scanner;

public class Main {

    //статичная булевая переменная для проверки, является ли любое из двух чисел римским
    public static boolean isRomeNumbers = false;
    //статичная булевая переменная для проверки, является ли любое из двух чисел арабским
    public static boolean isArabicNumbers = false;
    //повтор цикла
    static boolean have_task = true;

    //метод для математического расчета из строки
    public static String calc(String input) throws Exception {

        //создаем новый экземпляр класса NumberTransformer
        NumberTransformer transformer = new NumberTransformer();

        //делим строку из параметра по одному из 4ех разделителей, ограничиваем длину массива
        String[] items = input.split("[+-/*]", 2);

        //проверяем на правильность внесенного примера от пользователя
        if ((items[0].isEmpty()) || (items[1].isEmpty())) {
            throw new Exception("Строка не является математической операцией");
        }

        //создаем массив, куда будем заносить отформатированные числа из примера
        int[] twoNumbers = new int[2];
        int number;

        for (int i = 0; i < 2; i++) {
            //проверяем, является ли элемент массива арабским числом, если да - присваиваем переменной,
            //если нет - думаем что там римское число и преобразуем его в арабское
            //если не получается, значит вводили не число и кидается исключение
            if (isNumeric(items[i])) {
                number = Integer.parseInt(items[i]);
                isArabicNumbers = true;
            } else {
                number = transformer.romanToArabic(items[i]);
                isRomeNumbers = true;
            }
            //проверяем, попадает ли число в диапазон от 1 до 10, если нет - кидаем исключение
            if (number >= 1 && number <= 10) {
                twoNumbers[i] = number;

            } else throw new RuntimeException("Числа должны быть от 1 до 10");
        }

        //проверяем, если у нас одновременно введены и арабское, и римское число, то кидаем исключение
        if (isArabicNumbers && isRomeNumbers) {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        //считаем результат операции в зависимости от знака
        int result;
        if (input.contains("+")) {
            result = twoNumbers[0] + twoNumbers[1];
        } else if (input.contains("-")) {
            result = twoNumbers[0] - twoNumbers[1];
        } else if (input.contains("/")) {
            result = twoNumbers[0] / twoNumbers[1];
        } else if (input.contains("*")) {
            result = twoNumbers[0] * twoNumbers[1];
        } else {
            throw new Exception("Формат математической операции не удовлетворяет заданию");
        }

        //преобразуем результат в строку в зависимости от того, вводили числа арабские либо римские
        String strResult;
        if (isArabicNumbers) {
            strResult = String.valueOf(result);
        } else {
            if (result > 0) {
                strResult = transformer.arabicToRoman(result);
            } else throw new Exception("в римской системе нет отрицательных чисел и нуля");
        }
        return strResult;
    }

    //Метод для проверки, является ли строка числом
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Введи пример без пробелов, который необходимо решить, например a+b, a-b, a*b, a/b, " +
                "где a и b - целые цисла от 1 до 10, писать можно как римские, так и арабские:");
        while (have_task) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ввод: ");
        String str = scanner.nextLine();
        System.out.println("Ответ: " + calc(str));
        have_task = true;
    }
    }
}
