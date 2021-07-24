import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestCardDelivery {

    @Test
    void shouldBePassValidation() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Пенза");
        $("[data-test-id=date] input").setValue("27.07.2021");
        $("[data-test-id=name] input").setValue("Иванов Иван-Иванович");
        $("[data-test-id=phone] input").setValue("+79049999999");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Успешно!"))
                .shouldBe(visible, Duration.ofSeconds(15));
    }
}

