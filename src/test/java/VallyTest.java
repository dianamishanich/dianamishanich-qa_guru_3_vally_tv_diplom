import com.codeborne.selenide.Configuration;
import helpers.UserVariables;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class VallyTest {
    UserVariables config = ConfigFactory.newInstance().create(UserVariables.class);

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;

    }

//    @BeforeEach
//    public void logIn() {
//        $(".sign-in").click();
//        $("input[name='email']").val(userLogin);
//        $("input[name='password']").val(userPassword);
//        $(byText("Sign In")).click();
//        $(".site-header").shouldHave(".user-profile-header");
//
//    }

    @BeforeEach
    public void init() {
        open("https://vally.tv/");
    }

    @Test
    @DisplayName("Главная страница и меню")
    public void mainPageTest() {
        $(".main-page.site-container.categories-section").shouldHave(text("News and Events"));
        $$("[href='/category/2f047af9-45c5-445e-8665-9419150e0ab5']").find(visible).click();
        $(".site-page-header").shouldHave(text("Autos and Vehicles"));
        $(".site-header").$(byText("TV show")).hover();
        $(byText("Cirque du Soleil")).click();
        $(".site-page-header").shouldHave(text("Cirque du Soleil"));

    }

    @Test
    @DisplayName("Просмотр видеоконтента")
    public void watchingVideoTest() {
        //open("https://dev.vally.tv/video/bfe0a6f0-09ad-49af-9bea-5784cb561df5");
        $("[href='/video/6344e02d-f632-45a1-99d5-8133ed7b25aa']").scrollTo().click();
        $("#vally_player_iframe").click();
        WebElement frame = $("#vally_player_iframe");
        switchTo().frame(frame);

        $(".video-js").click();
        sleep(5000);

    }

    @Test
    @DisplayName("Загрузка видеоконтента для неавторизованного пользователя")
    public void uploadingVideoTest() {
        $(byText("Upload your video")).click();
        $(".sign-in-form").shouldHave(text("Sign in to Vally"));

    }

    @Test
    @DisplayName("Смена языка интерфейса")
    public void languageChangeTest() {
        $(".language-select.button").click();
        $(".language-select_popover").$(byText("Español")).click();
        $(".categories-nav").shouldHave(text("Сontenido de usuario"));
        $(".language-select.button").click();
        $(".language-select_popover").$(byText("English")).click();
        $(".categories-nav").shouldHave(text("User content"));

    }

    @Test
    @DisplayName("Смена цветовой темы на темную")
    public void changeThemeTest() {
        $(".dark_ico").click();
        $(".dark_theme").shouldBe(visible);

    }

    @Test
    @DisplayName("Клик по фильмам в Карусели")
    public void carouselUsingTest() {
        $("div.promo-preview:nth-child(4)").click();
        $("[href='/video/c25b92dc-c6fa-4e33-82a2-104189723697']").click();
        $("#vally_player_iframe").click();
        WebElement frame = $("#vally_player_iframe");
        switchTo().frame(frame);

        $(".video-js").click();

        sleep(5000);

    }

    @Test
    @DisplayName("Работа карусели с превью видеороликов")
    public void carouselPreviewTest() {
        $("[href='/video/4919e073-c997-4e23-9c02-2850c4540537']").shouldNotBe(disappear);
        $$(".slick-arrow.slick-next").get(2).scrollTo().click();
        $("[href='/video/4919e073-c997-4e23-9c02-2850c4540537']").shouldBe(visible);

    }

}

