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
import java.util.Optional;

@JsonComponent
public class GameDeserializer extends JsonDeserializer<Game> {

    @Override
    public Game deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode data = node.findPath("data");
        try {
            String title = data.get("name").asText();
            Long steamId = data.get("steam_appid").asLong();
            String image = data.get("header_image").asText();
            Integer price = data.get("is_free").asBoolean() ? 0 : data.findPath("price_overview").get("initial").asInt() / 100;

            String minReqHtml = data.findPath("pc_requirements").get("minimum").asText();

            String graphicCard = getComponentFromHtmlByName(minReqHtml, "Graphics")
                    .orElse(null);
            String processor = getComponentFromHtmlByName(minReqHtml, "Processor")
                    .orElse(null);
            String ram = getComponentFromHtmlByName(minReqHtml, "Memory")
                    .orElse(null);
            String diskMem = getComponentFromHtmlByName(minReqHtml, "Storage")
                    .orElse(getComponentFromHtmlByName(minReqHtml, "Hard Drive")
                            .orElse(null));

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

        } catch (NullPointerException e) {
            throw new IOException();
        }
    }

    private Optional<String> getComponentFromHtmlByName(String html, String componentName) {
        Document page = Jsoup.parse(html);
        return page.getElementsByTag("li").stream()
                .filter(element -> element.getElementsByTag("strong")
                        .text().equals(componentName + ":"))
                .findFirst()
                .map(element -> element.text().replaceAll(componentName + ":", ""));
    }
}
