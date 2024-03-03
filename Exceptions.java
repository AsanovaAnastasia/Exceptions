import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Exceptions {
    public static void main(String[] args) {
        String yesNo;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите следующие данные. Формат ввода указан в скобках: Фамилия(лат), Имя(лат), Отчество(лат), Дата рождения - (dd.mm.yyyy)," + " Номер телефона - (цифры 1234567890), Пол(m/f)");
                System.out.print("Введите данные через пробел!: ");

                String input = scanner.nextLine();

                String[] data = input.split(" ");

                if (data.length != 6) {
                    System.out.println("Неверное количество данных");
                    break;
                }

                String lastName = data[0];
                String name = data[1];
                String patronymic = data[2];
                String birthDay = data[3];
                String phoneNumber = data[4];
                String gender = data[5];
                String fullName = data[0] + data[1] + data[2];


                if (!fullName.matches("[A-Za-z]+")) {
                    System.out.println("Фамилия, Имя и Отчество должны содержать только латинские буквы!");
                    break;
                }

                try {
                    LocalDate date = LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                } catch (DateTimeParseException e) {
                    System.out.println("Формат ввода даты рождения неверен");
                    break;
                }

                try {
                    Long.parseLong(phoneNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Номер телефона должен содержать только цифры");
                    break;
                }

                if (!gender.equals("f") && !gender.equals("m") && !gender.equals("ж") && !gender.equals("м")){
                    System.out.println("Пол должен быть указан символом 'm' - 'f'");
                    break;
                }

                String member = lastName + " " + name + " " + patronymic + " " + birthDay + " " + phoneNumber + " " + gender;

                String fileName = lastName + ".txt";
                try (FileWriter writer = new FileWriter(fileName, true)) {
                    writer.write(member + System.lineSeparator());
                    System.out.println("Данные записаны в файл " + fileName);
                } catch (IOException e) {
                    System.out.println("Ошибка при записи данных в файл");
                    e.printStackTrace();
                }
                break;
            }
            System.out.print("\nЖелаете продолжить (y/n)?: ");
            yesNo = scanner.next();
            if (yesNo.equals("n")) {
                break;
            }
        }
    }
}