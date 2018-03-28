package ua.nure.manuilova.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Grabber {
    public static void main(String[] args) throws IOException {
        String url = "http://daily-menu.ru/dailymenu/recipes/view/12763";

        Document doc = Jsoup.connect(url).get();

    }
}
