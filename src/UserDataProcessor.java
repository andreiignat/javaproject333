import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataProcessor {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите пользовательские данные через пробел (Фамилия Имя дата рождения номер телефона пол ):");
            String userInput = scanner.nextLine();

            String[] userData = userInput.split("\\s+");

            if (userData.length != 6) {
                System.out.println("Ошибка: неправильное количество элементов данных. Пожалуйста, укажите правильные данные.");
                return;
            }

            String lastName = userData[0];
            String firstName = userData[1];
            String middleName = userData[2];
            String dateOfBirth = userData[3];
            String phoneNumber = userData[4];
            String gender = userData[5];

            if (!isValidDateOfBirth(dateOfBirth)) {
                System.out.println("Ошибка: неверный формат даты рождения. Пожалуйста, используйте дд.мм.гггг.");
                return;
            }

            if (!isValidPhoneNumber(phoneNumber)) {
                System.out.println("Ошибка: Неверный формат номера телефона. Пожалуйста, укажите номер на примере 9053334833.");
                return;
            }

            if (!isValidGender(gender)) {
                System.out.println("Ошибка: неверный пол. Пожалуйста, используйте «f» или «m».");
                return;
            }

            String fileName = lastName + ".txt";
            String userDataLine = lastName + firstName + middleName + dateOfBirth + " " + phoneNumber + gender;

            try (FileWriter fileWriter = new FileWriter(fileName, true)) {
                fileWriter.write(userDataLine + "\n");
                System.out.println("Данные успешно записаны в файл: " + fileName);
            } catch (IOException e) {
                System.out.println("Ошибка: Невозможно записать данные в файл. Пожалуйста, проверьте права доступа к файлу.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: Произошла непредвиденная ошибка. Пожалуйста, попробуйте еще раз.");
        }
    }

    private static boolean isValidDateOfBirth(String dateOfBirth) {

        return dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {

        return phoneNumber.matches("\\d+");
    }

    private static boolean isValidGender(String gender) {

        return gender.matches("[fm]");
    }

}
