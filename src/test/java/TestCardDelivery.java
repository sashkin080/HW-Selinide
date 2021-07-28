import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestCardDelivery {

    private String currentDate(int plusDays) {
        LocalDate currentDate = LocalDate.now();
        LocalDate deliveryDate = currentDate.plusDays(plusDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = deliveryDate.format(formatter);
        return date;
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);

    }

    @Test
    void shouldBePassValidation() {
        $("[data-test-id=city] input").setValue("Пенза");
        $("[data-test-id=date] input").setValue(currentDate(3));
        $("[data-test-id=name] input").setValue("Иванов Иван-Иванович");
        $("[data-test-id=phone] input").setValue("+79049999999");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15)).
                shouldHave(exactText("Успешно!" + "\n" + "Встреча успешно забронирована на " + currentDate(3)));

    }
}

