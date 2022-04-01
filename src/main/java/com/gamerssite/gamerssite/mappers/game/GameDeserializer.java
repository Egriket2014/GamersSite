package com.gamerssite.gamerssite.mappers.game;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.gamerssite.gamerssite.entity.Game;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class GameDeserializer extends JsonDeserializer<Game> {

    @Override
    public Game deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode data = node.findPath("data");

        String title = data.get("name").asText();
        Long steamId = data.get("steam_appid").asLong();
        String image = data.get("header_image").asText();
        Integer price = data.findPath("price_overview").get("initial").asInt() / 100;

        String minReqHtml = data.findPath("pc_requirements").get("minimum").asText();

        String graphicCard = getComponentFromHtmlByName(minReqHtml, "Graphics");
        String processor = getComponentFromHtmlByName(minReqHtml, "Processor");
        String ram = getComponentFromHtmlByName(minReqHtml, "Memory");
        String diskMem = getComponentFromHtmlByName(minReqHtml, "Storage");

        return Game.builder()
                .title(title)
                .steamId(steamId)
                .rating(0.0)
                .link(null)
                .image(image)
                .price(price)
                .minGraphicCard(graphicCard)
                .minProcessor(processor)
                .minRam(ram)
                .diskMemory(diskMem)
                .build();
    }

    private String getComponentFromHtmlByName(String html, String componentName) {
        Document page = Jsoup.parse(html);
        return page.getElementsByTag("li").stream()
                .filter(element -> element.getElementsByTag("strong")
                        .text().equals(componentName + ":"))
                .findFirst()
                .map(element -> element.text().replaceAll(componentName + ":", ""))
                .orElse(null);
    }
}
