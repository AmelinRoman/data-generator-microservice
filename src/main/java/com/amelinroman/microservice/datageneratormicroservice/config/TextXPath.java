package com.amelinroman.microservice.datageneratormicroservice.config;

import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;

/**
 * Класс TextXPath предоставляет возможность извлечь текст из указанного узла XML-документа.
 * Этот класс использует {@link XML} и {@link String} для предоставления требуемой функциональности.
 */
@RequiredArgsConstructor
public class TextXPath {

    /**
     * Объект XML, представляющий исходный XML-документ.
     */
    private final XML xml;

    /**
     * Строка, содержащая имя узла, из которого требуется извлечь текст.
     */
    private final String node;

    /**
     * Возвращает текст из первого найденного узла с заданным именем в XML-документе.
     * Этот метод использует XPath выражение "text()" для извлечения текста из узла.
     * @return текст, содержащийся внутри первого найденного узла с заданным именем.
     */
    @Override
    public String toString() {
        return this.xml.nodes(node)
                .get(0)
                .xpath("text()")
                .get(0);
    }
}
